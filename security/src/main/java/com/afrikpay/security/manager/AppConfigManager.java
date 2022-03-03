package com.afrikpay.security.manager;

import com.afrikpay.security.entity.AppConfig;
import com.afrikpay.security.repository.AppConfigRepository;
import com.afrikpay.security.utils.DataBaseUtil;
import dbgateway.exception.DBConnectionConfigException;
import dbgateway.manager.AbstractManager;
import org.hibernate.query.Query;

import java.util.List;

public class AppConfigManager extends AbstractManager {

    private final AppConfigRepository appConfigRepository;

    public AppConfigManager() throws DBConnectionConfigException {
        super(DataBaseUtil.getDatabaseName(), DataBaseUtil.getDataBaseClasses());
        appConfigRepository = new AppConfigRepository(getSession());
    }

    public AppConfig save(AppConfig appConfig){
        return appConfigRepository.save(appConfig);
    }

    public List<AppConfig> getAll(){
        return appConfigRepository.findAll();
    }

    public AppConfig findByName(String name){
        Query<AppConfig> query = getSession()
                .createQuery("select a from AppConfig a where a.name = :name", AppConfig.class)
                .setParameter("name", name);
        return query.uniqueResult();
    }

    public AppConfig findByAppId(String appId) {
        Query<AppConfig> query = getSession()
                .createQuery("select a from AppConfig a where a.appId = :appid", AppConfig.class)
                .setParameter("appid", appId);
        return query.uniqueResult();
    }

    public void delete(AppConfig tmpAppConfig) {
        this.appConfigRepository.delete(tmpAppConfig);
    }
}
