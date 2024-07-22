public class Onsitetestingmodel {
    String sessionId;
    String smsPIN;
    String type;
    String result;
    String status;

    public Onsitetestingmodel(String sessionId, String smsPIN, String type, String result, String status) {
        this.sessionId = sessionId;
        this.smsPIN = smsPIN;
        this.type = type;
        this.result = result;
        this.status = status;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSmsPIN() {
        return smsPIN;
    }

    public void setSmsPIN(String smsPIN) {
        this.smsPIN = smsPIN;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
