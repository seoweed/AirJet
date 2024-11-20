package com.meta.air_jet.girlvoice;

import com.meta.air_jet._core.file.AwsFileService;
import com.meta.air_jet.manvoice.VocAllResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class GirlVocService {
    private final GirlVoiceRepository girlVoiceRepository;
    private final AwsFileService awsFileService;

    public GirlVoc getGirlVocById(Long girlVoiceId) {
        return girlVoiceRepository.findById(girlVoiceId).orElseThrow();
    }

    // 보이스 저장
    public void saveVoice(String url, String description) {
        GirlVoc girlVoc = GirlVoc.builder()
                .description(description)
                .voice(url).build();
        girlVoiceRepository.save(girlVoc);
    }

    public List<VocAllResponseDTO> getVocAll() {
        List<GirlVoc> all = girlVoiceRepository.findAll();
        List<VocAllResponseDTO> vocAllResponseDTOs = new ArrayList<>();
        all.forEach(voice -> {
            vocAllResponseDTOs.add(
                    new VocAllResponseDTO(
                            (long) voice.getIdx(),
                            awsFileService.downloadAndEncodeFileFromUrl(voice.getVoice()),
                            voice.getDescription()
                    ));
        });
        return vocAllResponseDTOs;
    }

    public GirlVoc getGirlVocByIdx(Long idx) {
        return girlVoiceRepository.findByIdx(idx).orElseThrow();
    }
}
