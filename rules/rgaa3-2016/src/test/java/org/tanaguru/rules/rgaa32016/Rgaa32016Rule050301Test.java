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
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.TestSolution;
import static org.tanaguru.rules.keystore.AttributeStore.ABSENT_ATTRIBUTE_VALUE;
import static org.tanaguru.rules.keystore.AttributeStore.ROLE_ATTR;
import org.tanaguru.rules.keystore.HtmlElementStore;
import org.tanaguru.rules.keystore.RemarkMessageStore;
import static org.tanaguru.rules.keystore.MarkerStore.*;
import org.tanaguru.rules.rgaa32016.test.Rgaa32016RuleImplementationTestCase;

/**
 * Unit test class for the implementation of the rule 5-3-1 of the referential Rgaa 3-2016.
 *
 * @author jkowalczyk
 */
public class Rgaa32016Rule050301Test extends Rgaa32016RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa32016Rule050301Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.tanaguru.rules.rgaa32016.Rgaa32016Rule050301");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa32016.Test.05.03.01-2Failed-01",
                    createParameter("Rules", PRESENTATION_TABLE_MARKER, "class-presentation-table"));
        addWebResource("Rgaa32016.Test.05.03.01-2Failed-02",
                    createParameter("Rules", PRESENTATION_TABLE_MARKER, "class-presentation-table"));
        addWebResource("Rgaa32016.Test.05.03.01-3NMI-01",
                    createParameter("Rules", PRESENTATION_TABLE_MARKER, "presentation-table"));
        addWebResource("Rgaa32016.Test.05.03.01-3NMI-02",
                    createParameter("Rules", PRESENTATION_TABLE_MARKER, "presentation-table"));
        addWebResource("Rgaa32016.Test.05.03.01-3NMI-03");
        addWebResource("Rgaa32016.Test.05.03.01-3NMI-04");
        addWebResource("Rgaa32016.Test.05.03.01-4NA-01");
        addWebResource("Rgaa32016.Test.05.03.01-4NA-02",
                    createParameter("Rules", DATA_TABLE_MARKER, "id-data-table"),
                    createParameter("Rules", COMPLEX_TABLE_MARKER, "id-complex-table"));

    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------2Failed-01-----------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa32016.Test.05.03.01-2Failed-01");
        checkResultIsFailed(processResult, 1, 2);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_LINEARISED_CONTENT_MSG,
                HtmlElementStore.TABLE_ELEMENT,
                1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.PRESENTATION_TABLE_WITHOUT_ARIA_MARKUP_MSG,
                HtmlElementStore.TABLE_ELEMENT,
                2,
                new ImmutablePair(ROLE_ATTR, ABSENT_ATTRIBUTE_VALUE));
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-01-----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.05.03.01-2Failed-02");
        checkResultIsFailed(processResult, 1, 2);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_LINEARISED_CONTENT_MSG,
                HtmlElementStore.TABLE_ELEMENT,
                1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.PRESENTATION_TABLE_WITHOUT_ARIA_MARKUP_MSG,
                HtmlElementStore.TABLE_ELEMENT,
                2,
                new ImmutablePair(ROLE_ATTR, "heading"));
                
        //----------------------------------------------------------------------
        //------------------------------3NMI-01--------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.05.03.01-3NMI-01");
        checkResultIsPreQualified(processResult, 1,  1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_LINEARISED_CONTENT_MSG,
                HtmlElementStore.TABLE_ELEMENT,
                1);
                
        //----------------------------------------------------------------------
        //------------------------------3NMI-02--------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.05.03.01-3NMI-02");
        checkResultIsPreQualified(processResult, 1, 2);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_NATURE_OF_TABLE_AND_LINEARISED_CONTENT_MSG,
                HtmlElementStore.TABLE_ELEMENT,
                1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_TABLE_IS_PRESENTATION_WITH_ROLE_ARIA_MSG,
                HtmlElementStore.TABLE_ELEMENT,
                2,
                new ImmutablePair(ROLE_ATTR, "presentation"));
                
        //----------------------------------------------------------------------
        //------------------------------3NMI-03---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.05.03.01-3NMI-03");
        checkResultIsPreQualified(processResult, 1, 2);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_NATURE_OF_TABLE_AND_LINEARISED_CONTENT_MSG,
                HtmlElementStore.TABLE_ELEMENT,
                1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_TABLE_IS_NOT_PRESENTATION_WITHOUT_ROLE_ARIA_MSG,
                HtmlElementStore.TABLE_ELEMENT,
                2,
                new ImmutablePair(ROLE_ATTR, ABSENT_ATTRIBUTE_VALUE));
                
        //----------------------------------------------------------------------
        //------------------------------3NMI-04---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.05.03.01-3NMI-04");
        checkResultIsPreQualified(processResult, 1, 2);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_NATURE_OF_TABLE_AND_LINEARISED_CONTENT_MSG,
                HtmlElementStore.TABLE_ELEMENT,
                1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_TABLE_IS_NOT_PRESENTATION_WITHOUT_ROLE_ARIA_MSG,
                HtmlElementStore.TABLE_ELEMENT,
                2,
                new ImmutablePair(ROLE_ATTR, "heading"));
                
        //----------------------------------------------------------------------
        //------------------------------4NA-01------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.05.03.01-4NA-01"));
        //----------------------------------------------------------------------
        //------------------------------4NA-02------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.05.03.01-4NA-02"));
    }

}
