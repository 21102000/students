package com.sat.students.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response{

    private String code;
    private String message;
    private Object object;

    public Response(String code, String message, Object object) {
        this.message = message;
        this.code = code;
        this.object = object;
    }

    public Response(String code,String message){
        this.code = code;
        this.message = message;
    }
}
