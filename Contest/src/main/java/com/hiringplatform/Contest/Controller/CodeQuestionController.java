package com.hiringplatform.Contest.Controller;

import com.hiringplatform.Contest.Service.QuestionService.CodeQuestionService;
import com.hiringplatform.Contest.model.CodeQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/Code")
public class CodeQuestionController {

    @Autowired
    private CodeQuestionService codeQuestionService;

    @PostMapping("/Upload")
    public String saveCode(@RequestParam(value="file", required = false)MultipartFile file) throws IOException {
        this.codeQuestionService.saveCode(file);
        return "Success";
    }

    @PostMapping("/addCode")
    public String addCode(@RequestBody CodeQuestion codeQuestion){
        codeQuestionService.saveCode(codeQuestion);
        return "Successfully added";
    }
    @GetMapping("/getCode/{Cid}")
    public Object getCode(@PathVariable int Cid)
    {
       return codeQuestionService.codeGet(Cid);
    }

    @GetMapping("/getAll")
    public List<CodeQuestion> getMcq() {
        return codeQuestionService.getCodeQuestion();
    }
   @DeleteMapping("/Delete/{id}")
    public String DeleteQuestion(@PathVariable (value="id") String id)
   {
       return codeQuestionService.questionDelete(id);
   }
   @PutMapping("/Update/{id}")
    public CodeQuestion editQue(@PathVariable (value="id") String id,
                                      @RequestBody CodeQuestion codeQuestion)
   {
       return this.codeQuestionService.newUpdate(id,codeQuestion);
   }
}
