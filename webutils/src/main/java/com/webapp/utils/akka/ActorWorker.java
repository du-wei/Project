package com.webapp.utils.akka;

import akka.actor.ActorSystem;
import akka.actor.UntypedActor;

public class ActorWorker extends UntypedActor {


	@Override
    public void onReceive(Object msg) throws Exception {
//		getSender().tell("my", getSelf());
		System.out.println("worker");
    }

}
