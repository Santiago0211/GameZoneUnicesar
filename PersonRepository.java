package com.gamezone.persistence;

import com.gamezone.model.Customer;
import com.gamezone.model.Seller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * PersonRepository is the only class allowed to touch files for the
 * persons module (customers and sellers). Each subtype is stored in
 * its own file, but both are handled by this single repository class,
 * as requested by the workshop's module distribution.
 */
public class PersonRepository {

    private final String customersFilePath;
    private final String sellersFilePath;

    public PersonRepository(String customersFilePath, String sellersFilePath) {
        this.customersFilePath = customersFilePath;
        this.sellersFilePath = sellersFilePath;
    }

    @SuppressWarnings("unchecked")
    public List<Customer> loadCustomers() {
        File file = new File(customersFilePath);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Customer>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading customers: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void saveCustomers(List<Customer> customers) {
        File file = new File(customersFilePath);
        File parent = file.getParentFile();
        if (parent != null) {
            parent.mkdirs();
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(customers);
        } catch (IOException e) {
            System.out.println("Error saving customers: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public List<Seller> loadSellers() {
        File file = new File(sellersFilePath);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Seller>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading sellers: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void saveSellers(List<Seller> sellers) {
        File file = new File(sellersFilePath);
        File parent = file.getParentFile();
        if (parent != null) {
            parent.mkdirs();
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(sellers);
        } catch (IOException e) {
            System.out.println("Error saving sellers: " + e.getMessage());
        }
    }
}
