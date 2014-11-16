package com.webapp.utils.akka;

import static org.junit.Assert.*;

import org.junit.Test;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class AkkaMain {
	
	@Test
    public void testName() throws Exception {
	    ActorSystem system = ActorSystem.create("demo");
	    ActorRef actor1 = system.actorOf(Props.create(Actor1.class));
	    ActorRef actor2 = system.actorOf(Props.create(Actor2.class));
	    actor1.tell("hello akka", actor2);
	    
	    system.shutdown();
    }
}

class Actor1 extends UntypedActor{
	@Override
    public void onReceive(Object arg) throws Exception {
        if(arg instanceof String){
        	System.out.println("1 ------ " + arg);
        }
    }
}

class Actor2 extends UntypedActor{
	@Override
    public void onReceive(Object arg) throws Exception {
        if(arg instanceof String){
        	System.out.println("2 ------ " + arg);
        }
    }
}
