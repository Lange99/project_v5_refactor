package main.java.Net;




import java.util.ArrayList;
import java.util.HashSet;

public class Net extends BasicNet {

    private HashSet<Place> setOfPlace = new HashSet<Place>();
    private HashSet<Transition> setOfTrans = new HashSet<Transition>();
    private ArrayList<Pair> net = new ArrayList<Pair>();
    private String name;


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
     * constructor for the net
     *
     * @param name the name that the user what to give to the net
     */
    public Net(String name) {
        assert name != null;
        this.name = name;
    }

    /**
     * constructor for duplicate a net
     *
     * @param _net to duplicate
     */
    public Net(Net _net) {
        net.addAll(_net.getNet());
        this.setOfPlace.addAll(_net.setOfPlace);
        this.setOfTrans.addAll(_net.setOfTrans);
        this.name = _net.getName();
    }

    /**
     * this method allow to insert a new node, it is composed by a place and a transition
     */
    public boolean addPair(Transition t, Place p, int inOrOut) {
        assert t != null;
        assert p != null;
        assert inOrOut >= 0;
        assert inOrOut <= 1;

        //if the net is empty I don't recall the check method because this is useless
        if (net.size() == 0) {
            //with this if I check if the node is a in or an output
            if (inOrOut == 1) {
                //if this is an in I add the place to the pre
                t.addPre(p.getName());
            } else {
                //if this is an in I add the place to the p0st
                t.addPost(p.getName());
            }
            //we add the pair at the net because it is correct
            net.add(new Pair(p, t));

            setOfPlace.add(p);
            setOfTrans.add(t);

        } else {

            //I check if the pair is equal than an other one which already exists
            if (checkPair(new Pair(p, t))) {

                if (setOfTrans.add(t)) {
                    if (inOrOut == 1) {
                        //if this is an in I add the place to the pre
                        t.addPre(p.getName());
                    } else {
                        //if this is an in I add the place to the post
                        t.addPost(p.getName());
                    }
                    net.add(new Pair(p, t));
                } else {
                    for (Transition tr : setOfTrans) {
                        if (t.getName().equals(tr.getName())) {
                            if (inOrOut == 1) {
                                tr.addPre(p.getName());
                            } else {
                                tr.addPost(p.getName());

                            }

                            net.add(new Pair(p, tr));
                        }

                    }

                }

                //this for looks for if the place already exist
                setOfPlace.add(p);


            } else {
                //I say to the user that the pair already exists
                return false;
            }
        }
        return true;
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
     * This metod check if two Pair are equal
     *
     * @param pairToCheck
     * @return false if Pair are equal
     */
    public boolean checkPair(Pair pairToCheck) {
        assert pairToCheck!=null;
        for (Pair element : net) {
            if (element.compare(pairToCheck) == true) {
                return false;
            }
        }
        return true;
    }
/*
    /**
     * this method research a transition in the net's set of transitions
     *
     * @param nameTrans the name of the transition that we are looking for
     * @return the transition if we find it, or null if the transition doesn't exist
     *//*
    public Transition researchTrans(String nameTrans) {
        assert nameTrans != null;
        assert getSetOfTrans().size() != 0;
        for (Transition t : getSetOfTrans()) {
            if (t.getName().equals(nameTrans)) {
                return t;
            }
        }
        return null;
    }
*//*
    /**
     * this method research a place in the net's set of place
     *
     * @param namePlace the name of the place that we are looking for
     * @return the transition if we find it, or null if the place doesn't exist
     *//*
    public Place researchPlace(String namePlace) {
        assert namePlace != null;
        assert getSetOfPlace().size() != 0;
        for (Place p : getSetOfPlace()) {
            if (p.getName().equals(namePlace)) {
                return p;
            }
        }
        return null;
    }

    /**
     * this method research a pair in the net
     *
     * @param t the transition of the pair
     * @param p the place of the pair
     * @return the pair that we are looking for if we find it, else null
     *//*
    public Pair researchPair(Transition t, Place p) {
        assert t != null;
        assert p != null;
        assert getSetOfPlace().size() != 0;
        for (Pair pair : getNet()) {
            if (pair.getPlace().equals(p) && pair.getTrans().equals(t)) {
                return pair;
            }
        }
        return null;
    }*/

    public ArrayList<Pair> getPairs(){
        return net;
    }


    public Pair getPair(Place p, Transition t){

        for(Pair pa: net){
            if(p.getName().equals(pa.getPlaceName())&& t.getName().equals(pa.getTransName())){
                return pa;
            }
        }
        return  null;

    }
}
