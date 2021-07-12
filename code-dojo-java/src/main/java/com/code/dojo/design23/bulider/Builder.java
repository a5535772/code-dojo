package com.code.dojo.design23.bulider;

abstract class Builder {
	// 创建产品对象
	protected Product product = new Product();

	public abstract void buildPartA();

	public abstract void buildPartB();

	public abstract void buildPartC();

	// 返回产品对象
	public Product getResult() {
		return product;
	}
}
