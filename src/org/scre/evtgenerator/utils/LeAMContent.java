package org.scre.evtgenerator.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * A static class or storing information extracted from the content.
 * @author vanlabeke
 * @version $Revision: 1.1 $
 * @todo I assume that the content is solely in LeAM_calculus collection; need to 
 *       expand both this class and the parsing procedure to enable multiple collections. 
 * 
 */
public class LeAMContent 
{
    /**
     * Index of the current properly parsed theories (used to deal with server interruption).
     */
    public static int inc = 0;

    /**
     * Store the name (String) of all the collections to use in the EventGenerator
     */
    public static Set collections = new HashSet();

    /**
     *  Store the MBaseID of all the theories presents in the collections 
     */
    public static List theories = new ArrayList();
    
    /**
     * Store all the items used in the EventGenerator.
     * Each item is a vector containing:
     * - the ID (String) of the item, stripped from the "mbase://_collectioname_/" part.
     * - a String containing the OMDOC Tag (with - if available - the OMDOC Type)
     * - the Difficulty (if any)
     * - a List of Competency (if any)
     * - the CompetencyLevel  (if any)
     * - a list of top-level topics (HashSet of String)
     */
    public static List items = new ArrayList();

    /**
     * Store the Omdoc Tag (String) of all items used in the EventGenerator.
     * Used for filtering the items in the Editor.
     */
    public static Set types = new HashSet();
}
