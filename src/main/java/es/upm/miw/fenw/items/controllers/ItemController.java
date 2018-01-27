package es.upm.miw.fenw.items.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import es.upm.miw.fenw.items.daos.ItemDao;
import es.upm.miw.fenw.items.dtos.ItemDto;
import es.upm.miw.fenw.items.entities.Item;

@Controller
public class ItemController {

    @Autowired
    private ItemDao itemDao;

    private List<ItemDto> createItemDtoList(List<Item> itemList) {
        List<ItemDto> itemDtoList = new ArrayList<>();
        for (Item item : itemList) {
            itemDtoList.add(new ItemDto(item));
        }
        return itemDtoList;

    }

    public List<ItemDto> findAll() {
        List<Item> itemList = itemDao.findAll();
        return this.createItemDtoList(itemList);
    }

    public List<ItemDto> find(String name) {
        List<Item> itemList = itemDao.findByName(name);
        return this.createItemDtoList(itemList);
    }

    public int createItem(ItemDto itemDto) {
        Item item = new Item(itemDto.getName(), itemDto.getDescription());
        itemDao.save(item);
        return item.getId();
    }

    public Optional<ItemDto> readItem(int id) {
        Item item = itemDao.findOne(id);
        if (item != null) {
            return Optional.of(new ItemDto(item));
        } else {
            return Optional.empty();
        }
    }

    public void deleteItem(int id) {
        if (itemDao.exists(id)) {
            itemDao.delete(id);

        }
    }

    public Optional<ItemDto> putItem(int id, ItemDto itemDto) {
        Item item = itemDao.findOne(id);
        if (item != null) {
            item.setName(itemDto.getName());
            item.setDescription(itemDto.getDescription());
            itemDao.save(item);
            return Optional.of(new ItemDto(item));
        } else {
            return Optional.empty();
        }

    }

    public Optional<ItemDto> patchItem(int id, String description) {
        Item item = itemDao.findOne(id);
        if (item != null) {
            item.setDescription(description);
            itemDao.save(item);
            return Optional.of(new ItemDto(item));
        } else {
            return Optional.empty();
        }
    }

}
