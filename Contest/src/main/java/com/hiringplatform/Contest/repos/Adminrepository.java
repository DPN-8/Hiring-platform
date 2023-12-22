package com.hiringplatform.Contest.repos;

import com.hiringplatform.Contest.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Adminrepository extends JpaRepository<Admin,String> {
}
