package org.coffeecorner.domain.product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.coffeecorner.domain.product.extra.Extra;

public abstract class ProductWithExtra extends Product {

  private List<Extra> extras;

  protected ProductWithExtra(double basePrice, String label, Extra... extra) {
    super(basePrice, label);
    this.extras = new ArrayList<>(Arrays.asList(extra));
  }

  public List<Extra> getExtras() {
    return extras;
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
    ProductWithExtra that = (ProductWithExtra) o;
    return Objects.equals(extras, that.extras);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), extras);
  }
}
