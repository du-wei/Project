package com.webapp.database;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.webapp.utils.WebBeanUtils;

public class MultiDataSource extends AbstractRoutingDataSource {

	private static final Logger logger = LoggerFactory.getLogger(MultiDataSource.class);
	private static boolean isCheck = true;
	private static Object defDataSource = null;
	private static final ThreadLocal<Object> dataSourceKey = new InheritableThreadLocal<Object>();

	public static Object getDefDataSource() {
		return defDataSource;
	}
	public static void setDefDataSource() throws Throwable {
		if(isCheck && defDataSource == null){
			AbstractRoutingDataSource bean = WebBeanUtils.getBean(AbstractRoutingDataSource.class);
			Class<?> multiDs = bean.getClass().getSuperclass();
			
			Field field = multiDs.getDeclaredField("defaultTargetDataSource");
			field.setAccessible(true);
			Object defDs = field.get(bean);
			
			Field field1 = multiDs.getDeclaredField("targetDataSources");
			field1.setAccessible(true);
			@SuppressWarnings("unchecked")
            Map<Object, Object> allDs = (Map<Object, Object>)field1.get(bean);
			
			Iterator<Entry<Object, Object>> iterator = allDs.entrySet().iterator();
			while(iterator.hasNext()){
				Entry<Object, Object> next = iterator.next();
				if(next.getValue() == defDs){
					String defKey = next.getKey().toString();
					defDataSource = defKey;
				}
			}
			
		}
		if(defDataSource == null){
			isCheck = false;
			logger.warn("多数据源未配置defaultTargetDataSource");
		}else {
			dataSourceKey.set(defDataSource);
		}
	}

	public static void setDataSourceKey(String dataSource) {
        dataSourceKey.set(dataSource);
    }

    public static Object getDatasourcekey() {
		return dataSourceKey.get();
	}
	@Override
    protected Object determineCurrentLookupKey() {
        return dataSourceKey.get();
    }
	
}
