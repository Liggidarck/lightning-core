package ru.george.lightning.actors;

import akka.actor.ActorSystem;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

import java.util.List;

public class ActorsRouter extends AbstractBehavior<ActorsRouter.Message> {

    public static final String NAME = "messages_router";

    public ActorsRouter(ActorContext<Message> context) {
        super(context);
    }
    public static Behavior<Message> create(ActorSystem actorSystem) {
        return Behaviors.setup(ActorsRouter::new);
    }

    @Override
    public Receive<Message> createReceive() {
        return null;
    }

    public interface Message {
    }
}
