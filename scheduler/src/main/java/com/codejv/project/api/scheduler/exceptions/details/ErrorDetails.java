package com.codejv.project.api.scheduler.exceptions.details;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDetails {
    private String title;
    private int status;
    private String detail;
    private long timestamp;
    private String developerMessage;

    public static final class Builder {
        private String title;
        private int status;
        private String detail;
        private long timestamp;
        private String developerMessage;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder status(int status) {
            this.status = status;
            return this;
        }

        public Builder detail(String detail) {
            this.detail = detail;
            return this;
        }

        public Builder timestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder developerMessage(String developerMessage) {
            this.developerMessage = developerMessage;
            return this;
        }

        public ErrorDetails build() {
            ErrorDetails errorDetails = new ErrorDetails();
            errorDetails.setTitle(title);
            errorDetails.setStatus(status);
            errorDetails.setDetail(detail);
            errorDetails.setTimestamp(timestamp);
            errorDetails.setDeveloperMessage(developerMessage);
            return errorDetails;
        }
    }
}
