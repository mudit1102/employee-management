package com.work.management.service.employee.impl;


import static com.google.common.truth.Truth.assertThat;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.work.management.dto.BulkEmployeeDto;
import com.work.management.dto.EmployeeDto;
import com.work.management.entity.Employee;
import com.work.management.service.employee.EmployeeService;
import com.work.management.web.rest.resource.AcceptedFields;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
  private static final List<Employee> EMPLOYEE_LIST = getEmployeeList();
  private static final Map<AcceptedFields, String> ACCEPTED_FIELDS = getAcceptedFields();
  private static final List<Integer> EMPLOYEE_ID_LIST = getEmployeeIdList();
  private static final BulkEmployeeDto BULK_EMPLOYEE_DTO = getBulkEmployeeDto();

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
  public void bulkUpdate_forExistingEmployees_returnUpdatedEmployeesList() {
    //Saving into DB
    EMPLOYEE_LIST.forEach(employee -> employeeService.save(getEmployeeDto(employee)));

    //Bulk Update
    List<Employee> actualBulkUpdatedEmployees = employeeService.bulkUpdate(BULK_EMPLOYEE_DTO);

    //Getting employee list from the DB
    List<Employee> employeeList = EMPLOYEE_LIST.stream()
        .map(employee -> employeeService.getEmployeeByUserName(employee.getUserName()))
        .map(EmployeeServiceImplTest::getEmployee)
        .collect(
            Collectors.toList());

    assertThat(actualBulkUpdatedEmployees).isEqualTo(employeeList);
  }

  private static EmployeeDto getEmployeeDto(Employee employee) {
    EmployeeDto employeeDto = new EmployeeDto();
    BeanUtils.copyProperties(employee, employeeDto);
    return employeeDto;
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

  private static List<Employee> getEmployeeList() {
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

  private static List<Integer> getEmployeeIdList() {
    return ImmutableList.of(1, 2, 3);
  }

  private static Map<AcceptedFields, String> getAcceptedFields() {
    return ImmutableMap.<AcceptedFields, String>builder().put(AcceptedFields.MANAGER_ID, "10")
        .put(AcceptedFields.TEAM_ID, "c7f6fbab-22fb-41bc-9300-0cc27c0de5c5").build();
  }

  private static BulkEmployeeDto getBulkEmployeeDto() {
    return BulkEmployeeDto.builder()
        .employeeIds(EMPLOYEE_ID_LIST)
        .acceptedFieldsMap(ACCEPTED_FIELDS).build();
  }
}

