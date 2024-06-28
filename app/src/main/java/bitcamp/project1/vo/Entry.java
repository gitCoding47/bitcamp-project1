package bitcamp.project1.vo;

public class Entry {

  private int index;
  private String type; // "수입" 또는 "지출"
  private String description;
  private int amount;
  private String date;
  private String category;

  public Entry(String type, String description, int amount, String date, String category) {
    this.type = type;
    this.description = description;
    this.amount = amount;
    this.date = date;
    this.category = category;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }
}
