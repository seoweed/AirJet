package com.meta.air_jet.girlvoice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface GirlVoiceRepository extends JpaRepository<GirlVoc, Long> {
    @Query("select v from GirlVoc v where v.idx = :idx")
    Optional<GirlVoc> findByIdx(@Param("idx") Long idx);
}
