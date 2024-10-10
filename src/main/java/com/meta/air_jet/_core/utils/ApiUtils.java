package com.meta.air_jet._core.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class ApiUtils {

    public static <T> ApiResult<T> success(T response) {
        return new ApiResult<>(true, response);
    }

    public static <T> ApiResult<T> error(T errorMessage) {
        return new ApiResult<>(false, errorMessage);
    }

    public static <T> ApiLoginResult<T> loginSuccess(T response, String loginId) {
        return new ApiLoginResult<>(true, response, loginId);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private static class ApiResult<T> {
        private final boolean success;
        private final T response;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private static class ApiLoginResult<T> {
        private final boolean success;
        private final T response;
        private final String loginId;
    }

}
