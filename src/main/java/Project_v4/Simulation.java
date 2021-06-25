package main.java.Project_v4;

import java.util.ArrayList;

public interface Simulation {

    public  ArrayList<Transition> initializationInTheNet(ArrayList<Pair> initialSituation);
    public int calculateN(ArrayList<Pair> initialMark, boolean[] visit, int n, int i);

    }
