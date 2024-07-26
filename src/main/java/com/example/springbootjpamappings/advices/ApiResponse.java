package com.example.springbootjpamappings.advices;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    @JsonFormat(pattern = "hh:mm:ss dd-MM-yyyy")
    private LocalDateTime timestamp;
    private T data;
    private ApiError error;

    public ApiResponse(){
        this.timestamp=LocalDateTime.now();
    }

    public ApiResponse(T data){
        this();
        this.data=data;
    }

    public ApiResponse(ApiError error){
        this();
        this.error=error;
    }

}
