package org.amc.myservlet.test.spc

import org.amc.EntityManagerThreadLocal;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

class DatabaseFixture {
    private static final Logger logger = Logger.getLogger(DatabaseFixture);
    EntityManagerFactory factory;
    EntityManager entityManager;
    def entityManagerList;

    public void setUpEntitiyManagerFactory() {
        factory = Persistence.createEntityManagerFactory("myDatabase");
        entityManager = factory.createEntityManager();
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return factory;
    }

    public void setUp() {
        setUpEntitiyManagerFactory();
        entityManagerList = [];
    }

    public void tearDown() {
        closeEntityManagers();
        try {
            DriverManager.getConnection("jdbc:derby:memory:amcchessgametest;drop=true");
        } catch(SQLException sqle) {
            logger.info(sqle);
        }
    }
    
    private void closeEntityManagers() {
        entityManagerList.each {
            if(it?.isOpen()) {
                it.close();
            }
        };
    }
    
    public EntityManager getNewEntityManager() {
        def em  = factory.createEntityManager();
        entityManagerList.add(em);
        return em;
    }

    public void clearTables() throws SQLException {
        Connection c = entityManager.unwrap(Connection.class);
        List<String> tables = new ArrayList<>();
        try {
            Statement s = c.createStatement();
            ResultSet r = s.executeQuery("SELECT * FROM sys.systables");
            while(r.next()) {
                String tableName = r.getString(2);
                String tableType = r.getString(3);
                if("T".equals(tableType)) {
                    tables.add(tableName);
                }
            }

            for(String tableName : tables) {
                s.execute("DELETE FROM " + tableName);
            }
        }
        finally {
            
        }
    }
}
