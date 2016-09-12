package org.amc.dao.parsers

import org.amc.dao.DAO
import org.amc.model.Material
import org.amc.model.MouldingProcess;
import org.amc.model.Part
import org.amc.model.mouldingprocess.BasicInfo;

import javax.persistence.EntityManager;

class MouldProcessFixture {
    final int daysInPast = -1;
    
    Calendar calendar = Calendar.getInstance();
    EntityManager em;
    
    DAO<Part> partDAO = new DAO<Part>(Part);
    DAO<Material> materialDAO = new DAO<Material>(Material);
    DAO<MouldingProcess> mouldingDAO = new DAO<MouldingProcess>(MouldingProcess);
    
    MouldProcessFixture(EntityManager em) {
        this.em = em;
        
        partDAO.entityManager = em;
        materialDAO.entityManager = em;
        mouldingDAO.entityManager = em;
    }
    
    void setup() {
        setupAndAddPartsToDB();
        setupAndAddMaterialsToDB();
        setupAndMouldingProcessToDB();
        em.getTransaction().begin();
        em.flush();
        em.getTransaction().commit();
    }
    
    void setupAndAddPartsToDB() {
        def parts = [new Part(
            name: 'Pot',
            colour: 'Red',
            company: 'Forest Tosara',
            external: true,
            part_id: 'TF 001',
            qss_no: 'QSS: T023',
            revision: '2005',
            version: '250g'
        ),
        new Part(
            name: 'Pot',
            colour: 'Red',
            company: 'Forest Tosara',
            external: true,
            part_id: 'TF 002',
            qss_no: 'QSS: T024',
            revision: '2010',
            version: '125g'
        )]
    
        parts.each{
            partDAO.addEntity(it);
        }
        
    }
    
    void setupAndAddMaterialsToDB() {
        Material m = new Material(
              company: 'Exxon Mobil',
              name: 'High Density PolyPropylene',
              type: 'HDPP',
              density: 2.0f,  
        );
    
        materialDAO.addEntity(m);
    }
    
    void setupAndMouldingProcessToDB() {
        Part p = partDAO.findEntities()[0];
        Part pot125g = partDAO.findEntities()[1];
        
        Material m = materialDAO.findEntities()[0];
        Calendar c = Calendar.getInstance();
        
        List processes = [
            new MouldingProcess(
                basicInfo: new BasicInfo(
                    partId: p,
                    machineSize: 70,
                    machineNo: 'San 7',
                    material: m,
                    masterbatchNo: 'ro3993',
                    dateOfIssue: getNextDate(),
                    signOffBy: 'Adrian Mclaughlin'
                )
            ),
            new MouldingProcess(
                basicInfo: new BasicInfo(
                    partId: p,
                    machineSize: 120,
                    machineNo: 'San 9',
                    material: m,
                    masterbatchNo: 'ro3993',
                    dateOfIssue: getNextDate(),
                    signOffBy: 'Terry Nolan'
                )
            ),
            new MouldingProcess(
                basicInfo: new BasicInfo(
                    partId: pot125g,
                    machineSize: 120,
                    machineNo: 'San 12',
                    material: m,
                    masterbatchNo: 'ro3993a',
                    dateOfIssue: getNextDate(),
                    signOffBy: 'Adrian Mclaughlin'
                )
            )
        ]
    
        processes.each {
            mouldingDAO.addEntity(it);
        }
    }
    
    private Date getNextDate() {
        calendar.add(Calendar.DATE, daysInPast);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}
