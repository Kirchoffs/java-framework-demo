package org.syh.demo.rpc.pekko;

import org.apache.pekko.actor.AbstractActor;
import org.apache.pekko.actor.ActorRef;
import org.apache.pekko.japi.pf.ReceiveBuilder;

public class PekkoRpcSenderActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
            .match(AlphaPekkoData.class, this::handleMessageForAlpha)
            .match(BetaPekkoData.class, this::handleMessageForBeta)
            .build();
    }


    private void handleMessageForAlpha(final AlphaPekkoData message) {
        ActorRef sender = getSender();
        System.out.println("Sender - received alpha message: " + message.getInfo() + " from " + sender);
    }

    private void handleMessageForBeta(final BetaPekkoData message) {
        ActorRef sender = getSender();
        System.out.println("Sender - received beta message: " + message.getInfo() + " from " + sender);
    }
}
