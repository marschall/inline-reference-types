package com.github.marschall.inlinereferencetypes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.marschall.inlinereferencetypes.FieldInlineInteger;

class FieldInlineIntegerTest {

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
