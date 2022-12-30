package dev.leonardaprk.springbootelasticsearch.controller;

import dev.leonardaprk.springbootelasticsearch.exception.BookNotFoundException;
import dev.leonardaprk.springbootelasticsearch.exception.DuplicateIsbnException;
import dev.leonardaprk.springbootelasticsearch.model.Book;
import dev.leonardaprk.springbootelasticsearch.model.BookDto;
import dev.leonardaprk.springbootelasticsearch.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
  @Autowired
  private BookService bookService;

  @GetMapping
  public List<Book> getAllBooks() {
    return bookService.getAll();
  }

  @PostMapping
  public Book createBook(@RequestBody BookDto book) throws DuplicateIsbnException {
    return bookService.create(BookDto.transform(book));
  }

  @GetMapping(value = "/{isbn}")
  public Book getBookByIsbn(@PathVariable String isbn) throws BookNotFoundException {
    return bookService.getByIsbn(isbn).orElseThrow(() -> new BookNotFoundException("The given isbn is invalid"));
  }

  @GetMapping(value = "/query")
  public List<Book> getBooksByAuthorAndTitle(@RequestParam(value = "title") String title, @RequestParam(value = "author") String author) {
    return bookService.findByTitleAndAuthor(title, author);
  }

  @PutMapping(value = "/{id}")
  public Book updateBook(@PathVariable String id, @RequestBody BookDto book) throws BookNotFoundException {
    return bookService.update(id, BookDto.transform(book));
  }

  @DeleteMapping(value = "/{id}")
  public String deleteBook(@PathVariable String id) {
    bookService.deleteById(id);
    return "success";
  }
}
