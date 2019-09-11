package com.artilekt.bank.utils;

import java.util.Map;
import java.util.Objects;

import org.apache.commons.beanutils.PropertyUtilsBean;

public class JavaBeanMatcher {
	
	public static <T> Matcher propertiesOf(T bean) {
		return new Matcher(bean);
	}
	
	
	public  static class Matcher<A, B> {
		private A aBean;
		private PropertyUtilsBean propUtilsBean = new PropertyUtilsBean();
				
		private Matcher(A aBean) { this.aBean = aBean; }

		public boolean matchAllPropertiesOf(B bBean) {
			try { 
				Map<String, Object> propsA = propUtilsBean.describe(aBean);
				Map<String, Object> propsB = propUtilsBean.describe(bBean);
				removeNulls(propsA);
				removeNulls(propsB);
				return propsA.entrySet().containsAll(propsB.entrySet());
			}
			catch (Exception e) {
				return false;
			}
			
		}

		private Map<String, Object> removeNulls(Map<String, Object> props) {
			props.remove(null);
			props.values().removeIf(Objects::isNull);
			return props;
		}
	}
}
