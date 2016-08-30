import org.amc.servlet.APLProcessServlet
import org.amc.servlet.MaterialSaveController
import org.amc.servlet.MaterialSearchController;
import org.amc.servlet.action.MaterialActionFactoryImpl;


beans {
    APLProcessServlet(APLProcessServlet) {
        materialActionFactory =  ref('materialActionFactory');
    }
    
    MaterialSaveController(MaterialSaveController) {
        materialActionFactory = ref('materialActionFactory');
    }
    
    MaterialSearchController(MaterialSearchController) {
        materialActionFactory = ref('materialActionFactory');
    }

    materialActionFactory(MaterialActionFactoryImpl, ref('MaterialDAO')) {
    }
    
    MaterialDAO(org.amc.dao.MaterialDAO) {
        entityManager = ref('appEntityManager');
    }
}
