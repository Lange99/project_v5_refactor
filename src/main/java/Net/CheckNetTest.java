package main.java.Net;

import static org.junit.jupiter.api.Assertions.*;

class CheckNetTest {

    @org.junit.jupiter.api.Test
    void checkTransIncorrect() {
        Net n=new main.java.Net.Net("ReteProva");
        Transition t=new Transition("T1");
        Place p= new Place("BRESCIA");
        n.addPair(t,p,1);
        assertFalse(CheckNet.checkTrans(n));
    }

    @org.junit.jupiter.api.Test
    void checkTransCorrectTrans() {
        Net n=new main.java.Net.Net("ReteProva1");
        Transition t=new Transition("T1");
        Place p= new Place("BRESCIA");
        Place p1= new Place("BERGAMO");
        n.addPair(t,p1,0);
        n.addPair(t,p,1);
        assertTrue(CheckNet.checkTrans(n));
    }

    @org.junit.jupiter.api.Test
    void checkTransInorrectTransMore() {
        Net n=new main.java.Net.Net("ReteProva2");
        Transition t=new Transition("T1");
        Transition t1=new Transition("T2");
        Place p= new Place("BRESCIA");
        Place p1= new Place("BERGAMO");
        n.addPair(t,p1,0);
        n.addPair(t,p,1);
        n.addPair(t1,p1,0);
        assertFalse(CheckNet.checkTrans(n));
    }
}