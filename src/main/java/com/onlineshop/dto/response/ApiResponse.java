package com.onlineshop.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ApiResponse<T>{
    @Builder.Default
    private int code = HttpStatus.OK.value();
    private String message;
    private T result;
}
