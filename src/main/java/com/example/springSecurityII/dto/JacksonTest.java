package com.example.springSecurityII.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonTest {
    //username
    //profile.age
    //profile.city
    public static void main(String[] args) throws JsonProcessingException {
        String json = """
                {
                  "user": {
                    "id": 202,
                    "username": "bob123",
                    "profile": {
                      "age": 25,
                      "city": "Delhi"
                    }
                  }
                }
                """;
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json);
        String text = jsonNode.get("user").get("username").asText();
        int age = jsonNode.get("user").get("profile").get("age").asInt();
        String city = jsonNode.get("user").get("profile").get("city").asText();
        System.out.printf("username: %S age: %d city: %S", text, age, city);
    }

}
