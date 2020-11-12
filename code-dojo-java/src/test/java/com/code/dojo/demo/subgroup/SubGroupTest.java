package com.code.dojo.demo.subgroup;

import java.io.FileNotFoundException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SubGroupTest {
	@InjectMocks
	SubGroup subGroup;

	static final String FILE_PATH = "20201022.txt";

	@Test
	public void test() throws FileNotFoundException {
		final String path = Thread.currentThread().getContextClassLoader().getResource(FILE_PATH).getPath();
		System.out.println(subGroup.devide(path));
	}
}
