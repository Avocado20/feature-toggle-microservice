package net.galewski.master.feature.microserwis.rest;

import net.galewski.master.feature.microserwis.config.TableNameContants;
import net.galewski.master.feature.microserwis.domain.FeatureToggle;
import net.galewski.master.feature.microserwis.dto.FeatureToggleDto;
import net.galewski.master.feature.microserwis.service.FeatureToggleService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = TableNameContants.API_PATH + TableNameContants.TOGGLE)
public class FeatureToggleController implements FeatureToggleService {

    @Inject
    protected FeatureToggleService featureToggleService;

    @PostMapping
    public FeatureToggle create(@RequestBody FeatureToggleDto dto) {
        return this.featureToggleService.create(dto);
    }

    @GetMapping("/{" + TableNameContants.KEY + "}")
    public FeatureToggle findByKey(@PathVariable String key) throws NoSuchElementException {
        return this.featureToggleService.findByKey(key);
    }

    @GetMapping("/" + TableNameContants.PARENT + "/{" + TableNameContants.KEY + "}")
    public FeatureToggle findParentByKey(@PathVariable String key) throws NoSuchElementException {
        return this.featureToggleService.findParentByKey(key);
    }

    @GetMapping("/" + TableNameContants.ENABLED + "/{" + TableNameContants.KEY + "}")
    public Boolean isFeatureEnabled(@PathVariable String key) {
        return this.featureToggleService.isFeatureEnabled(key);
    }

    @PostMapping("/" + TableNameContants.ENABLE + "/{" + TableNameContants.KEY + "}")
    public void enable(@PathVariable String key) {
        this.featureToggleService.enable(key);
    }

    @PostMapping("/" + TableNameContants.DISABLE + "/{" + TableNameContants.KEY + "}")
    public void disable(@PathVariable  String key) {
        this.featureToggleService.disable(key);
    }

    @PutMapping
    public FeatureToggle update(@RequestBody FeatureToggleDto dto) throws NoSuchElementException {
        return this.featureToggleService.update(dto);
    }

    @DeleteMapping("/{" + TableNameContants.KEY + "}")
    public void deleteByKey(@PathVariable String key) throws NoSuchElementException {
        this.featureToggleService.deleteByKey(key);
    }
}
