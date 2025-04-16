package com.micro.shop.client;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.micro.shop.model.client.User;

@Component
public class UserServiceClient {

   private final WebClient webClient;

   public UserServiceClient(WebClient.Builder webClientBuilder) {
      this.webClient = webClientBuilder.baseUrl("http://nginx").build();
   }

   public List<User> getAllUser(Long userId) {
      return webClient.get()
            .uri("/users", userId)
            .retrieve()
            .bodyToFlux(User.class)
            .collectList()
            .block(); // Bloqueamos para mantener compatibilidad con el código existente

   }

   public User getUserById(Long userId) {
      return webClient.get()
            .uri("/users/{id}", userId)
            .retrieve()
            .bodyToMono(User.class)
            .block(); // Bloqueamos para mantener compatibilidad con el código existente
   }

   public boolean userExists(Long userId) {
      try {
         User user = getUserById(userId);
         return user != null;
      } catch (Exception e) {
         return false; // Manejo de error si el usuario no existe
      }
   }
}
