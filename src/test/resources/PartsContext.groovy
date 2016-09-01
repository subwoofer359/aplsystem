import org.amc.dao.DAO;
import org.amc.model.Part
import org.amc.servlet.MainController;
import org.amc.servlet.PartsModifyController;
import org.amc.servlet.PartsSearchController;
import org.amc.servlet.action.PartActionFactoryImpl;

beans {
    
    mainController(MainController);
    
    partsController(PartsSearchController) {
        partActionFactory = ref('partActionFactory');
    };

    partsModifyController(PartsModifyController) {
        partActionFactory = ref('partActionFactory');
    };

    partActionFactory(PartActionFactoryImpl, ref('partDAO'));

    partDAO(DAO, Part) {
        entityManager = ref('appEntityManager');    
    };
}
