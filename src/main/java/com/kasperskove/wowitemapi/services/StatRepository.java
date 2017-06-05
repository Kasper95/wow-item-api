package com.kasperskove.wowitemapi.services;

import com.kasperskove.wowitemapi.entities.Stat;
import org.springframework.data.repository.CrudRepository;

public interface StatRepository extends CrudRepository<Stat, Integer> {

    Stat findByStatName (String statName);
}
