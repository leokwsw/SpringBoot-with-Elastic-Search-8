package dev.leonardaprk.springbootelasticsearch.service;

import dev.leonardaprk.springbootelasticsearch.exception.BookNotFoundException;
import dev.leonardaprk.springbootelasticsearch.exception.DuplicateIsbnException;
import dev.leonardaprk.springbootelasticsearch.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
  Optional<Book> getByIsbn(String isbn);

  List<Book> getAll();

  List<Book> findByAuthor(String authorName);

  List<Book> findByTitleAndAuthor(String title, String author);

  Book create(Book book) throws DuplicateIsbnException;

  void deleteById(String id);

  Book update(String id, Book book) throws BookNotFoundException;
}
