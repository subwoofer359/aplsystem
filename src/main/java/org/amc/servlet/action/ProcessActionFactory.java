package org.amc.servlet.action;

import java.io.Serializable;

/**
 * 
 * @author Adrian Mclaughlin
 * @version 1
 */

public interface ProcessActionFactory extends Serializable {
    public SaveProcessSheetAction getSaveProcessSheetAction();

    public SearchProcessSheetAction getSearchProcessSheetAction();
}
