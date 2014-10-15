package org.amc.servlet.action;

/**
 * 
 * @author Adrian Mclaughlin
 * @version 1
 */

import org.amc.dao.DAO;
import org.amc.model.Part;
import org.springframework.beans.factory.annotation.Autowired;

public class PartActionFactoryImpl implements PartActionFactory {
    private static final long serialVersionUID = -2782238430222426152L;
    private final DAO<Part> jobDAO;

    @Autowired
    public PartActionFactoryImpl(DAO<Part> jobDAO) {
        this.jobDAO = jobDAO;
    }

    // public SaveJobTemplateAction getSaveJobTemplateAction()
    // {
    // return new SaveJobTemplateAction(jobDAO);
    // }
    @Override
    public SavePartAction getSaveJobTemplateAction() {
        return new SavePartAction(this.jobDAO);
    }

    @Override
    public SearchPartAction getSearchJobTemplateAction() {
        return new SearchPartAction(this.jobDAO);
    }
}
