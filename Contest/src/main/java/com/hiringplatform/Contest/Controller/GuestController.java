package com.hiringplatform.Contest.Controller;

import com.hiringplatform.Contest.Service.ControllerService.GuestService;
import com.hiringplatform.Contest.model.Contest;
import com.hiringplatform.Contest.model.DTO.LoginDTO;
import com.hiringplatform.Contest.model.DTO.ResponseDTO;
import com.hiringplatform.Contest.model.Guest;
import com.hiringplatform.Contest.repos.CodeQuestionrepository;
import com.hiringplatform.Contest.repos.Contestrepository;
import com.hiringplatform.Contest.repos.McqQuestionrepository;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/guest")
@Slf4j
public class GuestController {
    @Autowired
    GuestService guestService;
    @Autowired
    CodeQuestionrepository codeQuestionrepository;
    @Autowired
    McqQuestionrepository mcqQuestionrepository;

    @Autowired
    private Contestrepository contestrepository;

    @PostMapping("/login")
    public Object Login(@RequestBody LoginDTO loginDTO) {
       return guestService.loginService(loginDTO);
    }

    @GetMapping("/getDetails")
    public ResponseEntity<?> getDetails(Guest guest) {
        log.info("Getting details");
        return guestService.getDetails(guest);
    }

    @PostMapping("/signup")
    public void signUp(@RequestBody Guest guest){
        guestService.signupService(guest);
    }
    @GetMapping("/getContestDetails/{ContestId}")
    public Contest getContestDetails(@PathVariable int ContestId){
        return guestService.getContest(ContestId);
    }
    @GetMapping("/viewScore/{ContestId}")
    public List<Guest> viewScore(@PathVariable int ContestId){
        return guestService.viewscoreService(ContestId);
    }
    @PostMapping("/evaluate/{GuestId}")
    public void evaluate(@PathVariable int GuestId,@RequestBody String output){
        guestService.evaluateService(GuestId,output);
    }
    @PostMapping("/codeEvaluate/{GuestId}/{codeQuestionId}")
    public JSONObject codeEvaluate(@PathVariable int GuestId,@PathVariable String codeQuestionId,@RequestBody String code) throws IOException {
       return guestService.codeEvaluateService(GuestId,codeQuestionId,code);
    }
    @PostMapping("/codeSubmit/{GuestId}/{CodeQuestionId}")
    public void codeSubmit(@PathVariable int GuestId,@PathVariable String CodeQuestionId,@RequestBody String code) throws IOException {
        JSONObject jsonObject=guestService.codeEvaluateService(GuestId,CodeQuestionId,code);
        String output=(String)jsonObject.get("status");
        guestService.submitCode(output,code,GuestId,CodeQuestionId);
    }
    @RequestMapping("/split/{id}/{pass_mark}")
    public void split(@PathVariable("id") int id, @PathVariable("pass_mark") int pass_mark) {
        guestService.sentToOneonOne(id, pass_mark);
    }

    @GetMapping("/getUserScore/{Cid}")
    public ResponseEntity<ResponseDTO> getUser(@PathVariable int Cid){
        return guestService.findUser(Cid);
    }

    @PutMapping("/addfeedback/{userId}")
    public Guest updateGuest(@PathVariable int userId, @RequestBody Guest updatedGuest) {
        return guestService.updateGuest(userId, updatedGuest);
    }

    @GetMapping("/contests/{userId}")
    public Object getContestIdsByUserId(@PathVariable int userId) {
        List<Object[]> con =  contestrepository.findContestByUserId(userId);
        String conName ="";
        if (!con.isEmpty()) {
            Object[] resultRow = con.get(0);
            if (resultRow.length > 0) {
                conName = (String) resultRow[0];
            }
        }
        return contestrepository.findByName(conName);
    }











//    @PostMapping("/add")
//    public void add(@RequestBody String a){
//        System.out.println("ques   "+a);
//        Gson gson = new Gson();
//        Object[] jsonArray = gson.fromJson(a, Object[].class);
//        for(int i=0;i<jsonArray.length;i++)
//            System.out.println(jsonArray[i].);
////        JSONArray jsonArray = new JSONArray(a);
//        for(JsonElement jsonElement:jsonArray){
//            JsonObject jsonObject=jsonElement.getAsJsonObject();
//            System.out.println(jsonObject);
//    }
//    }
}

