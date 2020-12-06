use std::collections::HashMap;
use std::env;
use std::fs;

fn get_two_sum(test_num: i32, numbers: &Vec<&str>) -> i32 {
    let mut number_map: HashMap<i32, bool> = HashMap::new();

    for i in numbers {
        if !i.is_empty() {
            number_map.insert(i.parse().unwrap(), true);
        }
    }

    for i in numbers {
        if !i.is_empty() {
            let test: i32 = test_num - i.parse::<i32>().unwrap();

            if number_map.contains_key(&test) {
                return test * i.parse::<i32>().expect("found not int");
            }
        }
    }

    0
}

fn get_three_sum(test_num: i32, numbers: &Vec<&str>) -> i32 {
    for i in numbers {
        if !i.is_empty() {
            let test: i32 = test_num - i.parse::<i32>().unwrap();
            let result = get_two_sum(test, numbers);

            if result != 0 {
                return i.parse::<i32>().unwrap() * result;
            }
        }
    }
    0
}

fn main() {
    let args: Vec<String> = env::args().collect();
    let filename = &args[1];

    let contents = fs::read_to_string(filename).expect("Something went wrong");

    let numbers = contents.split('\n').collect::<Vec<&str>>();

    println!("{:?}", get_two_sum(2020, &numbers));
    println!("{:?}", get_three_sum(2020, &numbers));
}
