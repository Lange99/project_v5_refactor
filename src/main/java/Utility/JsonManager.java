package main.java.Utility;

import main.java.Project_v4.Net;
import main.java.Project_v4.PetriNet;
import main.java.Project_v4.PriorityPetriNet;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JsonManager { public static final String INSERT_THE_ID_OF_THE_FILE_YOU_WANT_TO_LOAD = "Insert the id of the file you want to load ";
    public static final String FILE_IS_LOADED = "File is loaded";
    public static final String SHOW_THE_NET = "Show the net";
    public static final String THERE_AREN_T_ANY_FILES_TO_LOAD = "There aren't any files to load";

    /**
     * method to load a net by json file
     *
     * @throws FileNotFoundException
     * @return newNet if there is a json, null if there isn't any file
     */
    public static String getPath(String path) throws FileNotFoundException {
        String[] pathname = getPathname(path);
        int i = checkNumFile(pathname);
        //Net newNet;
        //if there are not files in the directory print this
        if (i==0) {
            System.out.println(THERE_AREN_T_ANY_FILES_TO_LOAD);
        }
        else {
            //else ask to user to chose which file load

            int number = IO.readInteger(INSERT_THE_ID_OF_THE_FILE_YOU_WANT_TO_LOAD, 1, i);
            //get the name of file by the pathname string array and decrement 1 because the print file start with 1
            String pathFile = path + "/" + pathname[number - 1];
            return pathFile;
        }
        return null;
    }

    /**
     * Method allowing to load a Petri's net
     * @return the loaded network
     * @throws FileNotFoundException
     */
    public static PetriNet loadPetriNet() throws FileNotFoundException {
        String pathFile = getPath(IO.JSON_PETRI_FILE);
        if (pathFile != null) {
            PetriNet newNet = JsonReader.readPetriJson(pathFile);
            System.out.println(FILE_IS_LOADED);
            return newNet;
        }
        return null;
    }

    /**
     * Method allowing to load a net
     * @return the loaded network
     * @throws FileNotFoundException
     */
    public static Net loadNet() throws FileNotFoundException {
        String pathFile = getPath(IO.JSON_FILE);
        if (pathFile != null) {
            Net newNet = JsonReader.readJson(pathFile);
            System.out.println(FILE_IS_LOADED);
            return newNet;
        }
        return null;
    }

    public static PriorityPetriNet loadPriorityPetriNet() throws FileNotFoundException {
        String pathFile = getPath(IO.JSON_PRIORITY_PETRI_FILE);
        if (pathFile != null) {
            PriorityPetriNet newNet = JsonReader.readPriorityPetriNet(pathFile);
            System.out.println(FILE_IS_LOADED);
            return newNet;
        }
        return null;
    }

    /**
     * method used to get number of existing file
     * @param pathname
     * @return the number of file i
     */
    public static int checkNumFile(String[] pathname) {
        assert !pathname.equals(null);
        int i = 0;
        //for every name file in the directory print the name
        if (pathname != null) {
            for (String s : pathname) {
                i++;
                System.out.println(i + ") " + s);
            }
        }
        return i;
    }

    /**
     * method to get the pathname of the directory
     * @param path
     * @return pathname
     */
    public static String[] getPathname(String path) {
        assert !path.equals(null);
        //initialize the File object directory
        File directory = new File(path);
        //initialize the string that contains the list of name file
        String[] pathname = directory.list();
        return pathname;
    }

    /**
     * method to check if the index to check has already checked or not
     *
     * @param index is index hashmap of all pair
     * @param i first index to check
     * @param j second index to check
     * @return true if exist already, false vice versa
     */
    public static boolean existAlready(HashMap<Integer, Integer> index, int i, int j) {
        assert !index.isEmpty();
        boolean ctrl = false;
        for (Map.Entry<Integer, Integer> entry : index.entrySet()) {
            if (entry.getKey() == i) {
                if (entry.getValue() == j) {
                    ctrl = true;
                }
            }
        }
        return ctrl;
    }

    /**
     * VERSIONE 5
     */

    public static ArrayList<Net> loadAllSimpleNet() throws FileNotFoundException {
        ArrayList<Net> listOfNet = new ArrayList<>();
        String[] listOfAllFile = getPathname(IO.JSON_FILE);
        for (String pathnameSingleFile: listOfAllFile) {
            if (pathnameSingleFile != null) {
                Net newNetToLoad = JsonReader.readJson(pathnameSingleFile);
                listOfNet.add(newNetToLoad);
            }
        }
        return listOfNet;
    }

    public static ArrayList<PetriNet> loadAllPetriNet() throws FileNotFoundException {
        ArrayList<PetriNet> listOfNet = new ArrayList<>();
        String[] listOfAllFile = getPathname(IO.JSON_PETRI_FILE);
        for (String pathnameSingleFile: listOfAllFile) {
            if (pathnameSingleFile != null) {
                PetriNet newNetToLoad = JsonReader.readPetriJson(pathnameSingleFile);
                listOfNet.add(newNetToLoad);
            }
        }
        return listOfNet;
    }

    public static ArrayList<PriorityPetriNet> loadAllPriorityPetriNet() throws FileNotFoundException {
        ArrayList<PriorityPetriNet> listOfNet = new ArrayList<>();
        String[] listOfAllFile = getPathname(IO.JSON_PRIORITY_PETRI_FILE);
        for (String pathnameSingleFile: listOfAllFile) {
            if (pathnameSingleFile != null) {
                PriorityPetriNet newNetToLoad = JsonReader.readPriorityPetriNet(pathnameSingleFile);
                listOfNet.add(newNetToLoad);
            }
        }
        return listOfNet;
    }

    //VERSIONE 5
    public static void loadFileFromAnyPath(String pathSelected) throws FileNotFoundException {
        String pathOfFile = getPath(pathSelected);
        if (pathOfFile != null) {
            if (findInTheFile("@priority", pathSelected)) {
                PriorityPetriNet newNet = JsonReader.readPriorityPetriNet(pathSelected);

            }
            else if (findInTheFile("@token", pathSelected)) {
                PetriNet newNet = JsonReader.readPetriJson(pathSelected);

            }
            else {
                PetriNet newNet = JsonReader.readPetriJson(pathSelected);
            }
            System.out.println(FILE_IS_LOADED);
        }
    }

    public void

    private static boolean findInTheFile(String wordToFind, String pathnameFile) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(pathnameFile));
            String line = reader.readLine();
            while (line != null) {
                line = reader.readLine();
                if (line.contains(wordToFind)) {
                    return true;
                }
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
