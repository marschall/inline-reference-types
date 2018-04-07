package com.github.marschall.inlinereferencetype;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;

public class VarHandleInlineIntegerTest {

  public static void main(String[] args) {
    SimpleTO simpleTO = new SimpleTO();
    simpleTO.setTransactionClass(42);
    System.out.println(simpleTO.getTransactionClass());
  }
  
  static final class SimpleTO {
    
    static final VarHandleInlineInteger TRANSACTION_CLASS_INTEGER; 
    
    private int transactionClass;
    private boolean transactionClassIsNull;
    
    static {
      Lookup lookup = MethodHandles.lookup();
      TRANSACTION_CLASS_INTEGER = VarHandleInlineInteger.create(lookup, SimpleTO.class, "transactionClass", "transactionClassIsNull");
    }
    
    public void setTransactionClass(Integer value) {
      TRANSACTION_CLASS_INTEGER.setValue(this, value);
    }
    
    public Integer getTransactionClass() {
      return TRANSACTION_CLASS_INTEGER.getValue(this);
    }
    
  } 

}
