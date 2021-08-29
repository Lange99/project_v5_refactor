package main.java.Net;

import java.util.ArrayList;
import java.util.HashSet;

public class Research {

    /**
     * this method research a pair in the net
     *
     * @param t the transition of the pair
     * @param p the place of the pair
     * @return the pair that we are looking for if we find it, else null
     */
    public static Pair researchPair(Transition t, Place p, ArrayList<Pair> net) {
        assert t != null;
        assert p != null;
        for (Pair pair : net) {
            if (pair.getPlace().equals(p) && pair.getTrans().equals(t)) {
                return pair;
            }
        }
        return null;
    }

    /**
     * this method research a place in the net's set of place
     *
     * @param namePlace the name of the place that we are looking for
     * @return the transition if we find it, or null if the place doesn't exist
     */
    public static Place researchPlace(String namePlace, HashSet<Place> getSetOfPlace) {
        assert namePlace != null;
        assert getSetOfPlace.size() != 0;
        for (Place p : getSetOfPlace) {
            if (p.getName().equals(namePlace)) {
                return p;
            }
        }
        return null;
    }

    /**
     * this method research a transition in the net's set of transitions
     *
     * @param nameTrans the name of the transition that we are looking for
     * @return the transition if we find it, or null if the transition doesn't exist
     */
    public static Transition researchTrans(String nameTrans, HashSet<Transition> getSetOfTrans) {
        assert nameTrans != null;
        assert getSetOfTrans.size() != 0;
        for (Transition t : getSetOfTrans) {
            if (t.getName().equals(nameTrans)) {
                return t;
            }
        }
        return null;
    }
}
