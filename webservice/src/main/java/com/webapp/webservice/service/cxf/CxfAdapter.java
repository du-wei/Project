package com.webapp.webservice.service.cxf;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.webapp.webservice.service.cxf.CxfAdapterImpl.MapImpl;

/**
 * @ClassName: CxfAdapter.java
 * @Package com.webapp.webservice.service.cxf
 * @Description: TODO 转换器
 * @author king king
 * @date 2014年1月1日 下午8:24:02
 * @version V1.0
 */
public class CxfAdapter extends
		XmlAdapter<CxfAdapterImpl, Map<String, CxfUser>> {

	@Override
	public Map<String, CxfUser> unmarshal(CxfAdapterImpl v) throws Exception {

		Map<String, CxfUser> map = new HashMap<>();

		for (MapImpl mapImpl : v.getEntries()) {
			map.put(mapImpl.getKey(), mapImpl.getVal());
		}
		return map;
	}

	@Override
	public CxfAdapterImpl marshal(Map<String, CxfUser> v) throws Exception {
		CxfAdapterImpl mapImpl = new CxfAdapterImpl();

		for (String key : v.keySet()) {
			mapImpl.getEntries().add(new MapImpl(key, v.get(key)));
		}

		return mapImpl;
	}

}
