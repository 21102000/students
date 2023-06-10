package com.sat.students.respository;

import com.sat.students.entity.Candidates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatesRespository extends JpaRepository<Candidates, String> {

}
