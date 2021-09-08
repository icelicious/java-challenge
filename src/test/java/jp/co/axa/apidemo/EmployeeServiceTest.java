package jp.co.axa.apidemo;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.test.context.support.WithMockUser;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.exceptions.BizException;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import jp.co.axa.apidemo.services.EmployeeServiceImpl;
/**
 * @description: EmployeeServiceTest
 * @author: Li
 * @version: v1.0
 */
public class EmployeeServiceTest extends ApiDemoApplicationTests{

    @Mock   // mock object
    private EmployeeRepository employeeRepository;

    @InjectMocks    // module inject
    private EmployeeServiceImpl employeeServiceImpl;    
    
    @Before
	public void setUp() throws Exception {
    	super.setUp();
		MockitoAnnotations.initMocks(this);
    }
    @Test
    @WithMockUser(username = "admin", password = "admin",roles = {"ADMIN","USER"})
    public void test_retrieveEmployees() {
    	Mockito.when(employeeRepository.findAll()).thenReturn(super.employees);
    	List<Employee> employees = employeeRepository.findAll();
        // reslut check
    	Assert.assertEquals(true,employees.get(0).getName().equals("Steven1"));
		Assert.assertEquals(true,employees.get(0).getId() == 1L);
		Assert.assertEquals(true,employees.get(0).getDepartment().equals("HR"));
		Assert.assertEquals(true,employees.get(0).getSalary() == 10000);
    }

    @Test
    @WithMockUser(username = "admin", password = "admin",roles = {"ADMIN","USER"})
    public void test_getEmployee() {
    	Optional<Employee> opt = Optional.of(super.employees.get(0));
    	Mockito.when(employeeRepository.findById(1L)).thenReturn(opt);
    	Optional<Employee> employee = employeeRepository.findById(1L);
    	// reslut check
    	if(employee.get() != null) {
    		Assert.assertEquals(true,employee.get().getName().equals("Steven1"));
    		Assert.assertEquals(true,employee.get().getId() == 1L);
    		Assert.assertEquals(true,employee.get().getDepartment().equals("HR"));
    		Assert.assertEquals(true,employee.get().getSalary() == 10000);
    	}else {
    		//null case
    		throw new BizException("error case");
    	}
    }

    @Test
    @WithMockUser(username = "admin", password = "admin",roles = {"ADMIN","USER"})
    public void test_createEmployee() {
    	//init insert data
    	Employee employee = new Employee();
    	employee.setId(2L);
		employee.setName("Steven2");
		employee.setSalary(20000);
		employee.setDepartment("IT");
		Mockito.when(employeeRepository.save(employee)).thenReturn(employee);
		Employee res = employeeRepository.save(employee);
    	// reslut check
    	if(res != null) {
    		Assert.assertEquals(true,res.getName().equals("Steven2"));
    		Assert.assertEquals(true,res.getId() == 2L);
    		Assert.assertEquals(true,res.getDepartment().equals("IT"));
    		Assert.assertEquals(true,res.getSalary() == 20000);
    	}else {
    		//null case
    		throw new BizException("error case");
    	}
    }
    
    @Test
    @WithMockUser(username = "admin", password = "admin",roles = {"ADMIN","USER"})
    public void test_updateEmployee() {
    	//init update data
    	Employee employee = new Employee();
    	employee.setId(1L);
		employee.setName("Steven3");
		employee.setSalary(30000);
		employee.setDepartment("SALES");
		Mockito.when(employeeRepository.save(employee)).thenReturn(employee);
		Employee res = employeeRepository.save(employee);
		// reslut check
		
    	if(res != null) {
    		Assert.assertEquals(true,res.getName().equals("Steven3"));
    		Assert.assertEquals(true,res.getId() == 1L);
    		Assert.assertEquals(true,res.getDepartment().equals("SALES"));
    		Assert.assertEquals(true,res.getSalary() == 30000);
    	}else {
    		//null case
    		throw new BizException("error case");
    	}
		
    }

}