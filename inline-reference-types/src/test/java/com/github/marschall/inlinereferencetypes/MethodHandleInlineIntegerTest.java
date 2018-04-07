package com.github.marschall.inlinereferencetypes;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;

import com.github.marschall.inlinereferencetypes.MethodHandleInlineInteger;
import com.github.marschall.inlinereferencetypes.FieldInlineIntegerTest.SimpleTO;

public class MethodHandleInlineIntegerTest {

  public static void main(String[] args) {
    SimpleTO simpleTO = new SimpleTO();
    simpleTO.setQualityCode(42);
    System.out.println(simpleTO.getQualityCode());
  }

  static final class SimpleTO {

    static final MethodHandleInlineInteger QUALITY_CODE;

    private int qualityCode;
    private boolean qualityCodeIsNull;


    static {
      Lookup lookup = MethodHandles.lookup();
      QUALITY_CODE = MethodHandleInlineInteger.create(lookup, SimpleTO.class, "qualityCode", "qualityCodeIsNull");
    }

    public void setQualityCode(Integer value) {
      QUALITY_CODE.setValue(this, value);
    }

    public Integer getQualityCode() {
      return QUALITY_CODE.getValue(this);
    }

  }

}
