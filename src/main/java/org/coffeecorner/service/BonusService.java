package org.coffeecorner.service;

import org.coffeecorner.domain.Order;
import org.coffeecorner.domain.product.BaconRoll;
import org.coffeecorner.domain.product.Coffee;
import org.coffeecorner.domain.product.OrangeJuice;
import org.coffeecorner.domain.product.Product;
import org.coffeecorner.domain.product.ProductWithExtra;
import org.coffeecorner.domain.product.extra.Extra;

public class BonusService {

  public void applyBonus(Order order) {
    applyFreeCoffeeBonus(order);
    applyExtraBonus(order);
  }

  private void applyExtraBonus(Order order) {
    long nrOfSnacks = order.getProducts().stream().filter(BaconRoll.class::isInstance).count();
    long nrOfBeverages = order.getProducts().stream().filter(p -> p instanceof Coffee || p instanceof OrangeJuice).count();
    long nrOfBonus = nrOfSnacks < nrOfBeverages ? nrOfSnacks : nrOfBeverages;

    for (Product product : order.getProducts()) {
      if (product instanceof ProductWithExtra && nrOfBonus > 0) {
        for (Extra extra : ((ProductWithExtra) product).getExtras()) {
          if (nrOfBonus > 0) {
            extra.setPrice(0);
            nrOfBonus--;
          }
        }
      }
    }
  }

  private void applyFreeCoffeeBonus(Order order) {
    int nrOfCoffees = 0;
    for (Product product : order.getProducts()) {
      if (product instanceof Coffee) {
        nrOfCoffees++;
        if (nrOfCoffees % 5 == 0) {
          product.setPrice(0);
        }
      }
    }
  }

}
