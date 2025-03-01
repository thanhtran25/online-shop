package com.onlineshop.exception;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
public class AppException extends RuntimeException {
    private ErrorCode errorCode;
}
