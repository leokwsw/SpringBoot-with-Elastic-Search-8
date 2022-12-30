package dev.leonardaprk.springbootelasticsearch.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookDto {
  private String title;
  private Integer publicationYear;
  private String authorName;
  private String isbn;

  public static Book transform(BookDto bookDto) {
    Book book = new Book();
    book.setTitle(bookDto.title);
    book.setPublicationYear(bookDto.publicationYear);
    book.setAuthorName(bookDto.authorName);
    book.setIsbn(bookDto.isbn);
    return book;
  }
}
