package org.coffeecorner.printer;

public class StdOutPrinter implements Printer{

  @Override
  public void print(String receipt) {
    System.out.println(receipt);
  }
}
