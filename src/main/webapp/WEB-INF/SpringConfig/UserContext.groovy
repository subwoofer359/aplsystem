import org.amc.dao.DAO;
import org.amc.dao.UserRolesDAO;
import org.amc.model.User;
import org.amc.servlet.APLUserController;

beans {
    userServlet(APLUserController) {
        userDAO = ref('myUserDAO');
        userRolesDAO = ref('myUserRolesDAO');
    }

    myUserDAO(DAO, User) { bean ->
        bean.scope = 'prototype';
        entityManager = ref('appEntityManager');
    }

    myUserRolesDAO(UserRolesDAO) { bean ->
        bean.scope = 'prototype';
        entityManager = ref('appEntityManager');
    }
}
