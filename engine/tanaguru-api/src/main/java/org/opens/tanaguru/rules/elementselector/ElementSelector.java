/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2013  Open-S Company
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
 *  Contact us by mail: open-s AT open-s DOT com
 */

package org.opens.tanaguru.rules.elementselector;

import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.ElementHandler;

/**
 * This interface defines a select operation. This operation uses the
 * {@link ElementHandler} to store the selected elements.
 */
public interface ElementSelector {
    
    /**
     * Perform the select operation. The instance of {@link ElementHandler} 
     * received as a parameter is used to store elements selected during
     * operation
     * 
     * @param sspHandler
     * @param elementHandler
     *  
     */
    void selectElements(SSPHandler sspHandler, ElementHandler elementHandler);
    
}
