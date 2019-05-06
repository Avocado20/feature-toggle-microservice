package net.galewski.master.feature.microserwis.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Accessors(chain = true)
public class FeatureToggleDto {

    private String key;
    private String parentKey;

    @NotNull
    private Boolean isEnabled;
    private String description;

}
