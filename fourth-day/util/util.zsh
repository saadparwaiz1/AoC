#/usr/bin/env zsh

function validate_cheat(){
  echo "$@" | grep 'ecl:' | grep 'pid:' | grep 'eyr:' | grep 'hcl:' | grep 'byr:' | grep 'iyr:' | grep 'hgt:' &> /dev/null  && echo '1'}

function validate_fields(){
  ecl=$(echo $@ | grep -o 'ecl:amb\|ecl:blu\|ecl:brn\|ecl:gry\|ecl:grn\|ecl:hzl\|ecl:oth')

  if [ "$ecl" = "" ]; then
    return 0
  fi



  hcl=$(echo $@ | grep -o 'hcl:#[a-f0-9]\{6\}')

  if [ "$hcl" = "" ]; then
    return 0
  fi


  eyr=$(echo $@ | grep -o 'eyr:202[0-9]\|eyr:2030')

  if [ "$eyr" = "" ]; then
    return 0
  fi



  iyr=$(echo $@ | grep -o 'iyr:201[0-9]\|iyr:2020')

  if [ "$iyr" = "" ]; then
    return 0
  fi


  byr=$(echo $@ | grep -o 'byr:19[2-9][0-9]\|byr:200[0-2]')

  if [ "$byr" = "" ]; then
    return 0
  fi


  pid=$(echo $@ | grep -o 'pid:\d\+' | grep -o '\d\+')

  if [ "$pid" = "" ]; then
    return 0
  fi

  if [ ${#pid} -ge 10 -o ${#pid} -le 8 ]; then
    return 0
  fi

  hgt=$(echo $@ | grep -o 'hgt:1[5-8][0-9]cm\|hgt:19[0-3]\|hgt:5[6-9]in\|hgt:6[0-9]in\|hgt:7[0-6]in')

  if [ "$hgt" = "" ]; then
    return 0
  fi

  echo '1'
  return 0
}

function first(){
  start_passport='2'

  for end_passport in $(grep -n "^$" data/input | grep -o '[0-9]\+')
  do
    start_passport=$(($start_passport - 1))
    end_passport=$(($end_passport - 1))

    validate_cheat $(sed -n "${start_passport}, ${end_passport}p" data/input)

    start_passport=$(($end_passport + 3))
  done

  validate_cheat $(sed -n "${end_passport}, ${start_passport}p" data/input)
}

function second(){
  start_passport='2'

  for end_passport in $(grep -n "^$" data/input | grep -o '[0-9]\+')
  do
    start_passport=$(($start_passport - 1))
    end_passport=$(($end_passport - 1))

    validate_fields $(sed -n "${start_passport}, ${end_passport}p" data/input)

    start_passport=$(($end_passport + 3))
  done

  validate_fields $(sed -n "${end_passport}, ${start_passport}p" data/input)
}

if [ "$1" = "strict" ]; then
  second
else
  first
fi
