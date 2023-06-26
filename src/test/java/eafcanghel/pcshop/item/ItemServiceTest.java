package eafcanghel.pcshop.item;

import eafcanghel.pcshop.category.Category;
import eafcanghel.pcshop.category.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private ItemService itemService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllItems() {
        Page<Item> expectedItemsPage = mock(Page.class);
        when(itemRepository.findAll(any(Pageable.class))).thenReturn(expectedItemsPage);

        Page<Item> actualItemsPage = itemService.getAllItems(mock(Pageable.class));

        assertEquals(expectedItemsPage, actualItemsPage);
        verify(itemRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    void testGetAllCategories() {
        List<Category> expectedCategories = Collections.singletonList(mock(Category.class));
        when(categoryRepository.findAll()).thenReturn(expectedCategories);

        List<Category> actualCategories = itemService.getAllCategories();

        assertEquals(expectedCategories, actualCategories);
        verify(categoryRepository, times(1)).findAll();
    }

}
