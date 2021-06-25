package main.java.Project_v4;

import java.util.HashMap;

public class PriorityPetriNet extends PetriNet{

    private HashMap<Transition, Integer> PriorityMap;

    public PriorityPetriNet(PetriNet p){
        super(p);
    }
}
