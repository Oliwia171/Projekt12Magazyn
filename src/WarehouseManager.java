import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class WarehouseManager {
    private List<StorageRecord> records = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private List<Product> products = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    private double dailyRate = 1.0;
    private double penaltyRate = 2.0;

    // dodawanie użytkownika  s
    public void addUser() {
        System.out.println("Podaj identyfikator użytkownika: ");
        String id = scanner.nextLine();
        System.out.println("Podaj imię i nazwisko: ");
        String name = scanner.nextLine();
        users.add(new User(name, id));
        System.out.println("Dodano użytkownika.");
    }

    // usuwanie użytkownika
    public void removeUser() {
        System.out.println("Podaj identyfikator użytkownika do usunięcia: ");
        String id = scanner.nextLine();

        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);
            if (u.getID().equals(id)) {
                users.remove(i);
                System.out.println("Użytkownik usunięty.");
                return;
            }
        }

        System.out.println("Nie znaleziono użytkownika o podanym identyfikatorze.");
    }

    //dodawanie produktu
    public void addProduct() {
        System.out.println("Podaj nazwę produktu: ");
        String name = scanner.nextLine();
        products.add(new Product(name));
        System.out.println("Dodano produkt.");
    }

    //
    public void addRecord() {
        System.out.println("Podaj identyfikator użytkownika: ");
        String userId = scanner.nextLine();
        User foundUser = null;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getID().equals(userId)) {
                foundUser = users.get(i);
                break;
            }
        }
        if (foundUser == null) {
            System.out.println("Użytkownik o podanym identyfikatorze nie zostal znaleziony.");
            return;
        }

        System.out.println("Podaj nazwę produktu: ");
        String productName = scanner.nextLine();
        Product foundProduct = null;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getName().equals(productName)) {
                foundProduct = products.get(i);
                break;
            }
        }
        if (foundProduct == null) {
            System.out.println("Produkt nie znaleziony.");
            return;
        }

        try {
            System.out.println("Podaj datę przyjęcia (YYYY-MM-DD): ");
            LocalDate stored = LocalDate.parse(scanner.nextLine());
            System.out.println("Podaj datę ważności (YYYY-MM-DD): ");
            LocalDate expiry = LocalDate.parse(scanner.nextLine());

            records.add(new StorageRecord(foundUser, foundProduct, stored, expiry));
            System.out.println("Dodano rekord.");
        } catch (DateTimeParseException e) {
            System.out.println("Błędny format daty.");
        }
    }
    // wyświetlanie listy wszystkich rekordow
    public void listAll() {
        System.out.println("--- Wszystkie rekordy ---");
        for (int i = 0; i < records.size(); i++) {
            System.out.println(records.get(i));
        }
    }

    // wyswietlanie produktów przeterminowanych
    public void listExpired() {
        System.out.println("--- Produkty przeterminowane ---");
        LocalDate today = LocalDate.now();
        for (int i = 0; i < records.size(); i++) {
            StorageRecord r = records.get(i);
            if (r.isExpired(today)) {
                System.out.println(r);
            }
        }
    }
    // wyswietlanie listy uzytkownikow
    public void listUsers() {
        System.out.println("--- Lista użytkowników ---");
        for (int i = 0; i < users.size(); i++) {
            System.out.println(users.get(i));
        }
    }

    //wyswietlanie listy produktow
    public void listProducts() {
        System.out.println("--- Lista produktów ---");
        for (int i = 0; i < products.size(); i++) {
            System.out.println(products.get(i));
        }
    }

    // kalkulator oplat za skladowanie
    public void showStorageIncome() {
        LocalDate today = LocalDate.now();
        double total = 0;

        System.out.println("--- Opłaty za składowanie ---");
        for (int i = 0; i < records.size(); i++) {
            StorageRecord r = records.get(i);
            double fee = r.calculateFee(today, dailyRate, penaltyRate);
            total += fee;
            System.out.printf("- %s, użytkownik: %s, opłata: %.2f zł%n",
                    r.getProduct().getName(), r.getUser().getname(), fee);
        }

        System.out.printf("Łączny zysk magazynu wynosi: %.2f zł%n", total);
    }

    public void setRates() {
        try {
            System.out.println("Podaj dzienną stawkę za składowanie (zł): ");
            dailyRate = Double.parseDouble(scanner.nextLine());
            System.out.println("Podaj dzienną stawkę za karę po terminie (zł): ");
            penaltyRate = Double.parseDouble(scanner.nextLine());
            System.out.println("Stawki zaktualizowane.");
        } catch (NumberFormatException e) {
            System.out.println("Błąd: nieprawidłowy format liczby.");
        }
    }
}
