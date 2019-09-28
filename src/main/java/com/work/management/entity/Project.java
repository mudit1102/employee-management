package com.work.management.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * This class represents a single row in Projects Table.
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PROJECTS")
@EntityListeners(AuditingEntityListener.class)
public final class Project {

  @Column(name = "name")
  String name;

  @Id
  @Column(name = "id")
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  String id;

  @Column(name = "owner")
  Integer owner;

  @Column(name = "startDate")
  Date startDate;

  @Column(name = "endDate")
  Date endDate;

  @CreatedDate
  @Column(name = "createdAtTimeStamp")
  Date createdAtTimeStamp;

  @LastModifiedDate
  @Column(name = "lastUpdatedTimeStamp")
  Date lastUpdatedTimeStamp;

  @Column(name = "lastUpdatedBy")
  Integer lastUpdatedBy;

  @Column(name = "createdBy")
  Integer createdBy;

}
