float[][] dat1 = new float [2][100];
float[][] map_dat1 = new float [2][100];
float[][] dat2 = new float [100][10];
float[][] map_dat2 = new float [100][10];
float[] line = new float [10];
float[] map_line = new float [10];

float[] radius = new float [100];
color[] col_vec = new color[100];

float start_x;
float start_y;
float end_x;
float end_y;

int minIndex=0;


void setup(){
  
  size(800,600);
  frameRate(10);
  background(200);
  start_x = 0.2*width/2;
  end_x = 0.8*width/2;
  start_y = 0.2*height;
  end_y = 0.8*height;
  
  textSize(15);
  fill(0);
  text("2D interactive 2-panel scatter + line plot",0.3*width, 0.05*width);
  
 
  
  for(int i=0; i < 100; i++){
    dat1[0][i] = random(0,200);
    dat1[1][i] = dat1[0][i] + random(-10,10);
    for(int j=0; j < 10; j++){
      dat2[i][j] = random(-10,10);
    }
  
     radius[i]=5;
     col_vec[i] = color(0,255,255);
     println(i);
  }
  
  for(int j=0; j < 10; j++){
     line[j] = 5*(j+0.01);
  }
  
  for(int i=0; i < 100; i++){
    map_dat1[0][i] = map(dat1[0][i], min(dat1[0]),max(dat1[0]), start_x, end_x);
    map_dat1[1][i] = map(dat1[1][i], min(dat1[1]),max(dat1[1]), start_y, end_y);
    for(int j=0; j < 10; j++){
      map_dat2[i][j] = map(dat2[i][j], min(dat2[i]), max(dat2[i]), start_y, end_y);
    }
  }
  
   for(int j=0; j < 10; j++){
     map_line[j]  = map(line[j], min(line), max(line), width/2 + start_x, width/2 + end_x);
  }

}

void draw(){
  
  stroke(0);
  line(end_x, end_y, start_x, end_y);
  line(start_x, end_y, start_x, start_y);
  
   stroke(0);
   line(width/2+start_x, end_y, width/2+end_x, end_y);
   line(width/2+start_x, end_y, width/2+start_x, start_y);
  
   for(int i=0; i < 100; i++){
      stroke(col_vec[i]);
      fill(col_vec[i]);
      ellipse(map_dat1[0][i],map_dat1[1][i], radius[i], radius[i]);
   }  
   
  //    radius[minIndex]=5;
  //    col_vec[minIndex]=color(0,255,255);
}

void mousePressed(){
  background(200);
  col_vec[minIndex]=color(0,255,255);
  radius[minIndex]=5;
  float[] dist = new float [100];    
  for(int i=0; i < 100; i++){
      dist[i] = abs(map_dat1[0][i] - mouseX) +  abs(map_dat1[1][i] - mouseY);
  }
  
  for(int i=0; i < 100; i++){
    if(dist[i] <= min(dist)+0.01){
      minIndex = i;
    }
  }
  
  if(map_dat1[0][minIndex] > (mouseX - 30) && map_dat1[0][minIndex] < (mouseX +  30) 
         && map_dat1[1][minIndex] > (mouseY - 30) && map_dat1[1][minIndex] < (mouseY + 30)){
      col_vec[minIndex] = color(255,0,0);
      radius[minIndex] = 10;
    }
      
      
      for(int j=0; j < 10; j++){
         fill(col_vec[minIndex]);
         ellipse(map_line[j], map_dat2[minIndex][j], radius[j], radius[j]);
         if(j > 0){
           stroke(col_vec[minIndex]);
           fill(col_vec[minIndex]);
           strokeWeight(2);
           line(map_line[j-1], map_dat2[minIndex][j-1], map_line[j], map_dat2[minIndex][j]);
         }
      } 
}


   
   
   
   
  