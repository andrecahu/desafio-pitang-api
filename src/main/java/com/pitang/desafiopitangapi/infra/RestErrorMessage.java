package com.pitang.desafiopitangapi.infra;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter @Setter @AllArgsConstructor
public class RestErrorMessage {
    private String message;
    private HttpStatus status;
}
