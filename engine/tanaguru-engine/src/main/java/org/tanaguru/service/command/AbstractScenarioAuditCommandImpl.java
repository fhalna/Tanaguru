/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2015  Tanaguru.org
 * 
 *  This file is part of Tanaguru.
 * 
 *  Tanaguru is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 * 
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 *  Contact us by mail: tanaguru AT tanaguru DOT org
 */

package org.tanaguru.service.command;

import java.util.Set;
import org.apache.log4j.Logger;
import org.tanaguru.entity.audit.AuditStatus;
import org.tanaguru.entity.parameterization.Parameter;
import org.tanaguru.entity.service.audit.AuditDataService;
import org.tanaguru.service.ScenarioLoaderService;

/**
 *
 * @author jkowalczyk
 */
public abstract class AbstractScenarioAuditCommandImpl extends AuditCommandImpl {
    
    /**
     * Logger
     */
    private static final Logger LOGGER = Logger.getLogger(AbstractScenarioAuditCommandImpl.class);
    
    /**
     * The scenario loader Service instance
     */
    private ScenarioLoaderService scenarioLoaderService;

    public ScenarioLoaderService getScenarioLoaderService() {
        return scenarioLoaderService;
    }
    public void setScenarioLoaderService(ScenarioLoaderService scenarioLoaderService) {
        this.scenarioLoaderService = scenarioLoaderService;
    }
    
    /**
     * The scenario file
     */
    private String scenario;
    public String getScenario() {
        return scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }
    
    /**
     * The scenario file
     */
    private String scenarioName;
    public String getScenarioName() {
        return scenarioName;
    }

    public void setScenarioName(String scenarioName) {
        this.scenarioName = scenarioName;
    }
    
    private boolean isPage = false;
    public void setIsPage(boolean isPage) {
        this.isPage = isPage;
    }
    
    /**
     * 
     * @param paramSet
     * @param auditDataService 
     */
    public AbstractScenarioAuditCommandImpl(
                Set<Parameter> paramSet,
                AuditDataService auditDataService) {
        super(paramSet, auditDataService);
    }
    
     /**
     * 
     * @param paramSet
     * @param auditDataService 
     * @param w3cValidatorPath 
     * @param java8Path
     */
    public AbstractScenarioAuditCommandImpl(
                Set<Parameter> paramSet,
                AuditDataService auditDataService,
                String w3cValidatorPath,
                String java8Path) {
        super(paramSet, auditDataService, w3cValidatorPath, java8Path);
    }


    @Override
    public void init() {
        super.init();
        setStatusToAudit(AuditStatus.SCENARIO_LOADING);
    }

    @Override
    public void loadContent() {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Loading content for " + scenarioName);
        }
        if (!getAudit().getStatus().equals(AuditStatus.SCENARIO_LOADING) || scenario.isEmpty()) {
            LOGGER.warn(
                    new StringBuilder("Audit Status is ")
                    .append(getAudit().getStatus())
                    .append(" while ")
                    .append(AuditStatus.SCENARIO_LOADING)
                    .append(" was required ").toString());
            setStatusToAudit(AuditStatus.ERROR);
            return;
        }
        // the returned content list is already persisted and associated with
        // the current audit

        createEmptySiteResource(scenarioName);

        scenarioLoaderService.loadScenario(getAudit(), scenario);
        setStatusToAudit(AuditStatus.CONTENT_ADAPTING);
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(scenarioName +" has been loaded");
        }
    }
}