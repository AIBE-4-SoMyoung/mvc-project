package org.example.mvcprojcet.mvc.model.repository;

import io.github.cdimascio.dotenv.Dotenv;
import org.example.mvcprojcet.mvc.model.entity.Pet;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class SupabasePetRepository implements PetRepository {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final String apiURL;
    private final String apiKey;

    public SupabasePetRepository() {
        // 로컬 개발: .env 파일 로드, 프로덕션: 시스템 환경변수 사용
        Dotenv dotenv = null;
        try {
            dotenv = Dotenv
                    .configure()
                    .directory(System.getProperty("user.dir")) // 현재 작업 디렉토리 사용
                    .ignoreIfMissing() // .env 파일이 없으면 시스템 환경변수 사용
                    .load();
        } catch (Exception e) {
            System.err.println(".env file not found, using system environment variables");
        }

        // .env 파일이 있으면 사용, 없으면 시스템 환경변수 사용
        if (dotenv != null) {
            apiURL = dotenv.get("SUPABASE_URL");
            apiKey = dotenv.get("SUPABASE_KEY");
        } else {
            apiURL = System.getenv("SUPABASE_URL");
            apiKey = System.getenv("SUPABASE_KEY");
        }

        // 디버깅용 로그
        if (apiURL == null || apiURL.isBlank()) {
            System.err.println("SUPABASE_URL is not set!");
            throw new IllegalStateException("SUPABASE_URL environment variable is required");
        }
    }

    @Override
    public List<Pet> findAll() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("%s/rest/v1/pet?select=*".formatted(apiURL)))
                .GET()
                .headers("Authorization", "Bearer %s".formatted(apiKey), "apikey", apiKey)
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // 디버깅: 응답 출력
            System.out.println("Status Code: " + response.statusCode());
            System.out.println("Response Body: " + response.body());

            // 에러 응답 체크
            if (response.statusCode() != 200) {
                throw new RuntimeException("Supabase API error: " + response.statusCode() + " - " + response.body());
            }

            return objectMapper.readValue(response.body(), new TypeReference<>(){});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Pet pet) {
        String payload = objectMapper.writeValueAsString(pet);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("%s/rest/v1/pet".formatted(apiURL)))
                .POST(HttpRequest.BodyPublishers.ofString(
                        payload
                ))
                .headers("Authorization", "Bearer %s".formatted(apiKey),
                        "apikey", apiKey,
                        "Content-Type", "application/json",
                        "Prefer", "return=minimal")
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request,
                    HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());
            if (response.body().isBlank()) {
                System.out.println(response.body());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}