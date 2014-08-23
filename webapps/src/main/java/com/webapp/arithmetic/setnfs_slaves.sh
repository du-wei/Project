#!/bin/bash

# para --> nfssource nfstarget path

declare fstab="/etc/fstab"
declare nfsf=$1
declare nfst=$2
declare path=$3
declare ip=${nfsf%:*}

mts=(
	"$nfsf=$nfst"
)

# start
sed -i '/^'$ip'/d' $fstab
for mt in ${mts[@]}
do
	mount ${mt%=*} ${mt#*=}
	sed -i '$a '${mt%=*}'\t'${mt#*=}'\t\tnfs\tdefaults\t0 0' $fstab
done

#grep $ip $fstab

[ ! -f $path/.ssh/id_rsa.pub ] && ssh-keygen -t rsa -P '' -f $path/.ssh/id_rsa > /dev/null
cat $path/.ssh/id_rsa.pub >> $nfst/authorized_keys
rm -rf $path/.ssh/authorized_keys
ln -s $nfst/authorized_keys $path/.ssh/authorized_keys
