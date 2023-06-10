package com.sat.students.controller;

import com.sat.students.dto.Dto;
import com.sat.students.service.CandidateService;
import com.sat.students.utils.Response;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/satApp/v1")
public class Controller {

    @Autowired
    private final CandidateService service;

    public Controller(CandidateService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<Response> create(@Valid @RequestBody Dto dto, @RequestHeader HttpHeaders headers) {
        Response response = service.create(dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/update")
    public ResponseEntity<Response> update(@Valid @RequestBody Dto dto, @RequestHeader HttpHeaders headers) {
        Response response = service.update(dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/delete")
    public ResponseEntity<Response> delete(@Valid @RequestBody Dto dto, @RequestHeader HttpHeaders headers) {
        Response response = service.deleteById(dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/findAll")
    public ResponseEntity<Response> findAll(@Valid @RequestHeader HttpHeaders headers) {
        Response response = service.findAll();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/findbyRank")
    public ResponseEntity<Response> findAll(@Valid @RequestBody Dto dto,@RequestHeader HttpHeaders headers) {
        Response response = service.getRank(dto);
        return ResponseEntity.ok(response);
    }
}
