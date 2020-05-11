package sdk;

import lombok.Data;

/**
 * @author jinziyu
 * @date 2020/4/19 21:21
 */
@Data
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public Result<T> withData(T data) {
        this.data = data;
        return this;
    }

    public static Result success() {
        Result result = new Result();
        result.code = 0;
        return result;
    }

    public static Result fail(String message) {
        Result result = new Result();
        result.code = 1;
        result.message = message;
        return result;
    }

    public T getDataV2() throws TeachApiException {
        handleFailSituation();
        return data;
    }

    private void handleFailSituation() throws TeachApiException {
        if (code != 0) {
            throw new TeachApiException(String.format("teach api invoke error: %s", message));
        }
    }

}
