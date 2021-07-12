package com.code.dojo.design23.bulider;

public class Client {
	public static void main(String[] args) {
		Builder builder = new ConcreteBuilder();
		Director director = new Director(builder);
		Product product = director.construct();
		product.show();
	}
}