package com.github.marschall.inlinereferencetypes;

import java.lang.reflect.Field;

public class FieldInlineInteger implements InlineInteger {
  
  private final Field value;
  
  private final Field isNull;

  FieldInlineInteger(Field value, Field isNull) {
    this.value = value;
    this.isNull = isNull;
  }
  
  public static FieldInlineInteger create(Class<?> clazz, String valueVariableName, String isNullVariableName) {
    try {
      Field valueField = clazz.getDeclaredField(valueVariableName);
      valueField.setAccessible(true);
      Field isNullField = clazz.getDeclaredField(isNullVariableName);
      isNullField.setAccessible(true);
      return new FieldInlineInteger(valueField, isNullField);
    } catch (ReflectiveOperationException e) {
      throw new IllegalArgumentException("invalid field names or types", e);
    }
  }

  public void setValue(Object holder, Integer value) {
    int intValue;
    boolean booleanValue;
    if (value == null) {
      intValue = 0;
      booleanValue = true;
    } else {
      intValue = value.intValue();
      booleanValue = false;
    }
    try {
      this.value.set(holder, intValue);
      this.isNull.set(holder, booleanValue);
    } catch (IllegalAccessException e) {
      throw new RuntimeException("could not set field", e);
    }
  }
  
  public Integer getValue(Object holder) {
    try {
      boolean isNullValue = (boolean) this.isNull.get(holder);
      if (isNullValue) {
        return null;
      }
      return (int) this.value.get(holder);
    } catch (IllegalAccessException e) {
      throw new RuntimeException("could not get field", e);
    }
  }
  
  public void setInt(Object holder, int intValue) {
    try {
      this.value.set(holder, intValue);
      this.isNull.set(holder, false);
    } catch (IllegalAccessException e) {
      throw new RuntimeException("could not set field", e);
    }
  }
  
  public int getInt(Object holder) {
    try {
      boolean isNullValue = (boolean) this.isNull.get(holder);
      if (isNullValue) {
        throw new NullPointerException();
      }
      return (int) this.value.get(holder);
    } catch (IllegalAccessException e) {
      throw new RuntimeException("could not set field", e);
    }
  }

}
