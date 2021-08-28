package main.java.Project_v5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class PriorityPetriNet extends BasicNet implements Simulation {

    private HashMap<Transition, Integer> priorityMap;
    private final HashMap<Pair, Integer> initialMarking = new HashMap<>();
    private final ArrayList<Pair> initialMark = new ArrayList<>();
    private ArrayList<Pair> initialMarkCurretly = new ArrayList<>();
    private HashSet<Place> setOfPlace = new HashSet<Place>();
    private HashSet<Transition> setOfTrans = new HashSet<Transition>();
    private ArrayList<Pair> net = new ArrayList<Pair>();
    private String name;

    public void initMap() {
        for (Transition n : getSetOfTrans()) {
            priorityMap.put(n, 1);
        }
    }

    public PriorityPetriNet(PetriNet p) {
        net.addAll(p.getNet());
        this.setOfPlace.addAll(p.getSetOfPlace());
        this.setOfTrans.addAll(p.getSetOfTrans());
        this.name = p.getName();
        priorityMap = new HashMap<>();
        initMap();
    }

    /**
     * method to add priority
     *
     * @param nameTransitionToAddPriority
     * @param priorityValue
     * @return
     */
    public boolean addPriority(String nameTransitionToAddPriority, int priorityValue) {
        Transition transitionToAddPriority = Research.researchTrans(nameTransitionToAddPriority, getSetOfTrans());
        if (transitionToAddPriority == null) {
            return false;
        } else {
            addPriorityToNetList(transitionToAddPriority, priorityValue);
            return true;
        }
    }

    public PriorityPetriNet(PriorityPetriNet genericNet) {

        saveInitialMark();
    }

    public void addPriorityToNetList(Transition transition, Integer priorityValue) {
        priorityMap.put(transition, priorityValue);
    }

    public int getPriorityByTransition(Transition transition) {
        return priorityMap.get(transition);
    }

    public void setPriorityMap(HashMap<Transition, Integer> priorityMap) {
        this.priorityMap = priorityMap;
        priorityMap = new HashMap<>();
    }

    public int getPriorityByTransitionName(String transitionName) {
        return getPriorityByTransition(getTrans(transitionName));
    }

    /**
     * this method check all the differnt case in  a Petri's Net in order to decide which transitions can work
     *
     * @param initialMark, this parameter identify the initial situazion in that moment and it doesn't always indicate che initial mark of the net
     * @param temp         the arraylist where we put the transition that can be moved
     * @param finalTrans   we put the element that will be shows to the user, who has to choose which one has to work
     */
    public void initialSituationInTheNet(ArrayList<Pair> initialMark, ArrayList<Transition> temp, HashMap<Transition, ArrayList<Pair>> finalTrans) {
        assert initialMark != null;
        assert temp != null;
        assert finalTrans != null;
        //this array avoid to check pair that we have already checked
        boolean[] visit = new boolean[initialMark.size()];
        int maxPriority = 1;
        ArrayList<Pair> pairInTheTrans;
        for (int i = 0; i < initialMark.size(); i++) {
            //first of all we check if the pair has already checked
            pairInTheTrans = new ArrayList<>();
            if (visit[i] == true) {
                continue;
            }

            //we put visit true, so in the future this pair will not be check again
            visit[i] = true;

            //if the place isn't in the precs of the transition, that isn't usefull
            // se il posto non è nei predecessori della transizione pur avendo dei token viene saltata perchè non contribuisce allo scatto
            if (initialMark.get(i).getTrans().isIn(initialMark.get(i).getPlace().getName()) == false) {
                continue;
            }

            //if the transition has a priority lower than the highest that will be not checked because that will not added
            var var = priorityMap.get(initialMark.get(i).getTrans());
            if (priorityMap.get(initialMark.get(i).getTrans()) < maxPriority) {
                continue;
            } else {
                //we chek if the priority is higher that the max because in this case we delete all the element in the finals trans
                if (priorityMap.get(initialMark.get(i).getTrans()) > maxPriority) {
                    temp.clear();
                    finalTrans.clear();
                }

            }
            maxPriority = priorityMap.get(initialMark.get(i).getTrans());
            //if there is only and there are enough token the transition is add to temp and to th other elemet
            //se si ha un unico pre e si hanno abbastanza token la transizione viene subito aggiunta
            if (initialMark.get(i).getTrans().sizePre() == 1 && initialMark.get(i).getWeight() <= initialMark.get(i).getPlace().getNumberOfToken()) {
                temp.add(initialMark.get(i).getTrans());
                pairInTheTrans.add(initialMark.get(i));
                finalTrans.put(initialMark.get(i).getTrans(), pairInTheTrans);
                //checkTheElementMultipleCase(initialMark, visit, pairInTheTrans, i, 1, true);
                continue;
            }

            //we check if there are enough token in the transition, in the opposite case there the transition can't work
            //significa che la transazione non potrà mai scattare
            if (initialMark.get(i).getNumberOfToken() >= initialMark.get(i).getWeight()) {

                pairInTheTrans = new ArrayList<>();

                pairInTheTrans.add(initialMark.get(i));

                //we check if the other element in the initial mark that refers to the same transition are correct
                if (checkTheElementMultipleCase(initialMark, visit, pairInTheTrans, i)) continue;


                //we add to temp the current transition
                temp.add(initialMark.get(i).getTrans());
                //we insert in final trans the final elements, so the element that can be used for the simulation
                finalTrans.put(initialMark.get(i).getTrans(), pairInTheTrans);

            }


        }
    }

    /**
     * this method creates all the structures necessary for the simulation
     *
     * @param initialMark the initial situation when the method is called
     * @return a structure that contain the transitions which are avaible and their pair
     */
    public HashMap<Transition, ArrayList<Pair>> simulation(ArrayList<Pair> initialMark) {

        //in the we put the transition that can be chosen
        ArrayList<Transition> transitionThatCanWork = new ArrayList<Transition>();
        //visit avoid to check elements that we have already checked

        ArrayList<Pair> pairInTheTrans = new ArrayList<>();
        HashMap<Transition, ArrayList<Pair>> finalTrans = new HashMap<>();
        initialSituationInTheNet(initialMark, transitionThatCanWork, finalTrans);
        return finalTrans;
    }

    /**
     * this method dies all the actions necessary for the case where there are more than pair connected to the transition, so we have to check if all the pair are correct
     *
     * @param initialMark    contains all the pair that has token
     * @param visit          this array allows to check only the element that we haven't checked
     * @param pairInTheTrans this arrayList contains or it will contain the pair connected to the transition that we are checking
     * @param i              give us the indication about which element we are checking
     * @return
     */
    public boolean checkTheElementMultipleCase(ArrayList<Pair> initialMark, boolean[] visit, ArrayList<Pair> pairInTheTrans, int i) {
        assert initialMark != null;
        assert visit != null;
        assert pairInTheTrans != null;
        assert i > -1;
        //this variable contains the number of the elements in the initial markink which refer the current transition
        int elementOfTrans = 1;
        boolean checkIfTheTransitionCanWork = true;
        //we loop on all the pair in the initialMark in order to find only the one that hase the same transition of the pair that we are checking
        //calcolo quanti elementi della trans t sono presenti in intial
        for (int j = i + 1; j < initialMark.size(); j++) {
            //if checkIfTheTransitionCanWork is false that means that we don't have to check other pair
            //se checkIfTheTransitionCanWork è falso significa che non devo fare controlli ma indico già che è visitato
            if (checkIfTheTransitionCanWork == false) {
                //we decided to continue the loop because we can set visit true for all the other elements which have the same transition in order to avoid multiple check in the next actions
                //the element will not be add to the final array so che size will be incorrect
                visit[j] = true;
                continue;
            }

            // we want the elements that has the same transition of the pair that we are checking
            if (initialMark.get(i).getTrans().getName().equals(initialMark.get(j).getTrans().getName())) {

                //se non rispetta questa condizione significa che non si hanno abbastanza elementi totali, non continuo a fare controlli ma pongo gli altri elementi in modo
                //non ci siano ulteriori controlli
                //if the pair has less token that the transition required the transition can't work, so all the other actions will be usefull
                if (initialMark.get(j).getNumberOfToken() < initialMark.get(j).getWeight()) {
                    checkIfTheTransitionCanWork = false;
                    continue;
                }
                //in the case that the condition is  not verify we can continue to do action
                elementOfTrans++;
                // sumOfEveryTrans=sumOfEveryTrans+initialMark.get(j).getNumberOfToken();

                visit[j] = true;
                //so we had to add the pair to the arraylist
                pairInTheTrans.add(initialMark.get(j));
            }

        }

        //if the element of trans has less element than the element in the pre of the transition, we can't add the transition to the final check
        //also if the check in the transition can work is false the addition can be made. The second check is redundant
        //ho meno elementi di quelli che dovrei avere passo oltre o se un elemento non era corretto
        return elementOfTrans < initialMark.get(i).getTrans().sizePre() || checkIfTheTransitionCanWork == false;
    }

    /**
     * Method that allows you to determine if the Petri net with priority comes from netToCheck
     *
     * @param netToCheck
     * @return true if the Petri net with priority comes from netToCheck
     */
    public boolean checkFatherPetriNet(PetriNet netToCheck) {
        int nPlace = getSetOfPlace().size();
        int nPlace2 = netToCheck.getSetOfPlace().size();
        int nTrans = getSetOfTrans().size();
        int nTrans2 = netToCheck.getSetOfTrans().size();
        if (nPlace != nPlace2 || nTrans != nTrans2) {
            return false;
        }
        if (!getSetOfPlace().containsAll(netToCheck.getSetOfPlace())) {
            return false;
        }
        if (getSetOfTrans().containsAll(netToCheck.getSetOfTrans())) {
            for (Transition t : getSetOfTrans()) {
                for (Transition t2 : netToCheck.getSetOfTrans()) {
                    if (t.getName().equals(t2.getName())) {
                        if (!t.checkArray(t2)) {
                            return false;
                        }
                    }
                }
            }
        } else {
            return false;
        }
        return checkToken(netToCheck);
    }

    /**
     * Methode to check tokens in a petri net
     *
     * @param toCheck is the net to check
     * @return true if the two networks contain the same number of tokens in all places
     */
    private boolean checkToken(PetriNet toCheck) {
        assert getSetOfPlace().containsAll(toCheck.getSetOfPlace());

        for (Place p : getSetOfPlace()) {
            for (Place placeToCheck : toCheck.getSetOfPlace()) {
                if (p.equals(placeToCheck)) {
                    if (p.getNumberOfToken() != placeToCheck.getNumberOfToken()) {
                        return false;

                    }
                }
            }
        }
        return true;
    }

    public void setName(String _name) {
        assert !_name.equals(null);
        name = _name;
    }

    public ArrayList<Pair> getNet() {
        assert net != null;
        return net;
    }

    public String getName() {
        assert name != null;
        return name;
    }

    public HashSet<Place> getSetOfPlace() {
        assert setOfPlace.size() != 0;
        return setOfPlace;
    }

    public HashSet<Transition> getSetOfTrans() {
        assert setOfTrans.size() != 0;
        return setOfTrans;
    }

    /**
     * this method allow to add a pair in the net
     *
     * @param pair
     */
    public void addPair(Pair pair) {
        assert pair != null;
        net.add(pair);
    }

    public ArrayList<Pair> getPairs() {
        return net;
    }


    public Pair getPair(Place p, Transition t) {

        for (Pair pa : net) {
            if (p.getName().equals(pa.getPlaceName()) && t.getName().equals(pa.getTransName())) {
                return pa;
            }
        }
        return null;

    }

    /**
     * This method allow to search Place in SetOfPlace
     *
     * @param name this method returns a place from the set knowing the name
     * @return the place if it finds it, null if the place doesn't exist
     */
    public Place getPlace(String name) {
        assert name != null && setOfPlace != null;
        for (Place p : setOfPlace) {
            if (name.compareTo(p.getName()) == 0) {
                return p;
            }
        }
        return null;
    }

    /**
     * This method allow to search Transition in SetOfTrans
     *
     * @param name this method returns a trans from the set knowing the name
     * @return the transition if it finds it, null if the transition doesn't exist
     */
    public Transition getTrans(String name) {
        for (Transition t : setOfTrans) {
            if (name.compareTo(t.getName()) == 0) {
                return t;
            }
        }
        return null;
    }

    /**
     * This method allows you to add a Place in setOfPlace
     *
     * @param placeToAdd is the place to add
     */
    public void addSetOfPlace(Place placeToAdd) {
        assert placeToAdd != null;
        setOfPlace.add(placeToAdd);
    }

    /**
     * This method allows you to add a Transition in setOfTrans
     *
     * @param transitionToAdd is the transition to add
     */
    public void addSetOfTransition(Transition transitionToAdd) {
        assert transitionToAdd != null;
        setOfTrans.add(transitionToAdd);
    }

    /**
     * this method allows to add the weight to the pair
     *
     * @param nameTrans name of the trans
     * @param placeMod  name of the place
     * @param weight    the quantity of weight
     */
    public void addWeight(String nameTrans, String placeMod, int weight) {
        //we research the transition and the place that the user wants to change
        Transition transition = Research.researchTrans(nameTrans, getSetOfTrans());
        Place place = Research.researchPlace(placeMod, getSetOfPlace());

        //when we have the transition and the place we research the matching pair
        Pair pair = Research.researchPair(transition, place, net);
        //we set its weight
        pair.setWeight(weight);
    }

    /**
     * Method that allows you to save the initial marking
     */
    public void saveInitialMark() {

        for (Pair p : getNet()) {
            if (p.getPlace().getNumberOfToken() != 0)
                initialMarking.put(p, p.getPlace().getNumberOfToken());
        }
        for (Pair p : getPairs()) {
            if (p.getPlace().getNumberOfToken() != 0) {
                initialMark.add(p);
            }
        }
    }

    public void saveInitialMarkCurretly() {
        initialMarkCurretly.clear();

        for (Pair p : getPairs()) {
            if (p.getPlace().getNumberOfToken() != 0) {
                initialMarkCurretly.add(p);
            }
        }
    }
    public ArrayList<Pair> getInitialMarkCurrenly() {
        return initialMarkCurretly;
    }
    /**
     * this method modify the token in the post transition
     * @param transitionThatWeHaveToModify the transition that the user has choseen to be modify
     */
    public void setPreandPost(Transition transitionThatWeHaveToModify) {
        //we have to update all the post element of the transitions, we add a token in the post
        if (transitionThatWeHaveToModify.sizePost() == 1) {
            getPair(getPlace(transitionThatWeHaveToModify.getIdPost().get(0)), transitionThatWeHaveToModify).getPlace().updateToken();
        } else {
            for (int i = 0; i < transitionThatWeHaveToModify.getIdPost().size(); i++) {
                getPair(getPlace(transitionThatWeHaveToModify.getIdPost().get(i)), transitionThatWeHaveToModify).getPlace().updateToken();
            }
        }
    }
}
