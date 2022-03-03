package com.afrikpay.security;

import com.afrikpay.security.exception.BadDBConfigurationException;
import com.afrikpay.security.utils.DataBaseUtil;
import dbgateway.config.ConfigManager;
import dbgateway.config.DBConnectionManager;
import dbgateway.config.def.ConnectionProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SecurityApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		DBConnectionManager dbConnectionManager = new DBConnectionManager();
		ConfigManager configManager = new ConfigManager();
		if (dbConnectionManager.hasDataBaseConfig(DataBaseUtil.getDatabaseName())){
			ConnectionProperties connectionProperties = dbConnectionManager.getDBConnection(DataBaseUtil.getDatabaseName());
			configManager.initDb(DataBaseUtil.getDataBaseClasses(),connectionProperties);
			System.out.println("Database initialised");
		}else{
//			BadDBConfigurationException dbConfigurationException = new BadDBConfigurationException("No database named "+ DataBaseUtil.getDatabaseName() + " was found");
//			this.dispatchLogException(dbConfigurationException);
		}
	}
}
