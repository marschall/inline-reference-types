package com.github.marschall.inlinereferencetypes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MethodHandleInlineIntegerTest {

  private SimpleTO simpleTO;

  @BeforeEach
  void setUp () {
    this.simpleTO = new SimpleTO();
  }

  @Test
  void setReferenceValue() {
    simpleTO.setQualityCode(42);
    assertEquals(Integer.valueOf(42), simpleTO.getQualityCode());

    simpleTO.setQualityCode(null);
    assertNull(simpleTO.getQualityCode());
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
