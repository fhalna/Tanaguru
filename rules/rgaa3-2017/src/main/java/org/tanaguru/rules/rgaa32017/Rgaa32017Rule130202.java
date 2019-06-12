/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2017  Tanaguru.org
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

package org.tanaguru.rules.rgaa32017;

import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.ruleimplementation.AbstractDetectionPageRuleImplementation;
import org.tanaguru.rules.elementselector.MultipleElementSelector;
import static org.tanaguru.rules.keystore.HtmlElementStore.EMBED_ELEMENT;
import static org.tanaguru.rules.keystore.HtmlElementStore.OBJECT_ELEMENT;
import static org.tanaguru.rules.keystore.RemarkMessageStore.CHECK_USER_IS_WARNED_IN_CASE_OF_NEW_WINDOW_MSG;

/**
 * Implementation of the rule 13.2.2 of the referential Rgaa 3-2017.
 * <br>
 * For more details about the implementation, refer to <a href="http://tanaguru-rules-rgaa3.readthedocs.org/en/latest/Rule-13-2-2">the rule 13.2.2 design page.</a>
 * @see <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#test-13-2-2"> 13.2.2 rule specification</a>
 *
 * @author jkowalczyk
 */
public class Rgaa32017Rule130202 extends AbstractDetectionPageRuleImplementation {

    /**
     * Default constructor
     */
    public Rgaa32017Rule130202 () {
        super(
                new MultipleElementSelector(OBJECT_ELEMENT, EMBED_ELEMENT),
                // solution when at least one element is found
                TestSolution.NEED_MORE_INFO,
                // solution when no element is found
                TestSolution.NOT_APPLICABLE,
                // manual check message
                CHECK_USER_IS_WARNED_IN_CASE_OF_NEW_WINDOW_MSG,
                null);
    }

}
