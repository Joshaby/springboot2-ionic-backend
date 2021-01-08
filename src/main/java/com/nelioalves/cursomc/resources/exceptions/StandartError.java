package com.nelioalves.cursomc.resources.exceptions;

import java.io.Serializable;

public class StandartError implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer status;
    private String message;
    private Long time;

    public StandartError(Integer status, String message, Long time) {
        this.status = status;
        this.message = message;
        this.time = time;
    }

    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Long getTime() {
        return time;
    }
    public void setTime(Long time) {
        this.time = time;
    }
}
