package org.amc.servlet.action;

import org.amc.model.WorkEntity;
import org.amc.servlet.action.search.WebFormSearch;

public interface ActionFactory<T extends WorkEntity, S extends WebFormSearch> {
    /**
     * @return a SearchAction object
     */
    public SearchAction<T,S> getSearchAction();

    /**
     * @return a SaveAction object
     */
    public SaveAction<T> getSaveAction();

}
