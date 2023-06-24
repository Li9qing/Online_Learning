package edu.hubu.user.exception;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EduException extends RuntimeException {
    @ApiModelProperty(value = "异常信息")
    private String msg;
}
