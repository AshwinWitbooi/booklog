package za.co.ashtech.booklog.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Book
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-01-13T22:22:42.952Z[GMT]")


public class Book   {
  @JsonProperty("title")
  private String title = null;

  @JsonProperty("publisher")
  private String publisher = null;

  @JsonProperty("publishDate")
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date publishDate = null;

  @JsonProperty("ISBN")
  private String ISBN = null;

  @JsonProperty("authors")
  @Valid
  private List<Author> authors = new ArrayList<Author>();

  public Book title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
   **/
  @Schema(example = "You are your fears", required = true, description = "")
      @NotNull

    public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Book publisher(String publisher) {
    this.publisher = publisher;
    return this;
  }

  /**
   * Get publisher
   * @return publisher
   **/
  @Schema(example = "Ashtech Publishing Co", required = true, description = "")
      @NotNull

    public String getPublisher() {
    return publisher;
  }

  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  public Book publishDate(Date publishDate) {
    this.publishDate = publishDate;
    return this;
  }

  /**
   * Get publishDate
   * @return publishDate
   **/
  @Schema(example = "Mon Aug 29 00:00:00 GMT 2016", required = true, description = "")
      @NotNull

    @Valid
    public Date getPublishDate() {
    return publishDate;
  }

  public void setPublishDate(Date publishDate) {
    this.publishDate = publishDate;
  }

  public Book ISBN(String ISBN) {
    this.ISBN = ISBN;
    return this;
  }

  /**
   * Get ISBN
   * @return ISBN
   **/
  @Schema(example = "978-3-16-148410-0", required = true, description = "")
      @NotNull

    public String getISBN() {
    return ISBN;
  }

  public void setISBN(String ISBN) {
    this.ISBN = ISBN;
  }

  public Book authors(List<Author> authors) {
    this.authors = authors;
    return this;
  }

  public Book addAuthorsItem(Author authorsItem) {
    this.authors.add(authorsItem);
    return this;
  }

  /**
   * Get authors
   * @return authors
   **/
  @Schema(required = true, description = "")
      @NotNull
    @Valid
    public List<Author> getAuthors() {
    return authors;
  }

  public void setAuthors(List<Author> authors) {
    this.authors = authors;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Book book = (Book) o;
    return Objects.equals(this.title, book.title) &&
        Objects.equals(this.publisher, book.publisher) &&
        Objects.equals(this.publishDate, book.publishDate) &&
        Objects.equals(this.ISBN, book.ISBN) &&
        Objects.equals(this.authors, book.authors);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, publisher, publishDate, ISBN, authors);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Book {\n");
    
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    publisher: ").append(toIndentedString(publisher)).append("\n");
    sb.append("    publishDate: ").append(toIndentedString(publishDate)).append("\n");
    sb.append("    ISBN: ").append(toIndentedString(ISBN)).append("\n");
    sb.append("    authors: ").append(toIndentedString(authors)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
