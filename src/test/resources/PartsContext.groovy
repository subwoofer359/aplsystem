import org.amc.dao.DAO;
import org.amc.model.Part;
import org.amc.servlet.APLPartServlet;
import org.amc.servlet.action.PartActionFactoryImpl;

beans {
    
  APLSystemServlet(APLPartServlet) {
      partActionFactory = ref('partActionFactory');
  }
 
  partActionFactory(PartActionFactoryImpl, ref('partDAO'));
  
  partDAO(DAO, Part);
}
