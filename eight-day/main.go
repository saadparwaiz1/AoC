package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

func readLines(path string) ([]string, error) {
	file, err := os.Open(path)
	if err != nil {
		return nil, err
	}
	defer file.Close()

	var lines []string
	scanner := bufio.NewScanner(file)
	for scanner.Scan() {
		lines = append(lines, scanner.Text())
	}
	return lines, scanner.Err()
}

func loop_start() int {
	var m map[int]bool
	m = make(map[int]bool)
	acc := 0

	var lines, _ = readLines("data/input")

	for i := 0; i < len(lines); i++ {
		if m[i] {
			break
		} else {
			string_array := strings.Split(lines[i], " ")
			instruction := string_array[0]

			if instruction == "nop" {
				continue
			}

			value, _ := strconv.Atoi(string_array[1])

			if instruction == "acc" {
				acc += value
			}

			if instruction == "jmp" {
				m[i] = true
				i += value
				i -= 1
			} else {
				m[i] = true
			}

		}
	}

	return acc
}

func main() {
	acc := loop_start()
	fmt.Println(acc)
}
