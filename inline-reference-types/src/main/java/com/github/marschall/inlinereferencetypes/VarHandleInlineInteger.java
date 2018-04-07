package com.github.marschall.inlinereferencetypes;

import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.VarHandle;

public final class VarHandleInlineInteger implements InlineInteger {
  
  private final VarHandle value;
  
  private final VarHandle isNull;

  VarHandleInlineInteger(VarHandle value, VarHandle isNull) {
    this.value = value;
    this.isNull = isNull;
  }
  
  public static VarHandleInlineInteger create(Lookup lookup, Class<?> clazz, String valueVariableName, String isNullVariableName) {
    try {
      VarHandle valueHandle = lookup.findVarHandle(clazz, valueVariableName, int.class);
      VarHandle isNullHandle = lookup.findVarHandle(clazz, isNullVariableName, boolean.class);
      return new VarHandleInlineInteger(valueHandle, isNullHandle);
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
    this.value.set(holder, intValue);
    this.isNull.set(holder, booleanValue);
  }
  
  public Integer getValue(Object holder) {
    boolean isNullValue = (boolean) this.isNull.get(holder);
    if (isNullValue) {
      return null;
    }
    return (int) this.value.get(holder);
  }
  
  public void setInt(Object holder, int intValue) {
    this.value.set(holder, intValue);
    this.isNull.set(holder, false);
  }
  
  public int getInt(Object holder) {
    boolean isNullValue = (boolean) this.isNull.get(holder);
    if (isNullValue) {
      throw new NullPointerException();
    }
    return (int) this.value.get(holder);
  }

}
