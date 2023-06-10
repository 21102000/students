package com.sat.students.service;

import com.sat.students.entity.Candidates;
import com.sat.students.respository.CandidatesRespository;
import com.sat.students.dto.Dto;
import com.sat.students.utils.Constants;
import com.sat.students.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    private CandidatesRespository respository;

    private int maxSatScore = 1600;

    @Override
    public Response create(Dto dto) {
        Optional<Candidates> find = respository.findById(dto.getName());
        if (find.isPresent()){
            return new Response(Constants.SuccessCode,Constants.RECORD_EXISTS);
        }
        Candidates entity = dto.newCandidate();
        String result = calculateResult(dto.getSatScore());
        entity.setResult(result);
        int rank = calculateRank(dto);
        entity.setRank(rank);
        respository.save(entity);
        updateRank();
        return new Response(Constants.SuccessCode,Constants.SUCCESS_MESSAGE,entity);
    }

    @Override
    public Response update(Dto dto) {
        Optional<Candidates> find = respository.findById(dto.getName());
        if (find.isPresent()) {
            Candidates update = dto.updateCandidate(find.get());
            String result = calculateResult(dto.getSatScore());
            update.setResult(result);
            respository.save(update);
            return new Response(Constants.SuccessCode,Constants.RECORD_UPDATE_MESSAGE,update);
        }
        return new Response(Constants.SuccessCode, Constants.NOT_FOUND);

    }

    @Override
    public Response findAll() {
        List<Candidates> candidatesList = respository.findAll(Sort.by(Sort.Direction.ASC,"rank"));
        return new Response(Constants.SuccessCode,Constants.RECORD_FIND_SUCCESS,candidatesList);
    }

    private void updateRank(){
        List<Candidates> candidatesList = respository.findAll(Sort.by(Sort.Direction.DESC,"satScore"));
            for(int i = 0;i<candidatesList.size();i++) {
                Candidates c = candidatesList.get(i);
                c.setRank(i + 1);
        }
            respository.saveAll(candidatesList);
    }

    @Override
    public Response deleteById(Dto dto) {
        Optional<Candidates> find = respository.findById(dto.getName());
        if (find.isPresent()){
            respository.deleteById(dto.getName());
        }else {
            return new Response(Constants.SuccessCode,Constants.NOT_FOUND);
        }
        return new Response(Constants.SuccessCode,Constants.DELETE_MESSAGE);
    }

    @Override
    public Response getRank(Dto dto) {
        Optional<Candidates> candidates = respository.findById(dto.getName());
        if (candidates.isPresent()){
            String rank = String.valueOf(candidates.get().getRank());
            Map<String,String> temp= new HashMap<>();
            temp.put("name",dto.getName());
            temp.put("rank",rank);
            return new Response(Constants.SuccessCode,Constants.RECORD_FIND_SUCCESS,temp);
        }
        return new Response(Constants.SuccessCode,Constants.NOT_FOUND,dto.getName());
    }

    private String calculateResult(int score){
        double threshold = 0.30*maxSatScore;
        int pass = (int) threshold;

        if (score >= pass){
            return "PASS";
        }else {
            return "FAIL";
        }
    }

    private int calculateRank(Dto dto){
        List<Candidates> list = respository.findAll();
        int rank = 1;
        for (Candidates c :list){
            if (c.getSatScore() > dto.getSatScore()){
                rank++;
            }if (c.getSatScore() == dto.getSatScore()){
                rank = c.getRank();
            }
        }
        return rank;
    }

}
