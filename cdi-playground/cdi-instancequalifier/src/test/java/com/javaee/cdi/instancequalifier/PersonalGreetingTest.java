package com.javaee.cdi.instancequalifier;

import static org.junit.Assert.assertTrue;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.javaee.cdi.instancequalifier.annotation.Business;
import com.javaee.cdi.instancequalifier.annotation.Personal;

@RunWith(Arquillian.class)
public class PersonalGreetingTest {

	@Deployment
	public static Archive<?> deploy() {
		return ShrinkWrap.create(JavaArchive.class)
				.addClasses(Greeting.class, PersonalGreeting.class, BusinessGreeting.class, Business.class, Personal.class)
				.addAsManifestResource("beans.xml");
	}

	/**
	 * Qualifier @Personal is not qualifying any bean.
	 */
	@Inject @Personal
	private Instance<Greeting> instance;

	@Test
	public void test() throws Exception {
		// no instance should be available
		assertTrue(instance.isUnsatisfied());
	}
	
}
