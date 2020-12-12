CC=clang
INCLUDES=-I./util
CFLAGS=-g -Wall -std=c99 $(INCLUDES)
LDFLAGS=-g
LDLIBS=-lm

main: util/binary_range.o main.o

main.o: main.c

util/binary_range.o: util/binary_range.c

.PHONY:
clean:
	rm -f main main.o util/binary_range.o
