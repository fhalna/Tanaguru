/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2016  Tanaguru.org
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: tanaguru AT tanaguru DOT org
 */

package org.tanaguru.rules.rgaa32016;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.ruleimplementation.AbstractDetectionPageRuleImplementation;
import org.tanaguru.rules.elementselector.SimpleElementSelector;
import static org.tanaguru.rules.keystore.CssLikeQueryStore.SELECT_WITHIN_FORM_CSS_LIKE_QUERY;
import static org.tanaguru.rules.keystore.RemarkMessageStore.MANUAL_CHECK_ON_ELEMENTS_MSG;

/**
 * Implementation of the rule 11.8.1 of the referential Rgaa 3-2016.
 * <br>
 * For more details about the implementation, refer to <a href="http://tanaguru-rules-rgaa3.readthedocs.org/en/latest/Rule-11-8-1">the rule 11.8.1 design page.</a>
 * @see <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#test-11-8-1"> 11.8.1 rule specification</a>
 *
 */
public class Rgaa32016Rule110801 extends AbstractDetectionPageRuleImplementation {
    
    /**
     * Default constructor
     */
    public Rgaa32016Rule110801 () {
        super(
                new SimpleElementSelector(SELECT_WITHIN_FORM_CSS_LIKE_QUERY),
                // solution when at least one element is found
                new ImmutablePair(TestSolution.NEED_MORE_INFO,MANUAL_CHECK_ON_ELEMENTS_MSG),
                // solution when no element is found
                new ImmutablePair(TestSolution.NOT_APPLICABLE,"")
            );
    }

}
