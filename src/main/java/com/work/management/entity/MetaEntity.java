package com.work.management.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MetaEntity {

  private OperationType operationType;
  private EntityType entityType;
  private Object entity;

}
