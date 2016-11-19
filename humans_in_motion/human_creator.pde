Human[] humans = new Human [100];

float headX;
float headY;
float headsize;
float bodylength;
color col;
float[] speed = new float [humans.length];

float[] headXarr = new float [humans.length];
float[] headYarr = new float [humans.length];

void setup(){
  size(800, 600);
  frameRate(10);
  col = color(255, 0, 0);
  headsize = 10;
  bodylength = 30;
  
  for(int i=0; i < humans.length; i++){
    headXarr[i] = random(100, width-100);
    headYarr[i] = random(100, height-100);
    if(headXarr[i]> width-200 && headXarr[i] < width+100 || headYarr[i]> height-200 && headYarr[i] < height+100){
      speed[i] = -2;
    }else{
      speed[i] = 2;   
    }
  }
}

void draw(){
   background(255);
   textMode(CENTER);
   fill(0);
   textSize(25);
   text("Population in Motion ", 0.35*width, 10, 500, 40);
   for(int i=0; i < humans.length; i++){
    if(headXarr[i] > width - 100 || headXarr[i] < 100 || headYarr[i] > height - 100 || headYarr[i] < 100){
       speed[i] = -speed[i];
    }
    
    headXarr[i] = headXarr[i] + speed[i]*random(1,2);
    headYarr[i] = headYarr[i] + speed[i]*random(1,2);
    humans[i] = new Human(headXarr[i] , headYarr[i], headsize, bodylength, col);
    humans[i].head();
    humans[i].body();
    humans[i].hand();
    humans[i].leg();
   } 
}