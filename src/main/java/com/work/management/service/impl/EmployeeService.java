package com.work.management.service.impl;

import com.work.management.entity.Employee;
import com.work.management.repository.EmployeeRepository;
import com.work.management.web.rest.resource.EmployeeResource;
import com.work.management.service.DataPersistingService;
import com.work.management.utils.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public final class EmployeeService implements DataPersistingService<EmployeeResource, Employee> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public EmployeeResource preHandle(EmployeeResource document) {
        logger.info("PreHandling employee{} ", document.getFirstName() + "." + document.getLastName());
        if (Objects.nonNull(employeeRepository.findByPhoneNumber(document.getPhoneNumber()))) {
            ExceptionUtils.throwEntityAlreadyExistsException("Employee already exists, choose a different phone Number");
        }
        return document;
    }

    @Override
    public Employee handle(EmployeeResource document) {
        logger.info("Handling employee{} ", document.getFirstName() + " " + document.getLastName());
        return Employee.builder().firstName(document.getFirstName()).lastName(document.getLastName()).manager(document.getManager()).
                phoneNumber(document.getPhoneNumber()).createdBy(document.getManager()).createdAtTimeStamp(new Date()).lastUpdatedTimeStamp(new Date()).
                lastUpdatedBy(document.getManager()).userName(document.getFirstName() + "." + document.getLastName()).teamID(document.getTeamId()).build();
    }

    @Override
    public void postHandle(Employee document) {
        logger.info("postHandling employee{} ", document.getFirstName() + " " + document.getLastName());
        employeeRepository.save(document);
    }
}
