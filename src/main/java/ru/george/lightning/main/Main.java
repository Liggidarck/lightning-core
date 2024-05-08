package ru.george.lightning.main;
import akka.NotUsed;
import akka.actor.typed.ActorRef;
import akka.actor.typed.ActorSystem;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.Adapter;
import akka.actor.typed.javadsl.Behaviors;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.server.Route;
import akka.stream.Materializer;
import akka.stream.javadsl.Flow;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import ru.george.lightning.actors.ActorsRouter;
import ru.george.lightning.http.MainRouter;

import java.net.InetSocketAddress;
import java.util.concurrent.CompletionStage;

public class Main {

    public static void main(String[] args) {
        final Behavior<NotUsed> rootBehaviour = Behaviors.setup(context -> {
            ActorRef<ActorsRouter.Message> messageActorRef =
                    context.spawn(ActorsRouter.create(context.getSystem().classicSystem()), ActorsRouter.NAME);

            MainRouter exRoutes = new MainRouter(context.getSystem(), messageActorRef);
            CompletionStage<ServerBinding> future = startHttpServer(context.getSystem(), exRoutes.getterRoutes());

            return Behaviors.empty();
        });

        ActorSystem.create(rootBehaviour, "httpServer");
    }

    static CompletionStage<ServerBinding> startHttpServer(ActorSystem<?> system, Route route) {
        Config config = ConfigFactory.load();
        akka.actor.ActorSystem classicSystem = Adapter.toClassic(system);
        final Http http = Http.get(classicSystem);
        final Materializer materializer = Materializer.matFromSystem(system);

        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = route.flow(classicSystem, materializer);
        String host = config.getString("http.host");
        int port = config.getInt("http.port");
        final CompletionStage<ServerBinding> futureBinding = http.bindAndHandle(routeFlow,
                ConnectHttp.toHost(host, port), materializer);

        return futureBinding.whenComplete((binding, exception) -> {
            if (binding != null) {
                InetSocketAddress address = binding.localAddress();
                system.log().info("Server online at http://{}:{}/",
                        address.getHostString(),
                        address.getPort());
            } else {
                system.log().error("Failed to bind HTTP endpoint, terminating system", exception);
                system.terminate();
            }
        });
    }
}

