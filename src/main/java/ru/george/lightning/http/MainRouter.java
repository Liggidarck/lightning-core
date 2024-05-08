package ru.george.lightning.http;

import akka.actor.typed.ActorRef;
import akka.actor.typed.ActorSystem;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.model.StatusCode;
import akka.http.javadsl.model.StatusCodes;
import akka.http.javadsl.server.Route;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jdk.nashorn.internal.runtime.regexp.joni.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.george.lightning.actors.ActorsRouter;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static akka.http.javadsl.server.Directives.*;
import static akka.http.javadsl.server.Directives.complete;

public class MainRouter {
    private final ActorSystem<?> system;
    private final ActorRef<ActorsRouter.Message> rootActor;

    private Config config;
    private final Logger logger = LoggerFactory.getLogger(MainRouter.class);

    public MainRouter(ActorSystem<?> system, ActorRef<ActorsRouter.Message> rootActor) {
        this.system = system;
        this.rootActor = rootActor;
    }

    public Route getterRoutes() {
        return concat(
                pathPrefix("test", () -> pathEnd(() -> post(() -> entity(Jackson.unmarshaller(JsonNode.class),
                        jBody -> onSuccess(test(), emptyObject -> complete(StatusCodes.OK, emptyObject, Jackson.marshaller()))
                ))))
        );

    }

    @JsonSerialize
    public class EmptyObject {
    }

    public CompletionStage<EmptyObject> test() {
        return CompletableFuture.supplyAsync(EmptyObject::new);
    }

}
