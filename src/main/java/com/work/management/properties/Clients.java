package com.work.management.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Clients {

  private String hostname;
  private String scheme;
  private int httpPort;

}
