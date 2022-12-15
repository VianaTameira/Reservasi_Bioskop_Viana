package org.binar.msib.CinemaApp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageResponse {
    private String message;
    private Integer status;
    private Object data;

}
