package com.hiringplatform.Contest.repos;

import com.hiringplatform.Contest.model.Contest;
import com.hiringplatform.Contest.model.Employee;
import com.hiringplatform.Contest.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface Guestrepository extends JpaRepository<Guest,Integer> {

    boolean existsByEmail(String email);
    List<Guest> findByContestOrderByTotalMarksDesc(Contest c);
    Guest findByEmail(String email);
    @Query("SELECT Guest FROM Guest WHERE employee=?1")
    List<Guest> candidates(Employee e);

    @Query("SELECT g FROM Guest g WHERE g.contest = ?1 AND g.totalMarks >= ?2")
    List<Guest> findByMarks(int cid, int m);

    @Query("SELECT g FROM Guest g WHERE g.contest = ?1")
    List<Guest> getUserByContest(Contest id);

    @Query("SELECT g FROM Guest g WHERE g.contest = ?1 AND g.totalMarks >= ?2")
    List<Guest> findByPassMarks(Contest contest, int i);

    @Query("SELECT g FROM Guest g WHERE g.contest = ?1 AND g.totalMarks = ?2")
    List<Guest> findByFail(Contest contest, int i);

    @Query("SELECT g FROM Guest g WHERE g.employee.Eid = :employeeId")
    List<Guest> findAllocatedStudentsByEmployeeId(@Param("employeeId") int employeeId);
}
