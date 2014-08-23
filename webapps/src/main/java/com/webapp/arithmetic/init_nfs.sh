#!/bin/bash

tip=$(ifconfig eth0 |grep 'inet addr'|awk -F ' *|:' '{print $4}')
echo "The machine IP address is $tip"

ip="192.168.88.10"
fstab="/nfs/txt.txt"

mts=(
	"$ip:/root/.ssh=/nfs/ssh"
)

# mount
#mount 192.168.88.10:/root/.ssh /nfs/ssh

# start
sed -i '/^'$ip'/d' $fstab
for mt in ${mts[@]}
do
	echo $mt
	sed -i '$a '${mt%=*}'\t'${mt#*=}'\t\tnfs\tdefaults\t0 0' $fstab
done

grep $ip $fstab