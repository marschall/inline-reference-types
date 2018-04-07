package com.github.marschall.inlinereferencetypes.benchmark;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;

public class InlineReferenceTypeBenchmark {
  
  private IntegerUser integerUser;
  private FieldInlineIntegerUser fieldInlineIntegerUser;
  private MethodHandleInlineIntegerUser methodHandleInlineIntegerUser;
  private VarHandleInlineIntegerUser varHandleInlineIntegerUser;

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
  
  public Integer integer() {
    return this.integerUser.getQualityCode();
  }
  
  public Integer field() {
    return this.fieldInlineIntegerUser.getQualityCode();
  }
  
  public Integer methodHandle() {
    return this.methodHandleInlineIntegerUser.getQualityCode();
  }
  
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
