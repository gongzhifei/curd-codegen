package com.justdebugit.codegen.common;

import java.util.Map;

import com.google.common.collect.Maps;

public class MysqlTypeRepo {
  
 private static Map<String, String> typeMap = Maps.newHashMap();
  
  static{
    typeMap.put("bigint", "java.lang.Long");
    typeMap.put("tinyint", "java.lang.Integer");
    typeMap.put("int", "java.lang.Integer");
    typeMap.put("text", "java.lang.String");
    typeMap.put("varchar", "java.lang.String");
    typeMap.put("char", "java.lang.String");
    typeMap.put("timestamp", "java.util.Date");
    typeMap.put("date", "java.util.Date");
    typeMap.put("datetime", "java.util.Date");
    typeMap.put("smallint", "java.lang.Integer");
  }

  public static String getTypeName(String mysqlType) {
    String type =  typeMap.get(mysqlType);
    if (type == null) {
      throw new IllegalArgumentException("Can not find mysqlType mapping " + mysqlType);
    }
    return type;
  }
}
