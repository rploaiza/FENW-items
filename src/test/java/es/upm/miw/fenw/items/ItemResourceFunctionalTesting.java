package es.upm.miw.fenw.items;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import es.upm.miw.fenw.items.dtos.ItemDto;
import es.upm.miw.fenw.items.resources.ItemResource;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test.properties")
public class ItemResourceFunctionalTesting {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Value("${local.server.port}")
    private int port;

    @Value("${server.contextPath}")
    private String contextPath;

    private int createItem(String name) {
        ItemDto itemDto = new ItemDto(name, name + "...");
        return new RestBuilder<Integer>(port).path(contextPath).path(ItemResource.ITEMS).body(itemDto).clazz(Integer.class).post().build();
    }

    private int createItem() {
        return this.createItem("uno");
    }

    @Test
    public void testReadItems() {
        List<ItemDto> itemDtoList = Arrays
                .asList(new RestBuilder<ItemDto[]>(port).path(contextPath).path(ItemResource.ITEMS).clazz(ItemDto[].class).get().build());
        assertEquals(0, itemDtoList.size());
        int id = this.createItem();
        itemDtoList = Arrays.asList(new RestBuilder<ItemDto[]>(port).path(contextPath).path(ItemResource.ITEMS).clazz(ItemDto[].class).get().build());
        assertEquals(1, itemDtoList.size());
        this.deleteItem(id);
    }

    @Test
    public void testReadItemsFilter() {
        int id = this.createItem();
        int id2 = this.createItem("dos");
        List<ItemDto> itemDtoList = Arrays.asList(
                new RestBuilder<ItemDto[]>(port).path(contextPath).path(ItemResource.ITEMS).param("name", "uno").clazz(ItemDto[].class).get().build());
        assertEquals(1, itemDtoList.size());
        this.deleteItem(id);
        this.deleteItem(id2);
    }

    @Test
    public void testReadItem() {
        int id = this.createItem();
        ItemDto itemDto = new RestBuilder<ItemDto>(port).path(contextPath).path(ItemResource.ITEMS).path(ItemResource.ID).expand(id)
                .clazz(ItemDto.class).get().build();
        assertEquals("uno", itemDto.getName());
        assertEquals("uno...", itemDto.getDescription());
        this.deleteItem(id);
    }

    @Test
    public void testReadItemWithNonExistentItem() {
        thrown.expect(new HttpMatcher(HttpStatus.NOT_FOUND));
        new RestBuilder<Object>(port).path(contextPath).path(ItemResource.ITEMS).path(ItemResource.ID).expand(666).get().build();
    }

    @Test
    public void putItem() {
        int id = this.createItem();
        ItemDto itemDto = new ItemDto("uno bis", "uno... bis");
        itemDto = new RestBuilder<ItemDto>(port).path(contextPath).path(ItemResource.ITEMS).path(ItemResource.ID).expand(id).body(itemDto)
                .clazz(ItemDto.class).put().build();
        assertEquals("uno bis", itemDto.getName());
        assertEquals("uno... bis", itemDto.getDescription());
        this.deleteItem(id);
    }

    @Test
    public void patchItem() {
        int id = this.createItem();
        ItemDto itemDto = new ItemDto("uno NOT bis", "uno... bis");
        itemDto = new RestBuilder<ItemDto>(port).path(contextPath).path(ItemResource.ITEMS).path(ItemResource.ID).expand(id).body(itemDto)
                .clazz(ItemDto.class).patch().build();
        assertEquals("uno", itemDto.getName());
        assertEquals("uno... bis", itemDto.getDescription());
        this.deleteItem(id);
    }

    @Test
    public void deleteItem() {
        this.deleteItem(666);
    }

    private void deleteItem(int id) {
        new RestBuilder<Object>(port).path(contextPath).path(ItemResource.ITEMS).path(ItemResource.ID).expand(id).delete().build();
    }

}
