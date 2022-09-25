package org.coffeecorner.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.coffeecorner.domain.Order;
import org.coffeecorner.domain.product.Coffee;
import org.coffeecorner.domain.product.CoffeeSize;
import org.coffeecorner.domain.product.OrangeJuice;
import org.coffeecorner.domain.product.extra.ExtraMilk;
import org.coffeecorner.domain.product.extra.FoamedMilk;
import org.junit.jupiter.api.Test;

class OrderServiceTest {

  private OrderService orderService = new OrderService();

  @Test
  void calculateTotal_EmptyOrder() {

    // given
    Order order = new Order();

    // when
    double total = orderService.calculateTotal(order);

    // then
    assertEquals(0, total);
  }


  @Test
  void calculateTotal_OrderWithTwoProductsAndExtra() {

    // given
    Order order = new Order();
    order.addProduct(new Coffee(CoffeeSize.MEDIUM, new FoamedMilk(), new ExtraMilk()));
    order.addProduct(new OrangeJuice());

    // when
    double total = orderService.calculateTotal(order);

    // then
    assertEquals(7.75, total);
  }

  @Test
  void calculateTotal_OrderWithExtra() {

    // given
    Order order = new Order();
    for (int i = 0; i < 10; i++) {
      order.addProduct(new Coffee(CoffeeSize.MEDIUM));
    }

    // when
    double total = orderService.calculateTotal(order);

    // then
    assertEquals(24, total);
  }
}