namespace java com.webapp.remoting.service.thrift

/**
 * 搜索参数模型
 */
struct ParaDto {
	1:i32 id;
	2:string name;
	3:bool gender;
	4:byte bytes;
	5:double money;
	6:list<string> interest;
	7:map<i32,string> maps;
	8:set<string> sets;
}

/**
 * Thrift服务接口定义
 */
service ThriftService {
	string getAll(1:string para)
}