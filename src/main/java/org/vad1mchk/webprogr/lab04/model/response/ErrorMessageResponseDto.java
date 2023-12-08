package org.vad1mchk.webprogr.lab04.model.response;

public class ErrorMessageResponseDto {
    private String errorMessage;
    private String errorClassName;

    public ErrorMessageResponseDto(Throwable throwable) {
        this.errorMessage = throwable.getMessage();
        this.errorClassName = throwable.getClass().getName();
    }

    public ErrorMessageResponseDto(String errorMessage, Throwable throwable) {
        this.errorMessage = errorMessage;
        this.errorClassName = throwable.getClass().getName();
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorClassName() {
        return errorClassName;
    }

    public void setErrorClassName(String errorClassName) {
        this.errorClassName = errorClassName;
    }
}
