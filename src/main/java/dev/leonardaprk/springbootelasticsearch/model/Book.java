package dev.leonardaprk.springbootelasticsearch.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "books")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
  @Id
  private String id;

  private String title;

  private int publicationYear;

  private String authorName;

  private String isbn;
}
