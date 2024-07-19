package com.dux.security.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum SecurityResponseCode {
  TOKEN_UUID_DECODED(1), TOKEN_SERVICE_FAILED(2);

  private static final Map<String, SecurityResponseCode> BY_LABEL = new HashMap<>();

  static {
    for (SecurityResponseCode e : values()) {
      BY_LABEL.put(e.label.toString(), e);
    }
  }

  @JsonValue
  public final Integer label;

  private SecurityResponseCode(Integer label) {
    this.label = label;
  }

  public static SecurityResponseCode valueOfLabel(Integer label) {
    return BY_LABEL.get(label.toString());
  }

}
