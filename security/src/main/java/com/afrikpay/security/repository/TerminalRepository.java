package com.afrikpay.security.repository;

import com.afrikpay.security.entity.Terminal;
import dbgateway.dao.DBRepository;
import org.hibernate.Session;

public class TerminalRepository extends DBRepository<Terminal, Long> {
    public TerminalRepository(Session session) {
        super(session);
    }
}
