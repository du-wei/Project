#!/bin/bash

ifcfg="/etc/sysconfig/network-scripts/ifcfg-eth0"
resolv="/etc/resolv.conf"
dns="DNS"
ip=$1
ip1="${dns}1=$ip"

dc=$(grep "^$dns" $ifcfg |wc -l)
hdns=$(grep "^$dns.*=$ip" $ifcfg |wc -l)

echo " ----------set ifcfg-eth0---------- "
if [ $hdns == 0 ]; then
	# change dns name
	if grep -q '^DNS1' $ifcfg; then
		while grep -q $dns$dc $ifcfg
		do
			echo "$dns$dc --> $dns$(( $dc + 1 ))"
			eval sed -i 's/^$dns$dc/$dns$(( $dc + 1 ))/' $ifcfg
			dc=$(( $dc - 1 ))
		done
	fi

	# insert dns1
	hdns2=$(grep "DNS2" $ifcfg)
	if [ $hdns != "" ] && [ $hdns2 != "" ]; then
		echo "insert $ip1"
		sed -i '/^DNS2/i '$ip1 $ifcfg
	else
		sed -i '$a '$ip1 $ifcfg
	fi
fi

cat $ifcfg |grep "^DNS"

echo " ----------set resolv.conf---------- "
ns=$(grep "$ip" $resolv |wc -l)

if [ $ns == 0 ]; then
	sed -i "1a nameserver $ip" $resolv
fi

cat $resolv |grep "name"



