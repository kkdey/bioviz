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

public class human_creator extends PApplet {

Human[] humans = new Human [100];

float headX;
float headY;
float headsize;
float bodylength;
int col;
float[] speed = new float [humans.length];

float[] headXarr = new float [humans.length];
float[] headYarr = new float [humans.length];

public void setup(){
  
  frameRate(10);
  col = color(255, 0, 0);
  headsize = 10;
  bodylength = 30;
  
  for(int i=0; i < humans.length; i++){
    headXarr[i] = random(100, width-100);
    headYarr[i] = random(100, height-100);
    if(headXarr[i]> width-200 && headXarr[i] < width+100 || headYarr[i]> height-200 && headYarr[i] < height+100){
      speed[i] = -2;
    }else{
      speed[i] = 2;   
    }
  }
}

public void draw(){
   background(255);
   textMode(CENTER);
   fill(0);
   textSize(25);
   text("Population in Motion ", 0.35f*width, 10, 500, 40);
   for(int i=0; i < humans.length; i++){
    if(headXarr[i] > width - 100 || headXarr[i] < 100 || headYarr[i] > height - 100 || headYarr[i] < 100){
       speed[i] = -speed[i];
    }
    
    headXarr[i] = headXarr[i] + speed[i]*random(1,2);
    headYarr[i] = headYarr[i] + speed[i]*random(1,2);
    humans[i] = new Human(headXarr[i] , headYarr[i], headsize, bodylength, col);
    humans[i].head();
    humans[i].body();
    humans[i].hand();
    humans[i].leg();
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
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--stop-color=#cccccc", "human_creator" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
