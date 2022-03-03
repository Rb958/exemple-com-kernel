package com.afrikpay.security.service.authentication;

import com.afrikpay.security.entity.AppConfig;
import com.afrikpay.security.entity.Terminal;
import com.afrikpay.security.entity.WhiteList;
import com.afrikpay.security.exception.EntityNotFoundException;
import com.afrikpay.security.kernel.SecurityKernel;
import com.afrikpay.security.manager.AppConfigManager;
import com.afrikpay.security.manager.TerminalManager;
import com.afrikpay.security.model.AccessRequest;
import dbgateway.exception.DBConnectionConfigException;
import rkernel.KernelFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

public class AccessControlService {
    private final TerminalManager terminalManager;
    private final AppConfigManager appConfigManager;

    public AccessControlService() throws DBConnectionConfigException {
        this.terminalManager = new TerminalManager();
        this.appConfigManager = new AppConfigManager();
    }

    public boolean check(AccessRequest accessRequest) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        boolean allowed = false;
        try {
            AppConfig appConfig = this.appConfigManager.findByAppId(accessRequest.getAppId());
            if (appConfig == null) {
                throw new EntityNotFoundException("App config not found");
            }
            WhiteList whiteList = appConfig.getWhiteList();
            if (whiteList != null){
                List<Terminal> whiteListedTerminals = whiteList.getTerminals().stream()
                        .filter(terminal -> terminal.getIdentity().equals(accessRequest.getTerminalIdentity()))
                        .collect(Collectors.toList());
                if (!whiteListedTerminals.isEmpty()){
                    allowed = true;
                }
            }
            return allowed;
        }catch (EntityNotFoundException e) {
            KernelFactory.getInstance(SecurityKernel.class).dispatchLogException(e);
            return false;
        }
    }
}
