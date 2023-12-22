package com.hiringplatform.Contest.testController;

import com.hiringplatform.Contest.Controller.EmployeeController;
import com.hiringplatform.Contest.Service.ControllerService.EmployeeService;
import com.hiringplatform.Contest.model.Employee;
import com.hiringplatform.Contest.model.Guest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@ExtendWith(MockitoExtension.class)
public class TestEmployeeController {
    @Mock
    EmployeeService employeeService;
    @InjectMocks
    EmployeeController employeeController;

    @Test
    void testGetCandidatesDetails() {
        Guest testGuest = new Guest();
        testGuest.setUserId(1);
        testGuest.setName("Alice");
        Mockito.when(employeeService.getCandidatesDetailsService(1)).thenReturn(testGuest);
        Guest result = employeeController.getCandidatesDetails(1);
        assertEquals(testGuest, result);
        Mockito.verify(employeeService).getCandidatesDetailsService(1);
    }

    @Test
    void testGetCandidates(){
        Employee employee1=new Employee();employee1.setEid(1);
        Employee employee2=new Employee();employee2.setEid(2);
        Guest guest1=new Guest();
        guest1.setUserId(1);
        guest1.setEmployee(employee1);
        Guest guest2=new Guest();
        guest2.setUserId(2);
        guest2.setEmployee(employee1);
        List<Guest> guestList1=new ArrayList<>();
        guestList1.add(guest1);guestList1.add(guest2);
        employee1.setGuest2(guestList1);
        Guest guest3=new Guest();
        guest3.setUserId(3);
        guest3.setEmployee(employee2);
        List<Guest> guestList2=new ArrayList<>();
        guestList2.add(guest3);
        employee2.setGuest2(guestList2);
        Mockito.when(employeeService.getCandidatesService(employee1.getEid())).thenReturn(guestList1);
        List<Guest> result=employeeController.getCandidates(employee1.getEid());
        assertEquals(guestList1,result);
    }
    @Test
    void getFeedback(){
        Guest guest=new Guest();
        guest.setUserId(1);guest.setUserFeedback("good");guest.setAdminFeedback("bad");

    }
}
