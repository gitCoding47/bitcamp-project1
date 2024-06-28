package bitcamp.project1.vo;

import java.util.ArrayList;

public class AccountBook {

  private int monthlyBudget;
  private ArrayList<Entry> entries = new ArrayList<>();
  private int nextIndex = 1;

  public void setMonthlyBudget(int budget) {
    this.monthlyBudget = budget;
    System.out.println("월 예산이 설정되었습니다: " + budget);
  }

  public void addEntry(Entry entry) {
    entry.setIndex(nextIndex++);
    entries.add(entry);
    System.out.println("항목이 추가되었습니다: " + entry.getDescription());
  }

  public void showRemainingBudget() {
    int totalExpenditure = 0;
    int totalIncome = 0;

    for (Entry entry : entries) {
      if (entry.getType().equals("지출")) {
        totalExpenditure += entry.getAmount();
      } else if (entry.getType().equals("수입")) {
        totalIncome += entry.getAmount();
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
      System.out.printf("%d. %s | %s | %d | %s\n", i + 1, e.getIndex(), e.getDescription(),
          e.getAmount(), e.getDate());
    }
  }
}
