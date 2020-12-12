#include "binary_range.h"
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int *min_max_seating_id(int *tuple, int *elements){
  FILE *file = fopen("data/input", "r");

  char length[1000];
  int *ids;

  int max=-1;
  int min=128*8 + 8;

  while(fgets(length, 1000, file)){

    if(!elements){
      ids = malloc(sizeof(int));
      *elements += 1;
    }
    else{
      *elements += 1;
      ids = realloc(ids, sizeof(int)*(*elements));
    }

    length[strlen(length) -1] = 0;
    int num = search(length);

    ids[*elements - 1] = num;

    if(num > max){
      max = num;
    }

    if(num < min){
      min = num;
    }
  }

  tuple[0] = min;
  tuple[1] = max;

  fclose(file);

  return ids;
}

int find(int *ids, int length, int id){
  for(int i=0; i<length; i++){
    if(ids[i] == id){
      return 1;
    }
  }

  return 0;
}

int seating_id(int *tuple, int *ids, int length){
  for(int i=tuple[0]; i<=tuple[1]; i++){
    if(!find(ids, length, i)){
      return i;
    }
  }

  return -1;
}


int main(){
  int tuple[2];
  int elements = 0;
  int *ids = min_max_seating_id(tuple, &elements);

  printf("Max Seating id: %d\n", tuple[1]);
  printf("Customers Seating Id: %d\n", seating_id(tuple, ids, elements));
  free(ids);
}
