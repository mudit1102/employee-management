package com.work.management.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
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


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TEAMS")
public final class Team {

  @Column(name = "name")
  private String name;

  @Id
  @Column(name = "id")
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  private String id;

  @Column(name = "manager")
  private Integer manager;

  @CreatedDate
  @Column(name = "createdAtTimeStamp")
  private Date createdAtTimeStamp;

  @LastModifiedDate
  @Column(name = "lastUpdatedTimeStamp")
  private Date lastUpdatedTimeStamp;

  @Column(name = "lastUpdatedBy")
  private Integer lastUpdatedBy;

  @Column(name = "createdBy")
  private Integer createdBy;

  @Convert(converter = ListConverter.class)
  @Column(name = "employeeIds")
  private List<String> employeeIds;

}
