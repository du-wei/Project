namespace java com.webapp.remoting.service.thrift

/**
 * 搜索参数模型
 */
struct ParaDto {
	1:byte bytes;
	2:bool gender;
	3:i16 id;
	4:i32 listId;
	5:i64 longId;
	6:double money;
	7:string name;
	8:list<string> interest;
	9:map<i32,string> maps;
	10:set<string> sets;
}

/**
 * Thrift服务接口定义
 */
service ThriftService {
	string getAll(1:string para)
}