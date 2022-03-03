package com.afrikpay.logging.kernel;

import com.afrikpay.logging.signal.BadSignalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rkernel.BasicKernel;
import rkernel.IKernel;
import rkernel.component.IComponent;
import rkernel.signal.BasicSignal;
import rkernel.signal.ISignalManager;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class LoggingKernel implements IKernel {

    private final Logger logger = LoggerFactory.getLogger(LoggingKernel.class);
    private final String kernelName;
    private final BasicKernel basicKernel = new BasicKernel();

    public LoggingKernel() {
        super();
        kernelName = "Logging";
    }

    @Override
    public void load() {
        System.out.println("Logging loaded");
    }

    @Override
    public Object processSignal(BasicSignal<?> signal) {
        try {
            if ("exception_logging".equals(signal.getType())) {
                if (signal.getPayload() instanceof String){
//                    switch(signal.getPayload().){
//                        case ERROR:
//                            logger.error((String) signal.getPayload());
//                            break;
//                        case INFO:
                    System.out.println("Message received on Logging component : " + signal.getPayload());
                            logger.debug((String) signal.getPayload());
//                            break;
//                        case DEBUG:
//                            logger.debug((String) signal.getPayload());
//                            break;
//                    }
                }else if (signal.getPayload() instanceof Exception){
                    logger.error(((Exception) signal.getPayload()).getMessage(), signal.getPayload());
                }
                return null;
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
        return Collections.singletonList(
                "exception_logging"
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
    public String getName() {
        return kernelName;
    }

    @Override
    public Map<String, IComponent> getComponents() {
        return basicKernel.getComponents();
    }

    @Override
    public Object dispatchSignal(BasicSignal<?> signal) {
        return basicKernel.dispatchSignal(signal);
    }

    @Override
    public boolean isRunning() {
        return basicKernel.isRunning();
    }
}
