package com.afrikpay.logging.kernel;

public final class KernelFactory {
    private static LoggingKernel kernel;

    public static LoggingKernel getInstance(){
        if (kernel == null){
            kernel = new LoggingKernel();
        }
        return kernel;
    }
}
