package com.dux.backproxy.exception.errormanager;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TranslateErrorRequest {

  private String sysCode;
  private String module;
  private String subModule;
  private String rawCode;
  private String subRawCode;
  private String data;
  private Object[] parameters;
}
