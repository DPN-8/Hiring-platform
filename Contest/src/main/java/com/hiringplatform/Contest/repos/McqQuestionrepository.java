package com.hiringplatform.Contest.repos;

import com.hiringplatform.Contest.model.Contest;
import com.hiringplatform.Contest.model.McqQuestion;
import com.hiringplatform.Contest.model.Weightage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface McqQuestionrepository extends JpaRepository<McqQuestion,String> {

    @Query("SELECT q FROM McqQuestion q WHERE q.part = ?1 AND q.weightage = ?2 ORDER BY RANDOM() LIMIT ?3")
    List<McqQuestion> getRandomMcqQuestions(String part, String wei, int z);

    @Query("SELECT name FROM Part WHERE contest1=?1")
    List<String> findPart(Contest c);
    @Query("Select q.weight FROM Part q WHERE q.contest1=?1 and q.name=?2")
    Weightage findWid(Contest c, String p);
}
