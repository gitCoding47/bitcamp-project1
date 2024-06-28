package bitcamp.project1.command;

import bitcamp.project1.util.Prompt;
import bitcamp.project1.vo.AccountBook;
import bitcamp.project1.vo.Entry;

public class ChangeBoard {

  private final AccountBook accountBook;

  public ChangeBoard(AccountBook accountBook) {
    this.accountBook = accountBook;
  }

  public void change(String subMenuTitle) {
    switch (subMenuTitle) {
      case "예산 설정":
        changeBudget();
        break;
      case "수입 수정":
      case "지출 수정":
        changeEntry(subMenuTitle);
        break;
      default:
        System.out.println("잘못된 메뉴 선택입니다.");
    }
  }

  private void changeBudget() {
    int newBudget = Prompt.inputInt("새 예산 금액을 입력하세요: ");
    accountBook.setMonthlyBudget(newBudget);
  }

  private void changeEntry(String entryType) {
    accountBook.showEntries();

    int index = Prompt.inputInt("수정할 항목 번호를 입력하세요: ") - 1;

    if (index < 0 || index >= accountBook.getEntries().size()) {
      System.out.println("수정할 항목이 없습니다.");
      return;
    }

    Entry entry = accountBook.getEntries().get(index);

    if (entryType.equals("수입 수정") && !entry.getType().equals("수입")) {
      System.out.println("수입 항목이 아닙니다.");
      return;
    } else if (entryType.equals("지출 수정") && !entry.getType().equals("지출")) {
      System.out.println("지출 항목이 아닙니다.");
      return;
    }

    String description = Prompt.input(String.format("새 %s 내역을 입력하세요 (현재: %s): ", entry.getType(), entry.getDescription()));
    int amount = Prompt.inputInt(String.format("새 %s 금액을 입력하세요 (현재: %d): ", entry.getType(), entry.getAmount()));
    String date = Prompt.input(String.format("새 %s 날짜를 입력하세요 (현재: %s): ", entry.getType(), entry.getDate()));

    entry.setDescription(description);
    entry.setAmount(amount);
    entry.setDate(date);

    System.out.println("항목이 수정되었습니다.");
  }
}