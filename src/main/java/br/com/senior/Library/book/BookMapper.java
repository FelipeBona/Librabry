package br.com.senior.Library.book;

import br.com.senior.Library.book.BookDto;
import br.com.senior.Library.book.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookMapper {

    public BookDto toDto (Book book){
        final BookDto dto = new BookDto();
        dto.id = book.getId();
        dto.name = book.getName();
        dto.author = book.getAuthor();
        dto.cost = book.getCost();

        return dto;
    }

    public List<BookDto> toDto(List<Book> books){
        final ArrayList<BookDto> booksDto = new ArrayList<>();
        for(Book book : books){
            booksDto.add(toDto(book));
        }
        return booksDto;
    }

    public Book toEntity(BookDto dto){
        final Book book = new Book();
        book.setId(dto.id);
        book.setName(dto.name);
        book.setAuthor(dto.author);
        book.setCost(dto.cost);

        return book;
    }
}
