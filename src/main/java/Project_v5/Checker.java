package main.java.Project_v5;

import main.java.Net.Net;
import main.java.Net.PetriNet;
import main.java.Net.PriorityPetriNet;

import java.io.FileNotFoundException;

public interface Checker {
    boolean checkLoadGenericNet(PetriNet petriNet) throws FileNotFoundException;
    boolean checkLoadGenericNet(PriorityPetriNet priorityPetriNet) throws FileNotFoundException;
    boolean checkNetName(Net net);
    boolean checkNetName(PetriNet net);
    boolean checkGenericNet(PetriNet net);
    boolean checkGenericNet(PriorityPetriNet net);
}
