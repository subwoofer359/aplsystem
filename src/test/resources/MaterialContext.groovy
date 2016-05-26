import org.amc.servlet.APLMaterialServlet;
import org.amc.servlet.APLProcessServlet;
import org.amc.servlet.action.MaterialActionFactoryImpl;


beans {
    APLProcessServlet(APLProcessServlet) {
        materialActionFactory =  ref('materialActionFactory');
    }
    
    APLMaterialServlet(APLMaterialServlet) {
        materialActionFactory = ref('materialActionFactory');
    }

    materialActionFactory(MaterialActionFactoryImpl, ref('MaterialDAO')) {
    }
    
    MaterialDAO(org.amc.dao.MaterialDAO) {
        entityManager = ref('appEntityManager');
    }
}
