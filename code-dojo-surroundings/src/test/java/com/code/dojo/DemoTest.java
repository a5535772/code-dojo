package com.code.dojo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DemoTest {

	@InjectMocks
	Demo demo;

	@Test
	public void when_demo_say_hello_result_world_would_be_hello() {
		// given
		final String hello = "HELLO";
		// when
		String world = demo.sayHello();
		// then
		assertThat(world).isEqualTo(hello);
	}
}
