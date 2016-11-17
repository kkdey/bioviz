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

public class ppt_example extends PApplet {


//  Poisson process Processing design 

PP b;
float diameter = 10;
float step = 1;
float rate = 0.1f;
int counter_event = 0;
float startX;
float startY;

public void setup(){
  
  background(0);
  startX = 0;
  startY = 3*height/4;
  float [] poiss_times = event_times(width);
  b = new PP(startX, startY, diameter, rate, poiss_times);
}

public void draw(){
  fill(255,255);
  textSize(30);
  text("Poisson Process Simulator", 0.5f*width, height/20, 500, 40);
  
 // println(poiss_times[0]);
 // println(poiss_times[1]);
 // println(poiss_times[2]);
 // println(poiss_times[3]);
 // println(poiss_times[4]);
 if(counter_event==0){
   background(0);
   float [] poiss_times = event_times(width);
   b = new PP(startX, startY, diameter, rate, poiss_times);
 }
  b.rectbox();
  b.move();
  b.displayball();
  b.counter_event_adjust();
  b.eventcheck();
  b.textbox();
}

public void mousePressed(){
  noLoop();
}

public void mouseClicked(){
  loop();
}
class PP{
  float pstartX;
  float pstartY;
  float pdiameter;
  float prate;
  float[] poiss_times;
  float moveX = pstartX;

  PP (float tempX, float tempY, float temp_diameter, float temprate,
      float[] temp_poiss_times){
      pdiameter = temp_diameter;
      pstartY = tempY;
      pstartX = tempX;
      prate = temprate;
      poiss_times = temp_poiss_times;
  }
  
  public void move(){
      if(moveX < width){
          moveX = moveX + step;
      }
       if(moveX >= width){
          moveX = 0;
          counter_event = 0;
       }
       stroke(255);
       line(pstartX, pstartY, moveX, pstartY);
  }
  
  public void displayball(){
       fill(0,255,0, 200);
       stroke(0);
       strokeWeight(3);
       ellipse(moveX, pstartY, diameter, diameter);   
  }  
  
  public void eventcheck(){
      int flag = 0;
      while(flag == 0){
        float movePX = moveX - step;
        if (movePX <= poiss_times[counter_event] && moveX > poiss_times[counter_event]
              && counter_event < poiss_times.length){
          counter_event = counter_event + 1;
          fill(255,255,0, 200);
          stroke(0);
          strokeWeight(3);
          ellipse(moveX, pstartY, diameter, diameter);
          stroke(255, 255, 0, 200);
       //   println(counter_event);
       //   println(poiss_times.length);
          line(moveX, startY, moveX, startY-100);
           if(counter_event==poiss_times.length){
            flag = 1;
          }
          }else{
           flag = 1;
          }
   }
 }
  
  public void counter_event_adjust(){
    if(counter_event >= poiss_times.length){
      counter_event = 0;
    }
  }
  
  public void textbox(){
    String message1 = "Number of events: " + counter_event;
    String message2 = "Poisson process rate: " + rate;
    float roundOff = Math.round(moveX * 100.0f) / 100.0f;
    String message3 = "Time of motion: " + roundOff + "s";
    fill(255);
    textSize(15);
    text(message1, 0.60f*width, 0.25f*height);
    text(message2, 0.60f*width, 0.30f*height);
    text(message3, 0.60f*width, 0.35f*height);
  }
  public void rectbox(){
    rectMode(CENTER);
    fill(0);
    stroke(0,0);
    strokeWeight(0);
    rect(0.60f*width, 0.25f*height, 1*width, 0.25f*height);
  }
}
public static int getPoisson(double lambda) {
  double L = Math.exp(-lambda);
  double p = 1.0f;
  int k = 0;

  while(p > L){
    k++;
    p *= Math.random();
  }
  return k - 1;
}


public float [] event_times(float width2){
    int num_events  = getPoisson(round(rate*width2));
    println(num_events);
    float [] times = new float [num_events];
    for(int num=0; num < num_events; num++){
      times[num] = random(0, width2);
    }
    times = sort(times);
    return times;
}



  
  public void settings() {  size(800, 600); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--stop-color=#cccccc", "ppt_example" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
