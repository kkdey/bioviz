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

public class moving_graph extends PApplet {

int NUM_NODES = 5;
Node graph;
float[][] probMatrix = { {0, 0.05f, 0, 0.05f, 0.25f}, 
                         {0, 0, 0.2f, 0, 0}, 
                         {0,0.1f,0,0.1f, 0}, 
                         {0.05f, 0.05f, 0, 0, 0},
                         {0, 0.05f, 0.1f, 0, 0} };
float[][] EdgeWeights = new float [NUM_NODES][NUM_NODES];                        
String[] keywords = {"Black", "Police", "Terrorism", "Violence", "Lives"};
int fillcol = color(0, 255, 255);
float nodeX[] = new float [NUM_NODES];
float nodeY[] = new float [NUM_NODES];
float strength[] = {12, 6, 8, 3, 8};





public void setup(){
  
  frameRate(10);
  
  for(int i=0; i < NUM_NODES; i++){
    nodeX[i] = random(0, width );
    nodeY[i] = random(0, height); 
  }
  
  for(int i=0; i < NUM_NODES; i++){
    for(int j=0; j < NUM_NODES; j++){
      EdgeWeights[i][j] = 100*probMatrix[i][j];
    }
  }

  graph = new Node(nodeX, nodeY, strength, EdgeWeights);

}



public void draw(){
  background(0,100);
  graph.displaynode();
  graph.displayedge();
  graph.textbox();
}


  public void mouseDragged() 
{
  int minIndex=0;
  float[] dist = new float [NUM_NODES];

  
  for(int i=0; i < NUM_NODES; i++){
      dist[i] = abs(nodeX[i] - mouseX) +  abs(nodeY[i] - mouseY);
  }
  
  for(int i=0; i < NUM_NODES; i++){
    if(dist[i] <= min(dist)+0.01f){
      minIndex = i;
    }
  }

   if(nodeX[minIndex] > (mouseX - 30) && nodeX[minIndex] < (mouseX +  30) 
         && nodeY[minIndex] > (mouseY - 30) && nodeY[minIndex] < (mouseY + 30)){
     nodeX[minIndex] = mouseX;
     nodeY[minIndex] = mouseY;
    }
}


class Node{
  float[] pnodeX;
  float[] pnodeY;
  float[] pstrength;
  float[][] pEdgeWeights;
  
  Node(float[] tempnodeX, float[] tempnodeY, float[] tempstrength, float[][] temp_EdgeWeights){
      pnodeX = tempnodeX;
      pnodeY = tempnodeY;
      pstrength = tempstrength;
      pEdgeWeights = temp_EdgeWeights;
  }
  
  public void displaynode(){
    fill(255,255,0, 200);
    stroke(2);
    strokeWeight(2);
    for(int i=0; i < NUM_NODES; i++){
      ellipse(pnodeX[i], pnodeY[i], 10*pstrength[i], 10*pstrength[i]);
    }
  }
  
  public void textbox(){
    for(int i=0; i < NUM_NODES; i++){
      fill(255,0,0,200);
      textSize(pstrength[i]*2);
      text(keywords[i], pnodeX[i]-2*pstrength[i], pnodeY[i]);
    }
  }
  
  public void displayedge(){
    for(int i=0; i < NUM_NODES; i++){
      for(int j=0; j < NUM_NODES; j++){
        if(pEdgeWeights[i][j] !=0){
          stroke(fillcol, 200);
          strokeWeight(pEdgeWeights[i][j]);
          line(pnodeX[i], pnodeY[i], pnodeX[j], pnodeY[j]);
        }
      }
    }
  }
  
}
  public void settings() {  size(800,600); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--stop-color=#cccccc", "moving_graph" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
