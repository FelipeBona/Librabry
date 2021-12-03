package br.com.senior.Library.book;

import br.com.senior.Library.exception.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Book")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;

    @PostMapping
    public ResponseEntity<BookDto> addBook(@RequestBody BookDto bookDto) {

        if(bookDto != null){
            final Book bookEntity = bookMapper.toEntity(bookDto);
            final Book bookSaved = bookRepository.save(bookEntity);
            BookDto dto = bookMapper.toDto(bookSaved);

            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } else {
            throw new BookNotFoundException();
        }
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> all(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "author", required = false) String author) {

        List<Book> books = bookRepository.findAll();

        if(name == null && author == null){
            return ResponseEntity.ok().body(bookMapper.toDto(books));
        } else if (name != null && author != null) {
            List<Book> booksSameAuthorAndName = new ArrayList<>();
            for (Book book : books) {

                if (book.getName().toLowerCase().contains(name.toLowerCase()) && book.getAuthor().toLowerCase().equals(author.toLowerCase())) {
                    booksSameAuthorAndName.add(book);
                }
            }
            return new ResponseEntity<>(bookMapper.toDto(booksSameAuthorAndName), HttpStatus.OK);
        } else if (name != null && author == null) {
            List<Book> booksSameName = new ArrayList<>();

            for (Book book : books) {
                if (book.getName().toLowerCase().contains(name.toLowerCase())) {
                    booksSameName.add(book);
                }
            }

            return ResponseEntity.ok().body(bookMapper.toDto(booksSameName));
        } else if (author != null && name == null) {
            List<Book> booksSameAuthor = new ArrayList<>();

            for(Book book : books){
                if (book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                    booksSameAuthor.add(book);
                }
            }
            return new ResponseEntity<>(bookMapper.toDto(booksSameAuthor), HttpStatus.OK);
        }
        return new ResponseEntity<>(bookMapper.toDto(books), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BookDto> getById(@PathVariable(value = "id") Long id) {
        Book book = bookRepository.getById(id);
        if (book != null) {
            BookDto dto = bookMapper.toDto(book);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@RequestBody BookDto newbookDto, @PathVariable(value = "id") Long id){

        if(newbookDto != null && id != null){
            Book book = bookRepository.getById(id);
            book.setName(newbookDto.name);
            book.setAuthor(newbookDto.author);
            book.setCost(newbookDto.cost);

            Book bookSaved = bookRepository.save(book);
            BookDto dto = bookMapper.toDto(bookSaved);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } else{
            throw new BookNotFoundException();
        }

    }

    @DeleteMapping
    public void deletBook(@RequestBody BookDto bookDto) {

        if(bookDto != null){
            Book bookDelet = bookMapper.toEntity(bookDto);
            bookRepository.delete(bookDelet);
        } else{
            throw new BookNotFoundException();
        }
    }
}
