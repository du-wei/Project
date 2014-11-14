package com.webapp.design.strategy;

public class Cat implements Comparable<Cat> {

	private int height;
	private int weight;

	// private Comparator<Cat> comparator = new CatHeightComparator();

	public Cat(int height, int weight) {
		super();
		this.height = height;
		this.weight = weight;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	// public Comparator<Cat> getComparator() {
	// return comparator;
	// }
	//
	// public void setComparator(Comparator<Cat> comparator) {
	// this.comparator = comparator;
	// }

	@Override
	public int compareTo(Cat o) {
		if (this.getHeight() > o.getHeight()) return 1;
		else if (this.getHeight() < o.getHeight()) return -1;
		else return 0;
		// return comparator.compare(this, o);
	}

}
