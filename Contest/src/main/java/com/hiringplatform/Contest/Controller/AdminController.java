package com.hiringplatform.Contest.Controller;

import com.hiringplatform.Contest.Service.ControllerService.AdminService;
import com.hiringplatform.Contest.model.*;
import com.hiringplatform.Contest.model.DTO.MailDTO;
import com.hiringplatform.Contest.model.DTO.ResponseDTO;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/admin")
public class AdminController {
     @Autowired
    AdminService adminService;
    @Autowired
    private LoginController loginController;

    @PostMapping("/addAdmin")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ResponseDTO> addAdmin(@RequestBody Employee employee) {
        return adminService.addAdmin(employee);
    }

    @PostMapping("/addEmp")
    public void addEmp(@RequestBody Employee employee){
    adminService.addEmpService(employee);
    }

    @PostMapping("/addContest")
    public int addContest(@RequestBody Contest contest){
        log.info("Adding contest");
        return adminService.addContestService(contest);
    }

    @GetMapping("/getLiveContest")
    public List<Contest> getLiveContest(){
       return adminService.getLiveContestService();
    }

    @GetMapping("/getContest/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ResponseDTO> getContest(@PathVariable int id) {
        return adminService.getContest(id);
    }

    @GetMapping("/getContestLog")
    public List<Contest> getContest(){
        return adminService.getContestService();
    }



    @PostMapping("/addUser/{Cid}")
    public void addUser(@PathVariable int ContestId, @RequestBody Guest guest){
        adminService.addUserService(ContestId,guest);
    }
    @Transactional
    @PostMapping("/addPart/{Cid}")
    public List<Part> addPart(@PathVariable int Cid, @RequestBody String Part ) throws IOException {
        return adminService.addPartService(Cid,Part);
    }

    @GetMapping("/getEmployee")
    public List<Employee> getEmployee(){
        return adminService.getEmployeeService();
    }

    @PostMapping("/send/{cid}/{mark}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ResponseDTO> sendEmail(@RequestBody MailDTO mailStructure, @PathVariable("cid") int cid, @PathVariable("mark") int mark) {
        return adminService.sendEmail(mailStructure, cid, mark);
    }

    @GetMapping("/getMcqParts/{Cid}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ResponseDTO> getParts(@PathVariable("Cid") Contest contestId) {
        return adminService.getParts(contestId);
    }

    @GetMapping("getCoding/{Cid}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ResponseDTO> getCodingParts(@PathVariable("Cid") Contest contest) {
        return adminService.getCodingParts(contest);
    }

    @GetMapping("/getUser/{cid}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ResponseDTO> getUserByContest(@PathVariable int cid) {
        System.out.println(cid);
        return adminService.getUserById(cid);
    }

    @GetMapping("/pass/{cid}/{pass}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ResponseDTO> viewParticipantService(@PathVariable("cid") int contest, @PathVariable("pass") int pass){
        return adminService.viewParticipantService(contest, pass);
    }




}

