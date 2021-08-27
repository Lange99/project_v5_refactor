package main.java.Project_v5;

import java.io.FileNotFoundException;

public interface StrategyNetManager {
    boolean checkLoadGenericNet(PetriNet petriNet) throws FileNotFoundException;
    boolean checkLoadGenericNet(PriorityPetriNet priorityPetriNet) throws FileNotFoundException;
    boolean checkNetName(Net net);
    boolean checkNetName(PetriNet net);
    boolean checkGenericNet(PetriNet net);
    boolean checkGenericNet(PriorityPetriNet net);
}
