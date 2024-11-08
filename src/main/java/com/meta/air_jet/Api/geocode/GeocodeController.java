package com.meta.air_jet.Api.geocode;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GeocodeController {
    private final GeocodeClient geocodeClient;

    @PostMapping("/get-code")
    public GeocodeResponse getCoordinates(@RequestBody GeocodeRequest request) {
        return geocodeClient.getGeocode(request);
    }
}
