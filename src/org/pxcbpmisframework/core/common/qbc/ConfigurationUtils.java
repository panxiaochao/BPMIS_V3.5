package org.pxcbpmisframework.core.common.qbc;import org.hibernate.cfg.Configuration;import org.springframework.context.ApplicationContext;import org.springframework.context.support.ClassPathXmlApplicationContext;public class ConfigurationUtils {	private static final String DEFAULT_HIBERNATE_CFG = "/hibernate.cfg.xml";	private static final String DEFAULT_APPLILICATIONCOTEXT_CFG = "/applicationContext.xml";	public static Configuration getHibernateConfig() {		return new Configuration().configure("/hibernate.cfg.xml");	}	public static ApplicationContext getApplicationContextConfig() {		return new ClassPathXmlApplicationContext("/applicationContext.xml");	}}