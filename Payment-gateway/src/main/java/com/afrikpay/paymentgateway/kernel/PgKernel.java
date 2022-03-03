package com.afrikpay.paymentgateway.kernel;

import rkernel.BasicKernel;
import rkernel.BasicKernelLoader;
import rkernel.IKernel;
import rkernel.component.BasicComponentLoader;
import rkernel.component.IComponent;
import rkernel.exception.UnImplementedMethod;
import rkernel.signal.BasicSignal;
import rkernel.signal.ISignalManager;

import java.util.Collection;
import java.util.Map;

public final class PgKernel implements IKernel {

    private final BasicKernel basicKernel = new BasicKernel.Builder()
            .setName("PaymentGateway")
            .setKernelLoader(new BasicKernelLoader())
            .setComponentLoader(new BasicComponentLoader())
            .build();

    @Override
    public void load() {
        basicKernel.load();
    }

    @Override
    public String getName() {
        return basicKernel.getName();
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
    public Object processSignal(BasicSignal<?> signal) throws UnImplementedMethod {
        return null;
    }

    @Override
    public Map<String, IKernel> getKernels() {
        return basicKernel.getKernels();
    }

    @Override
    public Collection<String> getSignalType() {
        return basicKernel.getSignalType();
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
}
