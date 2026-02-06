package common;


public class OperationResult <T> {
    T data;
    boolean success;
    String message;

    public OperationResult(T data, boolean success, String message) {
        this.data = data;
        this.success = success;
        this.message = message;
    }

    public OperationResult(boolean success, String message) {
        this.data = null;
        this.success = success;
        this.message = message;
    }


    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }


    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }


    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }


}


