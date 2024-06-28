package bitcamp.project1.command;

import bitcamp.project1.vo.AccountBook;

public class DeleteBoard {

  AccountBook accountBook;

  public DeleteBoard(AccountBook accountBook) {
    this.accountBook = accountBook;
  }

  public void delete(String subMenuTitle) {
    switch (subMenuTitle) {
      case "예산 삭제":
        if (accountBook.getMonthlyBudget() == 0) {
          System.out.println("예산이 존재하지 않습니다.");
          break;
        }
        accountBook.setMonthlyBudget(0);
        System.out.println("예산이 삭제되었습니다.");
        break;
      case "가계부 내역 삭제":
        break;
      default:
        System.out.println("잘못된 메뉴 선택입니다.");
    }
  }
}
