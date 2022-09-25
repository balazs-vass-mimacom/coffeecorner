package org.coffeecorner.domain.product.extra;

import org.coffeecorner.domain.product.Product;

public abstract class Extra extends Product {

  protected Extra(double basePrice, String label) {
    super(basePrice, label);
  }

  @Override
  public String toString() {
    return "+" + this.getLabel() + " " + this.getPrice();
  }
}
