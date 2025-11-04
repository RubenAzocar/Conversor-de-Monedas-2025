package com.example.conversor;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * Clase responsable de consultar la API de Exchange Rate.
 */
public class ConsultaTasa {

    // Placeholder donde el usuario debe insertar su API key
    public static final String API_KEY = "32f74fd97093ccf7488d0952";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/"; // ejemplo

    private final HttpClient client;
    private final Gson gson;

    public ConsultaTasa() {
        this.client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
        this.gson = new Gson();
    }

    /**
     * Obtiene la tasa de conversión desde base -> target consultando la API.
     * @param base código de moneda base (ej. USD)
     * @param target código de moneda objetivo (ej. ARS)
     * @return la tasa de conversión (double)
     * @throws IOException si hay un error de red
     * @throws InterruptedException si la petición fue interrumpida
     * @throws IllegalStateException si la respuesta no contiene la tasa buscada
     */
    public double getRate(String base, String target) throws IOException, InterruptedException {
        if (API_KEY == null || API_KEY.isBlank() || API_KEY.contains("INGRESE")) {
            throw new IllegalStateException("API key no configurada. Edite ConsultaTasa.API_KEY e inserte su clave.");
        }

        String url = BASE_URL + API_KEY + "/latest/" + base;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(10))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("Respuesta no OK de la API: " + response.statusCode());
        }

        try {
            TasaCambio tasa = gson.fromJson(response.body(), TasaCambio.class);
            Map<String, Double> rates = tasa.conversion_rates();
            if (rates == null || !rates.containsKey(target)) {
                throw new IllegalStateException("La tasa para " + target + " no está disponible en la respuesta de la API.");
            }
            return rates.get(target);
        } catch (JsonSyntaxException e) {
            throw new IOException("Respuesta JSON inválida", e);
        }
    }
}
