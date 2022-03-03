package com.afrikpay.logging.signal;

import com.afrikpay.logging.kernel.KernelFactory;
import rkernel.signal.BasicSignal;


public  class LoggingSignal extends BasicSignal<Object> {

    protected LoggingSignal(LoggingRequest payload) {
        super("exception_logging", payload);
    }

    public LoggingRequest<Object> getParameters() throws BadSignalException {
        try {
            LoggingRequest<Object> loggingRequest = new LoggingRequest<>();
            if (payload instanceof String){
                loggingRequest.setMessage((String) payload);
            }else if (payload instanceof Exception){
                loggingRequest.setMessage(((Exception) payload).getMessage());
                loggingRequest.setSource(payload);
            }else{
                throw new BadSignalException();
            }
            return loggingRequest;
        }catch (Exception e){
            KernelFactory.getInstance().dispatchLogException(e);
            throw new BadSignalException(e.getMessage());
        }
    }
}
