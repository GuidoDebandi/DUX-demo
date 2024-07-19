package com.dux.backproxy.exception.errormanager;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TranslateErrorResponse {

  private String status;
  private String code;
  private String severity;
  private String type;
  private String description;
  private String detail;
  private Object[] parameters;

}
