package com.bluesky.restapi.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CustomResponseEntity {

    private int status;
    private LocalDateTime timestamp;
    private String message;
    private String desc;

}
