Grid g;
int NUM_PLAYERS = 5;
int[][] dat = new int [NUM_PLAYERS][NUM_PLAYERS];
float[][] score = new float [NUM_PLAYERS][NUM_PLAYERS];
String[] playernames = {"P1", "P2", "P3", "P4", "P5"};
float start_x;
float start_y;
float end_x;
float end_y;
float vertical_gap;
float horiz_gap;
String message;
float tw;
  
 


void setup(){
  size(800,600);
  frameRate(10); 
  g = new Grid();
  
  start_x  = 100;
  end_x = width -100;
  start_y = 100;
  end_y = height - 100;
  
  vertical_gap =  (end_y - start_y)/NUM_PLAYERS;
  horiz_gap =  (end_x - start_x)/NUM_PLAYERS;

  
  for(int i=0; i < (NUM_PLAYERS-1); i++){
    for(int j=(i+1); j < NUM_PLAYERS; j++){
      if(random(0,1) < 0.5){
        dat[i][j] = 1;
        score[i][j] = random(50, 100);
      }else{
        dat[i][j] = 0;
        score[i][j] = random(0, 50);
      }
      dat[j][i] = 1 - dat[i][j];
      score[j][i] = 100 - score[i][j];
    }
  }
  
  
  
}

void draw(){
  background(0);
  fill(255);
  textSize(20);
  text("Competition Game Tracker", width*0.35, start_y*0.2);
  g.horizline();
  g.verticalline();
  g.rectangles();
}


void mousePressed(){
  for(int i=0; i < NUM_PLAYERS; i++){
      for(int j=0; j < NUM_PLAYERS; j++){
        if(mouseX > start_x + (i)*horiz_gap && mouseX < start_x + (i+1)*horiz_gap
          && mouseY > start_y + (j)*vertical_gap && mouseY < start_y + (j+1)*vertical_gap){
            //float roundoff = round(score[i][j]);
            message = "Score : " + round(score[i][j]) + "-" + round(score[j][i]);
            println(message);
            tw = textWidth(message);
            println(tw);
              fill(255, 255, 255);
              rect(mouseX, mouseY, tw + 10, 40);
               fill(0);
              textSize(10);
              text(message, mouseX , mouseY+2, tw+10, 20);
          }
       }
     }  
}