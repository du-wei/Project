#!/bin/bash

profile="/etc/profile"

ph=(
	# java
	'JAVA_HOME=/webapp/jdk'
	'CALSSPATH=$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar:$CLASSPATH'
	'PATH=$JAVA_HOME/bin:$PATH'
	# mysql
	'MYSQL_HOME=/usr/local/mysql'
	'PATH=$MYSQL_HOME/bin:$PATH'
	# mongodb
	'MONGODB_HOME=/webapp/mongodb'
	'PATH=$MONGODB_HOME/bin:$PATH'
	# redis
	'REDIS_HOME=/webapp/redis'
	'PATH=$REDIS_HOME/src:$PATH'
	# hadoop
	'HADOOP_HOME=/webapp/hadoop'
	'PATH=$HADOOP_HOME/bin:$HADOOP_HOME/sbin:$PATH'
	# hbase
	'HBASE_HOME=/webapp/hbase'
	'PATH=$HBASE_HOME/bin:$PATH'
	# hive
	'HIVE_HOME=/webapp/hive'
	'PATH=$HIVE_HOME/bin:$PATH'
	'CALSSPATH=$HIVE_HOME/lib:$CLASSPATH'
)

sed -i '/^export/d' $profile

for p in ${ph[@]}
do
	sed -i '$a export '$p $profile
done

grep '^export' $profile

source $profile
