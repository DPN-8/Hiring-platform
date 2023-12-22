package com.hiringplatform.Contest.Service.QuestionService;

import com.hiringplatform.Contest.model.DTO.ResponseDTO;
import com.hiringplatform.Contest.model.McqQuestion;
import com.hiringplatform.Contest.model.Weightage;
import com.hiringplatform.Contest.repos.Contestrepository;
import com.hiringplatform.Contest.repos.McqQuestionrepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service

public class McqQuestionService {

    @Autowired
    private McqQuestionrepository mcqQuestionRepository;
    @Autowired
    Contestrepository contestrepo;


    public ResponseEntity<ResponseDTO> saveMcqtoDatabase(MultipartFile file) throws IOException {
        if(ExcelUploadService.isValidExcelFile(file))
        {
            System.out.println("The file is valid");
            List<McqQuestion> mcqQuestions =ExcelUploadService.getMcqQuestions(file.getInputStream());
            System.out.println(mcqQuestions);
            mcqQuestionRepository.saveAll(mcqQuestions);
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK,"Mcq Questions Added Successfuly From File!",null));

    }

    public List<McqQuestion> viewMcqService(){

        return mcqQuestionRepository.findAll();
    }

    public  ResponseEntity<ResponseDTO> addMcq(McqQuestion m){
        System.out.println(m.getQid());
        System.out.println(m.getCorrectOp());
        System.out.println(m.getQuestion());
        mcqQuestionRepository.save(m);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK,"Mcq Questions Added Successfully",m));
    }

    public ResponseEntity<ResponseDTO> recordEdit(@PathVariable (value="id") String string,
                                  @RequestBody McqQuestion mcqQuestion)
    {
        McqQuestion oldRecord=this.mcqQuestionRepository.findById(string).orElseThrow(()->new UsernameNotFoundException("Questions Not Found"));
        oldRecord.setQuestion(mcqQuestion.getQuestion());
        oldRecord.setPart(mcqQuestion.getPart());
        oldRecord.setOption1(mcqQuestion.getOption1());
        oldRecord.setOption2(mcqQuestion.getOption2());
        oldRecord.setOption3(mcqQuestion.getOption3());
        oldRecord.setOption4(mcqQuestion.getOption4());
        oldRecord.setWeightage(mcqQuestion.getWeightage());
        oldRecord.setCorrectOp(mcqQuestion.getCorrectOp());
        mcqQuestionRepository.save(oldRecord);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK,"Mcq Questions Updated Successfully",oldRecord));

    }
    public List<McqQuestion> getMcq(int Cid)
    {
        List<String> a=mcqQuestionRepository.findPart(contestrepo.findById(Cid).get());
        JSONObject jb=new JSONObject();
        for(int i=0;i<a.size();i++) {
            String part = a.get(i);
            System.out.println(part);
            Weightage w = mcqQuestionRepository.findWid(contestrepo.findById(Cid).get(), part);
            List<McqQuestion> al1 = mcqQuestionRepository.getRandomMcqQuestions(part, "EASY", w.getEasy());
            List<McqQuestion> al2 = mcqQuestionRepository.getRandomMcqQuestions(part, "MEDIUM", w.getMedium());
            List<McqQuestion> al3 = mcqQuestionRepository.getRandomMcqQuestions(part, "HARD", w.getHard());
            List<McqQuestion> new1 = new ArrayList<>();
            new1.addAll(al1);
            new1.addAll(al2);
            new1.addAll(al3);
            jb.put(part, new1);

        }
        return (List<McqQuestion>) jb;
    }

    public ResponseEntity<ResponseDTO> queDele(String id) {
        mcqQuestionRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK,"Successfully Deleted Questions By Id",null));
    }

    public List<McqQuestion> getMcq()
    {
        return mcqQuestionRepository.findAll();
          }

          public  ResponseEntity<ResponseDTO> deleteAll(@RequestBody McqQuestion mcqQuestion)
          {
              mcqQuestionRepository.deleteAll();
              return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK,"Deleted All Mcq Questions",null));
          }

}
