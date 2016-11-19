Cell [] cells = new Cell [200];
int Branches = 5;
float[] split_Y = new float[(Branches-1)];
color[] color_branches = new color[(Branches+10)];
int[] parents = {0, 1, 1, 1};
String[] dirs = {"NA", "left", "right", "right"};
float SamplingRate = 100;
float Xspeed;
float Yspeed;

float moveX;
float moveY;
float diameter;


void setup(){
  size(800, 600);
  
  for(int num=0; num < (Branches-1); num++){
  split_Y[num] = random(0, height);
  }
  split_Y = reverse(sort(split_Y));
 
  
  for(int num=0; num < (Branches+10); num++){
     int R = round(random(0,255));
     int G = round(random(0,255));
     int B = round(random(0,255));
     color_branches[num] = color(R, G, B);
  }
  
  moveX = width/2;
  moveY = height;
  diameter = 20;
  Xspeed = 1;
  Yspeed = 1;
  
   for(int i =0; i < cells.length; i++){
    cells[i] = new Cell(moveX, moveY, diameter, color_branches, split_Y, Xspeed, Yspeed);
 //   group[i] =1;
  }
  
}


void draw(){
   background(0);
   textMode(CENTER);
   fill(255,255);
   textSize(20);
   text("Cell Differentiation ", 0.1*width, 0.85*height, 300, 40);
   text("Process ", 0.1*width, 0.90*height, 500, 40);

  for(int i=0; i < cells.length; i++){
    cells[i].topcheck();
    cells[i].ascend();
    cells[i].branching();
    cells[i].ascend_steep();
    cells[i].timercheck();
    cells[i].display();
  }
  
}

void mousePressed() {
  noLoop();
}

void mouseClicked() {
  loop();
}