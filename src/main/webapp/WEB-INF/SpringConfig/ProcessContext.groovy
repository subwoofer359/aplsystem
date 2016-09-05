import org.amc.dao.DAO;
import org.amc.model.MouldingProcess;
import org.amc.servlet.MouldingProcessSaveController;
import org.amc.servlet.MouldingProcessSearchController;
import org.amc.servlet.action.ProcessActionFactoryImpl;


beans {
    mouldingProcessSearchController(MouldingProcessSearchController) {
        processActionFactory = ref('processActionFactory');
    }

    mouldingProcessSaveController(MouldingProcessSaveController) {
        processActionFactory = ref('processActionFactory');
    }
 
    processActionFactory(ProcessActionFactoryImpl, ref('mouldingProcessDAO'))
          
    mouldingProcessDAO(DAO, MouldingProcess) {
        entityManager = ref('appEntityManager');
    }
}