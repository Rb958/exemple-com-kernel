package com.afrikpay.security.service.encryption;

import com.afrikpay.security.entity.AppConfig;
import com.afrikpay.security.manager.AppConfigManager;
import com.afrikpay.security.manager.ParameterManager;
import com.afrikpay.security.utils.EncryptionUtil;
import dbgateway.exception.DBConnectionConfigException;
import org.apache.commons.codec.DecoderException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class EncryptionService {
    private final AppConfigManager appConfigManager;
    private final ParameterManager parameterManager;
    private final EncryptionUtil encryptionUtil;

    public EncryptionService(EncryptionUtil encryptionUtil) throws DBConnectionConfigException {
        this.encryptionUtil = new EncryptionUtil();
        this.parameterManager = new ParameterManager();
        this.appConfigManager = new AppConfigManager();
    }

    public String encrypt(String appId, String data)
            throws DecoderException, EncryptionParameterExeption,
            NoSuchAlgorithmException, InvalidKeySpecException, InvalidAlgorithmParameterException,
            NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        // Get AppConfig
        AppConfig appConfig = this.appConfigManager.findByAppId(appId);
        // Initialize Encryption parameters using app Configuration
        EncryptionParameter encryptionParameter = getEncryptionParams(appConfig);

        this.encryptionUtil.init(encryptionParameter);
        return this.encryptionUtil.encrypt(data.getBytes());
    }

    public String decrypt(String appId, String data) throws
            InvalidAlgorithmParameterException, NoSuchPaddingException,
            IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException,
            InvalidKeyException, DecoderException, EncryptionParameterExeption, InvalidKeySpecException {
        AppConfig appConfig = this.appConfigManager.findByAppId(appId);
        EncryptionParameter encryptionParameter = getEncryptionParams(appConfig);
        this.encryptionUtil.init(encryptionParameter);
        return this.encryptionUtil.decrypt(data);
    }

    private EncryptionParameter getEncryptionParams(AppConfig appConfig) throws
            DecoderException, EncryptionParameterExeption, NoSuchAlgorithmException, InvalidKeySpecException {
        EncryptionParameter encryptionParameter = new EncryptionParameter();
        encryptionParameter.setAlgo(appConfig.getEncryptionAlgo());
        if (appConfig.getSecretKey() == null){
            encryptionParameter.setAesMode("asym");
            encryptionParameter.setPrivKey(true);
            encryptionParameter.setKey(appConfig.getPrivateKey());
        }else{
            encryptionParameter.setKey(appConfig.getSecretKey());
            encryptionParameter.setIv(appConfig.getIvKey());
        }
        return encryptionParameter;
    }
}
