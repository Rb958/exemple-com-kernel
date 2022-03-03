package com.afrikpay.security.service.config;

import com.afrikpay.security.entity.AppConfig;
import com.afrikpay.security.exception.EntityNotFoundException;
import com.afrikpay.security.manager.AppConfigManager;
import dbgateway.exception.DBConnectionConfigException;

import java.util.List;

public class AppConfigService {
    private final AppConfigManager appConfigManager;

    public AppConfigService() throws DBConnectionConfigException {
        this.appConfigManager = new AppConfigManager();
    }

    public AppConfig create(AppConfig appConfig){
        return this.appConfigManager.save(appConfig);
    }

    public AppConfig getAppConfig(String appid) {
        return this.appConfigManager.findByAppId(appid);
    }

    public List<AppConfig> getAll() {
        return this.appConfigManager.getAll();
    }

    public AppConfig update(AppConfig appConfig, String appid) throws EntityNotFoundException {
        AppConfig tmpAppConfig = this.getAppConfig(appid);
        if (tmpAppConfig == null){
            throw new EntityNotFoundException();
        }
        tmpAppConfig.setEmails(appConfig.getEmails());
        tmpAppConfig.setEncryptionAlgo(appConfig.getEncryptionAlgo());
        tmpAppConfig.setIvKey(appConfig.getIvKey());
        tmpAppConfig.setName(appConfig.getName());
        tmpAppConfig.setPrivateKey(appConfig.getPrivateKey());
        tmpAppConfig.setPublicKey(appConfig.getPublicKey());
        tmpAppConfig.setSecretKey(appConfig.getSecretKey());
        tmpAppConfig.setSignAlgo(appConfig.getSignAlgo());
        tmpAppConfig.setState(appConfig.isState());
        tmpAppConfig.setUpdatedAt(appConfig.getUpdatedAt());
        tmpAppConfig.setWhiteList(appConfig.getWhiteList());
        return this.appConfigManager.save(tmpAppConfig);
    }

    public void delete(String appId) throws EntityNotFoundException {
        AppConfig tmpAppConfig = this.getAppConfig(appId);
        if (tmpAppConfig == null){
            throw new EntityNotFoundException();
        }
        this.appConfigManager.delete(tmpAppConfig);
    }
}
