package com.sat.students.service;

import com.sat.students.dto.Dto;
import com.sat.students.utils.Response;
import org.springframework.stereotype.Component;

@Component
public interface CandidateService {

    Response create(Dto dto);

    Response update(Dto dto);

    Response findAll();

    Response deleteById(Dto dto);

    Response getRank(Dto dto);

}
