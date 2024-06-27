package bitcamp.project1.command;

import bitcamp.project1.util.Prompt;
import bitcamp.project1.vo.AccountBook;
import bitcamp.project1.vo.Entry;

public class AppendBoard {

  AccountBook accountBook = new AccountBook();

  public void append(String subMenuTitle) {
    switch (subMenuTitle) {
      case "예산 설정":
        int budget = Prompt.inputInt("예산 금액을 입력하세요: ");
        accountBook.setMonthlyBudget(budget);
        break;
      case "수입 등록":
        String incomeDesc = Prompt.input("수입 내역을 입력하세요: ");
        int incomeAmount = Prompt.inputInt("수입 금액을 입력하세요: ");
        String incomeDate = Prompt.input("수입 날짜를 입력하세요 (YYYY-MM-DD): ");
        accountBook.addEntry(new Entry("수입", incomeDesc, incomeAmount, incomeDate));
        break;
      case "지출 등록":
        String expenseDesc = Prompt.input("지출 내역을 입력하세요: ");
        int expenseAmount = Prompt.inputInt("지출 금액을 입력하세요: ");
        String expenseDate = Prompt.input("지출 날짜를 입력하세요 (YYYY-MM-DD): ");
        accountBook.addEntry(new Entry("지출", expenseDesc, expenseAmount, expenseDate));
        break;
      default:
        System.out.println("잘못된 메뉴 선택입니다.");
    }
  }
}
