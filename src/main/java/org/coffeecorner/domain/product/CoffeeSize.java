package org.coffeecorner.domain.product;

public enum CoffeeSize {

  SMALL(2.5), MEDIUM(3.0), LARGE(4.0);

  private double price;

  CoffeeSize(double price) {
    this.price = price;
  }

  public double getPrice() {
    return price;
  }
}
