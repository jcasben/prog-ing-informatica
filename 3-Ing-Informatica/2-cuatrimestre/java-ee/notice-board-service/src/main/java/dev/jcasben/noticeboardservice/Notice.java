package dev.jcasben.noticeboardservice;

import java.time.LocalDateTime;

public class Notice {
    private String message;
    private LocalDateTime expiration;
    private String code;
    private String formatedExpiration;

    public Notice(String message, LocalDateTime expiration, String code, String formatedExpiration) {
        this.message = message;
        this.expiration = expiration;
        this.code = code;
        this.formatedExpiration = formatedExpiration;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getExpiration() {
        return expiration;
    }

    public void setExpiration(LocalDateTime expiration) {
        this.expiration = expiration;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFormatedExpiration() {
        return formatedExpiration;
    }

    public void setFormatedExpiration(String formatedExpiration) {
        this.formatedExpiration = formatedExpiration;
    }
}
