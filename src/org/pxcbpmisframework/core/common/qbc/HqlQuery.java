package org.pxcbpmisframework.core.common.qbc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class HqlQuery implements Query {
	public static String Restrictions_eq = "eq";
	public static String Restrictions_like = "like";
	public static String Restrictions_between = "between";

	public static Map<String, Integer> getParmsMaps() {
		return new HashMap();
	}

	public static Map<Object, String> getHashMaps() {
		return new HashMap();
	}

	public static Map<String, String> getHashMapsStr() {
		return new HashMap();
	}

	public static Map<Object, Object> getHashMapsObj() {
		return new HashMap();
	}

	public static Map<Object, String[]> getHashMapsObjs() {
		return new HashMap();
	}

	public static String getHQLList(String entityname) {
		return (" FROM " + entityname).trim();
	}

	public static String getHqlEntity(String entityname,
			Map<String, String> parms) {
		StringBuilder append = new StringBuilder();
		if ((parms.size() == 0) || (parms == null)) {
			return getHQLList(entityname);
		}
		Set<String> setkey = parms.keySet();
		for (Object key : setkey) {
			String value = (String) parms.get(key);
			append.append(key).append("='").append(value).append("' and ");
		}
		return (" FROM " + entityname + " WHERE " + append.toString()
				.substring(0, append.toString().lastIndexOf("and"))).trim();
	}

	public String getHqlEntity(String entityname, Map<String, String> parms,
			Map<String, String> miss) {
		StringBuilder append = new StringBuilder();
		if ((parms.size() > 0) || (parms != null)) {
			Set<String> setkey = parms.keySet();
			for (Object key : setkey) {
				String value = (String) parms.get(key);
				append.append(key).append("='").append(value).append("' and ");
			}
		}
		if ((miss.size() == 0) || (miss == null)) {
			return getHQLList(entityname);
		}
		Set<String> setkey = miss.keySet();
		for (Object key : setkey) {
			String value = (String) miss.get(key);
			append.append(key).append("='%").append(value).append("%' and ");
		}
		return (" FROM " + entityname + " WHERE " + append.toString()
				.substring(0, append.toString().lastIndexOf("and"))).trim();
	}

	public String RestrictionsList() {
		List<Criterion> list = new ArrayList();
		list.add(Restrictions.eq("", ""));

		return "";
	}
}
