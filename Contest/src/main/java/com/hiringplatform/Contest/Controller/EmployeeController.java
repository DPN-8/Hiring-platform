package com.hiringplatform.Contest.Controller;

import com.hiringplatform.Contest.Service.ControllerService.EmployeeService;
import com.hiringplatform.Contest.model.Guest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @GetMapping("/getCandiDetails/{GuestId}")
    public Guest getCandidatesDetails(@PathVariable int GuestId){
        return employeeService.getCandidatesDetailsService(GuestId);
    }
    @GetMapping("/getCandi/{EmployeeId}")
    public List<Guest> getCandidates(@PathVariable int EmployeeId){
        return employeeService.getCandidatesService(EmployeeId);
    }
    @PostMapping("/addFeedback/{GuestId}")
    public void getFeedback(@PathVariable int GuestId, @RequestBody Guest guest){
        employeeService.getFeedbackService(GuestId,guest);
    }
    @GetMapping("/viewParticipant/{ContestId}/{mark}")
    public Object viewParticipant(@PathVariable int ContestId,@PathVariable int mark){
        return employeeService.viewParticipantService(ContestId,mark);
    }
    @GetMapping("/getPassed/{Cid}/{mark}")
    public List<Guest> passed(@PathVariable int ContestId, @PathVariable int mark){
        return employeeService.passedService(ContestId,mark);
    }
}
