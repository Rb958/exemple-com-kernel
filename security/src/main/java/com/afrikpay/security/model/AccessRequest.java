package com.afrikpay.security.model;

public class AccessRequest {
    private String appId;
    private String terminalIdentity;

    public AccessRequest() { }

    public String getAppId() {
        return appId;
    }

    public AccessRequest setAppId(String appId) {
        this.appId = appId;
        return this;
    }

    public String getTerminalIdentity() {
        return terminalIdentity;
    }

    public AccessRequest setTerminalIdentity(String terminalIdentity) {
        this.terminalIdentity = terminalIdentity;
        return this;
    }
}
