package com.hiringplatform.Contest.repos;

import com.hiringplatform.Contest.model.Contest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.*;
@Repository
public interface Contestrepository extends JpaRepository<Contest,Integer> {
    List<Contest> findByEndTimeBefore(Timestamp current);
    List<Contest> findByEndTimeAfter(Timestamp current);


    @Query(nativeQuery = true, value = "SELECT c.name AS name, g.name AS name FROM Contest c JOIN Guest g ON c.Cid = g.Contest_id WHERE g.user_id = :userId")
    List<Object[]> findContestByUserId(int userId);

    List<Contest> findByName(String conName);
}
