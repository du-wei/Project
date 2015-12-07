#!/usr/bin/env bash

if [ $# == 0 ]; then
	echo "plaese input param"
	exit
fi
echo "------> execute func $1"

function cpu(){
	echo "ip --> $2 "
	pids=$(ssh root@$2 'cat /proc/cpuinfo | grep "physical id" | sort | uniq | wc -l')
	echo "physical  --> $pids"
	cores=$(ssh root@$2 'cat /proc/cpuinfo | grep "cpu cores" | wc -l')
	echo "cpu cores --> $cores"
	proc=$(ssh root@$2 'cat /proc/cpuinfo | grep "processor" | wc -l')
	echo "processor --> $proc"
	issue=$(ssh root@$2 'head -n 1 /etc/issue')
	echo "linux sys --> $issue"
}
[ $1 == '-cpu' ] && cpu $*


function stat(){
	echo "ip --> $2 "
	free=$(ssh root@$2 free -m|sed -n '3p'|awk '{print "used --> "$3"\nfree --> "$4"\nfree%--> "$4/($4+$3)*100"%"}')
	echo "$free"
}
[ $1 == '-stat' ] && stat $*

