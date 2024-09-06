package org.syh.demo.rpc.pekko;

import org.apache.pekko.actor.ActorSystem;
import org.apache.pekko.actor.ActorRef;
import org.apache.pekko.actor.Props;

public class Demo {
    // receiver.tell(message, sender)
    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("pekko-demo");
        ActorRef pekkoRpcReceiverRef = actorSystem.actorOf(Props.create(PekkoRpcReceiverActor.class), "pekko-rpc-receiver");
        ActorRef pekkoRpcSenderRef = actorSystem.actorOf(Props.create(PekkoRpcSenderActor.class), "pekko-rpc-sender");
        pekkoRpcReceiverRef.tell(new AlphaPekkoData("Hello"), pekkoRpcSenderRef);
        pekkoRpcReceiverRef.tell(new BetaPekkoData("Hello"), pekkoRpcSenderRef);
    }
}
