package com.afrikpay.logging.signal;

import java.util.Date;
import java.util.Objects;

public class LoggingRequest<T> {
    private String message;
    private LoggingType type;
    private Date date;
    private T source;

    public LoggingRequest(String message, Date date, T source) {
        this.message = message;
        this.date = date;
        this.source = source;
    }

    public LoggingRequest() {
        date = new Date();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public T getSource() {
        return source;
    }

    public void setSource(T source) {
        this.source = source;
    }

    public LoggingType getType() {
        return type;
    }

    public void setType(LoggingType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoggingRequest<?> that = (LoggingRequest<?>) o;
        return message.equals(that.message) && date.equals(that.date) && Objects.equals(source, that.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, date, source);
    }

    public enum LoggingType{
        ERROR,
        DEBUG,
        INFO
    }
}
