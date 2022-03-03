package com.afrikpay.security.repository;

import com.afrikpay.security.entity.Parameters;
import com.afrikpay.security.exception.ParameterNotFoundException;
import dbgateway.dao.DBRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class ParameterRepository extends DBRepository<Parameters, Long> {
    public ParameterRepository(Session session) {
        super(session);
    }

    public Parameters findOneByKey(String key) throws ParameterNotFoundException {
        Query<Parameters> query = session.createQuery("select p from Parameters p Where p.paramKey = :key", Parameters.class)
                .setParameter("key", key);
        Parameters param = query.uniqueResult();
        if (param != null){
            throw new ParameterNotFoundException();
        }
        return param;
    }
}
