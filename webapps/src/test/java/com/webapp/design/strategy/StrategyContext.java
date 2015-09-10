package com.webapp.design.strategy;

public class StrategyContext {

	private IStategy iStategy;

	public StrategyContext(IStategy iStategy) {
		this.iStategy = iStategy;
	}

	public void operate() {
		this.iStategy.operate();
	}

	public static void main(String[] args) {
		IStategy stategy = new IStategyOne();
		StrategyContext context = new StrategyContext(stategy);
		context.operate();
	}

}

interface IStategy {
	public void operate();
}

class IStategyOne implements IStategy {
	@Override
	public void operate() {
		System.out.println("a");
	}
}

class IStategyTwo implements IStategy {
	@Override
	public void operate() {
		System.out.println("b");
	}
}
