#include "binary_range.h"
#include <math.h>

int len(char *string){
  int i=0;
  while(string[i++]);
  return i-1;
}

char *__seperate(char *string){
  for(int i=0; i<len(string); i++){
    if(string[i] != 'B' && string[i] != 'F'){
      return &string[i];
    }
  }

  return "";
}

int __calculate_row(char *string, int length){
  int low = 0;
  int high = 127;
  double average;

  for(int i=0; i<length; i++){
    average = (low + high)/2.0;

    if(string[i] == 'F'){
      high = floor(average);
    }
    else{
      low = ceil(average);
    }
  }

  if(string[length - 1] == 'B'){
    return high;
  }

  return low;
}

int __calculate_column(char *string){
  int low = 0;
  int high = 7;

  int length=len(string);
  double average;

  for(int i=0; i<length; i++){
    average = (low + high)/2.0;

    if(string[i] == 'L'){
      high = floor(average);
    }
    else{
      low = ceil(average);
    }

  }

  if(string[length - 1] == 'R'){
    return high;
  }

  return low;
}

int search(char *string){
  char *column = __seperate(string);

  if(!*column){
    return -1;
  }

  int row = __calculate_row(string, column-string);
  int col = __calculate_column(column);

  return row*8 + col;
}

void position(char *string, int *pos){
  char *column = __seperate(string);

  pos[0] = __calculate_row(string, column-string);
  pos[1] = __calculate_column(column);
}
