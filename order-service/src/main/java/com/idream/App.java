package com.idream;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
public class App {
  public static String html(String product) { return "<html><head><meta charset='utf-8'><title>iDream Orders</title><style>body{font-family:Arial;background:#eaf2ff;text-align:center;padding:80px}main{background:white;padding:40px;border-radius:16px;display:inline-block}h1{color:#064b9a}</style></head><body><main><h1>iDream IT Solution</h1><h2>Order Service</h2><p>Product Service Response:</p><pre>"+product+"</pre><p>Java Microservices + Jenkins CI</p></main></body></html>"; }
  public static void main(String[] args) throws Exception {
    HttpServer server=HttpServer.create(new InetSocketAddress(8082),0);
    HttpClient client=HttpClient.newHttpClient();
    String productUrl=System.getenv().getOrDefault("PRODUCT_URL","http://localhost:8081/products");
    server.createContext("/", exchange -> {
      String product;
      try { product=client.send(HttpRequest.newBuilder(URI.create(productUrl)).GET().build(),HttpResponse.BodyHandlers.ofString()).body(); }
      catch(Exception ex){product="Product service unavailable";}
      byte[] body=html(product).getBytes(StandardCharsets.UTF_8);
      exchange.getResponseHeaders().set("Content-Type","text/html; charset=UTF-8");
      exchange.sendResponseHeaders(200,body.length);
      try(var out=exchange.getResponseBody()){out.write(body);}
    });
    server.start(); System.out.println("Order service: http://localhost:8082");
  }
}
