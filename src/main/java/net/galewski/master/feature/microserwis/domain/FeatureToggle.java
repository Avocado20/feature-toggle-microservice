package net.galewski.master.feature.microserwis.domain;

import ch.qos.logback.classic.db.names.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import net.galewski.master.feature.microserwis.config.TableNameContants;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Accessors(chain = true)
@Entity
public class FeatureToggle implements Serializable {

    @Id
    @Column(name = TableNameContants.KEY)
    private String key;

    private Boolean isEnabled = false;
    private String description;

    @ManyToOne
    @JoinColumn(name = TableNameContants.PARENT_KEY)
    @JsonIgnoreProperties({TableNameContants.PARENT})
    public FeatureToggle parent;

    @OneToMany(mappedBy= TableNameContants.PARENT)
    @JsonIgnore
    public Set<FeatureToggle> subToggles = new HashSet<>();

}
