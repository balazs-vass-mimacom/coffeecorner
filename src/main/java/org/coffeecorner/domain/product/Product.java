package org.coffeecorner.domain.product;

import java.util.Objects;

public abstract class Product {

  private double basePrice;
  private double price;
  private String label;

  protected Product(double basePrice, String label) {
    this.basePrice = basePrice;
    this.price = basePrice;
    this.label = label;
  }

  @Override
  public String toString() {
    return label + " " + price;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Product product = (Product) o;
    return Double.compare(product.basePrice, basePrice) == 0 && Double.compare(product.price, price) == 0 && Objects.equals(label, product.label);
  }

  @Override
  public int hashCode() {
    return Objects.hash(basePrice, price, label);
  }

  public String getLabel() {
    return label;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public double getPrice() {
    return price;
  }
}
