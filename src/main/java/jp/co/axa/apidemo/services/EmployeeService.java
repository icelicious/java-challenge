package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;

import java.util.List;

public interface EmployeeService {
	/*****************************read*****************************************/
    public List<Employee> retrieveEmployees();
    public Employee getEmployee(Long id);    
    /*****************************create****************************************/
    public Employee createEmployee(Employee employee);
    /*****************************delete****************************************/
    public void deleteEmployee(Long id);
    /*****************************update****************************************/
    public Employee updateEmployee(Employee employee);
}