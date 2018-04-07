package com.github.marschall.inlinereferencetypes;

import com.github.marschall.inlinereferencetypes.FieldInlineInteger;

public class FieldInlineIntegerTest {

  public static void main(String[] args) {
    SimpleTO simpleTO = new SimpleTO();
    simpleTO.setQualityCode(42);
    System.out.println(simpleTO.getQualityCode());
  }

  static final class SimpleTO {

    static final FieldInlineInteger QUALITY_CODE;

    private int qualityCode;
    private boolean qualityCodeIsNull;

    static {
      QUALITY_CODE = FieldInlineInteger.create(SimpleTO.class, "qualityCode", "qualityCodeIsNull");
    }

    public void setQualityCode(Integer value) {
      QUALITY_CODE.setValue(this, value);
    }

    public Integer getQualityCode() {
      return QUALITY_CODE.getValue(this);
    }

  }

}
