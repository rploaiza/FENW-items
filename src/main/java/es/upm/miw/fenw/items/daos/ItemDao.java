package es.upm.miw.fenw.items.daos;

import java.util.List;

import es.upm.miw.fenw.items.entities.Item;

public interface ItemDao extends GenericDao<Item, Integer> {
    List<Item> findByName(String name);
}
