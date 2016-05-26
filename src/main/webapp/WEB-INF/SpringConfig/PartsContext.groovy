import org.amc.dao.DAO;
import org.amc.model.Part;
import org.amc.servlet.APLPartServlet;
import org.amc.servlet.PartsController;
import org.amc.servlet.action.PartActionFactoryImpl;
import org.amc.servlet.validator.PartSearchFormValidator;

beans {
    
    partSearchFormValidator(PartSearchFormValidator);
    
    partsController(PartsController) {
        partActionFactory = ref('partActionFactory');
        searchFormValidator = ref('partSearchFormValidator');
    };

    partsModifyController(PartsModifyController) {
        partActionFactory = ref('partActionFactory');
    }

    partActionFactory(PartActionFactoryImpl, ref('partDAO'));

    partDAO(DAO, Part);
}