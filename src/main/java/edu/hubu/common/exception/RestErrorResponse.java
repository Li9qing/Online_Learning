package edu.hubu.common.exception;

import lombok.Data;

import java.io.Serializable;

/**
 * RestErrorResponse
 */
@Data
public class RestErrorResponse implements Serializable {

    String errMessage;

    public RestErrorResponse(String errMessage) {
        this.errMessage = errMessage;
    }
}
