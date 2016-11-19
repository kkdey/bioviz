import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class migration_patterns extends PApplet {

Human[] humans = new Human [100];
int NUM_ISLANDS = 4;
float[][] probMatrix = { {0, 0.3f, 0, 0.1f}, {0,0,0.2f,0}, {0,0.1f,0,0.1f}, {0.1f,0.1f,0,0} };
float[][] numHumans = new float [NUM_ISLANDS][NUM_ISLANDS];
float[][] speed = new float [NUM_ISLANDS][NUM_ISLANDS];
float[][] random_textbox = new float [NUM_ISLANDS][NUM_ISLANDS];

float[] islandX = new float [NUM_ISLANDS];
float[] islandY = new float [NUM_ISLANDS];
int[] colors = new int [NUM_ISLANDS];
float[] perlin_noise = new float [humans.length];
float[] radius_counter = new float [humans.length];
int[] parent_counter = new int [humans.length];
int[] child_counter = new int [humans.length];
float[] speed_counter = new float [humans.length];
int[] color_counter = new int [humans.length];
float[] headXarr = new float [humans.length];
float[] headYarr = new float [humans.length];
float[] angle_rad = new float [humans.length];
float[] angle_counter = new float [humans.length];

float headsize = 5;
float bodylength = 10;
float diameter = 50;
float yscale = 1;

public void setup(){
  
  frameRate(10);
  
  islandX[0] = width/4;
  islandX[1] = width/2;
  islandX[2] = 3*width/4;
  islandX[3] = width/2;
    
  islandY[0] = height/2;
  islandY[1] = 0.8f*height;
  islandY[2] = height/2;
  islandY[3] = 0.2f*height;
  
  colors[0] = color(192, 64, 64);
  colors[1] = color(192, 192, 0);
  colors[2] = color(128, 255, 255);
  colors[3] = color(255, 255, 0);

  println(colors[0]);
  
 println(probMatrix[0][1]);
  
  for(int i=0; i< NUM_ISLANDS; i++){
    for(int j=0; j< NUM_ISLANDS; j++){
      numHumans[i][j] = floor(humans.length*probMatrix[i][j]);
      speed[i][j] = 0.1f*probMatrix[i][j];
      random_textbox[i][j] = random(-15,15);
    }
  }
  println(speed[0][1]);
    
int flag = 0;
  
while(flag < humans.length){
  for(int i=0; i < NUM_ISLANDS; i++){
    for(int j=0; j < NUM_ISLANDS; j++){
      float r = sqrt(pow((islandX[i] - islandX[j]),2) + pow((islandY[i] - islandY[j]),2));
      float angle = (islandY[j] - islandY[i])/(islandX[j] - islandX[i]);
      if(floor(numHumans[i][j])>0){
                    for(int k=0; k < floor(numHumans[i][j]); k++){
                           if(flag < humans.length){
                           parent_counter[flag] = i;
                           child_counter[flag] = j;
                           speed_counter[flag] = speed[i][j];
                           color_counter[flag] = colors[i];
                           radius_counter[flag] = r;
                           angle_counter[flag] = angle;
                           flag = flag+1;
                           }
                       }
               }
           }
      }
  }

 println(flag);
  
  
  
}


public void draw(){
  textMode(CENTER);
  background(0);
  fill(255);
  textSize(25);
  text("Migration Patterns", 0.35f*width, 10, 500, 40);
   
  for(int i=0; i < islandX.length; i++){
    fill(colors[i]);
    ellipse(islandX[i], islandY[i], diameter, diameter);
  }
  
   for(int i=0; i < humans.length; i++){
     if(angle_rad[i] < 0.5f){
          angle_rad[i] = angle_rad[i] + speed_counter[i];
     }else{
          angle_rad[i] = 0;
     }
     headXarr[i] = islandX[parent_counter[i]] + (islandX[child_counter[i]] - islandX[parent_counter[i]])*sin(angle_rad[i] * PI) 
                  + random(-speed_counter[i]*1000, speed_counter[i]*1000)
                  + noise(10*angle_rad[i]);
     headYarr[i] = islandY[parent_counter[i]] + (islandY[child_counter[i]] - islandY[parent_counter[i]])*sin(angle_rad[i] * PI) 
                  + random(-speed_counter[i]*1000, speed_counter[i]*1000) 
                  + noise(10*angle_rad[i]);
     humans[i] = new Human(headXarr[i] ,headYarr[i], headsize, bodylength, color_counter[i]);
     humans[i].head();
     humans[i].body();
     humans[i].hand();
     humans[i].leg();
     }
     
     textbox();
}

public void mousePressed(){
  noLoop();
}

public void mouseClicked(){
  loop();
}
  
  public void textbox(){
    for(int i=0; i < NUM_ISLANDS; i++){
      for(int j=0; j < NUM_ISLANDS; j++){
        if(probMatrix[i][j] != 0){
          String message = "Migration prob: " + probMatrix[i][j];
          textSize(10);
          fill(colors[i]);
          text(message,  (islandX[i] + islandX[j])/2 + random_textbox[i][j], (islandY[i] + islandY[j])/2 + random_textbox[i][j]);         
      }
    }
  }
 }
class Human{
  float pheadX;
  float pheadY;
  float pheadsize;
  float pbodylength;
 // float phandlength;
 // float pleglength;
  int pcolor;
  
  Human(float temp_headX, float temp_headY, float temp_headsize,
        float temp_bodylength, int temp_color){
           
           pheadX = temp_headX;
           pheadY = temp_headY;
           pheadsize = temp_headsize;
           pbodylength = temp_bodylength;
  //         phandlength = temp_bodylength;
  //         pleglength = temp_bodylength; 
           pcolor = temp_color;
         }
         
   public void head(){
       fill(pcolor);
       stroke(1);
       ellipseMode(CENTER);
       ellipse(pheadX, pheadY, pheadsize, pheadsize);
   }
   
   public void body(){
       stroke(pcolor);
       strokeWeight(1);
       line(pheadX, pheadY+pheadsize, pheadX, pheadY+pheadsize+pbodylength);
   }
   
   public void hand(){
       stroke(pcolor);
       strokeWeight(1);
       line(pheadX, pheadY+pheadsize+0.2f*pbodylength, pheadX - 0.3f*pbodylength, 
            pheadY+pheadsize+0.3f*pbodylength);
       line(pheadX, pheadY+pheadsize+0.2f*pbodylength, pheadX + 0.3f*pbodylength, 
            pheadY+pheadsize+0.3f*pbodylength);   
   }
   
   public void leg(){
       stroke(pcolor);
       line(pheadX, pheadY+pheadsize+pbodylength, pheadX - 0.5f*pbodylength, 
            pheadY+pheadsize+1.5f*pbodylength);
       line(pheadX, pheadY+pheadsize+pbodylength, pheadX + 0.5f*pbodylength, 
            pheadY+pheadsize+1.5f*pbodylength);  
   }
}     
  public void settings() {  size(800, 600); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--stop-color=#cccccc", "migration_patterns" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
