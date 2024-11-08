package com.meta.air_jet.girlvoice;

import com.meta.air_jet.manvoice.ManVoc;
import com.meta.air_jet.manvoice.ManVocRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class GirlVocService {
    private final GirlVoiceRepository girlVoiceRepository;

    public GirlVoc getGirlVocById(Long girlVoiceId) {
        return girlVoiceRepository.findById(girlVoiceId).orElseThrow();
    }

}
