package com.afrikpay.security.kernel;

import com.afrikpay.security.exception.BadDBConfigurationException;
import com.afrikpay.security.model.AccessRequest;
import com.afrikpay.security.model.User;
import com.afrikpay.security.service.authentication.AccessControlService;
import com.afrikpay.security.service.authentication.AuthenticationService;
import com.afrikpay.security.service.encryption.EncryptRequestParameter;
import com.afrikpay.security.service.encryption.EncryptionParameter;
import com.afrikpay.security.utils.DataBaseUtil;
import com.afrikpay.security.utils.EncryptionUtil;
import com.afrikpay.security.service.encryption.KeyService;
import com.afrikpay.security.signal.BadSignalException;
import com.afrikpay.security.utils.MapperUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dbgateway.config.ConfigManager;
import dbgateway.config.DBConnectionManager;
import dbgateway.config.def.ConnectionProperties;
import rkernel.BasicKernel;
import rkernel.BasicKernelLoader;
import rkernel.IKernel;
import rkernel.component.BasicComponentLoader;
import rkernel.component.IComponent;
import rkernel.signal.BasicSignal;
import rkernel.signal.ISignalManager;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

public class SecurityKernel implements IKernel {

    private final BasicKernel basicKernel = (new BasicKernel.Builder())
            .setKernelLoader(new BasicKernelLoader())
            .setComponentLoader(new BasicComponentLoader())
            .setName("Security")
            .build();

    public SecurityKernel() {
        super();
    }

    @Override
    public void load() {
        System.out.println("Loading data base...");
        DBConnectionManager dbConnectionManager = new DBConnectionManager();
        ConfigManager configManager = new ConfigManager();
        if (dbConnectionManager.hasDataBaseConfig(DataBaseUtil.getDatabaseName())){
            ConnectionProperties connectionProperties = dbConnectionManager.getDBConnection(DataBaseUtil.getDatabaseName());
            configManager.initDb(DataBaseUtil.getDataBaseClasses(),connectionProperties);
            System.out.println("Database initialised");
        }else{
            BadDBConfigurationException dbConfigurationException = new BadDBConfigurationException("No database named "+ DataBaseUtil.getDatabaseName() + " was found");
            this.dispatchLogException(dbConfigurationException);
        }
    }

    private EncryptRequestParameter getParameters(String rawData) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(rawData, new TypeReference<EncryptRequestParameter>() {});
        }catch (Exception e){
            e.printStackTrace();
            dispatchLogException(e);
            return null;
        }
    }

    @Override
    public Object processSignal(BasicSignal<?> signal) {
        System.out.println(signal);
        try {
            if (signal.getPayload() instanceof String) {
                if (signal.getType().startsWith("security.access")){
                    AccessControlService accessControlService = new AccessControlService();
                    switch (signal.getType()) {
                        case "security.access.check":
                            AccessRequest accessRequest = (AccessRequest) MapperUtil.json2Object((String) signal.getPayload(), new TypeReference<AccessRequest>() {});
                            accessControlService.check(accessRequest);
                            return null;
                        default:
                            return null;
                    }
                }else if (signal.getType().startsWith("security.auth")){
                    AuthenticationService authenticationService = new AuthenticationService();
                    switch (signal.getType()){
                        case "security.auth.token.get":
                            User userDetails = (User) MapperUtil.json2Object((String) signal.getPayload(), new TypeReference<User>() {});
                            return authenticationService.auth(userDetails.getLogin(), userDetails.getPassword());
                        case "security.auth.token.verify":
                            return authenticationService.verifyToken((String) signal.getPayload());
                        default:
                            return null;
                    }

                }else if (signal.getType().startsWith("security.encryption")) {
                    EncryptRequestParameter requestParameter = getParameters((String) signal.getPayload());
                    EncryptionParameter encryptionParameter = new EncryptionParameter();
                    encryptionParameter.setAlgo(requestParameter.getAlgo());
                    encryptionParameter.setKey(requestParameter.getKey());
                    encryptionParameter.setPadding(requestParameter.getPadding());
                    encryptionParameter.setAesMode(requestParameter.getMode());
                    encryptionParameter.setIv(requestParameter.getIv());
                    EncryptionUtil service = new EncryptionUtil();
                    KeyService keyService = new KeyService(encryptionParameter);
                    service.init(encryptionParameter);
                    switch (signal.getType()) {
                        case "security.encryption.encrypt":
                            return service.encrypt(requestParameter.getData().getBytes());
                        case "security.encryption.decrypt":
                            return service.decrypt(requestParameter.getData());
                        case "security.encryption.genkey.asym":
                            KeyService.KeySpecicication spec1 = keyService.generateAsymKey(encryptionParameter.getAlgo(), requestParameter.getKeySize());
                            return MapperUtil.object2Json(spec1);
                        case "security.encryption.genkey.sym":
                            System.out.println("Generate sym key");
                            KeyService.KeySpecicication spec2 = keyService.generateSymKey(encryptionParameter.getAlgo(), requestParameter.getKeySize());
                            return MapperUtil.object2Json(spec2);
                        default:
                            return null;
                    }
                }else{
                    return null;
                }
            }else{
                throw new BadSignalException();
            }
        }catch(Exception e){
            this.dispatchLogException(e);
            return null;
        }
    }

    @Override
    public Map<String, IKernel> getKernels() {
        return basicKernel.getKernels();
    }

    @Override
    public Collection<String> getSignalType() {
        return Arrays.asList(
                "security.access.check",
                "security.auth.token.get",
                "security.auth.token.verify",
                "security.encryption.encrypt",
                "security.encryption.decrypt",
                "security.encryption.genkey.asym",
                "security.encryption.genkey.sym"
        );
    }

    @Override
    public Object getInterpreterOf(String signalType) {
        return basicKernel.getInterpreterOf(signalType);
    }

    @Override
    public IComponent findComponentByName(String componentName) {
        return basicKernel.findComponentByName(componentName);
    }

    @Override
    public IKernel findKernelByName(String kernelName) {
        return basicKernel.findKernelByName(kernelName);
    }

    @Override
    public ISignalManager getSignalManager() {
        return basicKernel.getSignalManager();
    }

    @Override
    public void addKernel(IKernel kernel) {
        basicKernel.addKernel(kernel);
    }

    @Override
    public void dispatchLogException(Exception e) {
        basicKernel.dispatchLogException(e);
    }

    @Override
    public boolean isRunning() {
        return basicKernel.isRunning();
    }

    @Override
    public String getName() {
        return "Security";
    }

    @Override
    public Map<String, IComponent> getComponents() {
        return basicKernel.getComponents();
    }

    @Override
    public Object dispatchSignal(BasicSignal<?> signal) {
        return basicKernel.dispatchSignal(signal);
    }
}
