package com.hiringplatform.Contest.Service.QuestionService;


import com.hiringplatform.Contest.model.CodeQuestion;
import com.hiringplatform.Contest.model.Weightage;
import com.hiringplatform.Contest.repos.CodeQuestionrepository;
import com.hiringplatform.Contest.repos.Contestrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CodeQuestionService {
   @Autowired
   Contestrepository contestrepo;

    @Autowired
    private CodeQuestionrepository codeQuestionRepository;

    public void saveCode(MultipartFile file) throws IOException {
        if (ExcelCoding.isValidExcelFile(file)) {
            List<CodeQuestion> codeQuestions = ExcelCoding.getCodeQuestions(file.getInputStream());
            codeQuestionRepository.saveAll(codeQuestions);

        }
    }

    public List<CodeQuestion> getCodeQuestion() {
        return this.codeQuestionRepository.findAll();
    }

    public String questionDelete(String id) {
        codeQuestionRepository.deleteById(id);
        return "Successfully Deleted";
    }

    public void saveCode(CodeQuestion codeQuestion){
        codeQuestionRepository.save(codeQuestion);
    }

    public CodeQuestion newUpdate(String id,CodeQuestion codeQuestion)
    {
        CodeQuestion oldQuestion=this.codeQuestionRepository.findById(id).orElseThrow(()->new UsernameNotFoundException("User Not Found"));
        oldQuestion.setQuestion(codeQuestion.getQuestion());
        oldQuestion.setInput(codeQuestion.getInput());
        oldQuestion.setOutput(codeQuestion.getOutput());
        oldQuestion.setWeightage(codeQuestion.getWeightage());
        return this.codeQuestionRepository.save(oldQuestion);

    }
    public List<CodeQuestion> codeGet(@PathVariable int Cid)
    {
//        List<String> a=codeQuestionRepository.findPart(contestrepo.findById(Cid).get());
//        JSONObject jb=new JSONObject();
//        codeQuestionRepository.findWid(contestrepo)
//        for(int i=0;i<a.size();i++){
//            String part=a.get(i);
//            System.out.println(part);
            Weightage w=codeQuestionRepository.findWid(contestrepo.findById(Cid).get(),"programming");
            List<CodeQuestion> al1=codeQuestionRepository.getRandomCodeQuestions("EASY",w.getEasy());
            List<CodeQuestion> al2=codeQuestionRepository.getRandomCodeQuestions("MEDIUM",w.getMedium());
            List<CodeQuestion> al3=codeQuestionRepository.getRandomCodeQuestions("HARD",w.getHard());
            List<CodeQuestion> new1=new ArrayList<>();
            new1.addAll(al1);
            new1.addAll(al2);
            new1.addAll(al3);
//            jb.put(part,new1);
//        }
        return new1;
    }
        }