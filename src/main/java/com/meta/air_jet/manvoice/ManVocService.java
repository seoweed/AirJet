package com.meta.air_jet.manvoice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ManVocService {
    private final ManVocRepository manVocRepository;

    public ManVoc getManVocById(Long manVoiceId) {
        return manVocRepository.findById(manVoiceId).orElseThrow();
    }

    public void saveVoice(String url, String description) {
        ManVoc manVoc = ManVoc.builder()
                .description(description)
                .voice(url).build();
        manVocRepository.save(manVoc);
    }
}
