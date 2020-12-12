main: util/binary_range.o main.o
	clang main.o util/binary_range.o -o main

test: util/binary_range.o test.o
	clang test.o util/binary_range.o -o test

main.o: main.c
	clang -c -I./util main.c

util/binary_range.o: util/binary_range.c
	clang -c util/binary_range.c -o util/binary_range.o

.PHONY:
clean:
	rm -f main main.o util/binary_range.o
