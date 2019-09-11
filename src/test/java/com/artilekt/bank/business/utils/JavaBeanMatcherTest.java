package com.artilekt.bank.business.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.artilekt.bank.business.Client;
import com.artilekt.bank.utils.JavaBeanMatcher;

public class JavaBeanMatcherTest {

	@Test
	public void testBeanMatcher() {
		Client cl1 = new Client("Vlad", null, "a111");
		Client cl2 = new Client("Vlad", null, null);
		
		assertTrue(JavaBeanMatcher.propertiesOf(cl1).matchAllPropertiesOf(cl2));
		assertFalse(JavaBeanMatcher.propertiesOf(cl2).matchAllPropertiesOf(cl1));
	}
}
