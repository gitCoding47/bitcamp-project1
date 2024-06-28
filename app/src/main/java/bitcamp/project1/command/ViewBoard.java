package bitcamp.project1.command;

import bitcamp.project1.vo.AccountBook;
import bitcamp.project1.vo.Entry;

public class ViewBoard {

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
      System.out.printf("남은 예산: %d원\n",remainingBudget);
    }
    System.out.printf("총 수입: %d원\n", totalIncome);
    System.out.printf("총 지출: %d원\n", totalExpenditure);
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
      System.out.printf("%d. %s | %s | %s | %s원\n", entry.getIndex(), entry.getDate(), entry.getType(),
          entry.getDescription(), entry.getAmount());
    }
  }
}
