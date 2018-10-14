package com.clicktools.api;

import org.glassfish.grizzly.Grizzly;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;
import java.util.ResourceBundle;

public class EmbeddedGrizzlyServer {
    public static final String BASE_URI = "http://localhost:8880/blackduck";

    private static HttpServer server;

    public static HttpServer startServer() {
        final ResourceConfig rc = new ResourceConfig().packages("com.clicktools.api");
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        System.out.println(String.format("Jersey app started with WADL available at %s application.wadl", BASE_URI));
    }
}