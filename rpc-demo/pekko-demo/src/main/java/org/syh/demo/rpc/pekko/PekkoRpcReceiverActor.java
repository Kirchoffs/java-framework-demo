package org.syh.demo.rpc.pekko;

import org.apache.pekko.actor.AbstractActor;
import org.apache.pekko.actor.ActorRef;
import org.apache.pekko.japi.pf.ReceiveBuilder;

public class PekkoRpcReceiverActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
            .match(AlphaPekkoData.class, this::handleMessageForAlpha)
            .match(BetaPekkoData.class, this::handleMessageForBeta)
            .build();
    }

    private void handleMessageForAlpha(final AlphaPekkoData message) {
        ActorRef sender = getSender();
        ActorRef self = getSelf();
        System.out.println("Received alpha message: " + message.getInfo());
        sender.tell(new AlphaPekkoData("Hi"), self);
    }

    private void handleMessageForBeta(final BetaPekkoData message) {
        ActorRef sender = getSender();
        ActorRef self = getSelf();
        System.out.println("Received beta message: " + message.getInfo());
        sender.tell(new BetaPekkoData("Hi"), self);
    }
}
