package com.github.marschall.inlinereferencetypes.benchmark;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import com.github.marschall.inlinereferencetypes.FieldInlineInteger;
import com.github.marschall.inlinereferencetypes.MethodHandleInlineInteger;
import com.github.marschall.inlinereferencetypes.VarHandleInlineInteger;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
public class InlineReferenceTypeBenchmark {

  private IntegerUser integerUser;
  private FieldInlineIntegerUser fieldInlineIntegerUser;
  private MethodHandleInlineIntegerUser methodHandleInlineIntegerUser;
  private VarHandleInlineIntegerUser varHandleInlineIntegerUser;

  @Setup
  public void setup() {
    this.integerUser = new IntegerUser();
    this.integerUser.setQualityCode(1024);

    this.fieldInlineIntegerUser = new FieldInlineIntegerUser();
    this.fieldInlineIntegerUser.setQualityCode(1024);

    this.methodHandleInlineIntegerUser = new MethodHandleInlineIntegerUser();
    this.methodHandleInlineIntegerUser.setQualityCode(1024);

    this.varHandleInlineIntegerUser = new VarHandleInlineIntegerUser();
    this.varHandleInlineIntegerUser.setQualityCode(1024);
  }

  @Benchmark
  public Integer integer() {
    return this.integerUser.getQualityCode();
  }

  @Benchmark
  public Integer field() {
    return this.fieldInlineIntegerUser.getQualityCode();
  }

  @Benchmark
  public Integer methodHandle() {
    return this.methodHandleInlineIntegerUser.getQualityCode();
  }

  @Benchmark
  public Integer varHandle() {
    return this.varHandleInlineIntegerUser.getQualityCode();
  }

  static final class IntegerUser {

    private Integer qualityCode;

    Integer getQualityCode() {
      return this.qualityCode;
    }

    void setQualityCode(Integer qualityCode) {
      this.qualityCode = qualityCode;
    }

  }

  static final class FieldInlineIntegerUser {

    static final FieldInlineInteger QUALITY_CODE;

    static {
      QUALITY_CODE = FieldInlineInteger.create(FieldInlineIntegerUser.class, "qualityCode", "qualityCodeIsNull");
    }

    private int qualityCode;
    private boolean qualityCodeIsNull;

    Integer getQualityCode() {
      return QUALITY_CODE.getValue(this);
    }

    void setQualityCode(Integer qualityCode) {
      QUALITY_CODE.setValue(this, qualityCode);
    }

  }

  static final class MethodHandleInlineIntegerUser {

    static final MethodHandleInlineInteger QUALITY_CODE;

    static {
      Lookup lookup = MethodHandles.lookup();
      QUALITY_CODE = MethodHandleInlineInteger.create(lookup, MethodHandleInlineIntegerUser.class, "qualityCode", "qualityCodeIsNull");
    }

    private int qualityCode;
    private boolean qualityCodeIsNull;

    Integer getQualityCode() {
      return QUALITY_CODE.getValue(this);
    }

    void setQualityCode(Integer qualityCode) {
      QUALITY_CODE.setValue(this, qualityCode);
    }

  }

  static final class VarHandleInlineIntegerUser {

    static final VarHandleInlineInteger QUALITY_CODE;

    static {
      Lookup lookup = MethodHandles.lookup();
      QUALITY_CODE = VarHandleInlineInteger.create(lookup, VarHandleInlineIntegerUser.class, "qualityCode", "qualityCodeIsNull");
    }

    private int qualityCode;
    private boolean qualityCodeIsNull;

    Integer getQualityCode() {
      return QUALITY_CODE.getValue(this);
    }

    void setQualityCode(Integer qualityCode) {
      QUALITY_CODE.setValue(this, qualityCode);
    }

  }

}
