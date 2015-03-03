package org.pxcbpmisframework.core.util;

import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReflectHelper {
	private static final Logger logger = LoggerFactory
			.getLogger(ReflectHelper.class);
	private Class<?> cls;
	private Object obj;
	private Hashtable<String, Method> getMethods = null;
	private Hashtable<String, Method> setMethods = null;

	public ReflectHelper(Object o) {
		this.obj = o;
		initMethods();
	}

	public void initMethods() {
		this.getMethods = new Hashtable();
		this.setMethods = new Hashtable();
		this.cls = this.obj.getClass();
		Method[] methods = this.cls.getMethods();

		String gs = "get(\\w+)";
		Pattern getM = Pattern.compile(gs);
		String ss = "set(\\w+)";
		Pattern setM = Pattern.compile(ss);

		String rapl = "$1";
		for (int i = 0; i < methods.length; i++) {
			Method m = methods[i];
			String methodName = m.getName();
			if (Pattern.matches(gs, methodName)) {
				String param = getM.matcher(methodName).replaceAll(rapl)
						.toLowerCase();
				this.getMethods.put(param, m);
			} else if (Pattern.matches(ss, methodName)) {
				String param = setM.matcher(methodName).replaceAll(rapl)
						.toLowerCase();
				this.setMethods.put(param, m);
			}
		}
	}

	public boolean setMethodValue(String property, Object object) {
		Method m = (Method) this.setMethods.get(property.toLowerCase());
		if (m != null) {
			try {
				m.invoke(this.obj, new Object[] { object });
				return true;
			} catch (Exception ex) {
				logger.info("invoke getter on " + property + " error: "
						+ ex.toString());
				return false;
			}
		}
		return false;
	}

	public Object getMethodValue(String property) {
		Object value = null;
		Method m = (Method) this.getMethods.get(property.toLowerCase());
		if (m != null) {
			try {
				value = m.invoke(this.obj, new Object[0]);
			} catch (Exception ex) {
				logger.info("invoke getter on " + property + " error: "
						+ ex.toString());
			}
		}
		return value;
	}
}
