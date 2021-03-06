package net.guides.springboot2.crud;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.*; 
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith; 
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import net.guides.springboot2.crud.controller.EmployeeController;
import net.guides.springboot2.crud.model.Employee;
import net.guides.springboot2.crud.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
class Springboot2MssqlJpaHibernateCrudExampleApplicationTests {

	private MockMvc mockMvc;
	
	ObjectMapper objectMapper = new ObjectMapper();
	ObjectWriter objectWriter = objectMapper.writer();

	@InjectMocks
	private EmployeeController employeeController;
	
	@Mock
	private EmployeeRepository employeeRepository;
	
	
	Employee employee_1 = new Employee(1L,"Sara","l","i");
	Employee employee_2 = new Employee(2L,"Juan","l","i");
	Employee employee_3 = new Employee(3L,"Peter","l","i");
	
	@BeforeAll
	void setUp() {
		MockitoAnnotations.openMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
	}
		
	@Test
	public void getAllEmployees() throws Exception {
		List<Employee> record = new ArrayList<>(Arrays.asList(employee_1,employee_2,employee_3));
		Mockito.when(employeeRepository.findAll()).thenReturn(record);
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/v1/employees")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(3)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName", is("Sara")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].firstName", is("Juan")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[2].firstName", is("Peter")));
	}
	
	//TODO https://youtu.be/KYkEMuA50yE?t=4601
	@Test
	public void getEmployeeById() throws Exception {
		List<Employee> record = new ArrayList<>(Arrays.asList(employee_1,employee_2,employee_3));
		Mockito.when(employeeRepository.findAll()).thenReturn(record);
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/v1/employees")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(3)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName", is("Sara")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].firstName", is("Juan")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[2].firstName", is("Peter")));
	}

}
