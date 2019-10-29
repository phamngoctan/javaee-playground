package com.javaee.cdi.instancequalifier;

import com.javaee.cdi.instancequalifier.annotation.Business;

@Business
public class BusinessGreeting implements Greeting {

	@Override
	public String greet(String name) {
		return "Good morning " + name;
	}

}
