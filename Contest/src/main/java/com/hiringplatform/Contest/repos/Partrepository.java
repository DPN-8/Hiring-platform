package com.hiringplatform.Contest.repos;

import com.hiringplatform.Contest.model.Contest;
import com.hiringplatform.Contest.model.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface Partrepository extends JpaRepository<Part,Integer> {
    List<Part> findByContest1(Contest c);

    @Query("SELECT p FROM Part p WHERE p.contest1 = ?1")
    List<Part> getParts(Contest contestId);

    @Query("SELECT p FROM Part p WHERE p.contest1 = ?1 AND p.name = 'CODING'")
    List<Part> getCodingParts(Contest contest);
}
