package com.meta.air_jet.manvoice;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.S3Object;
import com.meta.air_jet._core.file.AwsFileService;
import com.meta.air_jet._core.utils.S3UrlParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ManVocService {
    private final ManVocRepository manVocRepository;
    private final AwsFileService awsFileService;


    public ManVoc getManVocById(Long manVoiceId) {
        return manVocRepository.findById(manVoiceId).orElseThrow();
    }

    public void saveVoice(String url, String description) {
        ManVoc manVoc = ManVoc.builder()
                .description(description)
                .voice(url).build();
        manVocRepository.save(manVoc);
        // aaaaAa
    }

    public List<VocAllResponseDTO> getVocAll() {
        List<ManVoc> all = manVocRepository.findAll();
        List<VocAllResponseDTO> vocAllResponseDTOs = new ArrayList<>();
        all.forEach(voice -> {
            vocAllResponseDTOs.add(
                    new VocAllResponseDTO(
                            voice.getId(),
                            awsFileService.downloadAndEncodeFileFromUrl(voice.getVoice()),
                            voice.getDescription()
                    ));
        });
        return vocAllResponseDTOs;
    }
}
