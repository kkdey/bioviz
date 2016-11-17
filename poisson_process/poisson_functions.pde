public static int getPoisson(double lambda) {
  double L = Math.exp(-lambda);
  double p = 1.0;
  int k = 0;

  while(p > L){
    k++;
    p *= Math.random();
  }
  return k - 1;
}


float [] event_times(float width2){
    int num_events  = getPoisson(round(rate*width2));
    println(num_events);
    float [] times = new float [num_events];
    for(int num=0; num < num_events; num++){
      times[num] = random(0, width2);
    }
    times = sort(times);
    return times;
}



  