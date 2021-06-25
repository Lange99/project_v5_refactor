package main.java.Project_v4;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class PriorityPetriNet extends PetriNet implements Simulation{

    private HashMap<Transition, Integer> priorityMap;

    public PriorityPetriNet(PetriNet p){
        super(p);
        priorityMap = new HashMap<>();
    }

    public void addPriority(String tName, int n){
        priorityMap.put(super.getTrans(tName), n);
    }


    @Override
    public ArrayList<Transition> initializationInTheNet(ArrayList<Pair> initialSituation) {
        return null;
    }
    @Override
    public int calculateN(ArrayList<Pair> initialMark, boolean[] visit, int n, int i) {
        return 0;
    }
}
