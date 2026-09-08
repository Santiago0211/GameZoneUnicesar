package com.gamezone.ui;

import com.gamezone.model.Console;
import com.gamezone.model.Customer;
import com.gamezone.model.Product;
import com.gamezone.model.Sale;
import com.gamezone.model.Seller;
import com.gamezone.model.VideoGame;
import com.gamezone.service.PersonService;
import com.gamezone.service.ProductService;
import com.gamezone.service.SaleService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * ConsoleUI is the only class the user interacts with directly. */
public class ConsoleUI {

    private final ProductService productService;
    private final PersonService personService;
    private final SaleService saleService;
    private final Scanner scanner;

    public ConsoleUI(ProductService productService, PersonService personService, SaleService saleService) {
        this.productService = productService;
        this.personService = personService;
        this.saleService = saleService;
        this.scanner = new Scanner(System.in);
    }

    /** Starts the main menu loop. */
    public void start() {
        boolean running = true;
        while (running) {
            printMenu();
            int option = readInt("Select an option: ");
            switch (option) {
                case 1 -> registerVideoGame();
                case 2 -> registerConsole();
                case 3 -> listProducts();
                case 4 -> registerCustomer();
                case 5 -> listCustomers();
                case 6 -> listSellers();
                case 7 -> registerSale();
                case 8 -> listAllSales();
                case 9 -> listSalesByCustomer();
                case 10 -> listSalesBySeller();
                case 0 -> {
                    running = false;
                    System.out.println("Goodbye!");
                }
                default -> System.out.println("Invalid option, please try again.");
            }
        }
        scanner.close();
    }

    private void printMenu() {
        System.out.println("\n===== GAMEZONE UNICESAR =====");
        System.out.println("1.  Register a new video game");
        System.out.println("2.  Register a new console");
        System.out.println("3.  List all products");
        System.out.println("4.  Register a new customer");
        System.out.println("5.  List all customers");
        System.out.println("6.  List all sellers");
        System.out.println("7.  Register a new sale");
        System.out.println("8.  List all sales");
        System.out.println("9.  List sales by customer");
        System.out.println("10. List sales by seller");
        System.out.println("0.  Exit");
    }

    // -------------------- Products menu --------------------

    private void registerVideoGame() {
        System.out.println("\n-- Register Video Game --");
        String title = readString("Title: ");
        double price = readDouble("Price: ");
        int stock = readInt("Stock quantity: ");
        String platform = readString("Platform: ");
        String genre = readString("Genre: ");
        String ageRating = readString("Age rating: ");

        VideoGame game = productService.registerVideoGame(title, price, stock, platform, genre, ageRating);
        System.out.println("Video game registered with ID: " + game.getId());
    }

    private void registerConsole() {
        System.out.println("\n-- Register Console --");
        String title = readString("Title: ");
        double price = readDouble("Price: ");
        int stock = readInt("Stock quantity: ");
        String brand = readString("Brand: ");
        String model = readString("Model: ");
        String generation = readString("Generation: ");

        Console console = productService.registerConsole(title, price, stock, brand, model, generation);
        System.out.println("Console registered with ID: " + console.getId());
    }

    private void listProducts() {
        System.out.println("\n-- Product Inventory --");
        List<Product> products = productService.listProducts();
        if (products.isEmpty()) {
            System.out.println("No products registered yet.");
            return;
        }
        for (Product p : products) {
            System.out.println(p.getDescription());
        }
    }

    // -------------------- Persons menu --------------------

    private void registerCustomer() {
        System.out.println("\n-- Register Customer --");
        String name = readString("Name: ");
        String phone = readString("Phone: ");
        String email = readString("Email: ");

        Customer customer = personService.registerCustomer(name, phone, email);
        System.out.println("Customer registered with ID: " + customer.getId());
    }

    private void listCustomers() {
        System.out.println("\n-- Registered Customers --");
        List<Customer> customers = personService.listCustomers();
        if (customers.isEmpty()) {
            System.out.println("No customers registered yet.");
            return;
        }
        for (Customer c : customers) {
            System.out.println(c);
        }
    }

    private void listSellers() {
        System.out.println("\n-- Registered Sellers --");
        List<Seller> sellers = personService.listSellers();
        for (Seller s : sellers) {
            System.out.println(s);
        }
    }

    // -------------------- Sales menu --------------------

    private void registerSale() {
        System.out.println("\n-- Register Sale --");

        String customerId = readString("Customer ID: ");
        Customer customer = personService.findCustomerById(customerId);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        String sellerId = readString("Seller ID: ");
        Seller seller = personService.findSellerById(sellerId);
        if (seller == null) {
            System.out.println("Seller not found.");
            return;
        }

        List<Product> selectedProducts = new ArrayList<>();
        boolean addingMore = true;
        while (addingMore) {
            String productId = readString("Product ID to add: ");
            Product product = productService.findById(productId);
            if (product == null) {
                System.out.println("Product not found.");
            } else {
                int quantity = readInt("Quantity: ");
                for (int i = 0; i < quantity; i++) {
                    selectedProducts.add(product);
                }
            }
            String again = readString("Add another product? (y/n): ");
            addingMore = again.equalsIgnoreCase("y");
        }

        try {
            Sale sale = saleService.registerSale(customer, seller, selectedProducts);
            System.out.println("Sale registered successfully.");
            System.out.println(sale);
        } catch (IllegalArgumentException e) {
            System.out.println("Could not register sale: " + e.getMessage());
        }
    }

    private void listAllSales() {
        System.out.println("\n-- Sales History --");
        List<Sale> sales = saleService.listAllSales();
        if (sales.isEmpty()) {
            System.out.println("No sales registered yet.");
            return;
        }
        for (Sale s : sales) {
            System.out.println(s);
        }
    }

    private void listSalesByCustomer() {
        String customerId = readString("Customer ID: ");
        List<Sale> sales = saleService.listSalesByCustomer(customerId);
        if (sales.isEmpty()) {
            System.out.println("No sales found for this customer.");
            return;
        }
        for (Sale s : sales) {
            System.out.println(s);
        }
    }

    private void listSalesBySeller() {
        String sellerId = readString("Seller ID: ");
        List<Sale> sales = saleService.listSalesBySeller(sellerId);
        if (sales.isEmpty()) {
            System.out.println("No sales found for this seller.");
            return;
        }
        for (Sale s : sales) {
            System.out.println(s);
        }
    }

    

    private String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
