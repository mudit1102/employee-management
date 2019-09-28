package com.work.management.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "EMPLOYEES")
public final class Employee {

  @Column(name = "firstName")
  private String firstName;

  @Column(name = "lastName")
  private String lastName;

  @Column(name = "userName")
  private String userName;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Integer id;

  @Column(name = "manager")
  private Integer manager;

  @Column(name = "teamId")
  private String teamId;

  @Column(name = "phoneNumber")
  private String phoneNumber;

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

}
