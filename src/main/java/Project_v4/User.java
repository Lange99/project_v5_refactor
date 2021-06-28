package main.java.Project_v4;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import main.java.Utility.IO;
import main.java.Utility.JsonManager;
import main.java.Utility.JsonReader;

public class User {



    /**
     * In this method there are all the action that the user can do. This method allows to do the correct action
     * @param netM we pass the NetManager because that can load the net which will be use
     * @throws FileNotFoundException
     */
     public void operation(NetManager netM) throws FileNotFoundException {
        ArrayList<PetriNet> loadNetPetri=new ArrayList<>();
        ArrayList<PriorityPetriNet> loadPriorityNetPetri=new ArrayList<>();
        int select;
        PetriNet selected;

         boolean check=false;
         int choise = 0;
       //this switch manage the operations
         do {
             choise=IO.readInteger(IO.WHAT_DO_YOU_WANT_DO_0_EXIT_1_START_SIMULATION,0,4 );


             switch(choise) {
                 case 0:
                     check = true;
                     break;
                 case 1:

                     //the user decides which nets he wants to load
                     IO.print(IO.YOU_HAVE_TO_LOAD_A_NET_WHICH_ONE_DO_YOU_WANT);

                     do {

                         loadNetPetri.add(JsonManager.loadPetriNet());

                     } while (IO.yesOrNo(IO.DO_YOU_WANT_TO_LOAD_OTHER_NETS));

                     IO.printPetriNets(loadNetPetri);
                     check = IO.yesOrNo(IO.DO_YOU_WANT_CLOSE_THE_PROGRAM);

                     //simulazione(selected, selected.getInitialMark());
                     // } while (IO.yesOrNo(IO.DO_YOU_WANT_TO_MAKE_AN_OTHER_SIMULATION));
                     break;
                 //after the load the user can simulate the net
                 case 2:
                     if (loadNetPetri.size() == 0) {
                         IO.print(IO.THERE_AREN_T_ANY_NETS_LOADED_YOU_HAVE_TO_LOAD_ONE_NET_BEFORE_THE_SIMULATION);
                     } else {
                         //the user have to choosen one net
                         IO.printPetriNets(loadNetPetri);
                         select = IO.readInteger(IO.INSERT_THE_NUMBER_OF_THE_NET_THAT_YOU_WANT_TO_USE, 1, loadNetPetri.size());
                         selected = loadNetPetri.get(select - 1);
                         //we shows the net
                         IO.showPetriNet(selected);
                         selected.saveInitialMark();
                         //we start the simulation
                         //selected.simulation();
                        // simulation(selected, loadNetPetri.get(select - 1).getInitialMark());
                         boolean canContinue;
                         do{

                            canContinue= startSimulation(selected, selected.getInitialMark());

                         }while (canContinue==true && IO.yesOrNo(IO.DO_YOU_WANT_TO_CONTINUE_THE_SIMULATION));
                     }
                     check = IO.yesOrNo(IO.DO_YOU_WANT_CLOSE_THE_PROGRAM);

                     break;


                 case 3:
                     //caricare rete con priorità
                     PriorityPetriNet netToAdd = JsonManager.loadPriorityPetriNet();
                     loadPriorityNetPetri.add(netToAdd);
                     //loadPriorityNetPetri.addAll(netM.getPrioritynetList());
                     break;
                 case 4:
                     //simulation of Petri's Net with Priority
                     if (loadPriorityNetPetri.size() == 0) {
                         IO.print(IO.THERE_AREN_T_ANY_NETS_LOADED_YOU_HAVE_TO_LOAD_ONE_NET_BEFORE_THE_SIMULATION);
                     } else {
                         //the user have to choosen one net
                         IO.printPriorityPetriNets(loadPriorityNetPetri);
                         select = IO.readInteger(IO.INSERT_THE_NUMBER_OF_THE_NET_THAT_YOU_WANT_TO_USE, 0, loadPriorityNetPetri.size());

                         //qua ci va load PriorityPetriNet

                         PriorityPetriNet sel = loadPriorityNetPetri.get(select);


                         //we shows the net
                         //dobbiamo mostrare le reti anche con le priorità
                         IO.showPriorityPetriNet(sel);

                         //we start the simulation
                         boolean canContinue;
                         do{
                             sel.saveInitialMarkCurretly();
                              canContinue=   startSimulationPriority(sel, sel.getInitialMarkCurrenly());

                          }while (canContinue==true && IO.yesOrNo(IO.DO_YOU_WANT_TO_CONTINUE_THE_SIMULATION));

                     }
                     check = IO.yesOrNo(IO.DO_YOU_WANT_CLOSE_THE_PROGRAM);

                     break;

             }
         } while (!check );
    }

    private boolean startSimulationPriority(PriorityPetriNet sel, ArrayList<Pair> initialMark) {
        HashMap<Transition, ArrayList<Pair>> finalTrans = sel.simulation( initialMark);
//we have made all the checks, so in transitionThatCanWork there are the transitions that  we can use for the simulation
        //ho fatto i controlli possibili in pairInTheTrans ho le transazioni che possono scattare
        if (finalTrans.size() == 0) {
            //In this case there aren't any transitions avaible
            IO.print(IO.THERE_AREN_T_ANY_TRANSITION_AVAILABLE);
    return  false;
        } else {


            //we ask to the user which transition he wants to use
            Transition transitionChosen = whichPostisChosen(finalTrans.keySet());
            sel.setPreandPost(transitionChosen);
            modifyThePrePair(finalTrans.get(transitionChosen));
            //we have to remove the token in the pre pairs
            IO.showPriorityPetriNet(sel);
        }
        return  true;
    }
    public boolean startSimulation(PetriNet pN, ArrayList<Pair> initialMark) {

        HashMap<Transition, ArrayList<Pair>> finalTrans = pN.simulation(initialMark);
//we have made all the checks, so in transitionThatCanWork there are the transitions that  we can use for the simulation
        //ho fatto i controlli possibili in pairInTheTrans ho le transazioni che possono scattare
        if (finalTrans.size() == 0) {
            //In this case there aren't any transitions avaible
            IO.print(IO.THERE_AREN_T_ANY_TRANSITION_AVAILABLE);
    return false;
        } else {


            //we ask to the user which transition he wants to use
            Transition transitionChosen = whichPostisChosen(finalTrans.keySet());
            pN.setPreandPost(transitionChosen);
            modifyThePrePair(finalTrans.get(transitionChosen));
            //we have to remove the token in the pre pairs
            ArrayList<Pair> newInit=new ArrayList<>();
            //we have to calculate the new situation
            IO.showPetriNet(pN);
        }
        return true;
    }

    /**
     * this method ask to the user which transition he want to use
     * @param temp all the transitions avaible
     * @return the transition that the user has chosen
     */
    private Transition whichPostisChosen(Set<Transition> temp) {
        assert temp!=null;
        ArrayList<Transition> elementAvaible=new ArrayList(temp);
        IO.print(IO.THE_FOLLOWING_TRANSITION_ARE_AVAILABLE);
        //we print the transition
        IO.printTransition(temp);
       // IO.print(IO.STOP);
//the user inserts the number of the transition that he wants to use
      int choise=IO.readInteger(IO.INSERT_THE_NUMBER_OF_THE_TRANSITION_YOU_WANT_TO_USE, 1, temp.size()) - 1;
        return elementAvaible.get(choise);
    }

    /**
     * this method removes the token in the pre paris
     * @param PreElement the elements that should be modified
     */
    private void modifyThePrePair(ArrayList<Pair> PreElement) {
        assert PreElement!=null;
        //we have to make the difference between the token and the weight
        for(Pair p: PreElement){
            p.getPlace().differenceToken(p.getWeight());
        }
    }
}
