package com.hiringplatform.Contest.Service.ControllerService;

import com.hiringplatform.Contest.model.Contest;
import com.hiringplatform.Contest.model.Guest;
import com.hiringplatform.Contest.repos.Contestrepository;
import com.hiringplatform.Contest.repos.Employeerepository;
import com.hiringplatform.Contest.repos.Guestrepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {
    @Autowired
    Employeerepository employeerepository;
    @Autowired
    Guestrepository guestrepository;
    @Autowired
    Contestrepository contestrepository;
    public Guest getCandidatesDetailsService(int GuestId){
        return guestrepository.findById(GuestId).get();
    }
    public List<Guest> getCandidatesService(int EmployeeId){
        return employeerepository.findById(EmployeeId).get().getGuest2();
    }
    public void getFeedbackService(int GuestId,Guest guest){
        Guest guest1 = guestrepository.findById(GuestId).get();
        guest1.setUserFeedback(guest.getUserFeedback());
        guest1.setAdminFeedback(guest.getAdminFeedback());
        guestrepository.save(guest);
    }
    public Object viewParticipantService(int ContestId,int percentage){
        Contest contest=contestrepository.findById(ContestId).get();
        contest.setPassPercentage(percentage);
        contestrepository.save(contest);
        int mark=contest.getTotalScore();
        int passmark=(percentage*mark)/100;
        int nonparticipants=guestrepository.findByFail(contestrepository.findById(ContestId).get(),-1).size();
        int pass=guestrepository.findByMarks(ContestId,passmark).size();
        int participants=guestrepository.findByContestOrderByTotalMarksDesc(contestrepository.findById(ContestId).get()).size()-nonparticipants;
        int fail=participants-pass;
        Map<String, Integer> map = new HashMap<>();
        map.put("participants",participants);
        map.put("Non participants",nonparticipants);
        map.put("pass", pass);
        map.put("fail", fail);
        JSONObject jsonObject = new JSONObject(map);
        return jsonObject;
    }

    public List<Guest> passedService(int ContestId,int mark){
        Contest contest=contestrepository.findById(ContestId).get();
        contest.setPassPercentage(mark);
        mark=(mark*contest.getTotalScore())/100;
        return guestrepository.findByMarks(ContestId,mark);
    }
}
