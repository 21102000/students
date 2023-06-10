package com.sat.students.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sat.students.entity.Candidates;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Dto {

    @NotBlank(message = "name must not be blank")
    private String name;

    private String address;

    private String city;

    private String country;

    @Digits(integer = 6,fraction = 0,message = "pincode must be of 6 digits")
    private Integer pincode;

    @Min(value = 0,message = "marks cannot be negative")
    @Max(value = 1600,message = "marks not more than 100")
    private int satScore;

    @JsonIgnore
    private String result;

    @JsonIgnore
    private int rank;

    public Candidates newCandidate(){
        Candidates newEntity = new Candidates();
        newEntity.setName(name);
        newEntity.setAddress(address);
        newEntity.setCity(city);
        newEntity.setCountry(country);
        newEntity.setPincode(pincode);
        newEntity.setSatScore(satScore);
        return newEntity;
    }

    public Candidates updateCandidate(Candidates candidates){
        Candidates update = new Candidates();
        update.setName(candidates.getName());
        update.setSatScore(satScore);
        update.setAddress(candidates.getAddress());
        update.setCity(candidates.getCity());
        update.setCountry(candidates.getCountry());
        update.setPincode(candidates.getPincode());
        return update;
    }

}
