package com.webapp.sql;

public class Msql {

	// mysql common
	// 1 show databases;
	// 2 show tables from db;
	// 3 desc king;
	// 4 show columns from king;
	// 4 show index from king;
	// 4 show table status from db
	// 5 mysql -h 192.168.0.240 -u order -p
	// 6 show character set like 'xxx%'
	// 7 show collation like 'xxx%'
	// 8 show variables like 'xx'
	// 9 show warnings
	// 10 show create table king;
	// 11 shwo create database db;
	// 12 explain select * from king;
	// 13 grant all on *.* to root@'%'
	// 14 limit 3 offset 4
	// 15 where name regexp 'a$' 正则匹配 不区分大小写
	// 16 where name regexp binary 'a$' 区分
	// 17 select concat(name, ' - ', age) from table
	// select id, if(name is null, '-', name) as name from table
	// select id, ifnull(name ,'-') as name from table
	// 18 trim() ltrim() rtrim() upper() lower() substring() length() left()
	// right() locate() substring_index()

	// show status like 'Com%'
	// show global status like 'Com%'
	// show status like 'connections'
	// show status like 'uptime'
	// show status like 'slow_queries'
	// show variables like 'long_query_time'
	// select user(), charset(user()), collation(user())

	// insert into king (name) as select name from table
	// create table king (name varchar(20)) as select name from table
	//
	//
	// select date_format(date, 'year - > (%Y)-%y | month -> %M-%b-(%m-%c) | day
	// -> (%d-%e)-%W | time -> %r-%T-(%H-%i-%s) | %% '),
	// time_format(date, '%H-%i-%s'),
	// date_format(date, '%Y-%m-%d'),
	// date_format(date, '%Y-%c-%e') from city
	// curdate() now()
	// year() month() monthname() dayofmonth() dayname() dayofweek() weekday()
	// dayofyear() hour() minute() second()
	// maketime() makedate() concat(year(), month(), dayofmonth())
	// time_to_sec() sec_to_time() to_days() from_days() unix_timestamp()
	// from_unixtime()
	// datediff() timediff() timestampdiff()
	// addtime() timestamp() date_add() date_sub()

	// count(*) = select table_rows from information_schema.`TABLES` where
	// TABLE_SCHEMA = 'database' and TABLE_NAME = 'table';
}
