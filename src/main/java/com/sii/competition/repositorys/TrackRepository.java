package com.sii.competition.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.sii.competition.entity.TrackEntity;

@RepositoryRestResource(collectionResourceRel = "music", path = "music")
public interface TrackRepository extends JpaRepository<TrackEntity, Long> {

}
