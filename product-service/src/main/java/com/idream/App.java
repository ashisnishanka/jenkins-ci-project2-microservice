package com.idream;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
public class App {
  public static String productJson() { return "{\"id\":101,\"name\":\"DevOps Course\",\"price\":4999}"; }
  public static void main(String[] args) throws Exception {
    HttpServer server=HttpServer.create(new InetSocketAddress(8081),0);
    server.createContext("/products", exchange -> {
      byte[] body=productJson().getBytes(StandardCharsets.UTF_8);
      exchange.getResponseHeaders().set("Content-Type","application/json; charset=UTF-8");
      exchange.sendResponseHeaders(200,body.length);
      try(var out=exchange.getResponseBody()){out.write(body);}
    });
    server.start(); System.out.println("Product service: http://localhost:8081/products");
  }
}
