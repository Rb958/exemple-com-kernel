package example.kernel;

import dbgateway.config.ConfigManager;
import dbgateway.config.DBConnectionManager;
import dbgateway.config.def.ConnectionProperties;
import example.entity.EntityUtility;
import example.entity.User;
import example.exeception.BadDBConfigurationException;
import rkernel.BasicKernel;
import rkernel.BasicKernelLoader;
import rkernel.IKernel;
import rkernel.component.BasicComponentLoader;
import rkernel.component.IComponent;
import rkernel.signal.BasicSignal;
import rkernel.signal.ISignalManager;

import java.util.Collection;
import java.util.Map;


public class ExampleKernel implements IKernel {

    private final BasicKernel basicKernel = new BasicKernel.Builder()
            .setKernelLoader(new BasicKernelLoader())
            .setComponentLoader(new BasicComponentLoader())
            .setName("Exemple")
            .build();

    @Override
    public void load() {
        basicKernel.load();
        try {
            Thread.sleep(2000);
            System.out.println("Loading data base...");
            DBConnectionManager dbConnectionManager = new DBConnectionManager();
            ConfigManager configManager = new ConfigManager();
            final String DbName = EntityUtility.getDataBaseName();
            if (dbConnectionManager.hasDataBaseConfig(DbName)){
                ConnectionProperties connectionProperties = dbConnectionManager.getDBConnection(DbName); // Chargement de a configuration depuis le fichier de configuation
                configManager.initDb(EntityUtility.getClasses(),connectionProperties); // initialisation ou mise a jour des tables de la base de donnee
                System.out.println("Database initialised");
            }else{
                BadDBConfigurationException dbConfigurationException = new BadDBConfigurationException("No database named "+ DbName + " was found");
                this.dispatchLogException(dbConfigurationException);
            }
        } catch (InterruptedException e) {
            dispatchLogException(e);
        }
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
    public Collection<IKernel> dispatchSignal(BasicSignal<?> signal) {
        return basicKernel.dispatchSignal(signal);
    }

    @Override
    public Object processSignal(BasicSignal<?> signal) {
        return basicKernel.processSignal(signal);
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
}
