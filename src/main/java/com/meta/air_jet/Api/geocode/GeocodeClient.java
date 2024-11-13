package com.meta.air_jet.Api.geocode;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "geocodeClient", url = "https://electric-gorgeous-spaniel.ngrok-free.app")
public interface GeocodeClient {
    @PostMapping("/geocode")
    GeocodeResponse getGeocode(@RequestBody Map<String, Object> requestBody);
}
