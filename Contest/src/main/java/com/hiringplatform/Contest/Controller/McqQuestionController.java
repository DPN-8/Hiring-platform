package com.hiringplatform.Contest.Controller;

import com.hiringplatform.Contest.Service.QuestionService.McqQuestionService;
import com.hiringplatform.Contest.model.DTO.ResponseDTO;
import com.hiringplatform.Contest.model.McqQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/mcqQuestion")
public class McqQuestionController {
    @Autowired
    McqQuestionService mcqQuestionService;

    @PostMapping("/upload")
    public String uploadMcqQuestion(@RequestParam(value = "file", required = false)MultipartFile file) throws IOException {
        System.out.println("Inside service");
        mcqQuestionService.saveMcqtoDatabase(file);
        return "Success";
    }

    @GetMapping("/viewMcq")
    public List<McqQuestion> viewQuestion(){
       return mcqQuestionService.viewMcqService();
    }

    @PostMapping("/addMcq")
    public String addMcq(@RequestBody McqQuestion m){
        mcqQuestionService.addMcq(m);
        return "Successfully added";
    }

    @GetMapping("/getMcq/{Cid}")
    public Object getCode(@PathVariable int Cid)
    {
        return mcqQuestionService.getMcq(Cid);
    }
@PutMapping("/edit/{id}")
public ResponseEntity<ResponseDTO> editRecord(@PathVariable (value="id") String string, @RequestBody McqQuestion mcqQuestion)
{
    return mcqQuestionService.recordEdit(string,mcqQuestion);
}
@GetMapping("/getAll")
    public List<McqQuestion> getRandomHardMcq(int Qid) {
        return mcqQuestionService.getMcq(Qid);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteQues(@PathVariable (value="id") String id)
    {
        return this.mcqQuestionService.queDele(id);
    }
}
