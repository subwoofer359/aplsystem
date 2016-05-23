import javax.persistence.Persistence;

beans {
    xmlns([context:'http://www.springframework.org/schema/context',
        mvc:'http://www.springframework.org/schema/mvc'
        ]);
    //context.'component-scan'('base-package' : 'org.amc');
    applicationEntityManagerFactory(Persistence, 'myDatabase') { bean ->
            bean.factoryMethod = 'createEntityManagerFactory';
            bean.destroyMethod = 'close';
    };
}
 