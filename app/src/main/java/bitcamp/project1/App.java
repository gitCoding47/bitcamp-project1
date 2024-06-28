package bitcamp.project1;

import bitcamp.project1.command.AppendBoard;
import bitcamp.project1.command.ViewBoard;
import bitcamp.project1.command.DeleteBoard;
// import bitcamp.project1.command.ViewBoard;
import bitcamp.project1.util.Prompt;
import bitcamp.project1.vo.AccountBook;

public class App {

  String[] mainMenus = new String[] {"등록", "조회", "삭제", "수정", "도움말", "종료"};
  String[][] subMenus = {{"예산 설정", "수입 등록", "지출 등록"}, {"예산 조회", "가계부 내역 조회"}, {"예산 삭제", "내역 삭제"}};

  AccountBook accountBook = new AccountBook();
  AppendBoard appendBoard = new AppendBoard(accountBook);
  ViewBoard viewBoard = new ViewBoard(accountBook);
  DeleteBoard deleteBoard = new DeleteBoard(accountBook);
  // ChangeBoard changeBoard = new ChangeBoard(accountBook);


  public static void main(String[] args) {
    new App().execute();
  }

  void execute() {
    printMenu();
    while (true) {
      try {
        String command = Prompt.input("메인>");
        if (command.equals("menu")) {
          printMenu();
        } else {
          int menuNo = Integer.parseInt(command);
          String menuTitle = getMenuTitle(menuNo, mainMenus);
          if (menuTitle == null) {
            System.out.println("유효한 메뉴 번호가 아닙니다.");
          } else if (menuTitle.equals("종료")) {
            break;
          } else if (menuTitle.equals("도움말")) {
            showHelp();
          } else {
            processMenu(menuTitle, subMenus[menuNo - 1]);
          }
        }
      } catch (NumberFormatException ex) {
        System.out.println("숫자로 메뉴 번호를 입력하세요.");
      }
    }

    System.out.println("종료합니다.");

    Prompt.close();
  }

  void printMenu() {
    String boldAnsi = "\033[1m";
    String redAnsi = "\033[31m";
    String resetAnsi = "\033[0m";

    String appTitle = "[가계부 관리 시스템]";
    String line = "----------------------------------";

    System.out.println(boldAnsi + line + resetAnsi);
    System.out.println(boldAnsi + appTitle + resetAnsi);

    for (int i = 0; i < mainMenus.length; i++) {
      if (mainMenus[i].equals("종료")) {
        System.out.printf("%s%d. %s%s\n", (boldAnsi + redAnsi), (i + 1), mainMenus[i], resetAnsi);
      } else {
        System.out.printf("%d. %s\n", (i + 1), mainMenus[i]);
      }
    }

    System.out.println(boldAnsi + line + resetAnsi);
  }

  void printSubMenu(String menuTitle, String[] menus) {
    System.out.printf("[%s]\n", menuTitle);
    for (int i = 0; i < menus.length; i++) {
      System.out.printf("%d. %s\n", (i + 1), menus[i]);
    }
    System.out.println("9. 이전");
  }

  boolean isValidateMenu(int menuNo, String[] menus) {
    return menuNo >= 1 && menuNo <= menus.length;
  }

  String getMenuTitle(int menuNo, String[] menus) {
    return isValidateMenu(menuNo, menus) ? menus[menuNo - 1] : null;
  }

  void processMenu(String menuTitle, String[] menus) {
    printSubMenu(menuTitle, menus);
    while (true) {
      String command = Prompt.input(String.format("메인/%s>", menuTitle));
      if (command.equals("menu")) {
        printSubMenu(menuTitle, menus);
        continue;
      } else if (command.equals("9")) { // 이전 메뉴 선택
        break;
      }

      try {
        int menuNo = Integer.parseInt(command);
        String subMenuTitle = getMenuTitle(menuNo, menus);
        if (subMenuTitle == null) {
          System.out.println("유효한 메뉴 번호가 아닙니다.");
        } else {
          switch (menuTitle) {
            case "등록":
              appendBoard.append(subMenuTitle);
              break;
            case "조회":
              viewBoard.view(subMenuTitle);
              break;
            case "삭제":
               deleteBoard.delete(subMenuTitle);
              break;
            case "수정":
              // changeBoard.change(subMenuTitle);
              break;
            default:
              System.out.printf("%s 메뉴의 명령을 처리할 수 없습니다.\n", menuTitle);
          }
        }
      } catch (NumberFormatException ex) {
        System.out.println("숫자로 메뉴 번호를 입력하세요.");
      }
    }
  }

  void showHelp() {
    System.out.println("Help Menu:");
    System.out.println("1. 등록: 월 예산 설정 및 새로운 수입 또는 지출 기록을 등록할 수 있습니다.");
    System.out.println("2. 조회: 남은 예산 조회 및 가계부 내역 조회를 할 수 있습니다.");
    System.out.println("3. 삭제: ");
    System.out.println("4. 수정: ");
    System.out.println("5. 도움말");
    System.out.println("6. 종료: 애플리케이션을 종료합니다.");
    System.out.println("메뉴 옵션을 다시 보고 싶으시면 menu를 입력해주세요.");
  }
}

