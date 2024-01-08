package org.vad1mchk.webprogr.lab04.model.response;

public class ErrorMessageResponseDto {
    private String errorMessage;

    public ErrorMessageResponseDto(Throwable throwable) {
        this.errorMessage = throwable.getMessage();
    }

    public ErrorMessageResponseDto(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
