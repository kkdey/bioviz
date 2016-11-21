float[][] dat1 = new float [2][100];
float[][] dat2 = new float [2][100];
float[] radius = new float [100];
color[] col_vec = new color[100];

float[] map1 = new float[100];
float[] map2 = new float[100];
float[] map3 = new float[100];
float[] map4 = new float[100];

float start_x;
float start_y;
float end_x;
float end_y;

float max1;
float min1;
float max2;
float min2;


void setup(){
  
  size(800,600);
  frameRate(10);
  start_x = 0.1*width/2;
  end_x = 0.9*width/2;
  start_y = 0.2*height;
  end_y = 0.9*height;
  
   for(int i=0; i < 100; i++){
       dat1[0][i] = random(0,200);
       dat1[1][i] = dat1[0][i] + random(-10,10);
       dat2[0][i] = random(0, 50);
       dat2[1][i] = dat2[0][i] - random(0,100);
       radius[i]=5;
       col_vec[i] = color(0,255,255);
       map1[i] = map(dat1[0][i], min(dat1[0]),max(dat1[0]), start_x, end_x);
       map2[i] = map(dat1[1][i], min(dat1[1]),max(dat1[1]), start_y, end_y);
       map3[i] = map(dat2[0][i], min(dat2[0]),max(dat2[0]), width/2+start_x, width/2+end_x);
       map4[i] = map(dat2[1][i], min(dat2[1]),max(dat2[1]), start_y, end_y);
         
   }  
}

void draw(){
  textSize(15);
  text("2D interactive 2-panel scatter plot",0.3*width, 0.05*width);
  line(end_x, end_y, start_x, end_y);
  line(start_x, end_y, start_x, start_y);
  
  line(width/2+start_x, end_y, width/2+end_x, end_y);
  line(width/2+start_x, end_y, width/2+start_x, start_y);
  
  for(int i=0; i < 100; i++){
    fill(col_vec[i]);
    ellipse(map1[i],map2[i], radius[i], radius[i]);
  }
  
  for(int i=0; i < 100; i++){
    fill(col_vec[i]);
    ellipse(map3[i],map4[i], radius[i], radius[i]);
  }
  
  float xtick = (end_x - start_x)/5;
  float ytick = (end_y - start_y)/5;
  
  for(int k =0; k <= 5; k++){
    line(start_x + k*(xtick), end_y, start_x + k*(xtick), end_y + 5);
    float a = map(start_x + k*(xtick), start_x, end_x, min(dat1[0]),max(dat1[0]));
    fill(0);
    text(round(a), start_x + k*(xtick), end_y+30);
    line(start_x, end_y - k*(ytick), start_x - 5, end_y - k*(ytick));
    float b = map(end_y - k*(ytick), end_x, start_x, min(dat1[1]),max(dat1[1]));
    text(round(b), start_x - 30, end_y - k*(ytick));
    line(width/2+ start_x + k*(xtick), end_y, width/2 + start_x + k*(xtick), end_y+5);
    float c = map(width/2 + start_x + k*(xtick), start_x, end_x, min(dat2[0]),max(dat2[0]));
    text(round(c), width/2 + start_x+ k*(xtick),  end_y + 30);
    line(width/2+start_x, end_y - k*(ytick), width/2+start_x - 15, end_y - k*(ytick));
    float d = map(end_y - k*(ytick), end_x, start_x, min(dat2[1]),max(dat2[1]));
    text(round(d), width/2 + start_x - 30, end_y - k*(ytick));

   }
  
}

void mousePressed(){
  int minIndex=0;
  float[] dist = new float [100];

  if (mouseX < width/2){
    
  for(int i=0; i < 100; i++){
      dist[i] = abs(map1[i] - mouseX) +  abs(map2[i] - mouseY);
  }
  
  for(int i=0; i < 100; i++){
    if(dist[i] <= min(dist)+0.01){
      minIndex = i;
    }
  }
  
  if(map1[minIndex] > (mouseX - 30) && map1[minIndex] < (mouseX +  30) 
         && map2[minIndex] > (mouseY - 30) && map2[minIndex] < (mouseY + 30)){
      int R = int(random(0,255));
      int G = int(random(0,255));
      int B = int(random(0,255));
      col_vec[minIndex] = color(R,G,B);
      radius[minIndex] = 10;
   }
   
 }
  
  else{
    
  for(int i=0; i < 100; i++){
      dist[i] = abs(map3[i] - mouseX) +  abs(map4[i] - mouseY);
  }
  
  for(int i=0; i < 100; i++){
    if(dist[i] <= min(dist)+0.01){
      minIndex = i;
    }
  }
  
  if(map3[minIndex] > (mouseX - 30) && map3[minIndex] < (mouseX +  30) 
         && map4[minIndex] > (mouseY - 30) && map4[minIndex] < (mouseY + 30)){
      int R = int(random(0,255));
      int G = int(random(0,255));
      int B = int(random(0,255));
      col_vec[minIndex] = color(R,G,B);
      radius[minIndex] = 10;
   }
   
 }
}