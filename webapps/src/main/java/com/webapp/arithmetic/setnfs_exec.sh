#!/bin/bash

if [ $# == 0 ]; then
	echo "please input para"
	exit
elif [ -f $1 ]; then
	ips=$(grep ".*" $1)
else
	ips=($@)
fi

declare upwd "kingadmin"
declare rpwd "kingadmin"
declare file "/nfs/setnfs_expect.sh"

for ip in ${ips[@]}
do
	echo $ip
	# para --> user_host userpwd nfssource nfstarget rootpwd
	expect $file king@$ip $upwd 192.168.88.10:/home/king/.ssh /nfs/ssh $rpwd
done

