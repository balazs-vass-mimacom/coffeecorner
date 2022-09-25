package org.coffeecorner.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.coffeecorner.domain.Order;
import org.coffeecorner.domain.product.BaconRoll;
import org.coffeecorner.domain.product.Coffee;
import org.coffeecorner.domain.product.CoffeeSize;
import org.coffeecorner.domain.product.OrangeJuice;
import org.coffeecorner.domain.product.Product;
import org.coffeecorner.domain.product.extra.ExtraMilk;
import org.coffeecorner.domain.product.extra.FoamedMilk;
import org.junit.jupiter.api.Test;

class ReceiptServiceTest {

  private ReceiptService receiptService = new ReceiptService();

  @Test
  void toReceiptLine_ProductWithoutExtra() {

    // given
    Product product = new OrangeJuice();

    // when
    String receiptLine = receiptService.toReceiptLine(product);

    // then
    assertEquals("Orange Juice 3.95", receiptLine);
  }

  @Test
  void toReceiptLine_ProductWithExtra() {

    // given
    Product product = new Coffee(CoffeeSize.MEDIUM, new ExtraMilk(), new FoamedMilk());

    // when
    String receiptLine = receiptService.toReceiptLine(product);

    // then
    String expectedReceipt = "Coffee MEDIUM 3.0\n+Extra milk 0.3\n+Foamed milk 0.5";
    assertEquals(expectedReceipt, receiptLine);
  }

  @Test
  void generateReceipt_OrderWithExtraAndBonus() {

    // given
    Order order = new Order();
    order.addProduct(new Coffee(CoffeeSize.MEDIUM, new ExtraMilk(), new FoamedMilk()));
    order.addProduct(new BaconRoll());

    // when
    String receipt = receiptService.generateReceipt(order);

    // then
    String expectedReceipt = "Charlene's Coffee Corner\nCoffee MEDIUM 3.0\n+Extra milk 0.0\n+Foamed milk 0.5\nBacon Roll 4.5\nTotal: 8.0\nThank you for your purchase!\n";
    assertEquals(expectedReceipt, receipt);
  }

  @Test
  void generateReceipt_EmptyOrder() {

    // given
    Order order = new Order();

    // when
    String receipt = receiptService.generateReceipt(order);

    // then
    String expectedReceipt = "Charlene's Coffee Corner\nTotal: 0.0\nThank you for your purchase!\n";
    assertEquals(expectedReceipt, receipt);
  }
}