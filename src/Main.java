import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // obsługa głównych funkcji programu
        WarehouseManager manager = new WarehouseManager();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== MENU MAGAZYNU ===");
            System.out.println("1. Dodaj użytkownika");
            System.out.println("2. Usuń użytkownika");
            System.out.println("3. Pokaż bieżące opłaty za składowanie produktów");
            System.out.println("4. Ustaw stawki opłat i kar");
            System.out.println("5. Dodaj produkt");
            System.out.println("6. Dodaj produkt składowania");
            System.out.println("7. Pokaż wszystkie rekordy");
            System.out.println("8. Pokaż przeterminowane rekordy");
            System.out.println("9. Lista użytkowników");
            System.out.println("10. Lista produktów");
            System.out.println("11. Wyjdź");

            System.out.print("Wybór: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> manager.addUser();
                case "2" -> manager.removeUser();
                case "3" -> manager.showStorageIncome();
                case "4" -> manager.setRates();
                case "5" -> manager.addProduct();
                case "6" -> manager.addRecord();
                case "7" -> manager.listAll();
                case "8" -> manager.listExpired();
                case "9" -> manager.listUsers();
                case "10" -> manager.listProducts();
                case "o" -> {
                    System.out.println("Zamykam program.");
                    running = false;
                }
                default -> System.out.println("Nieprawidłowa opcja.");
            }
        }

        scanner.close();
    }
}