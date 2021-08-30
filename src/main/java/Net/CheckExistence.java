package main.java.Net;

import main.java.IO.IO;
import main.java.Json.JsonManager;


import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;

public class CheckExistence {
    /**
     * Method to check if the petri net insert exist already or is new and it is possible add it
     *
     * @param net
     * @return true if there is already the same net, false if there isn't
     * @throws FileNotFoundException
     */
    public static boolean existsAlreadyGenericNet(BasicNet net, String path) throws FileNotFoundException {
        assert net != null;
        // bulld array String of the list of all file in JsonPetri directory
        String[] pathname = JsonManager.getPathname(path);

        String nameNetToCheck = net.getName();
        ArrayList<String> pairsNetToCheck = getStringPairsFromPetriNet(net);
        int sizePairsNetToCheck = pairsNetToCheck.size();

        for (String pathnameOfFileToCheck : pathname) {
            PetriNet existingNet = JsonManager.readPetriJson(path + pathnameOfFileToCheck);
            ArrayList<String> pairsExistingNet = getStringPairsFromPetriNet(existingNet);
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

    private static ArrayList<String> getStringPairsFromPetriNet(BasicNet net) {
        int i = 0;
        ArrayList<String> stringPair = new ArrayList<>();
        for (Pair pair : net.getNet()) {
            String placeName = pair.getPlaceName();
            String tokenNumber = String.valueOf(pair.getNumberOfToken());
            String transitionName = pair.getTransName();
            String weightNumber = String.valueOf(pair.getWeight());
            String placeNameOfPrePlace = pair.getIdPreviusPlaceByIndex(i);
            String placeNameOfPostPlace = pair.getIdPostPlaceByIndex(i);
            String stringToElaborate = "Name: " + placeName + "\n" +
                    "Token: " + tokenNumber + "\n" +
                    "Transition: " + transitionName + "\n" +
                    "Weight: " + weightNumber + "\n" +
                    "First place: " + placeNameOfPrePlace + "\n" +
                    "Second place: " + placeNameOfPostPlace + "\n";
            String stringToAdd = getHashcode(stringToElaborate);
            stringPair.add(stringToAdd);
        }
        return stringPair;
    }

    private static String getHashcode(String stringToEncrypt) {
        String sha1 = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(stringToEncrypt.getBytes(StandardCharsets.UTF_8));
            sha1 = String.format("%040x", new BigInteger(1, digest.digest()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sha1;
    }

    /**
     * this method check if the net already exists and that can't be saved
     *
     * @param netToCheck the net that should be check
     * @return true if that net already exists and false if it doesn't
     * @throws FileNotFoundException PRECONDITION: NetToCheck!=null
     */
    public static boolean checkEqualNet(Net netToCheck) throws FileNotFoundException {
        assert netToCheck != null;
        //initialize the File object directory
        File directory = new File(IO.JSON_FILE);
        //initialize the string that contains the list of name file
        String[] pathname = directory.list();
        int dim;
        if (pathname != null)
            dim = pathname.length;
        else {
            return true;
        }

        ArrayList<Pair> pairsNewNet = netToCheck.getNet();
        int ctrl = 0;
        //consider all files in directory
        for (int i = 0; i < dim; i++) {
            if (ctrl == pairsNewNet.size()) {
                return false;
            }
            ctrl = 0;
            //get pathname of the file
            String path = IO.JSON_FILE + "/" + pathname[i];
            //build a net by the file
            Net net = JsonManager.readJson(path);
            //get all pairs of the net
            ArrayList<Pair> pairsOldNet = net.getNet();

            //if the size is equal chek, else change file
            if (pairsOldNet.size() == pairsNewNet.size()) {
                int j = 0;
                //for every pair in the new net, take every pair of the pre existing net and check
                for (Pair newPair : pairsNewNet) {
                    if (ctrl < j)
                        break;
                    for (Pair oldPair : pairsOldNet) {
                        if (newPair.getPlaceName().equals(oldPair.getPlaceName())) {
                            if (newPair.getPlaceName().equals(oldPair.getPlaceName())) {
                                ctrl++;
                                break;
                            }
                        }
                    }
                    j++;
                }
            }
        }
        return true;
    }
}
