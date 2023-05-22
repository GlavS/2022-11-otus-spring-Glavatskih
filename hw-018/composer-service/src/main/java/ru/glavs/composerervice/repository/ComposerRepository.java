package ru.glavs.composerervice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.glavs.composerervice.model.Composer;

@RepositoryRestResource(collectionResourceRel = "composer", path = "composer")
public interface ComposerRepository extends JpaRepository<Composer, Long> {

    @RestResource(path = "f", rel = "family")
    Composer findFirstByFamilyName(@Param("family") String familyName);
}
