/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
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
package org.tanaguru.rules.rgaa30;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.ruleimplementation.AbstractPageRuleWithSelectorAndCheckerImplementation;
import org.tanaguru.rules.elementchecker.element.ElementPresenceChecker;
import org.tanaguru.rules.elementselector.SimpleElementSelector;
import static org.tanaguru.rules.keystore.CssLikeQueryStore.TITLE_WITHIN_HEAD_CSS_LIKE_QUERY;
import static org.tanaguru.rules.keystore.RemarkMessageStore.TITLE_TAG_MISSING_MSG;

/**
 * Implementation of the rule 8.5.1 of the referential Rgaa 3.0.
 * <br>
 * For more details about the implementation, refer to <a href="http://tanaguru-rules-rgaa3.readthedocs.org/en/latest/Rule-8-5-1">the rule 8.5.1 design page.</a>
 * @see <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#test-8-5-1"> 8.5.1 rule specification</a>
 *
 */
public class Rgaa30Rule080501 extends AbstractPageRuleWithSelectorAndCheckerImplementation {
    
    /**
     * Default constructor
     */
    public Rgaa30Rule080501(){
        super(
                new SimpleElementSelector(TITLE_WITHIN_HEAD_CSS_LIKE_QUERY), 
                
                new ElementPresenceChecker(
                    // check unicity
                    true,
                    // result when element detected
                    new ImmutablePair(TestSolution.PASSED,""),
                    // result when element not detected
                    new ImmutablePair(TestSolution.FAILED,TITLE_TAG_MISSING_MSG),
                    // no message when multiple elements
                    null
                )
            );
    }
    
}
