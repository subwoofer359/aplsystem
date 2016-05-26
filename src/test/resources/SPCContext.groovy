import org.amc.dao.DAO;
import org.amc.dao.SPCDataDAO;
import org.amc.dao.SPCMeasurementDAO;
import org.amc.model.spc.SPCPartsList;

beans {
    spcDimensionDAO(SPCMeasurementDAO) { bean ->
        bean.scope = 'prototype';
    }
    
    spcPartsListDAO(DAO, SPCPartsList) { bean ->
        bean.scope = 'prototype';
    }
    
    spcDataDAO(SPCDataDAO) { bean ->
        bean.scope = 'prototype';
        entityManager = ref('appEntityManager');
    } 
}