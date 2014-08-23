/**   
 * @Title: MappingJacksonJsonViews.java 
 * @Package com.webapp.bug 
 * @Description: TODO 描述
 * @author king chenglong@coweibo.cn 
 * @date 2013-2-17 上午10:59:30 
 * @version V1.0   
 */
package com.webapp.bug;

import java.util.Map;

import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

/**
 * @ClassName: MappingJacksonJsonViews.java
 * @Package com.webapp.bug
 * @Description: TODO 类型描述
 * @author king chenglong@coweibo.cn
 * @date 2013-2-17 上午10:59:30
 * @version V1.0
 */
public class MappingJacksonJsonViews extends MappingJacksonJsonView {

	protected Object filterModel(Map<String, Object> model) {
		Map<?, ?> result = (Map<?, ?>) super.filterModel(model);
		if (result.size() == 1) {
			return result.values().iterator().next();
		} else {
			return result;
		}
	}
}
