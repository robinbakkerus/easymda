package com.example.model;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public abstract class AbstractExample {

	private String name;
	private Ford ford;
	private List<Car> cars;
	private Set<Boat> boats;
	private Opel[] opels;
	private Collection<FordMax> fordMaxColl;
	
	public Collection<FordMax> getFordMaxColl() {
		return fordMaxColl;
	}
	public void setFordMaxColl(Collection<FordMax> fordMaxColl) {
		this.fordMaxColl = fordMaxColl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Ford getFord() {
		return ford;
	}
	public void setFord(Ford ford) {
		this.ford = ford;
	}
	public List<Car> getCars() {
		return cars;
	}
	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
	public Set<Boat> getBoats() {
		return boats;
	}
	public void setBoats(Set<Boat> boats) {
		this.boats = boats;
	}
	public Opel[] getOpels() {
		return opels;
	}
	public void setOpels(Opel[] opels) {
		this.opels = opels;
	}
	
	
}
