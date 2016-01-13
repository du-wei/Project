
客户端：mysql, mysqladmin, mysqldump, mysqlimport, mysqlcheck
服务端：mysqld, mysqld_safe, mysqld_multi

[client]
default-character-set=utf8

[mysqld]
## charset ##
character_set_server=utf8
collation-server=utf8_general_ci

## basic settings##
port = 3306
user = mysql
bind_address = 10.166.224.32
datadir = /data/mysql_data
pid-file = /apps/data/mysql/mysqld.pid
tmpdir = /tmp
slow_query_log=on

## network ##
max_connections = 800
max_connect_errors = 1000
max_allowed_packet = 16777216

## global cache ##
read_buffer_size = 16777216
read_rnd_buffer_size = 33554432
sort_buffer_size = 33554432
join_buffer_size = 134217728

sql_mode=NO_ENGINE_SUBSTITUTION,STRICT_TRANS_TABLES



## memlock
transaction_isolation = READ-COMMITTED

## tmp table ##
tmp_table_size = 67108864

autocommit = 0
skip_name_resolve = 1
explicit_defaults_for_timestamp = 1
interactive_timeout = 1800
wait_timeout = 1800

########log settings########
log_error = error.log
slow_query_log = 1
slow_query_log_file = slow.log
log_queries_not_using_indexes = 1
log_slow_admin_statements = 1
log_slow_slave_statements = 1
log_throttle_queries_not_using_indexes = 10
expire_logs_days = 90
long_query_time = 2
min_examined_row_limit = 100
########replication settings########
master_info_repository = TABLE
relay_log_info_repository = TABLE
log_bin = bin.log
sync_binlog = 1
gtid_mode = on
enforce_gtid_consistency = 1
log_slave_updates
binlog_format = row
relay_log = relay.log
relay_log_recovery = 1
binlog_gtid_simple_recovery = 1
slave_skip_errors = ddl_exist_errors
########innodb settings########
innodb_page_size = 8192
innodb_buffer_pool_size = 6G
innodb_buffer_pool_instances = 8
innodb_buffer_pool_load_at_startup = 1
innodb_buffer_pool_dump_at_shutdown = 1
innodb_lru_scan_depth = 2000
innodb_lock_wait_timeout = 5
innodb_io_capacity = 4000
innodb_io_capacity_max = 8000
innodb_flush_method = O_DIRECT
innodb_file_format = Barracuda
innodb_file_format_max = Barracuda
innodb_log_group_home_dir = /redolog/
innodb_undo_directory = /undolog/
innodb_undo_logs = 128
innodb_undo_tablespaces = 3
innodb_flush_neighbors = 1
innodb_log_file_size = 4G
innodb_log_buffer_size = 16777216
innodb_purge_threads = 4
innodb_large_prefix = 1
innodb_thread_concurrency = 64
innodb_print_all_deadlocks = 1
innodb_strict_mode = 1
innodb_sort_buffer_size = 67108864
 