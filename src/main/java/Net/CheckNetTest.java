package main.java.Net;

import org.junit.jupiter.api.Test;

import static java.util.function.Predicate.isEqual;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import java.lang.*;
class CheckNetTest {


    private Object AssertionError;

    @org.junit.jupiter.api.Test
    void checkTransNoPre() {
        Net n=new Net("ReteProva");
        Transition t=new Transition("T1");
        Place p= new Place("BRESCIA");
        n.addPair(t,p,1);
        assertFalse(CheckNet.checkTrans(n));
    }
    @org.junit.jupiter.api.Test
    void checkTransNoPost() {
        Net n=new Net("ReteProva");
        Transition t=new Transition("T1");
        Place p= new Place("BRESCIA");
        n.addPair(t,p,0);
        assertFalse(CheckNet.checkTrans(n));
    }

    @org.junit.jupiter.api.Test
    void checkTransCorrectTransOneTrans() {
        Net n=new Net("ReteProva1");
        Transition t=new Transition("T1");
        Place p= new Place("BRESCIA");
        Place p1= new Place("BERGAMO");
        n.addPair(t,p1,0);
        n.addPair(t,p,1);
        assertTrue(CheckNet.checkTrans(n));
    }
    @org.junit.jupiter.api.Test
    void checkTransCorrectTransMoreTrans() {
        Net n=new Net("ReteProva2");
        Transition t=new Transition("T0");
        Transition t1=new Transition("T1");
        Transition t2=new Transition("T2");
        Transition t3=new Transition("T3");
        Place BS= new Place("BRESCIA");
        Place BG= new Place("BERGAMO");
        Place MI= new Place("MILANO");
        Place MA= new Place("MANTOVA");
        n.addPair(t,BG,0);
        n.addPair(t,BS,1);
        n.addPair(t1,BS,0);
        n.addPair(t1,MI,1);
        n.addPair(t2,MA,0);
        n.addPair(t2,BS,1);
        n.addPair(t3,MI,0);
        n.addPair(t3,BG,1);
        assertTrue(CheckNet.checkTrans(n));
    }
    @org.junit.jupiter.api.Test
    void checkTransNoPostWithMoreThanOneElement() {
        Net n=new Net("ReteProva3");
        Transition t=new Transition("T1");
        Transition t1=new Transition("T2");
        Place p= new Place("BRESCIA");
        Place p1= new Place("BERGAMO");
        n.addPair(t,p1,0);
        n.addPair(t,p,1);
        n.addPair(t1,p1,0);
        assertFalse(CheckNet.checkTrans(n));
    }
    @org.junit.jupiter.api.Test
    void checkTransNoPreWithMoreThanOneElement() {
        Net n=new Net("ReteProva4");
        Transition t=new Transition("T1");
        Transition t1=new Transition("T2");
        Place p= new Place("BRESCIA");
        Place p1= new Place("BERGAMO");
        n.addPair(t,p1,0);
        n.addPair(t,p,1);
        n.addPair(t1,p1,1);
        assertFalse(CheckNet.checkTrans(n));
    }
    @Test
    void checkTransNoElement() {
        Net n=new Net("ReteProva5");
      //  assertThat(, (AssertionError));
       try {
           CheckNet.checkTrans(n);
       }catch (AssertionError e){
           assertTrue(true);
           return;
       }
        assertTrue(false);
    }



}