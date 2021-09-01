package main.java.Project_v5;

import main.java.Net.NetManager;

import java.io.IOException;

public class Configurator implements StartOperation {

    private static Configurator configurator = new Configurator();

    private Configurator() {}

    public static Configurator getConfigurator() {
        return configurator;
    }

    @Override
    public void operation(NetManager netManager) throws IOException {
        netManager.menageOption();
    }
}
