package com.javaee.cdi.instancequalifier;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

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
public class GreetingTest {

	@Deployment
	public static Archive<?> deploy() {
		return ShrinkWrap.create(JavaArchive.class)
				.addClasses(Greeting.class, BusinessGreeting.class, PersonalGreeting.class, Business.class, Personal.class)
				.addAsManifestResource("beans.xml");
	}

	/**
	 * Container will assume built-in @Default qualifier here as well as for beans that don't declare a qualifier.
	 */
	@Inject
	private Instance<Greeting> instance;

	@Test
	public void test() throws Exception {
		assertFalse(instance.isUnsatisfied());
		assertFalse(instance.isAmbiguous());

		// use Instance<T>#get()
		Greeting bean = instance.get();
		assertThat(bean, instanceOf(PersonalGreeting.class));
		instance.destroy(bean);

		// use Instance<T>#select()
		Instance<Greeting> anotherInstance = instance.select(new AnnotationLiteral<Default>() {
		});
		Greeting anotherBean = anotherInstance.get();
		assertThat(anotherBean, instanceOf(PersonalGreeting.class));
		anotherInstance.destroy(anotherBean);
	}
	
}
