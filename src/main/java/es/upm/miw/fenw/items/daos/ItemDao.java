package es.upm.miw.fenw.items.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.upm.miw.fenw.items.entities.Item;

public interface ItemDao extends JpaRepository<Item, Integer> {
    List<Item> findByName(String name);
}
