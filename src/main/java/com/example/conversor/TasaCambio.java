package com.example.conversor;

import java.util.Map;

/**
 * Record inmutable para mapear la respuesta relevante de la API.
 * Se asume que la API devuelve un campo "conversion_rates" que es un map.
 */
public record TasaCambio(String base_code, Map<String, Double> conversion_rates) {
    // Gson mapeará los campos por nombre.
}
