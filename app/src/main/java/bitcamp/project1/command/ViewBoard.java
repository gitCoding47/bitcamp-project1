package bitcamp.project1.command;

import bitcamp.project1.vo.AccountBook;

public class ViewBoard {

  AccountBook accountBook = new AccountBook();

  public void view(String subMenuTitle) {
    switch (subMenuTitle) {
      case "예산 조회":
        accountBook.showRemainingBudget();
        break;
      case "가계부 내역 조회":
        accountBook.showEntries();
        break;
      default:
        System.out.println("잘못된 메뉴 선택입니다.");
    }
  }
}
