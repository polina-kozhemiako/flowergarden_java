package com.flowergarden.flowers;

import com.flowergarden.properties.FreshnessInteger;

public class Chamomile extends GeneralFlower {
	
	private int petals;
	
	public Chamomile(int petals, int length, float price, FreshnessInteger fresh){
		super(length, price, fresh);
		this.petals = petals;
	}
	
	public boolean getPetal(){
		if (petals <=0) return false;
		petals =-1;
		return true;
	}
	
	public int getPetals(){
		return petals;
	}
	


}
