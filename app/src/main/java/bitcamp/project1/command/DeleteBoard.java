package bitcamp.project1.command;

import bitcamp.project1.util.Prompt;
import bitcamp.project1.vo.AccountBook;
import bitcamp.project1.vo.Entry;

public class DeleteBoard {

  private AccountBook accountBook;

  public DeleteBoard(AccountBook accountBook) {
    this.accountBook = accountBook;
  }

  public void delete(String subMenuTitle) {
    switch (subMenuTitle) {
      case "예산 삭제":
        deleteMonthlyBudget();
        break;
      case "가계부 내역 삭제":
        deleteEntry();
        break;
      default:
        System.out.println("잘못된 메뉴 선택입니다.");
    }
  }

  private void deleteMonthlyBudget() {
    if (accountBook.getMonthlyBudget() == 0) {
      System.out.println("예산이 존재하지 않습니다.");
    } else {
      accountBook.setMonthlyBudget(0);
      System.out.println("예산이 삭제되었습니다.");
    }
  }

  private void deleteEntry() {
    if (accountBook.getEntries() == null || accountBook.getEntries().isEmpty()) {
      System.out.println("가계부 내역이 존재하지 않습니다.");
      return;
    }
    // 가계부 내역 출력
    for (int i = 0; i < accountBook.getEntries().size(); i++) {
      accountBook.getEntries().get(i).setIndex(i + 1);
      Entry entry = accountBook.getEntries().get(i);
      System.out.printf("%d. %s | %s | %s | %s원\n", entry.getIndex(), entry.getDate(),
          entry.getType(), entry.getDescription(), entry.getAmount());
    }

    int deleteIndex = Prompt.inputInt("삭제할 내역 번호를 입력해주세요.") - 1;
    if (deleteIndex >= 0 && deleteIndex < accountBook.getEntries().size()) {
      accountBook.getEntries().remove(deleteIndex);
      System.out.println("삭제되었습니다.");
    } else {
      System.out.println("잘못된 번호입니다. 다시 시도해주세요.");
    }
  }
}
