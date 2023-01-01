package dev.leonardaprk.springbootelasticsearch.service.impl;

import com.github.vanroy.springdata.jest.JestElasticsearchTemplate;
import dev.leonardaprk.springbootelasticsearch.exception.BookNotFoundException;
import dev.leonardaprk.springbootelasticsearch.exception.DuplicateIsbnException;
import dev.leonardaprk.springbootelasticsearch.model.Book;
import dev.leonardaprk.springbootelasticsearch.repository.BookRepository;
import dev.leonardaprk.springbootelasticsearch.service.BookService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

  @Autowired
  private BookRepository bookRepository;

//  @Autowired
//  private JestElasticsearchTemplate jestElasticsearchTemplate;

  @Override
  public Optional<Book> getByIsbn(String isbn) {
    return bookRepository.findByIsbn(isbn);
  }

  @Override
  public List<Book> getAll() {
    return new ArrayList<>() {{
      bookRepository.findAll().forEach(this::add);
    }};
  }

  @Override
  public List<Book> findByAuthor(String authorName) {
    return bookRepository.findByAuthorName(authorName);
  }

  @Override
  public List<Book> findByTitleAndAuthor(String title, String author) {
//    BoolQueryBuilder criteria = QueryBuilders.boolQuery();
//    criteria.must().addAll(List.of(QueryBuilders.matchQuery("authorName", author), QueryBuilders.matchQuery("title", title)));
//    return jestElasticsearchTemplate.queryForList(new NativeSearchQueryBuilder().withQuery(criteria).build(), Book.class);
    return null;
  }

  @Override
  public Book create(Book book) throws DuplicateIsbnException {
    if (getByIsbn(book.getIsbn()).isPresent()) {
      throw new DuplicateIsbnException(String.format("The provided ISBN: %s already exists. Use update instead!", book.getIsbn()));
    }
    return bookRepository.save(book);
  }

  @Override
  public void deleteById(String id) {
    bookRepository.deleteById(id);
  }

  @Override
  public Book update(String id, Book book) throws BookNotFoundException {
    Book oldBook = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("There is not book associated with the given id"));
    oldBook.setTitle(book.getTitle());
    oldBook.setPublicationYear(book.getPublicationYear());
    oldBook.setAuthorName(book.getAuthorName());
    oldBook.setIsbn(book.getIsbn());
    return bookRepository.save(oldBook);
  }
}
