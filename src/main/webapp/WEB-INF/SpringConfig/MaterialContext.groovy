import org.amc.dao.DAO;
import org.amc.model.Material;
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
    
    MaterialDAO(DAO, Material) {
        entityManager = ref('appEntityManager');
    }
}
