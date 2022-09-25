package org.coffeecorner.domain.product;

import java.util.Objects;
import org.coffeecorner.domain.product.extra.Extra;

public class Coffee extends ProductWithExtra {

  private CoffeeSize size;

  public Coffee(CoffeeSize size, Extra... extra) {
    super(size.getPrice(), "Coffee", extra);
    this.size = size;
  }

  @Override
  public String toString() {
    return this.getLabel() + " " + size.toString() + " " + this.getPrice();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    Coffee coffee = (Coffee) o;
    return size == coffee.size;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), size);
  }
}
