package org.coffeecorner.service;

import org.coffeecorner.domain.Order;
import org.coffeecorner.domain.product.Product;
import org.coffeecorner.domain.product.ProductWithExtra;

public class OrderService {

  private BonusService bonusService = new BonusService();

  public double calculateTotal(Order order) {
    bonusService.applyBonus(order);
    if (order.getProducts().isEmpty()) {
      return 0;
    }
    double total = 0;
    for (Product product : order.getProducts()) {
      total += product.getPrice();
      if (product instanceof ProductWithExtra) {
        total += ((ProductWithExtra) product).getExtras().stream().mapToDouble(Product::getPrice).sum();
      }
    }
    return total;
  }
}
