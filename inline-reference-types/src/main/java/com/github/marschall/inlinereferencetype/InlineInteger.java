package com.github.marschall.inlinereferencetype;


public interface InlineInteger {
  
  void setValue(Object holder, Integer value);
  
  Integer getValue(Object holder);
  
  void setInt(Object holder, int intValue);
  
  int getInt(Object holder);

}
