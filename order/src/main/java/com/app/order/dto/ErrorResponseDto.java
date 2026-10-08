package com.app.order.dto;

import java.time.LocalDateTime;

public record ErrorResponseDto(LocalDateTime localDateTimeStamp, int status, String error, String message) {

}
