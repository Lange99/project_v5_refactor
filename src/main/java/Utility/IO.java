package main.java.Utility;


import main.java.Project_v5.*;

import java.io.File;
import java.util.*;

public class IO {
    public static final String WHAT_DO_YOU_WANT_DO_0_EXIT_1_START_SIMULATION = "What do you want do?\n0)EXIT\n1)Load the net \n2) Simulation of Petri net\n3) Load Petri net with priority\n4) Simulation of Petri net with priority ";
    public static final String THERE_AREN_T_ANY_NETS_LOADED_YOU_HAVE_TO_LOAD_ONE_NET_BEFORE_THE_SIMULATION = "There aren't any nets loaded, you have to load one net before the simulation";
    public static final String DO_YOU_WANT_CLOSE_THE_PROGRAM = "Do you want close the program?\n";
    public static final String STOP = "If you want to stop the simulatoin press 0";
    public static final String THIS_TRANSITIONS_WILL_BE_UPDATED = "This transition will move the token to this place";
    public static final String DO_YOU_WANT_TO_CONTINUE_THE_SIMULATION = "Do you want to continue the simulation?";

    public static final String CHOOSE_THE_ELEMENT = "choose the mode:\n0) Exit \n1) User mode \n2) Configurator mode";
    public static final String MARKING_WITH_TOKEN = "The first marking is given by:";
    public static final String WHERE_THERE_ARE = " where there are ";
    public static final String SET_NEW_NAME = "A network with this name already exists, please enter a different name";
    public static final String DO_YOU_WANT_TO_SAVE_THAT_PETRI_S_NET = "Do you want to save that Petri's Net?";
    public static final String THERE_AREN_T_ANY_TRANSITION_AVAILABLE = "There aren't any transition available ";
    public static final String THE_FOLLOWING_TRANSITION_ARE_AVAILABLE = "The following transition are available";
    public static final String INSERT_THE_NUMBER_OF_THE_TRANSITION_YOU_WANT_TO_USE = "Insert the number of the transition you want to use";
    public static final String HOW_MANY_TOKEN = "How many tokens do you want this place to have?\n(if you don't want tokens enter 0)";
    public static final String DO_YOU_WANT_TO_LOAD_OTHER_NETS = "Do you want to load other nets?";
    public static final String DO_YOU_WANT_TO_ADD_TOKEN_TO_PLACE = "Do you want to add token to place ? ";
    public static final String THERE_AREN_T_ANY_FILES_TO_LOAD = "There aren't any files to load";
    public static final String WHERE_DO_YOU_WANT_TO_PUT_THE_TOKEN = "Where do you want to put the token?";
    public static final String THIS_TRANSITION_CAN_MOVE_THE_TOKENS_IN_DIFFERENT_PLACES = "This transition can move the tokens in different places";
    public static final String DO_YOU_WANT_TO_MAKE_AN_OTHER_SIMULATION = "Do you want to make an other simulation?";
    public static final String YOU_HAVE_TO_LOAD_A_NET_WHICH_ONE_DO_YOU_WANT = "\nYou have to load a net, which one do you want?";
    public static final String INSERT_THE_NUMBER_OF_THE_NET_THAT_YOU_WANT_TO_USE = "Insert the number of the net that you want to use";
    public static final String INSERT_PLACE_S_ID = "Insert place's Name ";
    public static final String INSERT_TRANSITION_S_ID = "Insert transition's Name ";
    public static final String YOU_CAN_T_ADD_THIS_PAIR_BECAUSE_ALREADY_EXISTS = "You can't Add this pair because it already exists";
    public static final String YOU_WANT_ADD_ANOTHER_PAIR = "You want add another Pair?";
    public static final String WRITING_FILE_ERROR = "writing file error.";
    public static final String TYPE_OF_NET = "Do you want load:\n1) simple net\n2) Petri Net\n3) Petri's Net with Priority" ;
    public static final String FILE_IS_LOADED = "File is loaded";
    public static final String VISUALIZE_THE_LIST = "Visualize the list";
    public static final String ADD_WEIGHT = "Do you want to add weight to the transition? If you say no we insert the default value";
    public static final String TRANSITION_CHOOSE = "These are the transition in the Net, do you have to choose which one modify: (insert the number)" ;
    public static final String INSERT_PATHNAME = "Insert the pathname of the file to load";
    public static final String ERROR_PATH = "The pathname is wrong";
    public static final String PATH_CORRECT = "The pathname is correct";
    private static final String path = new File("src/main/java/JsonFile").getAbsolutePath();
    private static final String petriPath = new File("src/main/java/JsonPetri").getAbsolutePath();
    public static final String ANOTHER_NET = "You want add another Net?";
    public static final String NAME_OF_NET = "Add the name of Net:";
    public static final String MENU = "What do you want do?\n0)EXIT\n1)Add new Net\n2)Load net\n3)Create a new Petri's Net\n4)Create a new Petri's Net with Priority\n5)import from a different Path name";
    public static final String WANT_TO_DO_ANOTHER_OPERATION = "you want to do another operation ";
    public static final String SAVE_NET = "Do you want to save the net that you have already made? ";
    public static final String DIGIT_YOUR_CHOISE = "Digit your choise ";
    public static final String DIGIT_VALID_CHOISE = "Digit valid choise!";
    public static final String THE_NET_IS_INCORRECT_IT_CAN_T_BE_SAVED = "The net is incorrect, it can't be saved";
    public static final String THE_NET_IS_CORRECT_WE_ARE_GOING_TO_SAVE_IT = "The net is correct, we are going to save it";
    public static final String NO_NORMAL_NET = "There aren't any nets! You have to insert or load a net before adding a Petri Net";
    public static final String NO_PETRI_NET = "There aren't any nets! You have to insert or load a net before adding a Petri Net";
    public static final String THE_NET_IS_NOT_VALID = "The file doesn't contain any net!";
    public static final String JSON_FILE = "src/main/java/JsonFile";
    public static final String JSON_PETRI_FILE = "src/main/java/JsonPetri/";
    public static final String JSON_PRIORITY_PETRI_FILE = "src/main/java/JsonPriority/";
    // public static final String HOW_MANY_TOKEN = "How many tokens do you want this place to have?\n(if you don't want tokens enter 0)";
    public final static String WHAT_PLACE_YOU_WANT_CHANGE = "What place you want change?";
    private final static String ERROR_FORMAT = "Warning: the entered data are in the wrong format.";
    private final static String MINIMUM_ERROR = "Warning: the value must to be grater or equal to ";
    private final static String STRING_EMPTY_ERROR = "Warning: the string entered is empty";
    private final static String MAXIMUM_ERROR = "Warning: the value must to be lower or equal to ";
    private final static String MESSAGES_ALLOWED = "Warning, the value allowed are: ";
    private final static char YES_ANSWER = 'Y';
    private final static char NO_ANSWER = 'N';
    public static final String INSERT_THE_WEIGHT_THAT_YOU_WANT_TO_GIVE_TO_THE_PLACE = "Insert the weight that you want to give to the place";
    public static final String DO_YOU_WANT_TO_ADD_OTHER_WEIGHTS_TO_THIS_TRANSITION = "Do you want to add other weights to this transition?";
    public static final String WHERE_DO_YOU_WANT_TO_ADD_THE_TOKENS = "where do you want to add the tokens?";
    public static final String INSERT_THE_NUMBER_OF_TOKENS = "Insert the number of tokens: ";
    public static final String THE_WEIGHT_HAS_BEEN_ADDED = "The weight has been added";
    public static final String THE_PLACE_DOESN_T_EXIST = "The place doesn't exist";
    public static final String INSERT_PRIORITY_OF_TRANSITION = "Insert the priority value of the transition: ";
    public static final String WHICH_TRANSITION_ADD_PRIORITY = "Chose the transition to add the priority: ";
    public static final String PRIORITY_ADDEN = "The priority has been added.";
    public static final String TRANSITION_DOESNT_EXIST = "The transition doesn't exist";
    public static final String DO_YOU_WANT_SAVE_PRIORITY_NET = "Do you want to save the net with priority?";
    public static final String WHAT_IS_THE_PRIORITY_PETRI_S_NET_CALLED = "What is the Priority Petri's net called?";
    public static final String DO_YOU_WANT_ADD_PRIORITIES = "Do you want add priorities?";
    public static final String WHICH_TRANSITION_DO_YOU_WANT_TO_PRIORITIZE = "Which transition do you want to prioritize?";
    public static final String WHAT_PRIORITY_DO_YOU_WANT_ASSIGN = "What priority do you want to assign?\nThe higher the number, the higher the priority of the transition)";
    public static final String SAVE_PRIORITY_PETRI_NET = "Do you want to save the new Petri Net with priority?";
    public static final String FILE_NOT_CORRECT = "The file that you are trying to load is in the wrong format.";
    public static final String YOU_CANNOT_ADD_THIS_PETRI_NET_BECAUSE_ITS_DESCRIPTION_IS_NOT_SAVED = "You cannot add this petri net because its description is not saved";
    public static final String YOU_CANNOT_ADD_THIS_PRIORITY_PETRI_NET_BECAUSE_ITS_DESCRIPTION_IS_NOT_SAVED = "You cannot add this Petri's net with Priority because the normal Petri's net is not saved";

    private static Scanner reader = scannerBuild();

    /**
     * method to print a string
     * @param s is a string to print
     */
    public static void print(String s){
        System.out.println(s);
    }

    /**
     * method to print an error
     * @param s is the error to print
     */
    public static void printError(String s){
        System.err.println(s);
    }

    /**
     * method to read a not empity string
     * @param message to print
     */
    public static String readNotEmptyString(String message) {
        boolean finish = false;
        String read = null;
        do {
            read = ReadString(message);
            read = read.trim();
            if (read.length() > 0)
                finish = true;
            else
                print(STRING_EMPTY_ERROR);
        } while (!finish);

        return read;
    }

    /**
     * method to check if the user want do something
     * @param message is the operation on which confirmation is requested
     */
    public static boolean yesOrNo(String message) {
        String myMessage = message + "(" + YES_ANSWER + "/" + NO_ANSWER + ")";
        char readValue = readUpperChar(myMessage, String.valueOf(YES_ANSWER) + String.valueOf(NO_ANSWER));

        if (readValue == YES_ANSWER)
            return true;
        else
            return false;
    }

    /**
     * This method allows me (concisely) to create the string I use to ask the user if the place he wants to add is a successor or predecessor of a transition
     * @param transName
     * @param placeName
     * @return the final string
     */
    public static String connectionBetweenPlaceandTrans(String transName, String placeName){
        String question = String.format("Which type of connection there is between the place %s and the transition %s? ", placeName, transName);
        String chose1 = String.format("\n1) %s is an INPUT of %s ", placeName, transName);
        String chose2 = String.format("\n2) %s is an OUTPUT of %s ", placeName, transName);
        String finalString = (question.concat(chose1)).concat(chose2);
        return finalString;
    };


    /**
     * method to read a Int with a minimum an maximus
     * @param message to print
     */
    public static int readInteger(String message, int min, int max) {
        boolean finish = false;
        int readValue = 0;
        do {
            readValue = readNumber(message);
            if (readValue >= min && readValue <= max)
                finish = true;
            else if (readValue < min)
                print(MINIMUM_ERROR + min);
            else
                print(MAXIMUM_ERROR + max);
        } while (!finish);

        return readValue;
    }

    /**
     * method to read a Int with a minimum
     * @param message to print
     */
    public static int readIntegerWithMin(String message, int min) {
        boolean finish = false;
        int readValue = 0;
        do {
            readValue = readNumber(message);
            if (readValue >= min)
                finish = true;
            else
                print(MINIMUM_ERROR+min);
        } while (!finish);

        return readValue;
    }


    public static int readNumber(String message) {
        boolean finish = false;
        int readValue = 0;
        do {
            print(message);
            try {
                readValue = reader.nextInt();
                finish = true;
            } catch (InputMismatchException e) {
                print(ERROR_FORMAT);
                String toDelete = reader.next();
            }
        } while (!finish);
        return readValue;
    }

    /**
     * Method to read char among those authorized
     * @param message to print
     * @param allowed is string allowed
     */
    public static char readUpperChar(String message, String allowed) {
        boolean finish = false;
        char readValue = '\0';
        do {
            readValue = readChar(message);
            readValue = Character.toUpperCase(readValue);
            if (allowed.indexOf(readValue) != -1)
                finish = true;
            else
                print(MESSAGES_ALLOWED+allowed);
        } while (!finish);
        return readValue;
    }

    /**
     * method to read a char
     * @param message to print
     */
    public static char readChar(String message) {
        boolean finish = false;
        char readValue = '\0';
        do {
            print(message);
            String read = reader.next();
            if (read.length() > 0) {
                readValue = read.charAt(0);
                finish = true;
            } else {
                print(STRING_EMPTY_ERROR);
            }
        } while (!finish);
        return readValue;
    }

    private static Scanner scannerBuild() {
        Scanner created = new Scanner(System.in);
        created.useDelimiter(System.lineSeparator() + "|\n");
        return created;
    }

    /**
     * method to read a string
     * @param message to print
     */
    public static String ReadString(String message) {
        print(message);
        return reader.next();
    }

    /**
     * method for print a list of Place
     * @param list to print
     */
    public static void printPlace(Iterable<Place> list){
        int i=1;
        for(Place p:list){
            IO.print(i+") "+ p.getName());
            i++;
        }

    }

    /**
     * method to print a list of Transition
     * @param list to print
     */
    public static void printTransition(Iterable<Transition> list){
        int i=1;

        for(Transition t:list){
            IO.print(i+") "+ t.getName());
            i++;
        }
    }

    /**
     * method to print a list of String
     * @param list to print
     */
    public static void printString(List<String> list) {
        for(int i=0; i<list.size();i++){
            IO.print((i+1)+") "+list.get(i));
        }
    }

    /**
     * method to view the Petri's net
     *
     * @param net
     */
    public static void showPetriNet(PetriNet net) {
        StringBuffer buffer = new StringBuffer();
        for(Transition t: net.getSetOfTrans()){
            for(String nPlace: t.getIdPre()){
                Place p = net.getPlace(nPlace);
                Pair tempPair = net.getPair(p, t);
                String temp = p.getName()+ "("+ p.getNumberOfToken()+")\t---["+ tempPair.getWeight()+"]--->\t" + t.getName() +"\n";
                buffer.append(temp);
            }
            for(String nPlace: t.getIdPost()){
                Place p = net.getPlace(nPlace);
                Pair tempPair = net.getPair(p, t);
                String temp = p.getName() + "("+ p.getNumberOfToken()+")\t<---["+ tempPair.getWeight()+"]---\t" + t.getName() +"\n";
                buffer.append(temp);
            }
        }
        IO.print(buffer.toString());
    }


    /**
     * method to view the Priority Petri's net
     *
     * @param net
     */
    public static void showPriorityPetriNet(PriorityPetriNet net) {
        StringBuffer buffer = new StringBuffer();
        for(Transition t: net.getSetOfTrans()){
            int priority = net.getPriorityByTransition(t);
            for(String nPlace: t.getIdPre()){
                Place p = net.getPlace(nPlace);
                Pair tempPair = net.getPair(p, t);
                String temp = p.getName()+ "("+ p.getNumberOfToken()+")\t---["+ tempPair.getWeight()+"]--->\t" + t.getName() +"\tPriority: "+ priority+"\n";
                buffer.append(temp);
            }
            for(String nPlace: t.getIdPost()){
                Place p = net.getPlace(nPlace);
                Pair tempPair = net.getPair(p, t);
                String temp = p.getName() + "("+ p.getNumberOfToken()+")\t<---["+ tempPair.getWeight()+"]---\t" + t.getName() +"\tPriority: "+ priority+"\n";
                buffer.append(temp);
            }
        }
        IO.print(buffer.toString());
    }
    /**
     * method to view the net
     *
     * @param net
     */
    public static void showNet(Net net) {
        StringBuffer buffer = new StringBuffer();
        for(Transition t: net.getSetOfTrans()){
            for(String nPlace: t.getIdPre()){
                Place p = net.getPlace(nPlace);
                String temp = p.getName()+ "\t------>\t" + t.getName() +"\n";
                buffer.append(temp);
            }
            for(String nPlace: t.getIdPost()){
                Place p = net.getPlace(nPlace);
                String temp = p.getName() + "\t<------\t" + t.getName() +"\n";
                buffer.append(temp);
            }
        }
        IO.print(buffer.toString());
    }

    /**
     * Method used for print a list of Petri's net
     * @param nets
     */
    public static void printPetriNets(Iterable<PetriNet> nets) {
        int i=1;
        for (Net n: nets){
            IO.print(i+") " + n.getName());
        i++;
        }

    }

    /**
     * Method used for print a list of Petri's net with Priority
     * @param nets
     */
    public static void printPriorityPetriNets(ArrayList<PriorityPetriNet> nets) {
        int i=0;
        for (Net n: nets){
            IO.print(i+") " + n.getName());
            i++;
        }

    }

}
