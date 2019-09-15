package com.work.management.service.impl;

import com.work.management.entity.Employees;
import com.work.management.repository.EmployeeRepository;
import com.work.management.resource.EmployeeResource;
import com.work.management.service.DataPersistingService;
import com.work.management.utils.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeRegistrationService implements DataPersistingService<EmployeeResource, Employees> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeResource preHandle(EmployeeResource document) {
        logger.info("PreHandling employee{} ", document.getFirstName() + " " + document.getLastName());
        if(Objects.nonNull(employeeRepository.findByPhoneNumber(document.getPhoneNumber()))){
            ExceptionUtils.throwEntityAlreadyExistsException("Employee already exists, choose a different phone Number");
        }
        return document;
    }

    @Override
    public Employees handle(EmployeeResource document) {
        logger.info("Handling employee{} ", document.getFirstName() + " " + document.getLastName());
        Employees employees = new Employees();
        employees.setFirstName(document.getFirstName());
        employees.setLastName(document.getLastName());
        employees.setUsername(document.getFirstName()+"."+document.getLastName());
        employees.setPhoneNumber(document.getPhoneNumber());
        employees.setCreatedAtTimeStamp(new Date());
        employees.setLastUpdatedTimeStamp(new Date());
        employees.setManager(document.getManager());
        employees.setCreatedBy(document.getManager());
        employees.setLastUpdatedBy(document.getManager());
        employees.setTeamID(document.getTeamId());
        return employees;
    }

    @Override
    public void postHandle(Employees document) {

    }
}
