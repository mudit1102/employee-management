package com.work.management;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

import com.work.management.dto.EmployeeDto;
import com.work.management.entity.Employee;
import com.work.management.repository.EmployeeRepository;
import com.work.management.service.employee.impl.EmployeeServiceImpl;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class EmployeeServiceTest {

  @InjectMocks
  private EmployeeServiceImpl employeeService;

  @Mock
  private EmployeeRepository employeeRepository;

  @Test
  public void getEmployeeByUserName() {
    Employee employee = Employee.builder().firstName("Mudit").lastName("Tanwar")
        .userName("Mudit" + "." + "Tanwar").teamId("TRM").phoneNumber("9654092992").manager(3)
        .teamId("4fbb7b55-9827-452b-ad22-24692b3476d9").build();

    EmployeeDto expectedEmployeeDto = new EmployeeDto();
    BeanUtils.copyProperties(employee, expectedEmployeeDto);
    Optional<Employee> optionalEmployee = Optional.of(employee);

    //Creating stub
    doReturn(optionalEmployee).when(employeeRepository)
        .findByUserName(expectedEmployeeDto.getUserName());

    EmployeeDto actualEmployeeDto = employeeService
        .getEmployeeByUserName(expectedEmployeeDto.getUserName());

    assertEquals(expectedEmployeeDto, actualEmployeeDto);

    ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
    Mockito.verify(employeeRepository).findByUserName(captor.capture());
    String username = captor.getValue();
    assertEquals(employee.getUserName(), username);
  }
}

