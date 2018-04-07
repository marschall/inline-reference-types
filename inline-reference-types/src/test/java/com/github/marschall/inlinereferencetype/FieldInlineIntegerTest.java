package com.github.marschall.inlinereferencetype;

public class FieldInlineIntegerTest {

  public static void main(String[] args) {
    SimpleTO simpleTO = new SimpleTO();
    simpleTO.setTransactionClass(42);
    System.out.println(simpleTO.getTransactionClass());
  }
  
  static final class SimpleTO {
    
    static final FieldInlineInteger TRANSACTION_CLASS_INTEGER; 
    
    private int transactionClass;
    private boolean transactionClassIsNull;
    
    static {
      TRANSACTION_CLASS_INTEGER = FieldInlineInteger.create(SimpleTO.class, "transactionClass", "transactionClassIsNull");
    }
    
    public void setTransactionClass(Integer value) {
      TRANSACTION_CLASS_INTEGER.setValue(this, value);
    }
    
    public Integer getTransactionClass() {
      return TRANSACTION_CLASS_INTEGER.getValue(this);
    }
    
  }

}
