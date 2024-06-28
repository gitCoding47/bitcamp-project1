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
  }

}
