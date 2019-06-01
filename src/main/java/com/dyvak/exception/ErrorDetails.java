package com.dyvak.exception;

import java.util.Date;
import java.util.Objects;

public class ErrorDetails {

    private Date timestamp;
    private String message;
    private String details;

    public ErrorDetails() {
    }

    ErrorDetails(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public static ErrorDetailBuilder builder() {
        return new ErrorDetailBuilder();
    }

    public static class ErrorDetailBuilder {

        private Date timestamp;
        private String message;
        private String details;

        public ErrorDetailBuilder timestamp(Date timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ErrorDetailBuilder message(String message) {
            this.message = message;
            return this;
        }

        public ErrorDetailBuilder details(String details) {
            this.details = details;
            return this;
        }

        public ErrorDetails build() {
            return new ErrorDetails(this.timestamp, this.message, this.details);
        }

    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorDetails that = (ErrorDetails) o;
        return Objects.equals(timestamp, that.timestamp) &&
                Objects.equals(message, that.message) &&
                Objects.equals(details, that.details);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, message, details);
    }

    @Override
    public String toString() {
        return "ErrorDetails{" +
                "timestamp=" + timestamp +
                ", message='" + message + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
