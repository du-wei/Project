package com.webapp.webservice.service.cxf;

import java.util.ArrayList;
import java.util.List;

public class CxfAdapterImpl {

	public static class MapImpl {
		public MapImpl() {

		}

		public MapImpl(String key, CxfUser val) {
			this.key = key;
			this.val = val;
		}

		private String key;
		private CxfUser val;

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public CxfUser getVal() {
			return val;
		}

		public void setVal(CxfUser val) {
			this.val = val;
		}
	}

	private List<MapImpl> entries = new ArrayList<>();

	public List<MapImpl> getEntries() {
		return entries;
	}

	public void setEntries(List<MapImpl> entries) {
		this.entries = entries;
	}

}
