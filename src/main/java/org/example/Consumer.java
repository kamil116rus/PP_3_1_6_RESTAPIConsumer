package org.example;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;



public class Consumer {
    public static void main(String[] args) {
        String url = "http://94.198.50.185:7081/api/users";

        RestTemplate restTemplate = new RestTemplate();


        // Создание объекта HttpHeaders и добавление заголовков
        HttpHeaders headers = new HttpHeaders();

        // Создание объекта HttpEntity с заголовками
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        // Выполнение запроса с использованием метода exchange()
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        String response = responseEntity.getBody();
        System.out.println(response);

        String header = responseEntity.getHeaders().getFirst("Set-Cookie");

        System.out.println("Code = " + postUser(url, header) + puttUser(url, header) + deleteUser(url, header, 3L));
    }

    public static String postUser(String url, String header) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", header);
        headers.setContentType(MediaType.APPLICATION_JSON);

        String ssss = "{\"id\":3,\"name\":\"James\",\"lastName\":\"Brown\",\"age\":21}";
        HttpEntity<String> requestEntity = new HttpEntity<>(ssss, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        return responseEntity.getBody();
    }

    public static String puttUser(String url, String header) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", header);
        headers.setContentType(MediaType.APPLICATION_JSON);

        String ssss = "{\"id\":3,\"name\":\"Thomas\",\"lastName\":\"Shelby\",\"age\":55}";
        HttpEntity<String> requestEntity = new HttpEntity<>(ssss, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);

        return responseEntity.getBody();
    }

    public static String deleteUser(String url, String header, Long id) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", header);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(url + "/" + id, HttpMethod.DELETE, requestEntity, String.class);

        return responseEntity.getBody();
    }


}
