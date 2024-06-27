package bitcamp.project1.vo;

public class Entry {
  String type; // "수입" 또는 "지출"
  String description;
  double amount;
  String date;

  public Entry(String type, String description, double amount, String date) {
    this.type = type;
    this.description = description;
    this.amount = amount;
    this.date = date;
  }
}
