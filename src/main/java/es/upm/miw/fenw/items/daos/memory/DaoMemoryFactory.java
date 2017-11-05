package es.upm.miw.fenw.items.daos.memory;

import es.upm.miw.fenw.items.daos.DaoFactory;
import es.upm.miw.fenw.items.daos.ItemDao;

public class DaoMemoryFactory extends DaoFactory {

    private ItemDao itemDao;

    @Override
    public ItemDao getItemDao() {
        if (itemDao == null) {
           itemDao = new ItemDaoMemory();
        }
        return itemDao;
    }

}
