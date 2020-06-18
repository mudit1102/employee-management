package com.work.management.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Index {

  private String name;
  private int shard;
  private int replica;
  private int from;
  private int size;
  private int timeout;
}
