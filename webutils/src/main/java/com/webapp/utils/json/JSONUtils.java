package com.webapp.utils.json;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.AfterFilter;
import com.alibaba.fastjson.serializer.BeforeFilter;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.NameFilter;
import com.alibaba.fastjson.serializer.PascalNameFilter;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.webapp.utils.string.Utils;

/**
* @ClassName: JSONUtils.java
* @Package com.webap.utils.json
* @Description: JSON序列号工具完整版
* @author  king king
* @date 2014年10月13日 下午9:41:36
* @version V1.0
*/
public class JSONUtils {

    private Object jsonObj;

    private JSONSerializer jsonSerializer;

    private JSONUtils(Object object) {
    	this.jsonObj = object;

    	SerializeWriter writer = new SerializeWriter();
    	jsonSerializer = new JSONSerializer(writer);
    }

	public static JSONUtils of(Object object) {
		return new JSONUtils(object);
	}

	/**
	 * For example
     * <pre>JSONUtils.toString(object) -> String </pre>
	 * @param object
	 * @return JSON.toJSONString(object)
	 */
	public static String toString(Object object) {
		return JSON.toJSONString(object);
	}

	/**
	 * For example
     * <pre>
     * Obj class {int user_id=1;}
     * JSONUtils.of(new Obj()).toCamelKey() -> {userId:1}   </pre>
	 * @return Change after the json string
	 */
	public String toCamelKey() {
		jsonSerializer.getNameFilters().add(new NameFilter() {
			public String process(Object object, String name, Object value) {
				return Utils.toCamel(name);
			}
		});
		return toString();
	}

	/**
	 * For example
     * <pre>
     * Obj class {int userId=1;}
     * JSONUtils.of(new Obj()).toPascalKey() -> {UserId:1}   </pre>
	 * @return Change after the json string
	 */
	public String toPascalKey() {
		jsonSerializer.getNameFilters().add(new PascalNameFilter());
		return toString();
	}

	/**
	 * For example
     * <pre>
     * Obj class {int userId=1;}
     * JSONUtils.of(new Obj()).toUnderlineKey() -> {user_id:1}   </pre>
	 * @return Change after the json string
	 */
	public String toUnderlineKey() {
		jsonSerializer.getNameFilters().add(new NameFilter() {
			public String process(Object object, String name, Object value) {
				return Utils.toUnderline(name);
			}
		});
		return toString();
	}

	/**
	 * @return json string
	 */
	public String toString() {
		jsonSerializer.write(jsonObj);
		return jsonSerializer.toString();
	}

	/**
	 * @return format json string
	 */
	public String toPrettyString() {
    	jsonSerializer.config(SerializerFeature.PrettyFormat, true);
		return toString();
	}

	/**
	 * For example
     * <pre>
     * Obj class {int id1=1, id2=2;}
     * JSONUtils.of(new Obj()).toBeanToArray() -> [1,2]   </pre>
	 * @return value array
	 */
	public String toBeanToArray() {
    	jsonSerializer.config(SerializerFeature.BeanToArray, true);
		return toString();
	}

	/**
	 * For example
     * <pre>
     * Obj class {int id=1;}
     * JSONUtils.of(new Obj()).before("b", "v").toString() -> {b:v, id:1}   </pre>
	 * @return this
	 */
	public JSONUtils before(final String key, final Object value) {
		jsonSerializer.getBeforeFilters().add(new BeforeFilter() {
			public void writeBefore(Object object) {
				this.writeKeyValue(key, value);
			}
		});
		return this;
	}

	/**
	 * For example
     * <pre>
     * Obj class {int id=1;}
     * JSONUtils.of(new Obj()).after("a", "v").toString() -> {id:1, a:f}   </pre>
	 * @return this
	 */
	public JSONUtils after(final String key, final Object value) {
		jsonSerializer.getAfterFilters().add(new AfterFilter() {
			public void writeAfter(Object object) {
				this.writeKeyValue(key, value);
			}
		});
		return this;
	}

	/**
	 * For example
     * <pre>
     * Obj class {int id=1; String name="2";}
     * JSONUtils.of(new Obj()).include("id").toString() -> {id:1}   </pre>
	 * @return this
	 */
	public JSONUtils include(String... includes) {
		SimplePropertyPreFilter filter = new SimplePropertyPreFilter(includes);
		jsonSerializer.getPropertyPreFilters().add(filter);
		return this;
	}

	/**
	 * For example
     * <pre>
     * Obj class {int id=1; String name="2";}
     * JSONUtils.of(new Obj()).exclude("name").toString() -> {id:1}   </pre>
	 * @return this
	 */
	public JSONUtils exclude(String... excludes) {
		SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
		filter.getExcludes().addAll(Arrays.asList(excludes));
		jsonSerializer.getPropertyPreFilters().add(filter);
		return this;
	}

	/**
	 * For example
     * <pre>
     * Obj class {int id=1;}
     * JSONUtils.of(new Obj()).modifyKey("id", "ok").toString() -> {ok:1}   </pre>
	 * @return this
	 */
	public JSONUtils modifyKey(final String key, final String replacement) {
		jsonSerializer.getNameFilters().add(new NameFilter() {
			public String process(Object object, String name, Object value) {
				return key.equals(name) ? replacement : name;
			}
		});
		return this;
	}

	/**
	 * For example
     * <pre>
     * Obj class {int id=1;}
     * JSONUtils.of(new Obj()).modifyVal("id", "2").toString() -> {id:2}   </pre>
	 * @return this
	 */
	public JSONUtils modifyVal(final String key, final Object replacement) {
		jsonSerializer.getValueFilters().add(new ValueFilter() {
			String orgKey = key;
			List<NameFilter> nameFilters = jsonSerializer.getNameFilters();
			public Object process(Object object, String name, Object value) {
				for(NameFilter filter : nameFilters){
					orgKey = filter.process(object, orgKey, value);
				}
				return orgKey.equals(name) ? replacement : value;
			}
		});
		return this;
	}

	/**
	 * For example
     * <pre>
     * Obj class {int id=1;}
     * JSONUtils.of(new Obj()).modifyVal("id", "\d", "2").toString() -> {id:2}   </pre>
	 * @return this
	 */
	public JSONUtils modifyVal(final String key, final String regexVal, final String replacement) {
		jsonSerializer.getValueFilters().add(new ValueFilter() {
			String orgKey = key;
			List<NameFilter> nameFilters = jsonSerializer.getNameFilters();
			public Object process(Object object, String name, Object value) {
				for(NameFilter filter : nameFilters){
					orgKey = filter.process(object, orgKey, value);
				}
				if(orgKey.equals(name) && String.valueOf(value).matches(regexVal)){
					value = replacement;
				}
				return value;
			}
		});
		return this;
	}

	/**
	 * For example
     * <pre>
     * Obj class {int id=1;}
     * JSONUtils.of(new Obj()).filterVal("id", "\d").toString() -> {id:2}   </pre>
	 * @return this
	 */
	public JSONUtils filterVal(final String key, final String regexVal) {
		PropertyFilter filter = new PropertyFilter() {
			public boolean apply(Object object, String name, Object value) {
				return key.equals(name) ? String.valueOf(value).matches(regexVal) :true;
			}
		};
		jsonSerializer.getPropertyFilters().add(filter);
		return this;
	}

	/**
	 * For example
     * <pre>JSONUtils.of(object).dateFormat("yyyy-MM-dd HH:mm:ss").toString() -> {date:2000-10-10 10:10:10}   </pre>
	 * @return this
	 */
	public JSONUtils dateFormat(String format) {
    	jsonSerializer.setDateFormat(new SimpleDateFormat(format));
    	return this;
	}

	public <T> JSONUtils doubleFormat(final String pattern, final Class<T> clz){
		jsonSerializer.getValueFilters().add(new ValueFilter() {
			DecimalFormat format = new DecimalFormat(pattern);
			List<NameFilter> nameFilters = jsonSerializer.getNameFilters();

			public Object process(Object object, String name, Object value) {
				Field[] fields = clz.getDeclaredFields();
				for(Field field : fields){
					if(field.getType().getSimpleName().equalsIgnoreCase(double.class.getSimpleName())){
						String key = field.getName();
						for(NameFilter filter : nameFilters){
							key = filter.process(null, key, null);
						}
						if(key.equals(name)) return format.format(value);
					}
				}
				return value;
			}
		});
		return this;
	}

	/**
	 * For example
     * <pre>
     * Obj class {String nation="中国";}
     * JSONUtils.of(new Obj()).toCompatible().toString() -> {nation:\u4E2D\u56FD}
     * </pre>
	 * @return this
	 */
	public JSONUtils toCompatible() {
    	jsonSerializer.config(SerializerFeature.BrowserCompatible, true);
    	return this;
    }

    public JSONUtils toSort() {
    	jsonSerializer.config(SerializerFeature.SortField, true);
    	return this;
    }

    /**
	 * For example
     * <pre>
     * Obj class {int i;}
     * JSONUtils.of(new Obj()).toNullNumAsZero().toString() -> {i:0}
     * </pre>
	 * @return this
	 */
    public JSONUtils toNullNumAsZero() {
    	jsonSerializer.config(SerializerFeature.WriteNullNumberAsZero, true);
    	return this;
    }

    /**
	 * For example
     * <pre>
     * Obj class {boolean i;}
     * JSONUtils.of(new Obj()).toNullBoolAsFalse().toString() -> {i:false}
     * </pre>
	 * @return this
	 */
    public JSONUtils toNullBoolAsFalse() {
    	jsonSerializer.config(SerializerFeature.WriteNullBooleanAsFalse, true);
    	return this;
    }

    /**
	 * For example
     * <pre>
     * Obj class {String i;}
     * JSONUtils.of(new Obj()).toNullStrAsEmpty().toString() -> {}
     * </pre>
	 * @return this
	 */
    public JSONUtils toNullStrAsEmpty() {
    	jsonSerializer.config(SerializerFeature.WriteNullStringAsEmpty, false);
    	return this;
    }

    public JSONUtils toNonStrKeyAsStr() {
    	jsonSerializer.config(SerializerFeature.WriteNonStringKeyAsString, true);
    	return this;
    }

    /**
	 * For example
     * <pre>
     * Obj class {Map i;}
     * JSONUtils.of(new Obj()).toMapNullValue().toString() -> {}
     * </pre>
	 * @return this
	 */
    public JSONUtils toMapNullValue() {
    	jsonSerializer.config(SerializerFeature.WriteMapNullValue, true);
    	return this;
    }

    /**
	 * For example
     * <pre>
     * Obj class {List i;}
     * JSONUtils.of(new Obj()).toNullListAsEmpty().toString() -> {}
     * </pre>
	 * @return this
	 */
    public JSONUtils toNullListAsEmpty() {
    	jsonSerializer.config(SerializerFeature.WriteNullListAsEmpty, true);
    	return this;
    }

}
