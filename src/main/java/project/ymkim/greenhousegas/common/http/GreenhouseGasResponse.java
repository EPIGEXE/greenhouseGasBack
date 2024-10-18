package project.ymkim.greenhousegas.common.http;


import java.util.Date;

import lombok.Getter;
import project.ymkim.greenhousegas.common.exception.YmError;

@Getter
public class GreenhouseGasResponse<T> {
    
    private Date timestamp;
    private String code;
    private T data;

    private GreenhouseGasResponse(String code, T data) {
        this.timestamp = new Date();
        this.code = code;
        this.data = data;
    }

    public static <T> GreenhouseGasResponse<T> success() {
        return new GreenhouseGasResponse<T>(YmError.SUCCESS.getCode(), null);
    }

    public static <T> GreenhouseGasResponse<T> success(T data) {
        return new GreenhouseGasResponse<T>(YmError.SUCCESS.getCode(), data);
    }
    
    public static <T> GreenhouseGasResponse<T> error(YmError error) {
        return new GreenhouseGasResponse<T>(error.getCode(), null);
    }

    public static <T> GreenhouseGasResponse<T> error(YmError error, T data) {
        return new GreenhouseGasResponse<T>(error.getCode(), data);
    }

}
