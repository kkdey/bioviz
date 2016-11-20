class Grid{
  float[] horiz_lines = new float[NUM_PLAYERS+1];
  float[] vertical_lines = new float[NUM_PLAYERS+1];

  void horizline(){
    stroke(255);
    strokeWeight(1);
    for(int k=0; k<= NUM_PLAYERS; k++){
      horiz_lines[k] = start_x + k*horiz_gap;
      if(k < NUM_PLAYERS){
         textSize(15);
         text(playernames[k], start_x + (k+0.5)*horiz_gap, 0.6*start_y);
      }
      line(horiz_lines[k], 100, horiz_lines[k], height -100);
    }
  }
  
  void verticalline(){
     stroke(255);
     strokeWeight(1);
    for(int k=0; k<= NUM_PLAYERS; k++){
      vertical_lines[k] = start_y + k*vertical_gap;
      if(k < NUM_PLAYERS){
         textSize(15);
         text(playernames[k], 0.6*start_x, start_y + (k+0.5)*vertical_gap);
      } 
      line(100, vertical_lines[k], width - 100, vertical_lines[k]);
    }
  }
  
  void rectangles(){
    for(int i=0; i < NUM_PLAYERS; i++){
      for(int j=0; j < NUM_PLAYERS; j++){
        if(dat[i][j]==1){
          fill(255,255,0);
          stroke(2);
          rect(start_x + (i)*horiz_gap, 
               start_y + (j)*vertical_gap, 
               horiz_gap, vertical_gap);
      }
    }
  }
 } 
 
 void playernames(){
   for(int i=0; i < NUM_PLAYERS; i++){
   }
   
 }
}