package com.afrikpay.security.repository;

import com.afrikpay.security.entity.AppConfig;
import dbgateway.dao.DBRepository;
import org.hibernate.Session;

public class AppConfigRepository extends DBRepository<AppConfig, Long> {
    public AppConfigRepository(Session session) {
        super(session);
    }
}
