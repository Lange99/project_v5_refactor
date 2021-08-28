package main.java.Project_v5;

import java.io.IOException;

public class Configurator implements StartOperation {

    private static Configurator configurator;

    private Configurator() {}

    public static Configurator getConfigurator() {
        return configurator;
    }

    @Override
    public void operation(NetManager netManager) throws IOException {
        netManager.menageOption();
    }
}
