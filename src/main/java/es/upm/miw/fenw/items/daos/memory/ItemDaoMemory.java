package es.upm.miw.fenw.items.daos.memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import es.upm.miw.fenw.items.daos.ItemDao;
import es.upm.miw.fenw.items.entities.Item;

public class ItemDaoMemory extends GenericDaoMemory<Item> implements ItemDao {

    private int id;

    public ItemDaoMemory() {
        this.setMap(new HashMap<Integer, Item>());
        id = 1;
    }

    @Override
    protected Integer getId(Item entity) {
        return id++;
    }

    @Override
    protected void setId(Item entity, Integer id) {
        entity.setId(id);

    }

    @Override
    public List<Item> findByName(String name) {
        List<Item> itemList = new ArrayList<>();
        for (Item item : findAll()) {
            if (name.equals(item.getName())) {
                itemList.add(item);
            }
        }
        return itemList;
    }

}
