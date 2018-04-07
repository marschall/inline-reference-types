package com.github.marschall.inlinereferencetypes;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.openjdk.jol.info.ClassLayout;

import com.github.marschall.inlinereferencetypes.VarHandleInlineIntegerTest.SimpleTO;

class ObjectSizeTest {

  @Test
  void smallerSize() {
    ClassLayout integerLayout = ClassLayout.parseClass(Integer.class);
    ClassLayout integerTOLayout = ClassLayout.parseClass(IntegerTO.class);

    ClassLayout simpleTOLayout = ClassLayout.parseClass(SimpleTO.class);

    assertThat(simpleTOLayout.instanceSize()).isLessThan(integerTOLayout.instanceSize() + integerLayout.instanceSize());
  }
  static final class IntegerTO {

    private Integer qualityCode;

    public void setQualityCode(Integer value) {
      this.qualityCode = value;
    }

    public Integer getQualityCode() {
      return this.qualityCode;
    }

  }

}
