package main.java.Project_v5;

import main.java.Net.NetManager;

import java.io.IOException;

public class StrategyContext {
    private StartOperation modalityContext;
    private NetManager netManager;

    public void setModalityContext(int choise) {
        if (choise == 1) {
            modalityContext = User.getUser();
            netManager = null;
        }
        else {
            modalityContext = Configurator.getConfigurator();
            netManager = NetManager.getNetManager();
        }
    }

    public void beginOperation() throws IOException {
        modalityContext.operation(netManager);
    }
}
