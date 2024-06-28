package bitcamp.project1.vo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AccountBook {
  private int monthlyBudget;
  private ArrayList<Entry> entries = new ArrayList<>();

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

  public ArrayList<Entry> getEntries() {
    return entries;
  }

  public int getMonthlyBudget() {
    return monthlyBudget;
  }

  public void setMonthlyBudget(int budget) {
    this.monthlyBudget = budget;
    System.out.println("월 예산이 설정되었습니다: " + budget);
  }

  public void showEntries() {

    if (entries.isEmpty()) {
      System.out.println("내역이 없습니다.");
      return;
    }

    System.out.println("번호 날짜 수입/지출 내역 금액");
    for (int i = 0; i < entries.size(); i++) {
      entries.get(i).setIndex(i + 1);
      Entry entry = entries.get(i);
      System.out.printf("%d. %s | %s | %s | %s원\n", entry.getIndex(), entry.getDate(), entry.getType(),
          entry.getDescription(), entry.getAmount());
    }
  }
}
