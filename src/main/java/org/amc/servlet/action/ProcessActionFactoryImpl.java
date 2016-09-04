package org.amc.servlet.action;

/**
 * 
 * @author Adrian Mclaughlin
 * @version 1
 */
import org.amc.dao.DAO;
import org.amc.model.MouldingProcess;
import org.amc.servlet.action.search.MouldingProcessSearch;
import org.springframework.beans.factory.annotation.Autowired;

public class ProcessActionFactoryImpl implements ActionFactory<MouldingProcess, MouldingProcessSearch> {

    private final DAO<MouldingProcess> mouldingProcessDAO;

    @Autowired
    public ProcessActionFactoryImpl(DAO<MouldingProcess> mouldingProcessDAO) {
        this.mouldingProcessDAO = mouldingProcessDAO;
    }

    @Override
    public SaveAction<MouldingProcess> getSaveAction() {
        return new SaveAction<MouldingProcess>(this.mouldingProcessDAO);
    }

    @Override
    public SearchAction<MouldingProcess, MouldingProcessSearch> getSearchAction() {
        return new SearchAction<MouldingProcess, MouldingProcessSearch>(this.mouldingProcessDAO);
    }

}
