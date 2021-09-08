package jp.co.axa.apidemo;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.axa.apidemo.entities.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiDemoApplicationTests {
	@Before
    public void init() {
        System.out.println("----------------test start-----------------");
    }
    List<Employee> employees;
	@Before
	public void setUp() throws Exception {
		//init data
    	employees = new ArrayList<>();
		Employee employee1 = new Employee();
		employee1.setId(1L);
		employee1.setName("Steven1");
		employee1.setSalary(10000);
		employee1.setDepartment("HR");
		employees.add(employee1);
    }
 
    @After
    public void after() {
        System.out.println("----------------test end-----------------");
    }


}
