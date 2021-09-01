package main.java.Project_v5;

import main.java.Net.NetManager;

import java.io.IOException;

public class StrategyContext {
    private StartOperation modalityContext;
    private NetManager netManager;

    public void setModalityContext(int choise) {
        if (choise == 1) {
            this.modalityContext = User.getUser();
            netManager = null;
        }
        else {
            this.modalityContext = Configurator.getConfigurator();
            netManager = NetManager.getNetManager();
        }
    }

    public void beginOperation() throws IOException {
        this.modalityContext.operation(netManager);
    }
}
