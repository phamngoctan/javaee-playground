package com.javaee.cdi.instancequalifier;

import com.javaee.cdi.instancequalifier.annotation.Personal;

//@Personal
public class PersonalGreeting implements Greeting {

	@Override
	public String greet(String name) {
		return "Hello " + name;
	}

}
