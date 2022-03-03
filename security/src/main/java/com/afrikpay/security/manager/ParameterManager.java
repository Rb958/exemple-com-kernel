package com.afrikpay.security.manager;

import com.afrikpay.security.entity.Parameters;
import com.afrikpay.security.exception.ParameterNotFoundException;
import com.afrikpay.security.repository.ParameterRepository;
import com.afrikpay.security.utils.DataBaseUtil;
import dbgateway.exception.DBConnectionConfigException;
import dbgateway.manager.AbstractManager;

import java.util.List;

public class ParameterManager extends AbstractManager {
    private final ParameterRepository parameterRepository;
    public ParameterManager() throws DBConnectionConfigException {
        super(DataBaseUtil.getDatabaseName(), DataBaseUtil.getDataBaseClasses());
        parameterRepository = new ParameterRepository(getSession());
    }

    public String get(String key) throws ParameterNotFoundException {
        Parameters param = parameterRepository.findOneByKey(key);
        return param.getParamValue();
    }

    public Parameters getParameters(String key) throws ParameterNotFoundException {
        return parameterRepository.findOneByKey(key);
    }

    public Parameters save(Parameters parameters){
        return this.parameterRepository.save(parameters);
    }

    public Parameters update(Parameters tmpParameter) {
        return parameterRepository.save(tmpParameter);
    }

    public void delete(Parameters tmpParameter) {
        this.parameterRepository.delete(tmpParameter);
    }

    public List<Parameters> getAll() {
        return parameterRepository.findAll();
    }
}
