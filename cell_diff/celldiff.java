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

public class celldiff extends PApplet {

Cell [] cells = new Cell [200];
int Branches = 5;
float[] split_Y = new float[(Branches-1)];
int[] color_branches = new int[(Branches+10)];
int[] parents = {0, 1, 1, 1};
String[] dirs = {"NA", "left", "right", "right"};
float SamplingRate = 100;
float Xspeed;
float Yspeed;

float moveX;
float moveY;
float diameter;


public void setup(){
  
  
  for(int num=0; num < (Branches-1); num++){
  split_Y[num] = random(0, height);
  }
  split_Y = reverse(sort(split_Y));
 
  
  for(int num=0; num < (Branches+10); num++){
     int R = round(random(0,255));
     int G = round(random(0,255));
     int B = round(random(0,255));
     color_branches[num] = color(R, G, B);
  }
  
  moveX = width/2;
  moveY = height;
  diameter = 20;
  Xspeed = 1;
  Yspeed = 1;
  
   for(int i =0; i < cells.length; i++){
    cells[i] = new Cell(moveX, moveY, diameter, color_branches, split_Y, Xspeed, Yspeed);
 //   group[i] =1;
  }
  
}


public void draw(){
   background(0);
   textMode(CENTER);
   fill(255,255);
   textSize(20);
   text("Cell Differentiation ", 0.1f*width, 0.85f*height, 300, 40);
   text("Process ", 0.1f*width, 0.90f*height, 500, 40);

  for(int i=0; i < cells.length; i++){
    cells[i].topcheck();
    cells[i].ascend();
    cells[i].branching();
    cells[i].ascend_steep();
    cells[i].timercheck();
    cells[i].display();
  }
  
}

public void mousePressed() {
  noLoop();
}

public void mouseClicked() {
  loop();
}

class Cell{
  float pmoveX;
  float pmoveY;
  int[] pcolor_branches;
  float[] psplit_Y;
  float pYspeed;
  float pXspeed;
  float pdiameter = 10;
  boolean stoptimer = false;
  int counter = 0;
  int flag = 0;
  boolean splitted = false;
  int parent = 0;
  int branch_counter =0;
  int count = 0;
  String dir;
  
  Cell(float temp_moveX, float temp_moveY, float temp_diameter, 
        int[] tempcolor_branches, float[] temp_split_Y, float temp_pXspeed, 
        float temp_pYspeed){
              pmoveX = temp_moveX;
              pmoveY = temp_moveY;
              pcolor_branches = tempcolor_branches;
              psplit_Y = temp_split_Y;
              pXspeed = temp_pXspeed;
              pYspeed = temp_pYspeed;
              pdiameter = temp_diameter;
  }
  
  public void display(){
    if(!stoptimer){
     fill(0, height - 0.8f*pmoveY);
     ellipse(pmoveX, pmoveY, pdiameter + random(-1,1), pdiameter +random(-1,1));
    }else{
      fill(pcolor_branches[branch_counter], height - 0.8f*pmoveY);
      ellipse(pmoveX, pmoveY, pdiameter + random(-1,1), pdiameter +random(-1,1));
    }
  }
  
  public void ascend(){
    if(!stoptimer && flag == 0){
    float yscale = 2;
    float xscale = random(-1,1);
       pmoveY = pmoveY - pYspeed*yscale;
       pmoveX = pmoveX + pXspeed*xscale;
    }
  }
  
  public void branching(){
      if(pmoveY < psplit_Y[count] && !splitted && count < 3){
        if(parents[count]==0){
                float u = random(0,1);
                if(u < 0.5f){
                     count = count +1;
                     branch_counter = branch_counter + 1;
                  //   pmoveX = pmoveX - 2;
                  //   pmoveY = pmoveY - 2;
                     flag = flag - 1;
                     splitted = true;
                     dir = "left";
                     parent = parents[count];
                 }else{
                     count = count +1;
                     branch_counter = branch_counter + 1;
                  //   pmoveX = pmoveX + 2;
                  //   pmoveY = pmoveY - 2;
                     flag = flag + 1;
                     splitted = true;
                     dir = "right";
                     parent = parents[count];
                 }
            }
          else if(parents[count] !=0 && dir == dirs[count]){
               float u = random(0,1);
               if(u < 0.5f){
                     count = count +1;
                     print(count);
                 //    pmoveX = pmoveX - 2;
                 //    pmoveY = pmoveY - 2;
                     branch_counter = branch_counter + 1;

                     flag = flag - 1;
                     splitted = true;
                     dir = "left";
                     parent = parents[count];
                 }else{
                     count = count +1;
                     println(count);
                  //   pmoveX = pmoveX + 2;
                  //   pmoveY = pmoveY - 2;
                     branch_counter = branch_counter + 1;
                     flag = flag + 1;
                     splitted = true;
                     dir = "right";
                     parent = parents[count];

                 }
             }
             else if (parents[count] !=0 && dir != dirs[count]){
                   count = count + 1;
                   println(parents);
                   parent = parents[count];
                   splitted = false;
             }
          }
       }

  
  public void ascend_steep(){
      if(!stoptimer){
        float yscale2 = 2;
        float xscale2 = 2;
      if(flag > 0){
        pmoveX = pmoveX + pYspeed*yscale2;
        pmoveY = pmoveY - pXspeed*xscale2;
        flag = flag + 1;
      }else if (flag < 0){
        pmoveX = pmoveX - pXspeed*xscale2;
        pmoveY = pmoveY - pYspeed*yscale2;
        flag = flag - 1;
      }
      if(flag == round(200/(count+1)) || flag == -round(200/(count+1))){
        flag = 0;
        splitted = false;
      }
     }
   }
   
   public void topcheck(){
    if(pmoveY <= (pdiameter/2 + random(-5,5))){
      pYspeed = 0;
    }
    if(pYspeed==0){
      pXspeed = 0;
    }  
  }
  
  public void timercheck(){
    if(pYspeed==0 && moveY > pdiameter){
      stoptimer = true;
    }
    else if(pYspeed==0 && moveY < pdiameter){
      stoptimer = false;
    }else if (random(0, SamplingRate) < 1){
      stoptimer = false;
      pYspeed = 0;
      pXspeed = 0;
    }}
    
}
  public void settings() {  size(800, 600); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--stop-color=#cccccc", "celldiff" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
