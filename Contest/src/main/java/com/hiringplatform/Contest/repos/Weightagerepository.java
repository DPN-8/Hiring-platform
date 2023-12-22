package com.hiringplatform.Contest.repos;

import com.hiringplatform.Contest.model.Weightage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Weightagerepository extends JpaRepository<Weightage,Integer> {
}
