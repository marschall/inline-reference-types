package com.github.marschall.inlinereferencetype;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.reflect.UndeclaredThrowableException;

public final class MethodHandleInlineInteger implements InlineInteger {
  
  private final MethodHandle valueGetter;
  
  private final MethodHandle valueSetter;
  
  private final MethodHandle isNullGetter;
  
  private final MethodHandle isNullSetter;
  
  MethodHandleInlineInteger(MethodHandle valueGetter, MethodHandle valueSetter, MethodHandle isNullGetter, MethodHandle isNullSetter) {
    this.valueGetter = valueGetter;
    this.valueSetter = valueSetter;
    this.isNullGetter = isNullGetter;
    this.isNullSetter = isNullSetter;
  }
  
  public static MethodHandleInlineInteger create(Lookup lookup, Class<?> clazz, String valueVariableName, String isNullVariableName) {
    try {
      MethodHandle valueSettter = lookup.findSetter(clazz, valueVariableName, int.class);
      MethodHandle valueGetter = lookup.findGetter(clazz, valueVariableName, int.class);
      MethodHandle isNullSetter = lookup.findSetter(clazz, isNullVariableName, boolean.class);
      MethodHandle isNullGetter = lookup.findGetter(clazz, isNullVariableName, boolean.class);
      return new MethodHandleInlineInteger(valueGetter, valueSettter, isNullGetter, isNullSetter);
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
      this.valueSetter.invoke(holder, intValue);
      this.isNullSetter.invoke(holder, booleanValue);
    } catch (Error | RuntimeException e) {
      throw e;
    } catch (Throwable e) {
      throw new UndeclaredThrowableException(e);
    }
  }
  
  public Integer getValue(Object holder) {
    try {
      boolean isNullValue = (boolean) this.isNullGetter.invoke(holder);
      if (isNullValue) {
        return null;
      }
      return (int) this.valueGetter.invoke(holder);
    } catch (Error | RuntimeException e) {
      throw e;
    } catch (Throwable e) {
      throw new UndeclaredThrowableException(e);
    }
  }
  
  public void setInt(Object holder, int intValue) {
    try {
      this.valueSetter.invoke(holder, intValue);
      this.isNullSetter.invoke(holder, false);
    } catch (Error | RuntimeException e) {
      throw e;
    } catch (Throwable e) {
      throw new UndeclaredThrowableException(e);
    }
  }
  
  public int getInt(Object holder) {
    try {
      boolean isNullValue = (boolean) this.isNullGetter.invoke(holder);
      if (isNullValue) {
        throw new NullPointerException();
      }
      return (int) this.valueGetter.invoke(holder);
    } catch (Error | RuntimeException e) {
      throw e;
    } catch (Throwable e) {
      throw new UndeclaredThrowableException(e);
    }
  }

}
