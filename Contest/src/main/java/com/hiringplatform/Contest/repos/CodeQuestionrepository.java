package com.hiringplatform.Contest.repos;

import com.hiringplatform.Contest.model.CodeQuestion;
import com.hiringplatform.Contest.model.Contest;
import com.hiringplatform.Contest.model.Weightage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CodeQuestionrepository extends JpaRepository<CodeQuestion,String> {

    @Query("SELECT q FROM CodeQuestion q WHERE q.weightage = ?1 ORDER BY RANDOM() LIMIT ?2")
    List<CodeQuestion> getRandomCodeQuestions(String wei, int z);



    //    @Query("SELECT name FROM Part WHERE contest1=?1")
//    List<String> findPart(Contest c);
    @Query("Select q.weight FROM Part q WHERE q.contest1=?1 and q.name=?2")
    Weightage findWid(Contest c, String p);
}
