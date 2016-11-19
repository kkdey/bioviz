
class Cell{
  float pmoveX;
  float pmoveY;
  color[] pcolor_branches;
  float[] psplit_Y;
  float pYspeed;
  float pXspeed;
  float pdiameter = 10;
  boolean stoptimer = false;
  int counter = 0;
  int flag = 0;
  boolean splitted = false;
  int parent = 0;
  int branch_counter =0;
  int count = 0;
  String dir;
  
  Cell(float temp_moveX, float temp_moveY, float temp_diameter, 
        color[] tempcolor_branches, float[] temp_split_Y, float temp_pXspeed, 
        float temp_pYspeed){
              pmoveX = temp_moveX;
              pmoveY = temp_moveY;
              pcolor_branches = tempcolor_branches;
              psplit_Y = temp_split_Y;
              pXspeed = temp_pXspeed;
              pYspeed = temp_pYspeed;
              pdiameter = temp_diameter;
  }
  
  void display(){
    if(!stoptimer){
     fill(0, height - 0.8*pmoveY);
     ellipse(pmoveX, pmoveY, pdiameter + random(-1,1), pdiameter +random(-1,1));
    }else{
      fill(pcolor_branches[branch_counter], height - 0.8*pmoveY);
      ellipse(pmoveX, pmoveY, pdiameter + random(-1,1), pdiameter +random(-1,1));
    }
  }
  
  void ascend(){
    if(!stoptimer && flag == 0){
    float yscale = 2;
    float xscale = random(-1,1);
       pmoveY = pmoveY - pYspeed*yscale;
       pmoveX = pmoveX + pXspeed*xscale;
    }
  }
  
  void branching(){
      if(pmoveY < psplit_Y[count] && !splitted && count < 3){
        if(parents[count]==0){
                float u = random(0,1);
                if(u < 0.5){
                     count = count +1;
                     branch_counter = branch_counter + 1;
                  //   pmoveX = pmoveX - 2;
                  //   pmoveY = pmoveY - 2;
                     flag = flag - 1;
                     splitted = true;
                     dir = "left";
                     parent = parents[count];
                 }else{
                     count = count +1;
                     branch_counter = branch_counter + 1;
                  //   pmoveX = pmoveX + 2;
                  //   pmoveY = pmoveY - 2;
                     flag = flag + 1;
                     splitted = true;
                     dir = "right";
                     parent = parents[count];
                 }
            }
          else if(parents[count] !=0 && dir == dirs[count]){
               float u = random(0,1);
               if(u < 0.5){
                     count = count +1;
                     print(count);
                 //    pmoveX = pmoveX - 2;
                 //    pmoveY = pmoveY - 2;
                     branch_counter = branch_counter + 1;

                     flag = flag - 1;
                     splitted = true;
                     dir = "left";
                     parent = parents[count];
                 }else{
                     count = count +1;
                     println(count);
                  //   pmoveX = pmoveX + 2;
                  //   pmoveY = pmoveY - 2;
                     branch_counter = branch_counter + 1;
                     flag = flag + 1;
                     splitted = true;
                     dir = "right";
                     parent = parents[count];

                 }
             }
             else if (parents[count] !=0 && dir != dirs[count]){
                   count = count + 1;
                   println(parents);
                   parent = parents[count];
                   splitted = false;
             }
          }
       }

  
  void ascend_steep(){
      if(!stoptimer){
        float yscale2 = 2;
        float xscale2 = 2;
      if(flag > 0){
        pmoveX = pmoveX + pYspeed*yscale2;
        pmoveY = pmoveY - pXspeed*xscale2;
        flag = flag + 1;
      }else if (flag < 0){
        pmoveX = pmoveX - pXspeed*xscale2;
        pmoveY = pmoveY - pYspeed*yscale2;
        flag = flag - 1;
      }
      if(flag == round(200/(count+1)) || flag == -round(200/(count+1))){
        flag = 0;
        splitted = false;
      }
     }
   }
   
   void topcheck(){
    if(pmoveY <= (pdiameter/2 + random(-5,5))){
      pYspeed = 0;
    }
    if(pYspeed==0){
      pXspeed = 0;
    }  
  }
  
  void timercheck(){
    if(pYspeed==0 && moveY > pdiameter){
      stoptimer = true;
    }
    else if(pYspeed==0 && moveY < pdiameter){
      stoptimer = false;
    }else if (random(0, SamplingRate) < 1){
      stoptimer = false;
      pYspeed = 0;
      pXspeed = 0;
    }}
    
}