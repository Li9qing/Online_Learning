package edu.hubu.common.exception;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * CustomException
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CustomException extends RuntimeException{

    private String errMessage;

    public CustomException() {}

    public CustomException(String errMessage) {
        super(errMessage);
        this.errMessage = errMessage;
    }

    public static void cast(String errMessage) {
        throw new CustomException(errMessage);
    }
}
