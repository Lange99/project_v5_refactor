package main.java.Net;


import main.java.IO.IO;
import main.java.Json.JsonManager;
import main.java.Project_v5.*;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class NetManager implements Checker {

    private final ArrayList<Net> netList = new ArrayList<>();
    private final ArrayList<PetriNet> petriNetList = new ArrayList<>();
    private final ArrayList<PriorityPetriNet> priorityPetriNetList = new ArrayList<>();
    private static NetManager netManager = new NetManager();

    /**
     * PATTERN SINGLETON
     */
    private NetManager() {}

    public static NetManager getNetManager() {
        return netManager;
    }

    /**
     * this method handles the interface with the user
     *
     * @throws FileNotFoundException
     */
    public void menageOption() throws IOException {
        boolean check = true;
        int choise = 0;
        do {
            IO.print(IO.MENU);
            choise = IO.readNumber(IO.DIGIT_YOUR_CHOISE);
            while (choise < 0 || choise > 5) {
                IO.print(IO.DIGIT_VALID_CHOISE);
                choise = IO.readNumber(IO.DIGIT_YOUR_CHOISE);
            }
            //this switch handles the different situation and it recalls the method for satisfy the user

            switch (choise) {
                case 0: //the program stops running
                    check = false;
                    break;

                case 1: //this chose allows to the user to create a new net
                    addNet();
                    check = IO.yesOrNo(IO.WANT_TO_DO_ANOTHER_OPERATION);
                    break;

                case 2: //this chose allows to the user to load a net
                    check = userloadNet();
                    break;

                case 3: //this chose allows to the user to create a new Petri's net
                    if (netList.size() == 0) {
                        IO.print(IO.NO_NORMAL_NET);
                    } else {
                        addPetriNet();
                    }
                    break;

                case 4: //this chose allows to the user to create a new Petri's net with Priority
                    if (petriNetList.size() == 0) {
                        IO.print(IO.NO_PETRI_NET);
                    } else {
                        PetriNet newNet = JsonManager.loadPetriNet();
                        addPriorityPetriNet(newNet);
                    }
                    break;

                case 5: //this chose allow to the user to build a net from an existing file from pathname
                    String path = IO.readNotEmptyString(IO.INSERT_PATHNAME);
                    if (isValidPath(path)) {
                        IO.print(IO.PATH_CORRECT);
                        handleNet(path);
                    } else {
                        IO.print(IO.ERROR_PATH);
                    }
                    break;
            }
        } while (check);

    }

    /**
     * Extract Method
     * @return true if user want do another operation
     * @throws FileNotFoundException
     */
    private boolean userloadNet() throws FileNotFoundException {
        boolean check;
        int typeNet = IO.readInteger(IO.TYPE_OF_NET, 1, 3);
        switch (typeNet) {

            case 1:
                Net newNet = JsonManager.loadNet();
                if (newNet != null) {
                    netList.add(newNet);
                    IO.showNet(newNet);
                }
                break;

            case 2:
                PetriNet newPetriNet = JsonManager.loadPetriNet();
                if (newPetriNet != null) {
                    if (checkLoadGenericNet(newPetriNet)) {
                        petriNetList.add(newPetriNet);
                        IO.showPetriNet(newPetriNet);
                    } else {
                        IO.print(IO.YOU_CANNOT_ADD_THIS_PETRI_NET_BECAUSE_ITS_DESCRIPTION_IS_NOT_SAVED);
                    }
                }
                break;

            case 3:
                PriorityPetriNet newPriorityPetriNet = JsonManager.loadPriorityPetriNet();
                if (newPriorityPetriNet != null) {
                    if (checkLoadGenericNet(newPriorityPetriNet)) {
                        priorityPetriNetList.add(newPriorityPetriNet);
                        IO.showPriorityPetriNet(newPriorityPetriNet);
                    } else {
                        IO.print(IO.YOU_CANNOT_ADD_THIS_PRIORITY_PETRI_NET_BECAUSE_ITS_DESCRIPTION_IS_NOT_SAVED);
                    }
                }
                break;
        }
        check = IO.yesOrNo(IO.WANT_TO_DO_ANOTHER_OPERATION);
        return check;
    }

    /**
     * method to check if the input path is correct or not
     *
     * @param path
     * @return true if is correct, false if is wrong
     */
    private boolean isValidPath(String path) {
        try {
            Paths.get(path);
        } catch (InvalidPathException e) {
            return false;
        }
        return true;
    }


    /**
     * Method that compares a petri net with those saved in the PetriNetList
     *
     * @param net is the net to check;
     * @return false if two net are equal
     */
    @Override
    public boolean checkGenericNet(PetriNet net) {
        try {
            if (CheckExistence.existsAlreadyGenericNet(net, IO.JSON_PETRI_FILE)) {
                return false;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (PetriNet n : petriNetList) {
            if (net.equals(n)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean checkGenericNet(PriorityPetriNet net) {
        try {
            if (CheckExistence.existsAlreadyGenericNet(net, IO.JSON_PRIORITY_PETRI_FILE)) {
                return false;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (PriorityPetriNet n : priorityPetriNetList) {
            if (net.equals(n)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean checkNetName(Net net) {
        String netName = net.getName();
        for (Net n : netList) {
            if (n.getName().equals(netName)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean checkNetName(PetriNet net) {
        String netName = net.getName();
        for (PetriNet n : petriNetList) {
            if (n.getName().equals(netName)) {
                return false;
            }
        }
        return true;
    }

    /**
     * this method allows to the user to create a new Petri's net
     */
    public void addPetriNet() {
        PetriNet newPetriNet = new PetriNet(loadOneNet());
        IO.showPetriNet(newPetriNet);
        newPetriNet.setName(IO.ReadString(IO.NAME_OF_NET));
        while (!checkNetName(newPetriNet)) {
            IO.print(IO.SET_NEW_NAME);
            newPetriNet.setName(IO.readNotEmptyString(IO.NAME_OF_NET));
        }
        //we add the token to the place
        while (IO.yesOrNo(IO.DO_YOU_WANT_TO_ADD_TOKEN_TO_PLACE)) {
            addTokenToPetriNet(newPetriNet);
        }

        //we add the weight to the transition
        addWeightToPetriNet(newPetriNet);


        if (checkGenericNet(newPetriNet)) {
            if (IO.yesOrNo(IO.DO_YOU_WANT_TO_SAVE_THAT_PETRI_S_NET)) {
                JsonManager.writeJsonPetri(newPetriNet);
            }
            petriNetList.add(newPetriNet);
        }
    }

    private void addPrioritysToTheNet(PriorityPetriNet priorityNet) {
        ArrayList<Transition> tempTransition = new ArrayList<>(priorityNet.getSetOfTrans());

        IO.printTransition(priorityNet.getSetOfTrans());
        int choise = IO.readInteger(IO.WHICH_TRANSITION_ADD_PRIORITY, 1, tempTransition.size());
        int priority = IO.readIntegerWithMin(IO.INSERT_PRIORITY_OF_TRANSITION, 1);

        String transitionId = tempTransition.get(choise - 1).getName();

        if (priorityNet.addPriority(transitionId, priority)) {
            IO.print(IO.PRIORITY_ADDEN);
        } else {
            IO.print(IO.TRANSITION_DOESNT_EXIST);
        }
    }

    private void addWeightToPetriNet(PetriNet newPetriNet) {
        while (IO.yesOrNo(IO.ADD_WEIGHT)) {
            ArrayList<Transition> transTemp = new ArrayList<>(newPetriNet.getSetOfTrans());

            // User choose the transition to modify
            IO.printTransition(transTemp);
            int choose = IO.readInteger(IO.TRANSITION_CHOOSE, 0, transTemp.size()) - 1;

            // show the predecessors and successors of the related transition
            ArrayList<String> placeTemp = new ArrayList<>();
            placeTemp.addAll(transTemp.get(choose).getIdPre());
            placeTemp.addAll(transTemp.get(choose).getIdPost());

            do {
                IO.printString(placeTemp);
                //I ask which transaction-seat you want to change
                String placeToChange = placeTemp.get(IO.readInteger(IO.WHAT_PLACE_YOU_WANT_CHANGE, 0, placeTemp.size()) - 1);
                int weight = IO.readIntegerWithMin(IO.INSERT_THE_WEIGHT_THAT_YOU_WANT_TO_GIVE_TO_THE_PLACE, 0);
                newPetriNet.addWeight(transTemp.get(choose).getName(), placeToChange, weight);
            } while (IO.yesOrNo(IO.DO_YOU_WANT_TO_ADD_OTHER_WEIGHTS_TO_THIS_TRANSITION));

        }


    }

    private void addTokenToPetriNet(PetriNet newPetriNet) {
        ArrayList<Place> tempPlace = new ArrayList<>(newPetriNet.getSetOfPlace());

        IO.printPlace(newPetriNet.getSetOfPlace());
        int choise = IO.readInteger(IO.WHERE_DO_YOU_WANT_TO_ADD_THE_TOKENS, 0, tempPlace.size());
        int token = IO.readIntegerWithMin(IO.INSERT_THE_NUMBER_OF_TOKENS, 0);

        String placeId = tempPlace.get(choise - 1).getName();

        if (newPetriNet.addToken(placeId, token)) {
            IO.print(IO.THE_WEIGHT_HAS_BEEN_ADDED);
        } else {
            IO.print(IO.THE_PLACE_DOESN_T_EXIST);
        }
    }

    /**
     * this method allows to add a net
     */
    public void addNet() throws FileNotFoundException {
        String placeName;
        String transName;
        int inOrOut;

        do {
            Net n = new Net(IO.readNotEmptyString(IO.NAME_OF_NET));
            while (!checkNetName(n)) {
                IO.print(IO.SET_NEW_NAME);
                n.setName(IO.readNotEmptyString(IO.NAME_OF_NET));
            }
            do {
                // ask to user the place's ID and the transition's ID
                placeName = IO.readNotEmptyString(IO.INSERT_PLACE_S_ID);
                transName = IO.readNotEmptyString(IO.INSERT_TRANSITION_S_ID);
                inOrOut = IO.readInteger(IO.connectionBetweenPlaceandTrans(transName, placeName), 1, 2);
                //this If check if the new node is equal to another one which is already in the net
                //I create a temporary transition and a temporary place because it makes easy to check them
                Transition t = new Transition(transName);
                Place p = new Place(placeName);
                if (!n.addPair(t, p, inOrOut)) {
                    System.out.println(IO.YOU_CAN_T_ADD_THIS_PAIR_BECAUSE_ALREADY_EXISTS);
                }
            } while (IO.yesOrNo(IO.YOU_WANT_ADD_ANOTHER_PAIR));
            //if the new net is correct we show it to the user and ask if he wants to save it
            if (CheckNet.checkPendantNode(n) && CheckNet.checkTrans(n) && CheckNet.checkConnect(n) && CheckExistence.checkEqualNet(n)) {
                IO.showNet(n);
                IO.print(IO.THE_NET_IS_CORRECT_WE_ARE_GOING_TO_SAVE_IT);

                netList.add(n);

                if (IO.yesOrNo(IO.SAVE_NET)) {
                    JsonManager.writeJsonNet(n);
                }
            } else {
                //if the net is incorrect we inform the user
                IO.printError(IO.THE_NET_IS_INCORRECT_IT_CAN_T_BE_SAVED);

            }
        } while (IO.yesOrNo(IO.ANOTHER_NET));
    }

    /**
     * method that prints all the nets in the netlist and returns a choice from the user
     *
     * @return the net choice
     */
    public Net loadOneNet() {

        for (int i = 0; i < netList.size(); i++) {
            IO.print(i + ") " + netList.get(i).getName());

        }
        int choise = IO.readInteger("choose the network number ", 0, netList.size());
        return netList.get(choise);
    }

    private PetriNet loadOnePetriNet() {
        for (int i = 0; i < petriNetList.size(); i++) {
            IO.print(i + ") " + petriNetList.get(i).getName());

        }
        int choise = IO.readInteger("choose the network number ", 0, petriNetList.size());
        return petriNetList.get(choise);
    }

    public void addPriorityPetriNet(PetriNet pN) {
        PriorityPetriNet newNet = new PriorityPetriNet(pN);
        String name = IO.ReadString(IO.WHAT_IS_THE_PRIORITY_PETRI_S_NET_CALLED);
        newNet.setName(name);
        while (IO.yesOrNo(IO.DO_YOU_WANT_ADD_PRIORITIES)) {
            assignPriority(newNet);
        }
        //priorityPetriNetList.add(newNet);
        if (checkGenericNet(newNet)) {
            if (IO.yesOrNo(IO.SAVE_PRIORITY_PETRI_NET)) {
                JsonManager.writeJsonPriorityPetriNet(newNet);
                //IO.print(IO.SET_NEW_NAME);
                //newNet.setName(IO.readNotEmptyString(IO.NAME_OF_NET));
            }
            priorityPetriNetList.add(newNet);
        }

    }

    public void assignPriority(PriorityPetriNet pnp) {
        ArrayList<Transition> tempArr = new ArrayList<>(pnp.getSetOfTrans());
        int i = 0;
        for (i = 0; i < tempArr.size(); i++) {
            IO.print(i + ") " + tempArr.get(i).getName());
        }
        int choise = IO.readInteger(IO.WHICH_TRANSITION_DO_YOU_WANT_TO_PRIORITIZE, 0, tempArr.size() - 1);
        int priorityNumber = IO.readIntegerWithMin(IO.WHAT_PRIORITY_DO_YOU_WANT_ASSIGN, 0);
        pnp.addPriority(tempArr.get(choise).getName(), priorityNumber);
    }

    public boolean existsAlreadyPriorityPetriNet(PriorityPetriNet newPriorityPetriNetToCheck) throws FileNotFoundException {
        assert newPriorityPetriNetToCheck != null;
        // bulld array String of the list of all file in JsonPetri directory
        String[] pathname = JsonManager.getPathname(IO.JSON_PRIORITY_PETRI_FILE);

        ArrayList<String> pairsNetToCheck = CheckExistence.getStringPairsFromPetriNet(newPriorityPetriNetToCheck);
        int sizePairsNetToCheck = pairsNetToCheck.size();

        for (String pathnameOfFileToCheck : pathname) {
            PetriNet existingNet = JsonManager.readPetriJson(IO.JSON_PRIORITY_PETRI_FILE + pathnameOfFileToCheck);
            ArrayList<String> pairsExistingNet = CheckExistence.getStringPairsFromPetriNet(existingNet);
            int counter = 0;
            int sizeArrayPairsExistingNet = pairsExistingNet.size();

            if (sizePairsNetToCheck == sizeArrayPairsExistingNet) {
                for (String toCheck : pairsNetToCheck) {
                    for (String existing : pairsExistingNet) {
                        if (toCheck.equals(existing)) {
                            counter = counter + 1;
                            continue;
                        }
                    }
                }
                if (counter == sizePairsNetToCheck) {
                    return true;
                }
            } else {
                continue;
            }
        }
        return false;
    }

    @Override
    public boolean checkLoadGenericNet(PetriNet net) throws FileNotFoundException {
        List<Net> netToCheck = JsonManager.loadAllSimpleNet();
        for (Net simpleNet : netToCheck) {
            if (net.checkFatherNet(simpleNet)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkLoadGenericNet(PriorityPetriNet petriNet) throws FileNotFoundException {
        List<PetriNet> petriNetList = JsonManager.loadAllPetriNet();
        for (PetriNet net : petriNetList) {
            if (petriNet.checkFatherPetriNet(net)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method that checks the correctness of a network inserted by files with different Paths
     * @param pathOfFile
     * @throws FileNotFoundException
     */
    public void handleNet(String pathOfFile) throws FileNotFoundException {
        BasicNet net = JsonManager.loadFileFromAnyPath(pathOfFile);
        if (net instanceof PriorityPetriNet) {
            priorityPetriNetList.add((PriorityPetriNet) net);
            IO.showPriorityPetriNet((PriorityPetriNet) net);
        } else if (net instanceof PetriNet) {
            petriNetList.add((PetriNet) net);
            IO.showPetriNet((PetriNet) net);
        } else if (net instanceof Net) {
            netList.add((Net) net);
            IO.showNet((Net)net);
        } else if (net == null) {
            IO.printError(IO.THE_NET_IS_NOT_VALID);
        }
    }
    public static PetriNet loadPetriNet() throws FileNotFoundException {
        return JsonManager.loadPetriNet();
    }

    public static PriorityPetriNet loadPriorityPetriNet() throws FileNotFoundException{
        return JsonManager.loadPriorityPetriNet();
    }


}
