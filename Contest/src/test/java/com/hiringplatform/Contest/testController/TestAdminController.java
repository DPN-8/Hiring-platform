package com.hiringplatform.Contest.testController;

import com.hiringplatform.Contest.Controller.AdminController;
import com.hiringplatform.Contest.Service.ControllerService.AdminService;
import com.hiringplatform.Contest.model.Contest;
import com.hiringplatform.Contest.model.Employee;
import com.hiringplatform.Contest.model.Part;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TestAdminController {

    @Mock
    AdminService adminService;
    @InjectMocks
    AdminController adminController;

    @Test
    void testAddEmp() {
        Employee employee = new Employee();
        adminController.addEmp(employee);
        Mockito.verify(adminService).addEmpService(employee);
    }

    @Test
    void testAddContest() {
        Contest contest = new Contest();
        Mockito.when(adminService.addContestService(contest)).thenReturn(1);
        int result = adminController.addContest(contest);
        assertEquals(1, result);
        Mockito.verify(adminService).addContestService(contest);
    }

    @Test
    void testAddPart() throws IOException {
        int contestId = 1;
        String part = "{\"Category\":\"programming\",\"Easy\":\"10\",\"Medium\":\"20\",\"Hard\":\"30\"}";
        List<Part> partList = new ArrayList<>();
        Mockito.when(adminService.addPartService(contestId, part)).thenReturn(partList);
        List<Part> result = adminController.addPart(contestId, part);
        // Assert the result returned by the controller
        assertEquals(partList, result);
        // Verify that the service method was called
        Mockito.verify(adminService).addPartService(contestId, part);
    }

    @Test
    void testGetEmployee(){
        List<Employee> employeeList=new ArrayList<>();
        employeeList.add(new Employee());
        Mockito.when(adminService.getEmployeeService()).thenReturn(employeeList);
        List<Employee> result=adminController.getEmployee();
        assertEquals(employeeList,result);
        Mockito.verify(adminService).getEmployeeService();
    }
    @Test
    void testGetLiveContest() {
        List<Contest> contestList = new ArrayList<>();
        contestList.add(new Contest());
        Mockito.when(adminService.getLiveContestService()).thenReturn(contestList);
        List<Contest> result = adminController.getLiveContest();
        assertEquals(contestList, result);
//        System.out.println(result);
        Mockito.verify(adminService).getLiveContestService();
    }

    @Test
    void testGetContestLog() {
        List<Contest> contestList = new ArrayList<>();
        Mockito.when(adminService.getContestService()).thenReturn(contestList);
        List<Contest> result = adminController.getContest();
        // Assert the result returned by the controller
        assertEquals(contestList, result);
        // Verify that the service method was called
        Mockito.verify(adminService).getContestService();
    }


}
