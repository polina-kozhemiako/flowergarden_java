package com.flowergarden.run;

import com.flowergarden.bouquet.WeddingBouquet;
import com.flowergarden.flowers.Chamomile;
import com.flowergarden.flowers.GeneralFlower;
import com.flowergarden.flowers.Rose;
import com.flowergarden.properties.FreshnessInteger;

import java.io.File;
import java.sql.Timestamp;

public class RunFolderBouquet {
    public static void main(String[] args) {
        WeddingBouquet originalBouquet = new WeddingBouquet();
        for(int i=0; i<3; i++){
            originalBouquet.addFlower(new Rose(false, 12,15, new FreshnessInteger(2)));
        }
        for (int i=0; i<2; i++){
            originalBouquet.addFlower(new Chamomile(7,5,2, new FreshnessInteger(1)));
        }
        String folderPath = "bouquets/weddingBouquet_" + System.currentTimeMillis();
        originalBouquet.saveToFile(folderPath);

        WeddingBouquet bouquetCopy = originalBouquet.assembleFromFolder(folderPath);
        System.out.println("\nAdded " + bouquetCopy.getFlowers().size() + " flowers from files:");
        for(GeneralFlower flower:bouquetCopy.getFlowers()){
            System.out.println(flower.getClass().getSimpleName());
        }
        System.out.println();
    }
}
