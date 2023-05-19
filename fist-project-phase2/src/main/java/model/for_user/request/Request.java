package model.for_user.request;

public abstract class Request {
    public Request(){
        this.idRequest = Integer.toHexString(3802+countForId++);
    }
    protected String idRequest;
    private static int countForId;

    public String getIdRequest() {
        return idRequest;
    }

    static {
        countForId = 0;
    }
}

