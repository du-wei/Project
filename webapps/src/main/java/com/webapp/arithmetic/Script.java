package com.webapp.arithmetic;

/** @ClassName: Script.java
 * @Package com.webapp.arithmetic
 * @Description: TODO 类型描述
 * @author king chenglong@coweibo.cn
 * @date 2012-11-23 下午4:44:06
 * @version V1.0 */
public class Script {

	// sort
	// -u 去重
	// -r 降序
	// -o 输出到源文件
	// -n 以数值排序
	// -t 指定列和列之间的分隔符
	// -k 指定用哪列来排序

	// -f会将小写字母都转换为大写字母来进行比较，亦即忽略大小写
	// -c会检查文件是否已排好序，如果乱序，则输出第一个乱序的行的相关信息，最后返回1
	// -C会检查文件是否已排好序，如果乱序，不输出内容，仅返回1
	// -M会以月份来排序，比如JAN小于FEB等等
	// -b会忽略每一行前面的所有空白部分，从第一个可见字符开始比较。

	// -c 只输出匹配行的计数。
	// -i 不区分大小写（只适用于单字符）。
	// -h 查询多文时不显示文名。
	// -l 查询多文时只输出包含匹配字符的文名。
	// -n 显示匹配行及行号。
	// -s 不显示不存在或无匹配文本的错误信息。
	// -v 显示不包含匹配文本的所有行。

	// grep -options "regular" file
	// egrep -f regularfile file

	// sed [options] '{command}' {filename}

	// 替换 sed 's/old/new/' file
	// 多次修改 sed 's/old/new/; s/old/new/' file
	// 全局修改 sed 's/old/new/g' file
	// 指定行修改 sed '/contain/ s/old/new/' file
	// 限制行 sed '4,5 s/old/new/' file
	// 执行文件 sed -f script file
	// 显示影响的行sed 's/old/new/p' file
	// 显示指定的行sed -n '2,6p' file

	// 删除行 sed '/value/d' file 可用!反删除
	// 从显示中删除sed '1,2 d' file

	// 末尾添加行 sed '$a\ok\hello' file \表示换行
	// 指定添加行 sed '3a\ok\hello' file
	// 指定插入行 sed '3i\ok\hello' file

	// 写出文件 sed 's/old/new 1,3 w newfile' file 将1到3行写如newfile中

	// 修改行 sed '/value/ c\hello' file 整行都被修改

	// 提前退出 sed 's/old/new/ 5q' file sed 's/old/new/; /ok/q' file

	// printf("%i", 20)
	// %c ascii ("%c", 65) -->A
	// %d %i int
	// %f float
	// %s string
	// %u +- -->+ int
	// %% -->%

	// printf("%2$s %1$s", "am", "I") --> I am
	// 1$ 位置定位符
	// - 左对齐
	// 空格 数字转换 正数加空格 负数加减号
	// + 数字加正负号
	// 0 宽度小时 用0填充
	// width eg +1 "ab " -2 "  ab"
	// .1 控制小数点后的位数 or 控制输出最多的字符数
	// * 动态width 根据最大位数确定其宽度

	// String fun gawk

	// close(FILENAME | COMMAND)
	// asort() a['last']="de" a["first"]="sac" --> a[1]="de" a[2]="sac"
	// index("str", "t") --> 2 if empty --> 0
	// length("str") --> 3 if empty length($0)
	// match(str, regex) --> position
	// split("a-b-c", a, "-") --> a[1]="a"
	// sprintf("%.1f", 22) --> 22.0
	// sub(/a/, "b", "ac") --> bc
	// gsub(/a/, 'b') 匹配所有
	// substr(str, start, [length])
	// tolower(str)
	// toupper(str)

	// fflush(FILENAME) 清除缓存
	// system() 执行系统命令

	// systime() 返回秒
	// mktime(YYYY MM DD HH MM SS [DST]) date->秒
	// strftime()

	// function(){}

}
