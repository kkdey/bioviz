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
  
  void displaynode(){
    fill(255,255,0, 200);
    stroke(2);
    strokeWeight(2);
    for(int i=0; i < NUM_NODES; i++){
      ellipse(pnodeX[i], pnodeY[i], 10*pstrength[i], 10*pstrength[i]);
    }
  }
  
  void textbox(){
    for(int i=0; i < NUM_NODES; i++){
      fill(255,0,0,200);
      textSize(pstrength[i]*2);
      text(keywords[i], pnodeX[i]-2*pstrength[i], pnodeY[i]);
    }
  }
  
  void displayedge(){
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