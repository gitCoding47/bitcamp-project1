package bitcamp.project1.vo;

import java.util.ArrayList;

public class AccountBook {
  ArrayList<Entry> entries = new ArrayList<>();
  double monthlyBudget;

  public void setMonthlyBudget(double budget) {
    this.monthlyBudget = budget;
    System.out.println("월 예산이 설정되었습니다: " + budget);
  }

  public void addEntry(Entry entry) {
    entries.add(entry);
    System.out.println("항목이 추가되었습니다: " + entry.description);
  }

  public void showRemainingBudget() {
    double totalExpenditure =
        entries.stream().filter(e -> e.type.equals("지출")).mapToInt(e -> (int) e.amount).sum();
    double remainingBudget = monthlyBudget - totalExpenditure;
    System.out.println("남은 예산: " + remainingBudget);
  }

  public void showEntries() {
    for (int i = 0; i < entries.size(); i++) {
      Entry e = entries.get(i);
      System.out.printf("%d. %s | %s | %d | %s\n", i + 1, e.type, e.description, e.amount, e.date);
    }
  }
}
