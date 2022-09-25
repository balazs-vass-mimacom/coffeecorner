package org.coffeecorner.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.coffeecorner.domain.Order;
import org.coffeecorner.domain.product.BaconRoll;
import org.coffeecorner.domain.product.Coffee;
import org.coffeecorner.domain.product.CoffeeSize;
import org.coffeecorner.domain.product.OrangeJuice;
import org.coffeecorner.domain.product.Product;
import org.coffeecorner.domain.product.extra.ExtraMilk;
import org.coffeecorner.domain.product.extra.FoamedMilk;
import org.junit.jupiter.api.Test;

class BonusServiceTest {

  private BonusService bonusService = new BonusService();


  @Test
  void applyBonus_EmptyOrder() {

    // given
    Order order = new Order();

    // when
    bonusService.applyBonus(order);

    // then nothing happens
  }

  @Test
  void applyBonus_FreeCoffeeBonus() {

    // given
    Order order = new Order();
    order.addProduct(new Coffee(CoffeeSize.MEDIUM));
    order.addProduct(new Coffee(CoffeeSize.MEDIUM));
    order.addProduct(new Coffee(CoffeeSize.MEDIUM));
    order.addProduct(new Coffee(CoffeeSize.MEDIUM));
    order.addProduct(new Coffee(CoffeeSize.MEDIUM));

    // when
    bonusService.applyBonus(order);

    // then
    Optional<Product> freeCoffee = order.getProducts().stream()
                                        .filter(product -> product.getPrice() == 0)
                                        .findAny();
    assertTrue(freeCoffee.isPresent());
  }

  @Test
  void applyBonus_NoFreeCoffeeBonus() {

    // given
    Order order = new Order();
    order.addProduct(new Coffee(CoffeeSize.MEDIUM));

    // when
    bonusService.applyBonus(order);

    // then
    Optional<Product> freeCoffee = order.getProducts().stream()
                                        .filter(product -> product.getPrice() == 0)
                                        .findAny();
    assertTrue(freeCoffee.isEmpty());
  }

  @Test
  void applyBonus_MultipleFreeCoffeeBonus() {

    // given
    Order order = new Order();
    for (int i = 0; i < 11; i++) {
      order.addProduct(new Coffee(CoffeeSize.MEDIUM));
    }

    // when
    bonusService.applyBonus(order);

    // then
    List<Product> freeCoffee = order.getProducts().stream()
                                    .filter(product -> product.getPrice() == 0)
                                    .collect(Collectors.toList());
    assertEquals(2, freeCoffee.size());
  }

  @Test
  void applyBonus_OneFreeExtra() {

    // given
    Order order = new Order();
    order.addProduct(new BaconRoll());
    Coffee coffee = new Coffee(CoffeeSize.MEDIUM, new ExtraMilk());
    order.addProduct(coffee);

    // when
    bonusService.applyBonus(order);

    // then
    assertEquals(0, coffee.getExtras().get(0).getPrice());
  }

  @Test
  void applyBonus_OneFreeExtraOneNotFree() {

    // given
    Order order = new Order();
    order.addProduct(new BaconRoll());
    Coffee coffee = new Coffee(CoffeeSize.MEDIUM, new ExtraMilk(), new FoamedMilk());
    order.addProduct(coffee);

    // when
    bonusService.applyBonus(order);

    // then
    assertEquals(0, coffee.getExtras().get(0).getPrice());
    assertEquals(new FoamedMilk().getPrice(), coffee.getExtras().get(1).getPrice());
  }

  @Test
  void applyBonus_OneFreeExtraMultipleBeverages() {

    // given
    Order order = new Order();
    order.addProduct(new BaconRoll());
    Coffee coffee = new Coffee(CoffeeSize.MEDIUM, new ExtraMilk(), new FoamedMilk());
    order.addProduct(coffee);
    order.addProduct(new OrangeJuice());

    // when
    bonusService.applyBonus(order);

    // then
    assertEquals(0, coffee.getExtras().get(0).getPrice());
    assertEquals(new FoamedMilk().getPrice(), coffee.getExtras().get(1).getPrice());
  }

  @Test
  void applyBonus_OneFreeExtraMultipleSnacks() {

    // given
    Order order = new Order();
    order.addProduct(new BaconRoll());
    Coffee coffee = new Coffee(CoffeeSize.MEDIUM, new ExtraMilk(), new FoamedMilk());
    order.addProduct(coffee);
    order.addProduct(new BaconRoll());

    // when
    bonusService.applyBonus(order);

    // then
    assertEquals(0, coffee.getExtras().get(0).getPrice());
    assertEquals(new FoamedMilk().getPrice(), coffee.getExtras().get(1).getPrice());
  }


}