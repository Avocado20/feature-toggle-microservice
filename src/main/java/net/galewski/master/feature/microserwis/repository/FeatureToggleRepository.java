package net.galewski.master.feature.microserwis.repository;


import net.galewski.master.feature.microserwis.domain.FeatureToggle;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeatureToggleRepository extends PagingAndSortingRepository<FeatureToggle, String> {

    Optional<FeatureToggle> findByKey(String key);
    Optional<List<FeatureToggle>> findByParentKey(String key);
}
