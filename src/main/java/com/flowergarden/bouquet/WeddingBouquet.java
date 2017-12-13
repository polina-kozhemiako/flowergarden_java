package com.flowergarden.bouquet;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

import com.flowergarden.flowers.Chamomile;
import com.flowergarden.flowers.GeneralFlower;
import com.flowergarden.flowers.Rose;
import com.flowergarden.properties.FreshnessInteger;

public class WeddingBouquet implements Bouquet<GeneralFlower> {

	private float assemblePrice = 120;
	private ArrayList<GeneralFlower> flowerList = new ArrayList<>();

	@Override
	public float getPrice() {
		if (flowerList.isEmpty()) return 0;
		float result = assemblePrice;

		for(GeneralFlower flower: flowerList){
			result+=flower.getPrice();
		}
		return result;
	}

	@Override
	public void addFlower(GeneralFlower flower) {
		flowerList.add(flower);
	}

	@Override
	public Collection<GeneralFlower> searchFlowersByLength(int start, int end) {
		List<GeneralFlower> searchResult = new ArrayList<>();
		for (GeneralFlower flower : flowerList) {
			if (flower.getLength() >= start && flower.getLength() <= end) {
				searchResult.add(flower);
			}
		}
		return searchResult;
	}

	@Override
	public void sortByFreshness() {
		Collections.sort(flowerList);
	}

	@Override
	public Collection<GeneralFlower> getFlowers() {
		return flowerList;
	}

	public void setAssembledPrice(float price) {
		assemblePrice = price;
	}

	public WeddingBouquet assembleFromFolder(String folderPath){
		WeddingBouquet newBouquet = new WeddingBouquet();
		File folder = new File(folderPath);
		File[] files = folder.listFiles();
		for (File file : files) {
			if (! file.isDirectory()) {
				try {
					newBouquet.addFlower(loadFromFile(file.getPath()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return newBouquet;
	}

	private GeneralFlower loadFromFile(String filePath) throws ClassNotFoundException {
		int flowerFreshness, flowerLength, petals;
		boolean hasSpikes;
		String s;
		float flowerPrice;
		String fileName = filePath.substring(filePath.lastIndexOf("\\")+1);
		String flowerClassName = fileName.split("_")[0];

		try(FileReader fr = new FileReader(filePath);
			BufferedReader br = new BufferedReader(fr)) {
			s = br.readLine();
			s = s.substring(8);
			flowerLength = Integer.valueOf(s);
			s = br.readLine();
			s = s.substring(11);
			flowerFreshness = Integer.valueOf(s);
			s = br.readLine();
			s = s.substring(7);
			flowerPrice = Float.valueOf(s);
			switch (flowerClassName) {
				case "Rose":
					s = br.readLine();
					s = s.substring(8);
					hasSpikes = Boolean.valueOf(s);
					return new Rose(hasSpikes, flowerLength, flowerPrice, new FreshnessInteger(flowerFreshness));
				case "Chamomile":
					s = br.readLine();
					s = s.substring(8);
					petals = Integer.valueOf(s);
					return new Chamomile(petals, flowerLength, flowerPrice, new FreshnessInteger(flowerFreshness));
				default:
					throw new ClassNotFoundException("Unknown flower.");
			}
		}	catch(IOException e){
				e.printStackTrace();
			}
		return null;
	}

	private void createFolderIfNotExist(String folderPath){
		String[] folders = folderPath.split("/");
		File directory = new File(folderPath);
		if (! directory.exists()){
			System.out.println("Create directory: " + folders[folders.length-1]);
			try{
				directory.mkdir();
			}
			catch (SecurityException e){
				e.printStackTrace();
			}
		}
	}

	public void saveToFile(String folderPath){
		createFolderIfNotExist(folderPath);
		for(GeneralFlower flower:flowerList){
			String generatedFileName = flower.getClass().getSimpleName() + "_" + flower.hashCode() + ".txt";
			String filePath = folderPath + "/" + generatedFileName;
			File file = new File(filePath);
			if (! file.exists()){
				try (FileWriter fw = new FileWriter(filePath)){
					System.out.println("Create file: " + generatedFileName);
					fw.write("Length: " + flower.getLength() + "\n");
					fw.write("Freshness: " + flower.getFreshness().getFreshness() + "\n");
					fw.write("Price: " + flower.getPrice() + "\n");
					if (flower instanceof Rose){
						fw.write("Spikes: " + ((Rose) flower).getSpike() + "\n");
					}
					if (flower instanceof Chamomile){
						fw.write("Petals: " + ((Chamomile) flower).getPetals());
					}
				}
				catch (FileNotFoundException e){
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
