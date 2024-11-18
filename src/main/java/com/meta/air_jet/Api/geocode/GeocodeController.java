package com.meta.air_jet.Api.geocode;

import com.meta.air_jet._core.utils.SecurityUtils;
import com.meta.air_jet.result.ResultRequestDTO;
import com.meta.air_jet.result.ResultResponseDTO;
import com.meta.air_jet.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class GeocodeController {

    private final GeocodeClient geocodeClient;

    @PostMapping("/geocode")
    public ResponseEntity<?> save(@RequestBody GeocodeRequest geocodeRequest) {
        if (geocodeRequest.getText().equals("서울")) {
            return ResponseEntity.ok(new GeocodeResponse(37.538281088987624, 127.01834119500641));
        } else if (geocodeRequest.getText().equals("몰타")) {
            return ResponseEntity.ok(new GeocodeResponse(35.939147676475244, 14.335729406295698));
        }

        // api 요청 body 생성, latitude, longitude
        Map<String, Object> requestBody = Map.of(
                "text", geocodeRequest.getText()
        );
        // api 요청 및 response
        GeocodeResponse geocodeResponse = geocodeClient.getGeocode(requestBody);

        return ResponseEntity.ok(geocodeResponse);
    }

}
