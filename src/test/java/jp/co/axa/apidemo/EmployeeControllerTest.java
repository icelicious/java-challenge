package jp.co.axa.apidemo;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import jp.co.axa.apidemo.controllers.EmployeeController;
import jp.co.axa.apidemo.services.EmployeeService;

/**
 * @description: EmployeeControllerTest
 * @author: Li
 * @version: v1.0
 */
public class EmployeeControllerTest extends ApiDemoApplicationTests{
	private static Logger logger = LoggerFactory.getLogger(EmployeeControllerTest.class);
	@Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		//init mock
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }
		
	@Test
	@WithMockUser(username = "admin", password = "admin")
    public void test_getEmployees() throws Exception{
        Mockito.when(employeeService.retrieveEmployees()).thenReturn(super.employees);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employees").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andReturn();
        String expected = "[{\"id\":1,\"name\":\"Steven1\",\"salary\":10000,\"department\":\"HR\"}]";
        logger.info(result.getResponse().getContentAsString());
        Assert.assertEquals(200,result.getResponse().getStatus());
        JSONAssert.assertEquals(expected,result.getResponse().getContentAsString(),false);
    }
	
	@Test
	@WithMockUser(username = "admin", password = "admin")
    public void test_getEmployee() throws Exception{
        Mockito.when(employeeService.getEmployee(1L)).thenReturn(employees.get(0));
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employees/1").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andReturn();
        String expected = "{\"id\":1,\"name\":\"Steven1\",\"salary\":10000,\"department\":\"HR\"}";
        logger.info(result.getResponse().getContentAsString());
        Assert.assertEquals(200,result.getResponse().getStatus());
        JSONAssert.assertEquals(expected,result.getResponse().getContentAsString(),false);
    }
	
	@Test
	@WithMockUser(username = "admin", password = "admin",roles = {"ADMIN","USER"})
    public void test_createEmployee() throws Exception{
		String input = "{\"name\":\"Steven1\",\"salary\":10000,\"department\":\"HR\"}";
        Mockito.when(employeeService.createEmployee(employees.get(0))).thenReturn(employees.get(0));
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/employees").content(input).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
        logger.info(result.getResponse().getContentAsString());
    }
	
	@Test
	@WithMockUser(username = "admin", password = "admin",roles = {"ADMIN","USER"})
    public void test_updateEmployee() throws Exception{
		String input = "{\"name\":\"Steven2\",\"salary\":20000,\"department\":\"IT\"}";
        Mockito.when(employeeService.updateEmployee(employees.get(0))).thenReturn(employees.get(0));
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/employees/1").content(input).contentType(MediaType.APPLICATION_JSON)).andReturn();
        logger.info(result.getResponse().getContentAsString());
        Assert.assertEquals(200,result.getResponse().getStatus());
    }
	
	@Test
	@WithMockUser(username = "admin", password = "admin",roles = {"ADMIN","USER"})
    public void test_deleteEmployee() throws Exception{
		String input = "{\"name\":\"Steven1\",\"salary\":10000,\"department\":\"HR\"}";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/employees/1").content(input).contentType(MediaType.APPLICATION_JSON)).andReturn();
        logger.info(result.getResponse().getContentAsString());
        Assert.assertEquals(200,result.getResponse().getStatus());
    }
}
