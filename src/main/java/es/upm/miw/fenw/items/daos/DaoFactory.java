package es.upm.miw.fenw.items.daos;

import org.springframework.stereotype.Repository;

@Repository
public abstract class DaoFactory {

    private static DaoFactory factory = null;

    public static void setFactory(DaoFactory factory) {
        DaoFactory.factory = factory;
    }

    public static DaoFactory getFactory() {
        assert factory != null;
        return factory;
    }

    public abstract ItemDao getItemDao();

}
