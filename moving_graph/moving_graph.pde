 Table table1;
 Table table2;
 float[][] probMatrix;
 int NUM_NODES ;
 Node graph;
 float[] strength ;
 String[] keywords;
 float[][] EdgeWeights;                        
 color fillcol = color(0, 255, 255);
 float[] nodeX;
 float[] nodeY;


void setup(){
  size(800,600);
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



void draw(){
  background(0,100);
  graph.displaynode();
  graph.displayedge();
  graph.textbox();
}


  void mouseDragged() {
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