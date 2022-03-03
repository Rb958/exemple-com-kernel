package com.afrikpay.security.manager;

import com.afrikpay.security.repository.TerminalRepository;
import com.afrikpay.security.utils.DataBaseUtil;
import dbgateway.exception.DBConnectionConfigException;
import dbgateway.manager.AbstractManager;


public class TerminalManager extends AbstractManager {
    private final TerminalRepository terminalRepository;
    public TerminalManager() throws DBConnectionConfigException {
        super(DataBaseUtil.getDatabaseName(), DataBaseUtil.getDataBaseClasses());
        terminalRepository = new TerminalRepository(getSession());
    }
}
