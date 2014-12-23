/**
 *
 */
package com.webapp.thrift.generator.test;

import org.junit.Test;

import com.webapp.thrift.generator.builder.ThriftFileBuilder;
import com.webapp.thrift.generator.test.ICommonUserService;

/**
 * @author hongliuliao
 *
 * createTime:2012-12-6 下午3:24:05
 */
public class ThriftStructBuilderTest {

	private ThriftFileBuilder fileBuilder = new ThriftFileBuilder();

	@Test
	public void toOutputstream() throws Exception {
		this.fileBuilder.buildToOutputStream(ICommonUserService.class, System.out);
	}

}
