package net.galewski.master.feature.microserwis.service;

import net.galewski.master.feature.microserwis.config.Translations;

import javax.inject.Inject;

/**
 * Service skeleton to provide error translations.
 *
 */
public abstract class AbstractService {

    @Inject
    protected Translations i18n;
}