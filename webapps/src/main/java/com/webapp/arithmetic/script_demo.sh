#!/bin/bash
# debug sh -n -v -x

#0	标准输入 	stdin = standard input
#1	标准输出	stdout = standard output
#2	标准错误输出	stderr = standard error

# Declare a variable
# tp="test var --> ok"
# echo $tp

# $0 --> The Script's Name
# $# --> The number of parameters
# $@ --> Parameters of the array ["$1" "$2"]
# $* --> ["$1 $2"]
# shift n 	--> Remove the front n parameters
# read name --> Get the user input
# $((1+1)) 	--> operation
# eval		--> 执行命令行之前扫描它两次
# ()和{}		--> 都是对一串的命令进行执行

# [ ] condition
# [ "what" == "what" ] && echo "==" || echo "!="
# test -e "$p" && echo "$p" || echo "$p --> not exists"

# if condition -a and -o or
# if [ -n "a" ]; then
# 	echo " a == a "
# elif [ "a" == "b" -a "a" == "a" ]; then
# 	echo " "
# else
# 	echo " "
# fi

# case condition
# case "name" in
#	"name")
#		echo "hello name";;
#	*)
#		echo "other";;
# esac

# while until loop
# while [ ! 0 ]
# do
# 	echo "while"
# done

# for loop
# 1
# for p in "$@"
# do
# 	echo "$p"
# done
# 2
# for((i=1;i<5;i++))
# do
# 	echo "$i"
# done

# function
# function name(){
# 	echo "function"
# }
# name

# array
# ${array[1]} --> Get an array element
# ${array[@]} --> Get all elements of an array
# ${#array[@]}--> Get the length of the array
# ${#array[*]}--> Get the length of the array

# ${var:-str} --> if var is empty，str replace ${var:-str}
# ${var:+str} --> if var is not empty，str replace ${var:+str}
# ${var:=str} --> if var is empty，str replace ${var:-str} var=str
# ${var:?str} --> if var is empty，str system out
# ${mt%=*} ${mt#*=}

# str=abc
# expr index $str "a"  # 1
