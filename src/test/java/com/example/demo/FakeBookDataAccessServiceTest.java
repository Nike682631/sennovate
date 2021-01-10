package com.example.demo;

import com.example.demo.api.BookController;
import com.example.demo.model.Book;
import com.example.demo.repository.FakeBookDataAccessService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;


public class FakeBookDataAccessServiceTest {

    private MockMvc mvc;

    @Mock
    private BookController controller;
    @InjectMocks
    private FakeBookDataAccessService underTest;


    @Before
    public void setUp() throws Exception{
        mvc = MockMvcBuilders.standaloneSetup(controller)
                .build();
        underTest = new FakeBookDataAccessService();
    }

    @Test
    public void canPerformCrud() {
        // Given book called James Bond
        UUID idOne = UUID.randomUUID();
        Book bookOne = new Book(idOne, "James Bond");

        // ...And harry potter
        UUID idTwo = UUID.randomUUID();
        Book bookTwo = new Book(idTwo, "harry potter");

        // When James and harry added to db
        underTest.insertBook(idOne, bookOne);
        underTest.insertBook(idTwo, bookTwo);

        // Then can retrieve James by id
        assertThat(underTest.selectBookById(idOne))
                .isPresent()
                .hasValueSatisfying(BookFromDb -> assertThat(BookFromDb).isEqualToComparingFieldByField(bookOne));

        // ...And also harry by id
        assertThat(underTest.selectBookById(idTwo))
                .isPresent()
                .hasValueSatisfying(BookFromDb -> assertThat(BookFromDb).isEqualToComparingFieldByField(bookTwo));

        // When get all books
        List<Book> books = underTest.selectAllBooks();

        // ...List should have size 2 and should have both James and harry
        assertThat(books)
                .hasSize(2)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(bookOne, bookTwo);

        // ... An update request (James Bond book name to Jake Black)
        Book bookUpdate = new Book(idOne, "Jake Black");

        // When Update
        assertThat(underTest.updateBookById(idOne, bookUpdate)).isEqualTo(1);

        // Then when get book with idOne then should have name as James Bond > Jake Black
        assertThat(underTest.selectBookById(idOne))
                .isPresent()
                .hasValueSatisfying(bookFromDb -> assertThat(bookFromDb).isEqualToComparingFieldByField(bookUpdate));

        // When Delete Jake Black
        assertThat(underTest.deleteBookById(idOne)).isEqualTo(1);

        // When get bookOne should be empty
        assertThat(underTest.selectBookById(idOne)).isEmpty();

        // Finally DB should only contain only harry potter
        assertThat(underTest.selectAllBooks())
                .hasSize(1)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(bookTwo);
    }

    @Test
    public void willReturn0IfNobookFoundToDelete() {
        // Given
        UUID id = UUID.randomUUID();

        // When
        int deleteResult = underTest.deleteBookById(id);

        // Then
        assertThat(deleteResult).isEqualTo(0);
    }

    @Test
    public void willReturn0IfNobookFoundToUpdate() {
        // Given
        UUID id = UUID.randomUUID();
        Book book = new Book(id, "James Not In Db");

        // When
        int deleteResult = underTest.updateBookById(id, book);

        // Then
        assertThat(deleteResult).isEqualTo(0);
    }
}
