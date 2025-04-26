package com.alirizakaygusuz.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class RootEntity<T> {

    private Integer status;
    private T payload;
    private String errorMessage;

   
    private RootEntity(Status status, T payload, String errorMessage) {
        this.status = status.getCode();
        this.payload = payload;
        this.errorMessage = errorMessage;
    }

    //200
    public static <T> RootEntity<T> ok(T payload) {
        return new RootEntity<>(Status.OK, payload, null);
    }

    // 500
    public static <T> RootEntity<T> error(String errorMessage) {
        return new RootEntity<>(Status.INTERNAL_SERVER_ERROR, null, errorMessage);
    }

    
    public static <T> RootEntityBuilder<T> builder() {
        return new RootEntityBuilder<>();
    }

    public static class RootEntityBuilder<T> {
        private Status status;
        private T payload;
        private String errorMessage;

        public RootEntityBuilder<T> status(Status status) {
            this.status = status;
            return this;
        }

        public RootEntityBuilder<T> payload(T payload) {
            this.payload = payload;
            return this;
        }

        public RootEntityBuilder<T> errorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }

        public RootEntity<T> build() {
            return new RootEntity<>(status, payload, errorMessage);
        }
    }

    
    public enum Status {
        OK(200), INTERNAL_SERVER_ERROR(500);

        private final int code;

        Status(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }
}
