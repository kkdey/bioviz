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

public class cell_cycle extends PApplet {


// Cell cycle simulation example 

Cell b;
float diameter = 20;
float scale = 2;
float angle = 0;
int counter = 0;

public void setup(){
  
  float startX = 0.75f*width;
  float startY = height/2;
  b = new Cell(startX, startY, diameter, scale, angle);
}  

public void draw(){
  background(0);
  textMode(CENTER);
  fill(255,255);
  textSize(30);
  text("Cell Cycle Process", 0.7f*width/2, height/20, 500, 40);
  b.ring();
  b.scale_inc();
  b.cellmotion();
  b.nucleusmotion();
  b.chromosomemotion();
  b.g0phase();
  b.textbox();
}



 
 class Cell{
  float pdiameter;
  float pscale;
  float pstartX;
  float pstartY;
  float pangle;
  float stopper = 1;
 
 Cell(float temp_startX, float temp_startY, float temp_diameter, float temp_scale,
        float temp_angle){
        pdiameter = temp_diameter;
        pscale = temp_scale;
        pstartX = temp_startX;
        pstartY = temp_startY;
        pangle = temp_angle;
  }
  
  public void scale_inc(){
    if(pangle < 2*PI){
       pangle = pangle+ stopper * 0.01f;
       pscale = pscale + stopper * 0.002f;
    }
    else{
        pangle = 0;
        pscale = scale;
        counter = 0;
    }
  }
  
  
   
  
  public void cellmotion(){
    
    float radius = pstartX - width/2;
    float circX =  width/2 + radius/2 * cos(pangle);
    float circY =  height/2 + radius/2 * sin(pangle);
  
   
    ellipseMode(CENTER);
    fill(130,200,200,100);
    stroke(1);
    strokeWeight(1);
    ellipseMode(CENTER);
    if (pangle < 3.5f){
      ellipse(circX, circY, pscale* pdiameter, pscale * pdiameter);
    }else if (pangle > 3.5f && pangle < 4){
      ellipse(circX, circY, 0.9f*pscale* pdiameter, 1.1f*pscale * pdiameter);
    }else if (pangle > 4 && pangle < 4.5f){
      ellipse(circX, circY, 0.8f*pscale* pdiameter, 1.2f*pscale * pdiameter);
    }else if (pangle > 4.5f && pangle < 5){
      ellipse(circX, circY, 0.7f*pscale* pdiameter, 1.3f*pscale * pdiameter);
    }else if (pangle > 5 && pangle < 5.3f){
      // stroke(255,0);
   //   ellipse(circX, circY + 0.5*pscale* pdiameter, 0.5*pscale* pdiameter, 1.2*pscale * pdiameter);
   //   ellipse(circX, circY - 0.5*pscale* pdiameter, 0.5*pscale* pdiameter, 1.2*pscale * pdiameter);
      ellipse(circX, circY, 0.7f*pscale* pdiameter, 1.3f*pscale * pdiameter);
    }else if (pangle > 5.3f && pangle < 5.6f){
      // stroke(255,0);
   //   ellipse(circX, circY + 0.5*pscale* pdiameter, 0.5*pscale* pdiameter, 1.2*pscale * pdiameter);
   //   ellipse(circX, circY - 0.5*pscale* pdiameter, 0.5*pscale* pdiameter, 1.2*pscale * pdiameter);
      ellipse(circX, circY, 0.6f*pscale* pdiameter, 1.5f*pscale * pdiameter);
    }else if (pangle > 5.6f && pangle < 5.9f){
      ellipse(circX, circY + 0.5f*pscale* pdiameter, 0.5f*pscale* pdiameter, 1.3f*pscale * pdiameter);
      ellipse(circX, circY - 0.5f*pscale* pdiameter, 0.5f*pscale* pdiameter, 1.3f*pscale * pdiameter);
      fill(0,255);
      stroke(255,0);
      ellipse(circX, circY , 0.30f*pscale* pdiameter, 0.5f*pscale * pdiameter);
      fill(130,200,200,100);
      stroke(130,200,200,0);
      ellipse(circX, circY , 0.30f*pscale* pdiameter, 0.5f*pscale * pdiameter);
    }else if (pangle > 5.9f && pangle < 6.25f){
      ellipse(circX, circY + 0.5f*pscale* pdiameter, 0.5f*pscale* pdiameter, 0.8f*pscale * pdiameter);
      ellipse(circX, circY - 0.5f*pscale* pdiameter, 0.5f*pscale* pdiameter, 0.8f*pscale * pdiameter);
    }else if (pangle > 6.25f && pangle < 6.27f){
      ellipse(circX, circY + 1*pscale* pdiameter, 2*(1/pscale)*pscale* pdiameter, 2*(1/pscale)*pscale* pdiameter);
      ellipse(circX, circY , 2*(1/pscale)*pscale* pdiameter, 2*(1/pscale)*pscale* pdiameter);
    }else if (pangle > 6.27f && pangle < 2*PI){
      ellipse(circX, circY , 2*(1/pscale)*pscale* pdiameter, 2*(1/pscale)*pscale* pdiameter);
    }
    
  }
  
  public void nucleusmotion(){
    
    float radius = pstartX - width/2;
    float circX =  width/2 + radius/2 * cos(pangle);
    float circY =  height/2 + radius/2 * sin(pangle);
  
    fill(255,0,0);
    stroke(1);
    strokeWeight(1);
    if (pangle < 3.5f){
      ellipse(circX, circY, pscale* pdiameter/2, pscale * pdiameter/2);
      ellipse(circX, circY, pscale* pdiameter/2, pscale * pdiameter/2);
    }
    else if (pangle > 3.5f && pangle < 4){
      fill(255,0,0);
      stroke(1);
      strokeWeight(1);
      ellipse(circX, circY, pscale* pdiameter/2, 0.53f*pscale * pdiameter);
     // ellipse(circX - 1*(pangle - 4), circY - 1*(pangle-4), pscale* pdiameter/3, pscale * pdiameter/2);
     //  ellipse(circX + 1*(pangle + 4), circY + 1*(pangle+4), pscale* pdiameter/3, pscale * pdiameter/2);
    }
    else if (pangle > 4 && pangle < 4.5f){
      fill(255,0,0);
      stroke(1);
      strokeWeight(1);
      ellipse(circX, circY, pscale* pdiameter/2, 0.57f*pscale * pdiameter);
     // ellipse(circX - 1*(pangle - 4), circY - 1*(pangle-4), pscale* pdiameter/3, pscale * pdiameter/2);
     //  ellipse(circX + 1*(pangle + 4), circY + 1*(pangle+4), pscale* pdiameter/3, pscale * pdiameter/2);
    }
    else if (pangle > 4.5f && pangle < 5){
      fill(255,0,0);
      stroke(1);
      strokeWeight(1);
   //   ellipse(circX, circY, pscale* pdiameter/2, 0.64*pscale * pdiameter);
      float temp2 =  pscale* pdiameter/6;
      ellipse(circX, circY - temp2*(pangle-4), pscale* pdiameter/6, pscale * pdiameter/2);
      println(pdiameter);
      println(pscale);
      ellipse(circX, circY + temp2*(pangle - 4), pscale* pdiameter/6, pscale * pdiameter/2);
      fill(255,0,0);
      stroke(255,0,0,0);
      ellipse(circX, circY , pscale* pdiameter/6, pscale * pdiameter/4);

     // ellipse(circX - 1*(pangle - 4), circY - 1*(pangle-4), pscale* pdiameter/3, pscale * pdiameter/2);
     //  ellipse(circX + 1*(pangle + 4), circY + 1*(pangle+4), pscale* pdiameter/3, pscale * pdiameter/2);
    }
     else if (pangle > 5 && pangle < 5.3f){
      fill(255,0,0);
      stroke(1);
      strokeWeight(1);
     // ellipse(circX, circY, pscale* pdiameter/2, 0.7*pscale * pdiameter);
      float temp2 =  pscale* pdiameter/6;
      ellipse(circX, circY - temp2*(pangle-4), pscale* pdiameter/6, pscale * pdiameter/2);
      println(pdiameter);
      println(pscale);
      ellipse(circX, circY + temp2*(pangle - 4), pscale* pdiameter/6, pscale * pdiameter/2);
      fill(255,0,0);
      stroke(255,0,0,0);
      ellipse(circX, circY , pscale* pdiameter/10, pscale * pdiameter/4);

    }
    
    else if (pangle > 5.3f && pangle < 5.6f){
      fill(255,0,0);
      stroke(1);
      strokeWeight(1);
     // ellipse(circX, circY, pscale* pdiameter/2, 0.7*pscale * pdiameter);
      float temp2 =  pscale* pdiameter/6;
      ellipse(circX, circY - 1.5f*temp2*(pangle - 4), pscale* pdiameter/3, pscale * pdiameter/3);
      ellipse(circX, circY + 1.5f*temp2*(pangle - 4), pscale* pdiameter/3, pscale * pdiameter/3);
     // fill(255,0,0);
     // stroke(255,0,0,0);
     // ellipse(circX, circY , pscale* pdiameter/10, pscale * pdiameter/4);
    }
    
    else if (pangle > 5.6f && pangle < 6.25f){
   //   float temp2 =  pscale* pdiameter/6;
      ellipse(circX, circY + 0.5f*pscale* pdiameter, 0.25f*pscale* pdiameter, 0.4f*pscale * pdiameter);
      ellipse(circX, circY - 0.5f*pscale* pdiameter, 0.25f*pscale* pdiameter, 0.4f*pscale * pdiameter);
    }
    
    else if (pangle > 6.25f && pangle < 6.27f){
      ellipse(circX, circY + 1*pscale* pdiameter, (1/pscale)*pscale* pdiameter, (1/pscale)*pscale * pdiameter);
      ellipse(circX, circY , (1/pscale)*pscale* pdiameter, (1/pscale)*pscale * pdiameter);
    }
    else if (pangle > 6.27f && pangle < 2*PI){
     ellipse(circX, circY, scale* pdiameter/2, scale * pdiameter/2);
     ellipse(circX, circY, scale* pdiameter/2, scale * pdiameter/2);
    }
    
  }
  
  
  public void chromosomemotion(){
    
    float radius = pstartX - width/2;
    float circX =  width/2 + radius/2 * cos(pangle);
    float circY =  height/2 + radius/2 * sin(pangle);
  
        if(pangle < 2){
    for(int num =0; num < 10; num ++){
          float temp = pscale* pdiameter/6;
          stroke(0);
          line(circX + random(-temp,temp), circY + random(-temp, temp),
          circX + random(-temp,temp), circY + random(-temp, temp));
      }
    }
    else if(pangle > 2 && pangle < 2.2f){
        for(int num =0; num < 3; num ++){
          float temp = pscale* pdiameter/6;
          float xrandom = random(-temp,temp);
          float yrandom = random(-temp, temp);
          stroke(255,255,0);
          line(circX + xrandom, circY + yrandom,
          circX + xrandom + temp/6, circY + yrandom + temp/6);
      }
      for(int num =0; num < 7; num ++){
          float temp = pscale* pdiameter/6;
          stroke(0);
          line(circX + random(-temp,temp), circY + random(-temp, temp),
          circX + random(-temp,temp), circY + random(-temp, temp));
      }
    }
     else if(pangle > 2.2f && pangle < 2.7f){
        for(int num =0; num < 5; num ++){
          float temp = pscale* pdiameter/6;
          float xrandom = random(-temp,temp);
          float yrandom = random(-temp, temp);
          stroke(255,255,0);
          line(circX + xrandom, circY + yrandom,
          circX + xrandom + temp/6, circY + yrandom + temp/6);
      }
      for(int num =0; num < 5; num ++){
          float temp = pscale* pdiameter/6;
          stroke(0);
          line(circX + random(-temp,temp), circY + random(-temp, temp),
          circX + random(-temp,temp), circY + random(-temp, temp));
      }
    }
    else if(pangle > 2.7f && pangle < 3.0f){
        for(int num =0; num < 20; num ++){
          float temp = pscale* pdiameter/6;
          float xrandom = random(-temp,temp);
          float yrandom = random(-temp, temp);
          stroke(255,255,0);
          line(circX + xrandom, circY + yrandom,
          circX + xrandom + temp/6, circY + yrandom + temp/6);
      }}
      
    
    else if(pangle > 3 && pangle < 3.5f){
      for(int num =0; num < 20; num ++){
          float temp = pscale* pdiameter/6;
          float xrandom = random(-temp,temp);
          float yrandom = random(-temp, temp);
          stroke(255,255,0);
          line(circX + xrandom, circY + yrandom,
          circX + xrandom + temp/6, circY + yrandom + temp/6);
      }}
       
   
      else if(pangle > 3.5f && pangle < 4.5f){
      for(int num =0; num < 20; num ++){
          float temp = pscale* pdiameter/6;
          float xrandom = random(-temp,temp);
          float yrandom = random(-temp, temp);
          stroke(255,255,0);
          line(circX + xrandom, circY + yrandom,
          circX + xrandom + temp/6, circY + yrandom + temp/6);
      }}
      else if(pangle > 4.5f && pangle < 5){
      float temp = pscale* pdiameter/6;
           float circX2 = circX;
           float circY2 = circY - 1*temp*(pangle-4);
           float circX3 = circX;
           float circY3 = circY + 1*temp*(pangle-4);
          
      for(int num =0; num < 20; num ++){
          float xrandom = random(-temp/2,temp/2);
          float yrandom = random(-temp/2, temp/2);
          float xrandom2 = random(-temp/2,temp/2);
          float yrandom2 = random(-temp/2, temp/2);
          stroke(255, 255, 0);
          line(circX2 + xrandom, circY2 + yrandom,
          circX2 + xrandom + temp/20, circY2 + yrandom + temp/20);
          stroke(255, 255, 0);
          line(circX3 + xrandom2, circY3 + yrandom2,
          circX3 + xrandom2 + temp/20, circY3 + yrandom2 + temp/20);
        }}
          
      else if(pangle > 5 && pangle < 5.3f){
           float temp = pscale* pdiameter/6;
           float circX2 = circX;
           float circY2 = circY - 1*temp*(pangle-4);
           float circX3 = circX;
           float circY3 = circY + 1*temp*(pangle-4);
          
      for(int num =0; num < 20; num ++){
          float xrandom = random(-temp/2,temp/2);
          float yrandom = random(-temp/2, temp/2);
          float xrandom2 = random(-temp/2,temp/2);
          float yrandom2 = random(-temp/2, temp/2);
          stroke(255, 255, 0);
          line(circX2 + xrandom, circY2 + yrandom,
          circX2 + xrandom + temp/20, circY2 + yrandom + temp/20);
          stroke(255, 255, 0);
          line(circX3 + xrandom2, circY3 + yrandom2,
          circX3 + xrandom2 + temp/20, circY3 + yrandom2 + temp/20);
      }}
      
      else if(pangle > 5.3f && pangle < 5.6f){
          float temp = pscale* pdiameter/6;
          float circX2 = circX;
          float circY2 = circY - 1.5f*temp*(pangle-4);
          float circX3 = circX;
          float circY3 = circY + 1.5f*temp*(pangle-4);
          
      for(int num =0; num < 20; num ++){
          float xrandom = random(-temp,temp);
          float yrandom = random(-temp, temp);
          float xrandom2 = random(-temp,temp);
          float yrandom2 = random(-temp, temp);
          stroke(255,255,0);
          line(circX2 + xrandom, circY2 + yrandom,
          circX2 + xrandom + temp/20, circY2 + yrandom + temp/20);
          stroke(255,255,0);
          line(circX3 + xrandom2, circY3 + yrandom2,
          circX3 + xrandom2 + temp/20, circY3 + yrandom2 + temp/20);
      }}
      
       else if(pangle > 5.6f && pangle < 6.25f){
           float temp = pscale* pdiameter;
           float circX2 = circX;
           float circY2 = circY - 0.5f*temp;
           float circX3 = circX;
           float circY3 = circY + 0.5f*temp;
          
      for(int num =0; num < 20; num ++){
          stroke(0,200);
          stroke(0);
          line(circX2 + random(-temp/10,temp/10), circY2 + random(-temp/10, temp/10),
          circX2 + random(-temp/10,temp/10), circY2 + random(-temp/10, temp/10));
          stroke(0,200);
          stroke(0);
          line(circX3 + random(-temp/10,temp/10), circY3 + random(-temp/10, temp/10),
          circX3 + random(-temp/10,temp/10), circY3 + random(-temp/10, temp/10));
      }}
      
      else if(pangle > 6.25f && pangle < 2*PI){
           float temp = pdiameter;
           float circX2 = circX;
           float circY2 = circY + 1*pscale*pdiameter;
           float circX3 = circX;
           float circY3 = circY ;
          
      for(int num =0; num < 20; num ++){
          stroke(0,200);
          stroke(0);
          line(circX2 + random(-temp/10,temp/10), circY2 + random(-temp/10, temp/10),
          circX2 + random(-temp/10,temp/10), circY2 + random(-temp/10, temp/10));
          stroke(0,200);
          stroke(0);
          line(circX3 + random(-temp/10,temp/10), circY3 + random(-temp/10, temp/10),
          circX3 + random(-temp/10,temp/10), circY3 + random(-temp/10, temp/10));
      }}
  }
 
  
  public void ring(){
    float radius = pstartX - width/2;
    fill(0);
    stroke(130, 0, 200, 100);
    strokeWeight(5);
    ellipseMode(CENTER);
    ellipse(width/2, height/2, radius, radius);
  }
  
  public void g0phase(){
    if(pangle > 0 && pangle < 2 && counter < 5){
      float u = random(0,1);
      if (u < 0.1f){
        stopper = stopper/(counter+1);
        counter = counter +1;
      }}
      else if(counter >= 5 && counter <=100){
        stopper = 0;
        counter = counter +1;
      }
      else if(counter > 10){
        stopper = 1;
    }
    else{
      stopper =1;
   }
  }
  
  public void textbox(){
    float radius = pstartX - width/2;
    float circXX =  width/2 + 1.2f*radius * cos(pangle);
    float circYY =  height/2 + 1.2f*radius * sin(pangle);
  
    if(counter >= 5 && counter <=100){
      fill(255,255,0,200);
      stroke(3);
      rect(circXX, circYY, 80, 80);
      stroke(0);
      fill(0);
      textSize(10);
      text("The cell is at G0-rest phase", circXX, circYY, 80, 100);
    }
    else if(pangle > 1.0f && pangle < 2.0f){
      fill(255,255,0,200);
      stroke(3);
      rect(circXX, circYY, 80, 80);
      stroke(0);
      fill(0);
      textSize(10);
      text("The cell is at G-1 growth phase", circXX, circYY, 80, 100);
    }
    else if(pangle > 2.0f && pangle < 3.5f){
      fill(255,255,0,200);
      stroke(3);
      rect(circXX, circYY, 80, 80);
      stroke(0);
      fill(0);
      textSize(10);
      text("The cell is at S phase - chromosome duplicates", circXX, circYY, 80, 100);
    }
    else if(pangle > 3.5f && pangle < 4.5f){
      fill(255,255,0,200);
      stroke(3);
      rect(circXX, circYY, 80, 80);
      stroke(0);
      fill(0);
      textSize(10);
      text("The cell is at G2 growth phase", circXX, circYY, 80, 100);
    }
    else if(pangle > 4.5f && pangle < 5.5f){
     // fill(255,127,80,100);
      fill(255,255,0,200);
      stroke(3);
      rect(circXX, circYY, 80, 80);
      stroke(0);
      fill(0);
      textSize(10);
      text("The cell is at mitosis phase - the nucleus segregates ", circXX, circYY, 80, 100);
    }
    else if(pangle > 5.5f && pangle < 6.2f){
      fill(255,255,0,200);
      stroke(3);
      rect(circXX, circYY, 80, 80);
      stroke(0);
      fill(0);
      textSize(10);
      text("The cell is at cytokinesis phase - the cytoplasm segregates ", circXX, circYY, 80, 100);
    }
  }
  
}
  public void settings() {  size(800, 600); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "cell_cycle" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
