package com.example.springwebflux.server;

import com.example.springwebflux.handler.UserHandler;
import com.example.springwebflux.service.IUserService;
import com.example.springwebflux.service.impl.UserServiceImpl;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.netty.http.server.HttpServer;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.toHttpHandler;

public class Server {

    public RouterFunction<ServerResponse> routingFunction() {
        IUserService userService = new UserServiceImpl();
        UserHandler userHandler = new UserHandler(userService);
        return RouterFunctions.route(GET("/users/{id}").and(accept(APPLICATION_JSON)), userHandler::getUserById);
    }

    //2 创建服务器完成适配
    public void createReactorServer() {
        //路由和 handler 适配
        RouterFunction<ServerResponse> route = routingFunction();
        HttpHandler httpHandler = RouterFunctions.toHttpHandler(route);
        ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(httpHandler);
        //创建服务器
        HttpServer httpServer = HttpServer.create().port(8088);
        httpServer.handle(adapter).bindNow();
    }

    public static void main(String[] args) throws Exception{
        Server server = new Server();
        server.createReactorServer();
        System.out.println("enter to exit");
        System.in.read();
    }

}
