package net.galewski.master.feature.microserwis.service;

import net.galewski.master.feature.microserwis.domain.FeatureToggle;
import net.galewski.master.feature.microserwis.dto.FeatureToggleDto;

import java.util.NoSuchElementException;

public interface FeatureToggleService {

    FeatureToggle create(FeatureToggleDto dto) throws IllegalArgumentException;
    FeatureToggle findByKey(String key) throws NoSuchElementException;
    FeatureToggle findParentByKey(String key) throws NoSuchElementException;
    FeatureToggle update(FeatureToggleDto dto) throws NoSuchElementException;
    void deleteByKey(String key) throws NoSuchElementException;

    Boolean isFeatureEnabled(String key);
    FeatureToggle enable(String key);
    FeatureToggle disable(String key);
}
