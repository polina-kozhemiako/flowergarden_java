package com.flowergarden.flowers;

import javax.xml.bind.annotation.XmlRootElement;

import com.flowergarden.properties.FreshnessInteger;

public class Rose extends GeneralFlower {
	
	private boolean hasSpikes;
	
	public Rose(boolean hasSpike, int length, float price, FreshnessInteger fresh){
		super(length, price, fresh);
		this.hasSpikes = hasSpike;
	}
	public Rose(){
		
	}
	
	public boolean getSpike(){
		return hasSpikes;
	}
	


}
