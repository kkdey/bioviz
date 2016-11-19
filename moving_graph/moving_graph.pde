int NUM_NODES = 5;
Node graph;
float[][] probMatrix = { {0, 0.05, 0, 0.05, 0.25}, 
                         {0, 0, 0.2, 0, 0}, 
                         {0,0.1,0,0.1, 0}, 
                         {0.05, 0.05, 0, 0, 0},
                         {0, 0.05, 0.1, 0, 0} };
float[][] EdgeWeights = new float [NUM_NODES][NUM_NODES];                        
String[] keywords = {"Black", "Police", "Terrorism", "Violence", "Lives"};
color fillcol = color(0, 255, 255);
float nodeX[] = new float [NUM_NODES];
float nodeY[] = new float [NUM_NODES];
float strength[] = {12, 6, 8, 3, 8};





void setup(){
  size(800,600);
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



void draw(){
  background(0,100);
  graph.displaynode();
  graph.displayedge();
  graph.textbox();
}


  void mouseDragged() 
{
  int minIndex=0;
  float[] dist = new float [NUM_NODES];

  
  for(int i=0; i < NUM_NODES; i++){
      dist[i] = abs(nodeX[i] - mouseX) +  abs(nodeY[i] - mouseY);
  }
  
  for(int i=0; i < NUM_NODES; i++){
    if(dist[i] <= min(dist)+0.01){
      minIndex = i;
    }
  }

   if(nodeX[minIndex] > (mouseX - 30) && nodeX[minIndex] < (mouseX +  30) 
         && nodeY[minIndex] > (mouseY - 30) && nodeY[minIndex] < (mouseY + 30)){
     nodeX[minIndex] = mouseX;
     nodeY[minIndex] = mouseY;
    }
}