package com.meta.air_jet._core.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class ApiUtils {

    public static <T> ApiResult<T> success(T response) {
        return new ApiResult<>(true, response);
    }

    public static <T> ApiResult<T> error(String errorMessage) {
        return new ApiResult<>(false, null, errorMessage);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private static class ApiResult<T> {
        private final boolean success;
        private final T response;
        private String error;

        public ApiResult(boolean success, T response) {
            this.success = success;
            this.response = response;
        }
    }

    @Getter @Setter @AllArgsConstructor
    public static class ApiError {
        private final String message;
    }

}
