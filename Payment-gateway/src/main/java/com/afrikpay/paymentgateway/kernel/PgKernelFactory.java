package com.afrikpay.paymentgateway.kernel;

public class PgKernelFactory {
    private static PgKernelFactory instance;
    private final PgKernel pgKernel;

    public PgKernelFactory() {
        pgKernel = new PgKernel();
    }

    public static PgKernelFactory getInstance(){
        if (instance == null){
            instance = new PgKernelFactory();
        }
        return instance;
    }

    public PgKernel getPgKernel(){
        return pgKernel;
    }
}
