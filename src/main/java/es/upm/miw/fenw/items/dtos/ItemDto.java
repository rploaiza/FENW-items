package es.upm.miw.fenw.items.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import es.upm.miw.fenw.items.entities.Item;

public class ItemDto {

    private int id;

    private String name;

    @JsonInclude(Include.NON_NULL)
    private String description;

    public ItemDto() {
    }

    public ItemDto(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public ItemDto(Item item) {
        id = item.getId();
        name = item.getName();
        description = item.getDescription();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ItemDto[" + id + ": name=" + name + ", description=" + description + "]";
    }

}
