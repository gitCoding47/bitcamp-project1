package bitcamp.project1.command;

import bitcamp.project1.util.Prompt;
import bitcamp.project1.vo.AccountBook;
import bitcamp.project1.vo.Entry;

import java.util.Set;

public class ViewBoard {
  String boldAnsi = "\033[1m";
  String redAnsi = "\033[31m";
  String resetAnsi = "\033[0m";
  String blueAnsi = "\033[34m";

  private AccountBook accountBook;

  public ViewBoard(AccountBook accountBook) {
    this.accountBook = accountBook;
  }

  public void view(String subMenuTitle) {
    switch (subMenuTitle) {
      case "예산 조회":
        showRemainingBudget();
        break;
      case "가계부 내역 조회":
        showEntries();
        break;
      case "카테고리별 내역 조회":
        showCategories();
        String category = Prompt.input("조회할 카테고리를 입력하세요: ");
        showEntriesByCategory(category);
        break;
      default:
        System.out.println("잘못된 메뉴 선택입니다.");
    }
  }

  private void showRemainingBudget() {
    int totalExpenditure = 0;
    int totalIncome = 0;

    // 입력된 값이 지출이면 totalExpenditure , 수입이면 totalIncome에 더해서 저장
    for (Entry entry : accountBook.getEntries()) {
      if (entry.getType().equals("지출")) {
        totalExpenditure += entry.getAmount();
      } else if (entry.getType().equals("수입")) {
        totalIncome += entry.getAmount();
      }
    }

    int remainingBudget = accountBook.getMonthlyBudget() - totalExpenditure;

    if (accountBook.getMonthlyBudget() == 0) {
      System.out.println("예산이 등록되지 않았습니다.");
    } else {
      System.out.printf("남은 예산: %d원\n", remainingBudget);
    }
    System.out.printf(blueAnsi + "총 수입: %d원\n" + resetAnsi, totalIncome);
    System.out.printf(redAnsi + "총 지출: %d원\n" + resetAnsi, totalExpenditure);
  }

  public void showEntries() {
    if (accountBook.getEntries().isEmpty()) {
      System.out.println("내역이 없습니다.");
      return;
    }

    System.out.println("번호 날짜 수입/지출 내역 금액");
    for (int i = 0; i < accountBook.getEntries().size(); i++) {
      accountBook.getEntries().get(i).setIndex(i + 1);
      Entry entry = accountBook.getEntries().get(i);
      System.out.printf("%d. %s | %s | %s | %s원\n", entry.getIndex(), entry.getDate(),
          entry.getType(), entry.getDescription(), entry.getAmount());
    }
  }

  private void showEntriesByCategory(String category) {
    System.out.println("카테고리: " + category);
    System.out.println("번호 날짜 수입/지출 내역 금액");

    for (int i = 0; i < accountBook.getEntries().size(); i++) {
      Entry entry = accountBook.getEntries().get(i);
      if (entry.getCategory().equals(category)) {
        entry.setIndex(i + 1);
        System.out.printf("%d. %s | %s | %s | %s원\n", entry.getIndex(), entry.getDate(),
            entry.getType(), entry.getDescription(), entry.getAmount());
      }
    }
  }

  private void showCategories() {
    Set<String> categories = accountBook.getCategories();
    System.out.println("등록된 카테고리:");
    for (String category : categories) {
      System.out.println("- " + category);
    }
  }
}
