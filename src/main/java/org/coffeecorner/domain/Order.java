package org.coffeecorner.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.coffeecorner.domain.product.Product;

public class Order {

  private List<Product> products = new ArrayList<>();

  public List<Product> getProducts() {
    return products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }

  public void addProduct(Product product) {
    products.add(product);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Order order = (Order) o;
    return Objects.equals(products, order.products);
  }

  @Override
  public int hashCode() {
    return Objects.hash(products);
  }
}
