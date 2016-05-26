import org.amc.dao.DAO;
import org.amc.model.Part;
import org.amc.servlet.PartsController;
import org.amc.servlet.action.PartActionFactoryImpl;
import org.amc.servlet.validator.PartSearchFormValidator;

beans {
    
    partSearchFormValidator(PartSearchFormValidator);
    
    partsController(PartsController) {
        partActionFactory = ref('partActionFactory');
        searchFormValidator = ref('partSearchFormValidator');
    };

    partActionFactory(PartActionFactoryImpl, ref('partDAO'));

    partDAO(DAO, Part);
}
