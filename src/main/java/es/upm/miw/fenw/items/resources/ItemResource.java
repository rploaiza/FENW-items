package es.upm.miw.fenw.items.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.upm.miw.fenw.items.controllers.ItemController;
import es.upm.miw.fenw.items.dtos.ItemDto;
import es.upm.miw.fenw.items.resources.exceptions.ItemIdNotFoundException;

@RestController
@RequestMapping(ItemResource.ITEMS)
public class ItemResource {

    public static final String ITEMS = "items";
    
    public static final String ID = "/{id}";

    private ItemController itemController;

    @Autowired
    public void setItemController(ItemController itemController) {
        this.itemController = itemController;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Integer createItem(@RequestBody ItemDto itemDto) {
        return itemController.createItem(itemDto);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<ItemDto> find(@RequestParam(required = false) String name) {
        if (name == null) {
            return itemController.findAll();
        } else {
            return itemController.find(name);
        }
    }

    @RequestMapping(value = ID, method = RequestMethod.GET)
    public ItemDto readItem(@PathVariable int id) throws ItemIdNotFoundException {
        return itemController.readItem(id).orElseThrow(() -> new ItemIdNotFoundException(Integer.toString(id)));
    }

    @RequestMapping(value = ID, method = RequestMethod.PUT)
    public ItemDto putItem(@PathVariable int id, @RequestBody ItemDto itemDto) throws ItemIdNotFoundException {
        return itemController.putItem(id, itemDto).orElseThrow(() -> new ItemIdNotFoundException(Integer.toString(id)));
    }

    @RequestMapping(value = ID, method = RequestMethod.PATCH)
    public ItemDto patchItem(@PathVariable int id, @RequestBody ItemDto itemDto) throws ItemIdNotFoundException {
        return itemController.patchItem(id, itemDto.getDescription()).orElseThrow(() -> new ItemIdNotFoundException(Integer.toString(id)));
    }

    @RequestMapping(value =ID, method = RequestMethod.DELETE)
    public void deleteItem(@PathVariable int id) {
        itemController.deleteItem(id);
    }

}
