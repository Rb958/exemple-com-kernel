package com.afrikpay.security.signal;

import com.afrikpay.security.kernel.SecurityKernel;
import com.afrikpay.security.service.encryption.EncryptRequestParameter;
import com.afrikpay.security.service.encryption.EncryptionParameter;
import rkernel.KernelFactory;
import rkernel.signal.BasicSignal;

import java.lang.reflect.InvocationTargetException;


public final class EncryptionSignal extends BasicSignal<EncryptRequestParameter> {
    public EncryptionSignal(String type, EncryptRequestParameter payload) {
        super(type, payload);
    }
    public EncryptionParameter getParameters() throws BadSignalException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        try {
            EncryptionParameter parameter = new EncryptionParameter();
            parameter.setKey(payload.getKey())
                    .setPubKey(payload.isPublicKey())
                    .setPrivKey(payload.isPrivateKey())
                    .setMode(payload.getMode())
                    .setPadding(payload.getPadding())
                    .setAesMode(payload.getEncryptMode())
                    .setIv(payload.getIv())
                    .setAlgo(payload.getAlgo());
            return parameter;
        }catch (Exception e){
            KernelFactory.getInstance(SecurityKernel.class).dispatchLogException(e);
            throw new BadSignalException(e.getMessage());
        }
    }
}
