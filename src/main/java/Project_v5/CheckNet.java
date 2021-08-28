package main.java.Project_v5;

import java.util.ArrayList;
import java.util.HashMap;

public class CheckNet {

    /**
     * this method check if there is only transition in input or only in output
     *
     * @return true if it is correct, false it isn't
     */
    public static boolean checkTrans(BasicNet net) {
        assert !net.getSetOfTrans().isEmpty();
        for (Transition t : net.getSetOfTrans()) {
            if (t.sizePre() == 0 || t.sizePost() == 0) {
                return false;
            }
        }
        return true;
    }
    /**
     * this algorithm checks if the graph is connect
     * The idea on the algorithm is taken from here: https://www.geeksforgeeks.org/check-if-a-directed-graph-is-connected-or-not/
     * <p>
     * At first all data structures are created to subsequently execute a recursive algorithm that will visit all nodes if the graph is connected.
     * <p>
     * The necessary data structures are:
     * 1) A HashMap that associates a Boolaean to each node
     * 2) A map that associates each node with its neighbors in any direction
     * <p>
     * After the creation of these structures, the recursive algorithm is started
     *
     * @return true if the net is connect
     */
    public static boolean checkConnect(BasicNet net) {
        String firstPlace = null;
        //HashMap di bool
        HashMap<String, Boolean> check = new HashMap<>();

        //this map to point out the graph's direction
        HashMap<String, ArrayList<String>> map = new HashMap<>();


        ArrayList<String> temp = new ArrayList<>();
        //this for fills both the boolean and the transitions maps,
        for (Place place : net.getSetOfPlace()) {//this for checks all the place
            firstPlace = place.getName();
            check.put(place.getName(), false);
            //this for checks all the transition to see if the place is in its pre or post
            if (!map.containsKey(place.getName())) {
                for (Transition trans : net.getSetOfTrans()) {
                    addTempArray(temp, trans.getIdPre(), trans.getIdPost(), place.getName());
                    /* If we find the place in the array that contains the predecessors of a transition we will check all the successor and we add them to the
                     * temporary array
                     * This parth of the methods allows to fill the map of the link
                     *
                     */
                }
                //we insert the place that we have found and have linked to the pre/post in the map
                map.put(place.getName(), new ArrayList<>(temp));
                temp.clear();
            } else {
                //If the key already exist  we add the new place to its array
                for (Transition trans : net.getSetOfTrans()) {
                    addTempArray(temp, trans.getIdPre(), trans.getIdPost(), place.getName());
                }
                map.get(place.getName()).addAll(temp);
                temp.clear();
            }
        }
        //this for checks the place in the opposite direction because we want to add the place that we haven't seen yet and to update the information
        for (Place place : net.getSetOfPlace()) {
            //if the key isn't in the map we go on without problems
            if (!map.containsKey(place.getName())) {
                for (Transition trans : net.getSetOfTrans()) {
                    addTempArray(temp, trans.getIdPost(), trans.getIdPre(), place.getName());
                }
                map.put(place.getName(), new ArrayList<>(temp));
                temp.clear();
            } else {
                //If the key already exist  we add the new place to its array
                for (Transition trans : net.getSetOfTrans()) {
                    addTempArray(temp, trans.getIdPost(), trans.getIdPre(), place.getName());
                }
                map.get(place.getName()).addAll(temp);
                temp.clear();
            }

        }

        //we start the recursion
        recursion(map, check, firstPlace);
        //we check the result of the recursion, if there is a false in the map this means that the place hasn't been check, so that isn't linked
        for (Place id : net.getSetOfPlace()) {
            if (check.get(id.getName()) == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method that allows me to fill a temporary array by cycling the firstArray and comparing it to PlaceName
     * In case an element in firstArray is equal to PlaceName then I will have to clone secondArray in Temp
     *
     * @param temp               is the resulting array
     * @param firstArrayOfPlace  is the arraylist that I compare with PlaceName
     * @param secondArrayOfPlace
     * @param placeName
     */
    public static void addTempArray(ArrayList<String> temp, ArrayList<String> firstArrayOfPlace, ArrayList<String> secondArrayOfPlace, String placeName) {
        assert temp.size() != 0;
        assert firstArrayOfPlace.size() != 0;
        assert secondArrayOfPlace.size() != 0;
        assert !placeName.equals(null);

        for (String name : firstArrayOfPlace) { //this for checks if that place is in the post of the transition
            if (name.equals(placeName)) {
                temp.addAll(secondArrayOfPlace);
                break;
            }
        }
    }


    /**
     * Recursive method that allows, starting from a random node, to visit all the nodes of the graph if it is connected.
     * Starting from the first node, it is marked as already visited on the boolmap, then it cycles on the neighbors and invoking this method again if they have not been visited.
     *
     * @param map     it is the map on which I perform the recursion to move from one place to its neighbors, it does not have to be empty
     * @param boolmap is the map in which I sign if the node has been visited or not, there must be all the places present in the network
     * @param key     is the key to the place to recursion, it does not have to be empty
     */
    public static void recursion(HashMap<String, ArrayList<String>> map, HashMap<String, Boolean> boolmap, String key) {
        assert !map.isEmpty();
        assert !boolmap.isEmpty();
        assert !key.equals(null);
        boolmap.replace(key, true);
        for (String nextKey : map.get(key)) {
            if (!boolmap.get(nextKey)) {
                recursion(map, boolmap, nextKey);
            }
        }

    }
    /**
     * this method checks if there are some pendant place
     *
     * @return true if there aren't pendant places
     */
    //controllo dei nodi pendenti, da rivedere e commentare
    public static boolean checkPendantNode(BasicNet net) {
        assert net != null;
        for (int i = 0; i < net.getNet().size(); i++) {
            boolean check = false;
            String toCheck = net.getNet().get(i).getTransName();
            for (int j = 0; j < net.getNet().size(); j++) {
                if (i != j && toCheck.compareTo(net.getNet().get(j).getTransName()) == 0) {
                    check = true;
                }
            }
            if (check == false) {
                return false;
            }
        }
        return true;
    }
}
