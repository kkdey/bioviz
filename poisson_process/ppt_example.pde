
//  Poisson process Processing design 

PP b;
float diameter = 10;
float step = 1;
float rate = 0.1;
int counter_event = 0;
float startX;
float startY;

void setup(){
  size(800, 600);
  background(0);
  startX = 0;
  startY = 3*height/4;
  float [] poiss_times = event_times(width);
  b = new PP(startX, startY, diameter, rate, poiss_times);
}

void draw(){
  fill(255,255);
  textSize(30);
  text("Poisson Process Simulator", 0.5*width, height/20, 500, 40);
  
 // println(poiss_times[0]);
 // println(poiss_times[1]);
 // println(poiss_times[2]);
 // println(poiss_times[3]);
 // println(poiss_times[4]);
 if(counter_event==0){
   background(0);
   float [] poiss_times = event_times(width);
   b = new PP(startX, startY, diameter, rate, poiss_times);
 }
  b.rectbox();
  b.move();
  b.displayball();
  b.counter_event_adjust();
  b.eventcheck();
  b.textbox();
}

void mousePressed(){
  noLoop();
}

void mouseClicked(){
  loop();
}