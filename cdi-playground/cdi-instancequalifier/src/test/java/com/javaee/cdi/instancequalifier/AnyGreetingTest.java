package com.javaee.cdi.instancequalifier;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Instance;
import javax.enterprise.util.AnnotationLiteral;
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
public class AnyGreetingTest {

	@Deployment
	public static Archive<?> deploy() {
		return ShrinkWrap.create(JavaArchive.class)
				.addClasses(Greeting.class, PersonalGreeting.class, BusinessGreeting.class, Business.class, Personal.class)
				.addAsManifestResource("beans.xml");
	}

	/**
	 * Built-in qualifier @Any is assumed on each bean regardless other qualifiers specified.
	 */
	@Inject @Any
	private Instance<Greeting> instance;

	@Test
	public void test() throws Exception {
		assertFalse(instance.isUnsatisfied());
		assertTrue(instance.isAmbiguous());

		// use Instance<T>#select()
		Instance<Greeting> businessInstance = instance.select(new AnnotationLiteral<Business>() {});
		Greeting businessBean = businessInstance.get();
		assertThat(businessBean, instanceOf(BusinessGreeting.class));
		businessInstance.destroy(businessBean);

		Instance<Greeting> defaultInstance = instance.select(new AnnotationLiteral<Default>() {});
		Greeting defaultBean = defaultInstance.get();
		assertThat(defaultBean, instanceOf(PersonalGreeting.class));
		defaultInstance.destroy(defaultBean);
	}
	
}
