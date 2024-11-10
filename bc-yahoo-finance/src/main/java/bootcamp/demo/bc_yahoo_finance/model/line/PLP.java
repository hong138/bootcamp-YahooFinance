package bootcamp.demo.bc_yahoo_finance.model.line;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class PLP {
  private List<Transaction> transactions;

  public PLP(List<Transaction> transactions) {
    this.transactions = transactions;
  }

  public Set<PricePoint> getIntervals(int minutePerInterval) {
    // ! Step 1: Group transactions into 5-minute intervals
    Map<LocalDateTime, List<Transaction>> groupedBy5Min =
        this.transactions.stream()
            .collect(Collectors
                .groupingBy(transaction -> roundUpForInterval(minutePerInterval,
                    transaction.getTimestamp())));

    // ! Step 2: Pick the last transaction for each 5-minute interval
    List<Transaction> transactions = new ArrayList<>();
    for (Map.Entry<LocalDateTime, List<Transaction>> entry : groupedBy5Min
        .entrySet()) {
      Transaction lastTransaction = entry.getValue().stream()
          .max((t1, t2) -> t1.getTimestamp().compareTo(t2.getTimestamp()))
          .orElse(null);
      if (lastTransaction != null)
        lastTransaction.setTimestamp(entry.getKey());
      transactions.add(lastTransaction);
    }

    // ! Step 3: Sort the list by timestamp, return list
    Comparator<Transaction> sortbyTimestamp =
        (t1, t2) -> t1.getTimestamp().compareTo(t2.getTimestamp());
    transactions = transactions.stream() //
        .sorted(sortbyTimestamp) //
        .collect(Collectors.toList());

    // ! Step 4: Fill the empty intervals, return list
    int size = transactions.size();
    for (int i = 0; i < size; i++) {
      LocalDateTime head = transactions.get(i - 1).getTimestamp();
      LocalDateTime tail = transactions.get(i + 1).getTimestamp();
      while (head.isBefore(tail)) {
        LocalDateTime newTime = head.plusMinutes(minutePerInterval);
        Transaction newAdd =
            new Transaction(newTime, transactions.get(i).getClosePrice());
        transactions.add(newAdd);
        head = newTime;
      }
    }

    // ! Step 5: Convert from List<Transaction> to Set<PricePoint>
    return transactions.stream() //
        .sorted(sortbyTimestamp)
        .map(t -> new PricePoint(t.getTimestamp(), t.getClosePrice()))
        .collect(Collectors.toSet());
  }

  private LocalDateTime roundUpForInterval(int minutePerInterval,
      LocalDateTime timestamp) {
    return timestamp.truncatedTo(ChronoUnit.HOURS).plusMinutes(
        (timestamp.getMinute() / minutePerInterval + 1) * minutePerInterval);
  }

  public static class Transaction {
    private LocalDateTime timestamp;
    private double closePrice;

    public Transaction(LocalDateTime timestamp, double closePrice) {
      this.timestamp = timestamp;
      this.closePrice = closePrice;
    }

    public LocalDateTime getTimestamp() {
      return this.timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
      this.timestamp = timestamp;
    }

    public double getClosePrice() {
      return this.closePrice;
    }

    @Override
    public String toString() {
      return "Transaction[" //
          + "timestamp=" + this.timestamp //
          + "closePrice" + this.closePrice //
          + "]";
    }
  }

  // ! Refer to equals() and hashCode()
  // ! Each yyyyMMddHHmm should have one point only.
  public static class PricePoint {
    private LocalDateTime timestamp;
    private double closePrice;

    public PricePoint(LocalDateTime timestamp, double closePrice) {
      this.timestamp = timestamp;
      this.closePrice = closePrice;
    }

    public LocalDateTime getTimestamp() {
      return this.timestamp;
    }

    public double getClosePrice() {
      return this.closePrice;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (!(obj instanceof PricePoint))
        return false;
      PricePoint point = (PricePoint) obj;
      return Objects.equals(this.timestamp, point.getTimestamp());
    }

    @Override
    public int hashCode() {
      return Objects.hash(this.timestamp);
    }
  }

  public static void main(String[] args) {
    // Example usage
    List<Transaction> transactions = List.of( //
        new Transaction(LocalDateTime.of(2024, 11, 6, 10, 3), 100.5),
        new Transaction(LocalDateTime.of(2024, 11, 6, 10, 7), 101.0),
        new Transaction(LocalDateTime.of(2024, 11, 6, 10, 8), 102.5),
        new Transaction(LocalDateTime.of(2024, 11, 6, 10, 23), 97.5),
        new Transaction(LocalDateTime.of(2024, 11, 6, 10, 29), 92.5),
        new Transaction(LocalDateTime.of(2024, 11, 6, 10, 28), 99.5) //
    );

    Set<PricePoint> points = new PLP(transactions).getIntervals(5);
    points.forEach(point -> {
      System.out.println("Time: " + point.getTimestamp() + ", Price: "
          + point.getClosePrice());
    });
  }
}
