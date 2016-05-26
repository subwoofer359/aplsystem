import org.amc.dao.DAO;
import org.amc.model.MouldingProcess;
import org.amc.servlet.APLProcessServlet;
import org.amc.servlet.action.ProcessActionFactoryImpl;


beans {
    APLProcessServlet(APLProcessServlet) {
        processActionFactory = ref('processActionFactory');
    };
 
    processActionFactory(ProcessActionFactoryImpl, ref('mouldingProcessDAO'));
          
    mouldingProcessDAO(DAO, MouldingProcess) {
        entityManager = ref('appEntityManager');
    }
}