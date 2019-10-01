package net.galewski.master.feature.microserwis.service.impl;

import net.galewski.master.feature.microserwis.domain.FeatureToggle;
import net.galewski.master.feature.microserwis.dto.FeatureToggleDto;
import net.galewski.master.feature.microserwis.repository.FeatureToggleRepository;
import net.galewski.master.feature.microserwis.service.AbstractService;
import net.galewski.master.feature.microserwis.service.FeatureToggleService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class FeatureToggleServiceImpl extends AbstractService implements FeatureToggleService {

    @Inject
    protected FeatureToggleRepository featureToggleRepository;

    @Override
    public FeatureToggle create(FeatureToggleDto dto) {
        if (dto.getKey() == null)
        {
            throw new IllegalArgumentException(this.i18n.get("feature.key.null", dto.getKey()));
        }
        FeatureToggle parent  = this.featureToggleRepository.findByKey(dto.getParentKey()).orElse(null);
        if (this.featureToggleRepository.findByKey(dto.getKey()).isPresent())
        {
            throw new IllegalArgumentException(this.i18n.get("feature.such.feature.exists", dto.getKey()));
        }
        FeatureToggle toggle = new FeatureToggle().setKey(dto.getKey()).setIsEnabled(dto.getIsEnabled()).setDescription(dto.getDescription()).setParent(parent);
        return this.featureToggleRepository.save(toggle);
    }

    @Override
    public FeatureToggle findByKey(String key) throws NoSuchElementException {
        return this.featureToggleRepository.findByKey(key).orElseThrow(() -> new NoSuchElementException(this.i18n.get("feature.no.such.feature", key)));
    }

    @Override
    //TODO
    //duplicated code
    public FeatureToggle findParentByKey(String key) throws NoSuchElementException {
        return this.featureToggleRepository.findByKey(key).orElseThrow(() -> new NoSuchElementException(this.i18n.get("feature.no.such.parent.feature", key)));
    }

    @Override
    public FeatureToggle update(FeatureToggleDto dto) throws NoSuchElementException {
        FeatureToggle existingToggle = this.findByKey(dto.getKey());
//        FeatureToggle parent = existingToggle.getParent();
        existingToggle.setDescription(dto.getDescription()).setIsEnabled(dto.getIsEnabled());
        if (dto.getParentKey() == null)
        {
            existingToggle.setParent(null);
        } else if (!existingToggle.getParent().getKey().equals(dto.getParentKey())) {
            existingToggle.setParent(this.findParentByKey(dto.getParentKey()));
        }

        return this.featureToggleRepository.save(existingToggle);
    }

    @Override
    public void deleteByKey(String key) throws NoSuchElementException {
        if (this.featureToggleRepository.findByKey(key).isPresent()) {
            this.featureToggleRepository.deleteById(key);
        } else {
            new NoSuchElementException(this.i18n.get("feature.no.such.feature", key));
        }
    }

    @Override
    public Boolean isFeatureEnabled(String key) {
        FeatureToggle toggle = this.findByKey(key);
        if (toggle.getParent() != null)
        {
            return this.isFeatureEnabled(toggle.getParent().getKey()) && toggle.getIsEnabled();
        }
        return toggle.getIsEnabled();
    }

    @Override
    public FeatureToggle enable(String key) {
        FeatureToggle toggle = this.findByKey(key);
        toggle.setIsEnabled(true);
        return this.featureToggleRepository.save(toggle);
    }

    @Override
    public FeatureToggle disable(String key) {
        FeatureToggle toggle = this.findByKey(key);
        toggle.setIsEnabled(false);
        return this.featureToggleRepository.save(toggle);
    }
}
