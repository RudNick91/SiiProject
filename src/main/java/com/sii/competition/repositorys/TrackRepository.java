package com.sii.competition.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.sii.competition.entity.TrackEntity;

@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface TrackRepository extends JpaRepository<TrackEntity, Long> {


}
