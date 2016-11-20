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

 Table table1;
 Table table2;
 float[][] probMatrix;
 int NUM_NODES ;
 Node graph;
 float[] strength ;
 String[] keywords;
 float[][] EdgeWeights;                        
 int fillcol = color(0, 255, 255);
 float[] nodeX;
 float[] nodeY;


public void setup(){
  
  frameRate(10);
  
   table1 = loadTable("node_strength.csv");
   table2 = loadTable("node_edge_weights.csv", "header");
   NUM_NODES = table2.getRowCount();
   strength = new float [table1.getRowCount()];
   keywords = new String [table1.getRowCount()];
   EdgeWeights = new float [NUM_NODES][NUM_NODES];
   probMatrix = new float [NUM_NODES][NUM_NODES];
   nodeX = new float [NUM_NODES];
   nodeY = new float [NUM_NODES];
   
  for(int i=0; i < table2.getRowCount(); i++){
   for(int j=0; j < table2.getColumnCount(); j++){
      probMatrix[i][j] = table2.getFloat(i,j);
   }
 }
 
 for(int i=0; i < table1.getRowCount(); i++){
     strength[i] = table1.getFloat(i,1);
     keywords[i] = table1.getString(i,0);
 }
 
 
  
  for(int i=0; i < NUM_NODES; i++){
    nodeX[i] = random(0, width );
    nodeY[i] = random(0, height); 
  }
  
  println(NUM_NODES);
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


  public void mouseDragged() {
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
