package com.gamezone;

import com.gamezone.persistence.PersonRepository;
import com.gamezone.persistence.ProductRepository;
import com.gamezone.persistence.SaleRepository;
import com.gamezone.service.PersonService;
import com.gamezone.service.ProductService;
import com.gamezone.service.SaleService;
import com.gamezone.ui.ConsoleUI;

/**
 * Main is the application's entry point. Its only job is to create
 * one repository, one service and the UI for each module, and wire
 * them together (this is a very simple form of dependency injection:
 * every class receives the objects it depends on through its
 * constructor, instead of creating them itself).
 */
public class Main {

    public static void main(String[] args) {
        // 1. Repositories: they know how to read/write each data file.
        ProductRepository productRepository = new ProductRepository("data/products.dat");
        PersonRepository personRepository = new PersonRepository("data/customers.dat", "data/sellers.dat");
        SaleRepository saleRepository = new SaleRepository("data/sales.dat");

        // 2. Services: they hold the business rules and use the repositories.
        ProductService productService = new ProductService(productRepository);
        PersonService personService = new PersonService(personRepository);
        SaleService saleService = new SaleService(saleRepository, productService);

        // 3. UI: it uses the services to interact with the user.
        ConsoleUI ui = new ConsoleUI(productService, personService, saleService);
        ui.start();
    }
}
