package com.hiringplatform.Contest.Service.ControllerService;

import com.hiringplatform.Contest.Service.CustomService.AdminServices;
import com.hiringplatform.Contest.model.*;
import com.hiringplatform.Contest.model.DTO.MailDTO;
import com.hiringplatform.Contest.model.DTO.ResponseDTO;
import com.hiringplatform.Contest.repos.*;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class AdminService {
    @Autowired
    Employeerepository employeerepository;

    @Autowired
    private AdminServices adminServices;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    Contestrepository contestrepository;
    @Autowired
    Guestrepository guestrepository;
    @Autowired
    Partrepository partrepository;
    @Autowired
    Weightagerepository weightagerepository;

    public void addEmpService(Employee employee){
        employee.setRole(employee.getRole());
        employeerepository.save(employee);
    }
    public int addContestService(Contest contest){
        log.info(contest.toString());
        contestrepository.save(contest);
        return contest.getCid();
    }

    public List<Contest> getLiveContestService(){
        Timestamp current = new Timestamp(System.currentTimeMillis());
        return contestrepository.findByEndTimeAfter(current);
    }

    public List<Contest> getContestService(){
        Timestamp current = new Timestamp(System.currentTimeMillis());
        return contestrepository.findByEndTimeBefore(current);
    }
    public void addUserService(int ContestId,Guest guest){
        guest.setTotalMarks(-1);
        guest.setContest(contestrepository.findById(ContestId).get());
        guest.setRole(guest.getRole());
        System.out.println(guest.getRole());
        guestrepository.save(guest);
    }
    public List<Part> addPartService(int ContestId,String Part ) throws IOException {
        Object file= JSONValue.parse(Part);
        JSONObject jsonObject=(JSONObject)file;
        Part part=new Part();
        part.setName((String)jsonObject.get("Category"));
        Weightage weightage=new Weightage();
        Integer easy=Integer.parseInt((String)jsonObject.get("Easy"));
        Integer medium=Integer.parseInt((String)jsonObject.get("Medium"));
        Integer hard=Integer.parseInt((String)jsonObject.get("Hard"));
        weightage.setEasy(easy);
        weightage.setHard(hard);
        weightage.setMedium(medium);
        int totalScore=easy+medium+hard;
        weightagerepository.save(weightage);
        part.setWeight(weightage);
        Contest contest= contestrepository.findById(ContestId).get();
        if(jsonObject.getAsString("Category").equals("programming")) {
            contest.setTotalScore((totalScore * 10)+contest.getTotalScore());
            String score = contest.getScoreRules();
            if (score == null)
                score = "";
            score = score + "Coding: " + score + " marks";
            contest.setScoreRules(score);
        } else {
            contest.setTotalScore(totalScore+contest.getTotalScore());
            String score = contest.getScoreRules();
            if (score == null)
                score = "";
            if(jsonObject.getAsString("Category").equals("coding"))
            score = score + "Programming: " + totalScore + " marks";
            else score=score+jsonObject.getAsString("Category")+totalScore+"marks";
            contest.setScoreRules(score);
        }
        part.setContest1(contest);
        List<Part> partList = contest.getPart1();
        partList.add(part);
        contest.setPart1(partList);
        contestrepository.save(contest);
        partrepository.save(part);
        return partrepository.findByContest1(contest);
    }
    public List<Employee> getEmployeeService(){
        return employeerepository.findAll();
    }

    public ResponseEntity<ResponseDTO> addAdmin(Employee employee) {
            employee.setPassword(passwordEncoder.encode(employee.getPassword()));
            employee.setRole(Role.ADMIN);
            employeerepository.save(employee);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK, "Employee added" , employee));
    }

    public ResponseEntity<ResponseDTO> sendEmail(MailDTO mailStructure, int cid, int mark) {
        return adminServices.sendMails(mailStructure, cid, mark);
    }

    public ResponseEntity<ResponseDTO> getParts(Contest contestId) {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK, "MCQ Parts " , partrepository.getParts(contestId)));
    }

    public ResponseEntity<ResponseDTO> getCodingParts(Contest contest) {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK, "Coding Parts " , partrepository.getCodingParts(contest)));
    }

    public ResponseEntity<ResponseDTO> getUserById(int id) {
        Contest contest = contestrepository.findById(id).get();
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK, "Users " , guestrepository.getUserByContest(contest)));
    }

    public ResponseEntity<ResponseDTO> getContest(int id) {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK, "Users " , contestrepository.findById(id)));

    }

    public ResponseEntity<ResponseDTO> viewParticipantService(int ContestId,int percentage){
        Contest contest=contestrepository.findById(ContestId).get();
        contest.setPassPercentage(percentage);
        contestrepository.save(contest);
        int mark=contest.getTotalScore();
        int passmark = (percentage*mark)/100;
        int nonparticipants = guestrepository.findByFail(contestrepository.findById(ContestId).get(),-1).size();
        int pass = guestrepository.findByPassMarks(contestrepository.findById(ContestId).get(),passmark).size();
        int participants = guestrepository.findByContestOrderByTotalMarksDesc(contestrepository.findById(ContestId).get()).size()-nonparticipants;
        int fail = participants-pass;
        Map<String, Integer> map = new HashMap<>();
        map.put("participants",participants);
        map.put("Na",nonparticipants);
        map.put("pass", pass);
        map.put("fail", fail);
        JSONObject jsonObject = new JSONObject(map);
        System.out.println(map);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK, "Users " , jsonObject));
    }
}
