import java.util.*;

class Product {
    int productId;
    String productName;
    double price;
    int quantity;

    Product(int productId, String productName, double price, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    void displayProduct() {
        System.out.println(productId + ". " + productName + " - ₹" + price + " × " + quantity + " = ₹" + (price * quantity));
    }
}

class Cart {
    ArrayList<Product> products = new ArrayList<>();
    double totalAmount = 0;

    void addProduct(Product p) {
        products.add(p);
        calculateTotal();
    }

    void removeProduct(int productId) {
        boolean found = false;

        for (Product p : products) {
            if (p.productId == productId) {
                products.remove(p);
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("Product removed successfully!");
        } else {
            System.out.println("Product not found!");
        }

        calculateTotal();
    }

    void calculateTotal() {
        totalAmount = 0;
        for (Product p : products) {
            totalAmount += p.price * p.quantity;
        }
    }

    void displayCart() {
        if (products.isEmpty()) {
            System.out.println("Cart is empty!");
            return;
        }

        System.out.println("\nProducts in Cart:");
        for (Product p : products) {
            p.displayProduct();
        }

        System.out.println("Total: ₹" + totalAmount);
    }

    void checkout() {
        if (products.isEmpty()) {
            System.out.println("Cart is empty! Cannot checkout.");
            return;
        }

        double discount = totalAmount * 0.10;
        double tax = (totalAmount - discount) * 0.05;
        double finalAmount = totalAmount - discount + tax;

        System.out.println("\n----- FINAL BILL -----");
        displayCart();

        System.out.println("Discount (10%): ₹" + discount);
        System.out.println("Tax (5%): ₹" + tax);
        System.out.println("Final Amount: ₹" + finalAmount);
    }
}

public class Project {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Cart cart = new Cart();

        while (true) {
            System.out.println("\n1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. View Cart");
            System.out.println("4. Checkout");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Product ID: ");
                    int id = sc.nextInt();

                    System.out.print("Enter Product Name: ");
                    String name = sc.next();

                    System.out.print("Enter Price: ");
                    double price = sc.nextDouble();

                    System.out.print("Enter Quantity: ");
                    int qty = sc.nextInt();

                    if (price > 0 && qty > 0) {
                        cart.addProduct(new Product(id, name, price, qty));
                    } else {
                        System.out.println("Invalid price or quantity!");
                    }
                    break;

                case 2:
                    System.out.print("Enter Product ID to remove: ");
                    int removeId = sc.nextInt();
                    cart.removeProduct(removeId);
                    break;

                case 3:
                    cart.displayCart();
                    break;

                case 4:
                    cart.checkout();
                    break;

                case 5:
                    System.out.println("Thank you!");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}