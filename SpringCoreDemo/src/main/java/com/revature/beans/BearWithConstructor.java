package com.revature.beans;

public class BearWithConstructor extends Bear {
	
	private int id;
	private String name;
	private Cave cave;
	
	public BearWithConstructor(Cave cave) {
		super();
		this.cave= cave;
	}
	
	@Override
	public String toString() {
		return "BearWithContructor [id=" + id + ", name=" + name + ", cave=" + cave + "]";
	}
	
	
	@Override
	public void methodInBear() {
		System.out.println("method in BearWithConstructor. this bear is: " + toString());
	}

}
