package pl.edu.wszib.marketplace.exceptions;

public class OnLoginException extends RuntimeException {
    private String info;

    public OnLoginException(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
