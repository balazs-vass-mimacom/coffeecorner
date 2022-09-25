package org.coffeecorner.service;

import org.coffeecorner.domain.Order;
import org.coffeecorner.domain.product.Product;
import org.coffeecorner.domain.product.ProductWithExtra;

public class ReceiptService {

  public static final String HEADER = "Charlene's Coffee Corner\n";
  public static final String FOOTER = "Thank you for your purchase!\n";
  public static final String TOTAL = "Total: ";
  public static final String NL = "\n";
  private OrderService orderService = new OrderService();

  public String generateReceipt(Order order) {
    double total = orderService.calculateTotal(order);
    StringBuilder receipt = new StringBuilder();
    receipt.append(HEADER);
    for (Product product : order.getProducts()) {
      receipt.append(toReceiptLine(product) + NL);
    }
    receipt.append(TOTAL + total + NL);
    receipt.append(FOOTER);
    return receipt.toString();
  }

  String toReceiptLine(Product product) {
    StringBuilder receiptLines = new StringBuilder();
    receiptLines.append(product.toString());
    if (product instanceof ProductWithExtra) {
      receiptLines.append(NL);
      ((ProductWithExtra) product).getExtras().stream().forEach(i -> receiptLines.append(i.toString() + NL));
    }
    return receiptLines.toString().trim();
  }
}
