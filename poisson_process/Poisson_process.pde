class PP{
  float pstartX;
  float pstartY;
  float pdiameter;
  float prate;
  float[] poiss_times;
  float moveX = pstartX;

  PP (float tempX, float tempY, float temp_diameter, float temprate,
      float[] temp_poiss_times){
      pdiameter = temp_diameter;
      pstartY = tempY;
      pstartX = tempX;
      prate = temprate;
      poiss_times = temp_poiss_times;
  }
  
  void move(){
      if(moveX < width){
          moveX = moveX + step;
      }
       if(moveX >= width){
          moveX = 0;
          counter_event = 0;
       }
       stroke(255);
       line(pstartX, pstartY, moveX, pstartY);
  }
  
  void displayball(){
       fill(0,255,0, 200);
       stroke(0);
       strokeWeight(3);
       ellipse(moveX, pstartY, diameter, diameter);   
  }  
  
  void eventcheck(){
      int flag = 0;
      while(flag == 0){
        float movePX = moveX - step;
        if (movePX <= poiss_times[counter_event] && moveX > poiss_times[counter_event]
              && counter_event < poiss_times.length){
          counter_event = counter_event + 1;
          fill(255,255,0, 200);
          stroke(0);
          strokeWeight(3);
          ellipse(moveX, pstartY, diameter, diameter);
          stroke(255, 255, 0, 200);
       //   println(counter_event);
       //   println(poiss_times.length);
          line(moveX, startY, moveX, startY-100);
           if(counter_event==poiss_times.length){
            flag = 1;
          }
          }else{
           flag = 1;
          }
   }
 }
  
  void counter_event_adjust(){
    if(counter_event >= poiss_times.length){
      counter_event = 0;
    }
  }
  
  void textbox(){
    String message1 = "Number of events: " + counter_event;
    String message2 = "Poisson process rate: " + rate;
    float roundOff = Math.round(moveX * 100.0) / 100.0;
    String message3 = "Time of motion: " + roundOff + "s";
    fill(255);
    textSize(15);
    text(message1, 0.60*width, 0.25*height);
    text(message2, 0.60*width, 0.30*height);
    text(message3, 0.60*width, 0.35*height);
  }
  void rectbox(){
    rectMode(CENTER);
    fill(0);
    stroke(0,0);
    strokeWeight(0);
    rect(0.60*width, 0.25*height, 1*width, 0.25*height);
  }
}