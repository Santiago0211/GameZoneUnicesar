package com.gamezone.persistence;

import com.gamezone.model.Sale;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * SaleRepository is the only class allowed to touch files for the
 * sales module. */
public class SaleRepository {

    private final String filePath;

    public SaleRepository(String filePath) {
        this.filePath = filePath;
    }

    @SuppressWarnings("unchecked")
    public List<Sale> loadAll() {
        File file = new File(filePath);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Sale>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading sales: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void saveAll(List<Sale> sales) {
        File file = new File(filePath);
        File parent = file.getParentFile();
        if (parent != null) {
            parent.mkdirs();
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(sales);
        } catch (IOException e) {
            System.out.println("Error saving sales: " + e.getMessage());
        }
    }
}
