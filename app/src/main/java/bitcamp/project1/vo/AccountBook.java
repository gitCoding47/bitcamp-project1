package bitcamp.project1.vo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AccountBook {
  private int monthlyBudget;
  private ArrayList<Entry> entries = new ArrayList<>();

  public void setMonthlyBudget(int budget) {
    this.monthlyBudget = budget;
    System.out.println("월 예산이 설정되었습니다: " + budget);
  }

  public void addEntry(Entry entry) {
    entries.add(entry);
    // 항목을 날짜 순으로 정렬
    Collections.sort(entries, new Comparator<Entry>() {
      public int compare(Entry e1, Entry e2) {
        return e1.getDate().compareTo(e2.getDate());
      }
    });

    System.out.println("항목이 추가되었습니다: " + entry.getDescription());
  }

  public void showRemainingBudget() {
    int totalExpenditure = 0;
    int totalIncome = 0;

    // 입력된 값이 지출이면 totalExpenditure , 수입이면 totalIncome에 더해서 저장
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
      return;
    }

    for (int i = 0; i < entries.size(); i++) {
      entries.get(i).setIndex(i + 1);
      Entry entry = entries.get(i);
      System.out.printf("%d. %s | %s | %d | %s\n", entry.getIndex(), entry.getType(),
          entry.getDescription(), entry.getAmount(), entry.getDate());
    }
  }
}
