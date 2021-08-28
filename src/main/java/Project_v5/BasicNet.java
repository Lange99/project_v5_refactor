package main.java.Project_v5;

import java.util.ArrayList;
import java.util.HashSet;

public abstract class BasicNet {

    public abstract void setName(String _name);

    public abstract ArrayList<Pair> getNet() ;

    public abstract String getName() ;

    public abstract HashSet<Place> getSetOfPlace() ;

    public abstract HashSet<Transition> getSetOfTrans();

    /**
     * this method allow to add a pair in the net
     *
     * @param pair
     */
    public abstract void addPair(Pair pair) ;

    public abstract ArrayList<Pair> getPairs();


    public abstract Pair getPair(Place p, Transition t);
    /**
     * This method allows you to add a Place in setOfPlace
     *
     * @param placeToAdd is the place to add
     */
    public abstract void addSetOfPlace(Place placeToAdd) ;

    /**
     * This method allows you to add a Transition in setOfTrans
     *
     * @param transitionToAdd is the transition to add
     */
    public abstract void addSetOfTransition(Transition transitionToAdd) ;
    public abstract Transition getTrans(String name);
}
