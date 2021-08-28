package main.java.Project_v5;

import main.java.Utility.IO;

import java.io.IOException;

public class Manager {

    NetManager manager = NetManager.getNetManager();
    User user = new User();
    Configurator config = new Configurator();

    public void start() throws IOException {
        boolean check=false;
        do {
            int choice = IO.readInteger(IO.CHOOSE_THE_ELEMENT, 0, 2);
            switch (choice) {
                case 0:
                    check=false;
                    break;
                case 1:
                    user.operation();
                    check = IO.yesOrNo(IO.DO_YOU_WANT_CLOSE_THE_PROGRAM);
                    break;

                case 2:
                    config.operation(manager);
                    check = IO.yesOrNo(IO.DO_YOU_WANT_CLOSE_THE_PROGRAM);
                    break;
            }
        } while (!check);
    }

}
