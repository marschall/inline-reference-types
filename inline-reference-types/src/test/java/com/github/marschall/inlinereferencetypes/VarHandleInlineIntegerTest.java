package com.github.marschall.inlinereferencetypes;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;

import com.github.marschall.inlinereferencetypes.VarHandleInlineInteger;

public class VarHandleInlineIntegerTest {

  public static void main(String[] args) {
    SimpleTO simpleTO = new SimpleTO();
    simpleTO.setQualityCode(42);
    System.out.println(simpleTO.getQualityCode());
  }

  static final class SimpleTO {

    static final VarHandleInlineInteger QUALITY_CODE;

    private int qualityCode;
    private boolean qualityCodeIsNull;



    static {
      Lookup lookup = MethodHandles.lookup();
      QUALITY_CODE = VarHandleInlineInteger.create(lookup, SimpleTO.class, "transactionClass", "transactionClassIsNull");
    }

    public void setQualityCode(Integer value) {
      QUALITY_CODE.setValue(this, value);
    }

    public Integer getQualityCode() {
      return QUALITY_CODE.getValue(this);
    }

  }

}
