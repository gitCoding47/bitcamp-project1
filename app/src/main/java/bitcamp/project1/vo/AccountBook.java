package bitcamp.project1.vo;

import java.util.ArrayList;

public class AccountBook {
  ArrayList<Entry> entries = new ArrayList<>();
  int monthlyBudget;

  public void setMonthlyBudget(int budget) {
    this.monthlyBudget = budget;
    System.out.println("월 예산이 설정되었습니다: " + budget);
  }

  public void addEntry(Entry entry) {
    entries.add(entry);
    System.out.println("항목이 추가되었습니다: " + entry.description);
  }

  public void showRemainingBudget() {
    int totalExpenditure = 0;
    int totalIncome = 0;

    for (Entry e : entries) {
      if (e.type.equals("지출")) {
        totalExpenditure += e.amount;
      } else if (e.type.equals("수입")) {
        totalIncome += e.amount;
      }
    }
    int remainingBudget = monthlyBudget - totalExpenditure;
    if (monthlyBudget == 0) {
      System.out.println("예산이 등록되지 않았습니다.");
    } else {
      System.out.println("남은 예산: " + remainingBudget);
    }
    System.out.println("총 수입: " + totalIncome);
    System.out.println("총 지출: " + totalExpenditure);
  }

  public void showEntries() {
    if (entries.isEmpty()) {
      System.out.println("내역이 없습니다.");
    }
    for (int i = 0; i < entries.size(); i++) {
      Entry e = entries.get(i);
      System.out.printf("%d. %s | %s | %d | %s\n", i + 1, e.type, e.description, e.amount, e.date);
    }
  }
}
