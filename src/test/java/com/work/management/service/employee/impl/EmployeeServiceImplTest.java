package com.work.management.service.employee.impl;


import static com.google.common.collect.ImmutableList.toImmutableList;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.work.management.dto.BulkEmployeeDto;
import com.work.management.dto.EmployeeDto;
import com.work.management.entity.Employee;
import com.work.management.exceptions.BadRequestException;
import com.work.management.service.employee.EmployeeService;
import com.work.management.web.rest.resource.AcceptedFields;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public final class EmployeeServiceImplTest {

  private static final Employee EMPLOYEE = getEmployee();

  @Resource
  private EmployeeService employeeService;

  @Test
  public void getEmployeeByUserName_forExistingEmployee_returnsEmployee() {
    EmployeeDto employeeDto = getEmployeeDto(EMPLOYEE);
    employeeService.save(employeeDto);

    EmployeeDto actualEmployeeDto = employeeService
        .getEmployeeByUserName(employeeDto.getUserName());

    assertThat(actualEmployeeDto).isEqualTo(employeeDto);
  }

  @Test
  public void updateEmployeeEntity_forExistingEmployee_returnsUpdatedEmployee() {
    EmployeeDto employeeDto = getEmployeeDto(EMPLOYEE);
    employeeService.save(employeeDto);

    employeeDto.setPhoneNumber("9999745902");
    EmployeeDto actualEmployeeDto = employeeService.updateEmployeeEntity(employeeDto);

    assertThat(actualEmployeeDto).isEqualTo(employeeDto);
  }

  @Test
  public void bulkUpdate_forExistingEmployees_returnUpdatedEmployeesList() throws Exception {
    ImmutableList<Employee> employeeList = getEmployeeList();
    employeeList.forEach(employee -> employeeService.save(getEmployeeDto(employee)));

    ImmutableList<Employee> actualBulkUpdatedEmployeesInfo = employeeService
        .bulkUpdate(getBulkEmployeeDto());
    ImmutableList<Employee> updatedEmployeesInfo = employeeList.stream()
        .map(employee -> employeeService.getEmployeeByUserName(employee.getUserName()))
        .map(EmployeeServiceImplTest::getEmployee)
        .collect(toImmutableList());

    assertThat(actualBulkUpdatedEmployeesInfo).isEqualTo(updatedEmployeesInfo);
  }

  @Test
  public void bulkUpdate_forDuplicateEmployee_throwsBadRequestException() {
    BadRequestException exception = assertThrows(BadRequestException.class,
        () -> employeeService.bulkUpdate(getDuplicateBulkEmployeeDto()));
    assertThat(exception).hasMessageThat().contains("Duplicate employee ids exist");
  }

  private static Employee getEmployee(EmployeeDto employeeDto) {
    Employee employee = new Employee();
    BeanUtils.copyProperties(employeeDto, employee);
    return employee;
  }

  private static Employee getEmployee() {
    return Employee.builder().firstName("Mudit").lastName("Tanwar").userName("Mudit.Tanwar")
        .teamId("c7f6fbab-22fb-41bc-9300-0cc27c0de5c5")
        .phoneNumber("9654092992").manager(3)
        .build();
  }

  private static ImmutableList<Employee> getEmployeeList() {
    return ImmutableList.of(
        Employee.builder().firstName("Mudit").lastName("Tanwar").userName("Mudit.Tanwar")
            .teamId("c7f6fbab-22fb-41bc-9300-0cc27c0de5c5").phoneNumber("9654092992").manager(3)
            .build(),
        Employee.builder().firstName("Ankit").lastName("Mathur").userName("Ankit.Mathur")
            .teamId("c7f6fbab-22fb-41bc-9300-0cc27c0de5c6").phoneNumber("9654092990").manager(4)
            .build(),
        Employee.builder().firstName("Vishesh").lastName("Wason").userName("Vishesh.Wason")
            .teamId("c7f6fbab-22fb-41bc-9300-0cc27c0de5c9").phoneNumber("9654092934").manager(5)
            .build());
  }

  private static EmployeeDto getEmployeeDto(Employee employee) {
    EmployeeDto employeeDto = new EmployeeDto();
    BeanUtils.copyProperties(employee, employeeDto);
    return employeeDto;
  }

  private static BulkEmployeeDto getBulkEmployeeDto() {
    return BulkEmployeeDto.builder()
        .employeeIds(ImmutableList.of(1, 2, 3))
        .acceptedFieldsMap(getAcceptedFields()).build();
  }

  private static ImmutableMap<AcceptedFields, String> getAcceptedFields() {
    return ImmutableMap.<AcceptedFields, String>builder()
        .put(AcceptedFields.MANAGER_ID, "10")
        .put(AcceptedFields.TEAM_ID, "c7f6fbab-22fb-41bc-9300-0cc27c0de5c5").build();
  }

  private static BulkEmployeeDto getDuplicateBulkEmployeeDto() {
    return BulkEmployeeDto.builder()
        .employeeIds(ImmutableList.of(1, 2, 1))
        .acceptedFieldsMap(getAcceptedFields()).build();
  }
}

