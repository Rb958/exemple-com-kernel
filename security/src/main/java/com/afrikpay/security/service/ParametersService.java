package com.afrikpay.security.service;

import com.afrikpay.security.entity.Parameters;
import com.afrikpay.security.exception.ParameterNotFoundException;
import com.afrikpay.security.manager.ParameterManager;
import dbgateway.exception.DBConnectionConfigException;

import java.util.List;

public class ParametersService {

    private final ParameterManager parameterManager;

    public ParametersService() throws DBConnectionConfigException {
        this.parameterManager = new ParameterManager();
    }

    public Parameters create(Parameters parameters) {
        return this.parameterManager.save(parameters);
    }

    public Parameters update(Parameters parameters, String key) throws ParameterNotFoundException {
        Parameters tmpParameter = this.parameterManager.getParameters(key);
        tmpParameter.setParamValue(parameters.getParamValue());
        return this.parameterManager.update(tmpParameter);
    }

    public void delete(String key) throws ParameterNotFoundException {
        Parameters tmpParameter = this.parameterManager.getParameters(key);
        parameterManager.delete(tmpParameter);
    }

    public List<Parameters> getAll() {
        return parameterManager.getAll();
    }
}
