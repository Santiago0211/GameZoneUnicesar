package com.gamezone.service;

import com.gamezone.model.Customer;
import com.gamezone.model.Seller;
import com.gamezone.persistence.PersonRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * PersonService contains the business rules for customers and
 * sellers: how a new customer gets registered, and making sure that
 * the very first time the application runs there are already three
 * sellers available (they are hired employees, they are not created
 * through the menu).
 */
public class PersonService {

    private final PersonRepository repository;
    private final List<Customer> customers;
    private final List<Seller> sellers;
    private int nextCustomerId;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
        this.customers = repository.loadCustomers();
        this.sellers = repository.loadSellers();
        this.nextCustomerId = customers.size() + 1;
        preloadSellersIfEmpty();
    }

    /**
     * Guarantees that on the very first run the seller file already
     * contains three sellers, as required by the workshop.
     */
    private void preloadSellersIfEmpty() {
        if (sellers.isEmpty()) {
            sellers.add(new Seller("SEL-1", "Carlos Martinez", "3001234567", "EMP-001", "Morning"));
            sellers.add(new Seller("SEL-2", "Laura Gomez", "3007654321", "EMP-002", "Afternoon"));
            sellers.add(new Seller("SEL-3", "Andres Rojas", "3009876543", "EMP-003", "Evening"));
            repository.saveSellers(sellers);
        }
    }

    public Customer registerCustomer(String name, String phone, String email) {
        String id = "CUS-" + nextCustomerId++;
        Customer customer = new Customer(id, name, phone, email);
        customers.add(customer);
        repository.saveCustomers(customers);
        return customer;
    }

    public List<Customer> listCustomers() {
        return new ArrayList<>(customers);
    }

    public List<Seller> listSellers() {
        return new ArrayList<>(sellers);
    }

    public Customer findCustomerById(String id) {
        for (Customer c : customers) {
            if (c.getId().equalsIgnoreCase(id)) {
                return c;
            }
        }
        return null;
    }

    public Seller findSellerById(String id) {
        for (Seller s : sellers) {
            if (s.getId().equalsIgnoreCase(id)) {
                return s;
            }
        }
        return null;
    }
}
