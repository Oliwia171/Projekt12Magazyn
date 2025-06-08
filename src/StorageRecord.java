import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class StorageRecord {
    private User user;
    private Product product;
    private LocalDate storageDate;
    private LocalDate expiryDate;

    public StorageRecord(User user, Product product, LocalDate storageDate, LocalDate expiryDate) {
        this.user = user;
        this.product = product;
        this.storageDate = storageDate;
        this.expiryDate = expiryDate;
    }
    // obliczanie towarów po dacie
    public boolean isExpired(LocalDate today) {
        return today.isAfter(expiryDate);
    }

    // kalkulacja opłat
    public double calculateFee(LocalDate today, double dailyRate, double penaltyRate) {
        long totalDays = ChronoUnit.DAYS.between(storageDate, today);
        long overdueDays = isExpired(today) ? ChronoUnit.DAYS.between(expiryDate, today) : 0;
        long normalDays = totalDays - overdueDays;
        return normalDays * dailyRate + overdueDays * penaltyRate;
    }
    // pobieranie użytkownika
    public User getUser() {
        return user;
    }
    // pobieranie produktu
    public Product getProduct() {
        return product;
    }

    // pobieranie daty skladowania
    public LocalDate getStorageDate() {
        return storageDate;
    }

    // pobieranie daty wygaśniecia
    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    @Override
    public String toString() {
        return "Rekord{" +
                "Użytkownik=" + user.getname() +
                ", Produkt=" + product.getName() +
                ", Data przyjęcia do magazynu=" + storageDate +
                ", Wygasa=" + expiryDate +
                '}';
    }
}
