package org.coffeecorner;

import org.coffeecorner.domain.Order;
import org.coffeecorner.domain.product.BaconRoll;
import org.coffeecorner.domain.product.Coffee;
import org.coffeecorner.domain.product.CoffeeSize;
import org.coffeecorner.domain.product.extra.ExtraMilk;
import org.coffeecorner.printer.Printer;
import org.coffeecorner.printer.StdOutPrinter;
import org.coffeecorner.service.ReceiptService;

public class Main {


  public static void main(String[] args) {
    ReceiptService receiptService = new ReceiptService();
    Order order = new Order();
    order.addProduct(new BaconRoll());
    order.addProduct(new Coffee(CoffeeSize.LARGE, new ExtraMilk()));

    String receipt = receiptService.generateReceipt(order);

    Printer printer = new StdOutPrinter();
    printer.print(receipt);
  }

}
