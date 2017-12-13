package com.flowergarden.bouquet;

import com.flowergarden.flowers.GeneralFlower;

import java.util.*;

public class HashBouquet implements Bouquet<GeneralFlower> {

        private float assemblePrice = 120;
        private Map<String, Set<GeneralFlower>> flowerMap = new HashMap<>();

        @Override
        public float getPrice() {
            if (flowerMap.isEmpty()) return 0;
            float result = assemblePrice;
            Collection<Set<GeneralFlower>> flowerColl = flowerMap.values();
            for(Set<GeneralFlower> flowers: flowerColl) {
                for (GeneralFlower flower : flowers) {
                    result += flower.getPrice();
                }
            }
//          for (Map.Entry<String, Set<GeneralFlower>> item : flowerListMap.entrySet()){
//			result += item.getValue().//.getPrice();
//		    }
            return result;
        }

        @Override
        public void addFlower(GeneralFlower flower) {
            String flowerName = flower.getClass().getSimpleName();
		    Set<GeneralFlower> set = flowerMap.get(flowerName);
            if (set==null){
                set = flowerMap.put(flowerName, new TreeSet<>());
            }
            set.add(flower);
        }

        @Override
        public Collection<GeneralFlower> searchFlowersByLength(int start, int end) {
            List<GeneralFlower> searchResult = new ArrayList<GeneralFlower>();
            return searchResult;
        }

        @Override
        public void sortByFreshness() {
//            Collection<Set<GeneralFlower>> flowerColl = flowerMap.values();
//            Collections.sort(flowerColl);
        }

        @Override
        public Collection<GeneralFlower> getFlowers() {
//            Collection<Set<GeneralFlower>> flowers = flowerMap.values();
            return null;
        }

        public void setAssembledPrice(float price) {
            assemblePrice = price;
        }

        public com.flowergarden.bouquet.WeddingBouquet assembleFromFolder(String folderPath){
            return new com.flowergarden.bouquet.WeddingBouquet();
        }

}
