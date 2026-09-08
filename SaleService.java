
package com.gamezone.service;

import com.gamezone.model.Customer;
import com.gamezone.model.Product;
import com.gamezone.model.Sale;
import com.gamezone.model.Seller;
import com.gamezone.persistence.SaleRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class SaleService {

    private final SaleRepository repository;
    private final ProductService productService;
    private final List<Sale> sales;
    private int nextId;

    public SaleService(SaleRepository repository, ProductService productService) {
        this.repository = repository;
        this.productService = productService;
        this.sales = repository.loadAll();
        this.nextId = sales.size() + 1;
    }

    
    public Sale registerSale(Customer customer, Seller seller, List<Product> selectedProducts) {
        if (selectedProducts == null || selectedProducts.isEmpty()) {
            throw new IllegalArgumentException("A sale must contain at least one product.");
        }

        // Count how many units of each product were selected.
        Map<String, Integer> quantityByProductId = new LinkedHashMap<>();
        for (Product p : selectedProducts) {
            quantityByProductId.merge(p.getId(), 1, Integer::sum);
        }

        // First validate stock for every product, before changing anything.
        for (Map.Entry<String, Integer> entry : quantityByProductId.entrySet()) {
            Product product = productService.findById(entry.getKey());
            int quantity = entry.getValue();
            if (product == null || !productService.hasEnoughStock(product, quantity)) {
                throw new IllegalArgumentException("Insufficient stock for product: " + entry.getKey());
            }
        }

        // Only after every product passed validation, discount the stock.
        for (Map.Entry<String, Integer> entry : quantityByProductId.entrySet()) {
            Product product = productService.findById(entry.getKey());
            productService.reduceStock(product, entry.getValue());
        }

        String id = "SALE-" + nextId++;
        Sale sale = new Sale(id, LocalDate.now(), customer, seller, selectedProducts);
        sales.add(sale);
        repository.saveAll(sales);
        return sale;
    }

    public List<Sale> listAllSales() {
        return new ArrayList<>(sales);
    }

    public List<Sale> listSalesByCustomer(String customerId) {
        List<Sale> result = new ArrayList<>();
        for (Sale s : sales) {
            if (s.getCustomer().getId().equalsIgnoreCase(customerId)) {
                result.add(s);
            }
        }
        return result;
    }

    public List<Sale> listSalesBySeller(String sellerId) {
        List<Sale> result = new ArrayList<>();
        for (Sale s : sales) {
            if (s.getSeller().getId().equalsIgnoreCase(sellerId)) {
                result.add(s);
            }
        }
        return result;
    }
}
