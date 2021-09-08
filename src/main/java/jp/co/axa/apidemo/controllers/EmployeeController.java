package jp.co.axa.apidemo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.EmployeeService;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        List<Employee> employees = employeeService.retrieveEmployees();
        return employees;
    }
    
    @GetMapping("/employees/{employeeId}")
    @Cacheable(value = {"employee"}, key = "#employeeId") //can add more parameters ---> condition = "",unless = "",keyGenerator = "" 
    public Employee getEmployee(@PathVariable(name="employeeId")Long employeeId) {
    	return employeeService.getEmployee(employeeId);
    }

    @PostMapping("/employees")
    @CachePut(value = {"employee"}, key = "#result.id")
    public Employee createEmployee(@RequestBody Employee employee){
    	Employee e = employeeService.createEmployee(employee);
        System.out.println("Employee Saved Successfully");
        return e;
    }

    @DeleteMapping("/employees/{employeeId}")
    @CacheEvict(value = {"employee"}, beforeInvocation = true,key="#employeeId")
    public String deleteEmployee(@PathVariable(name="employeeId")Long employeeId){
        employeeService.deleteEmployee(employeeId);
        System.out.println("Employee Deleted Successfully");
        return "success";
    }

    @PutMapping("/employees/{employeeId}")
    @CachePut(value = {"employee"}, key = "#employeeId")
    public Employee updateEmployee(@RequestBody Employee employee,
                               @PathVariable(name="employeeId")Long employeeId){
    	//get record from employeeService
    	Employee emp = employeeService.getEmployee(employeeId);
        if(emp != null){
        	//set data to emp
        	emp.setDepartment(employee.getDepartment());
        	emp.setSalary(employee.getSalary());
        	emp.setName(employee.getName());
        	//update record
            employeeService.updateEmployee(emp);
        }
        return emp;
    }

}
