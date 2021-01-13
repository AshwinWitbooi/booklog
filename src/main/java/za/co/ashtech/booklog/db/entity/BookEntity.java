package za.co.ashtech.booklog.db.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the book database table.
 * 
 */
@Entity
@NamedQuery(name="Book.findAll", query="SELECT b FROM Book b")
public class BookEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATE_DATE")
	private Date createDate;

	private String isbn;

	@Temporal(TemporalType.DATE)
	@Column(name="PUBLISH_DATE")
	private Date publishDate;

	private String publisher;

	private String title;

	//bi-directional many-to-one association to Author
	@OneToMany(mappedBy="book")
	private List<AuthorEntity> authors;

	public BookEntity() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Date getPublishDate() {
		return this.publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public String getPublisher() {
		return this.publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<AuthorEntity> getAuthors() {
		return this.authors;
	}

	public void setAuthors(List<AuthorEntity> authors) {
		this.authors = authors;
	}

	public AuthorEntity addAuthor(AuthorEntity author) {
		getAuthors().add(author);
		author.setBook(this);

		return author;
	}

	public AuthorEntity removeAuthor(AuthorEntity author) {
		getAuthors().remove(author);
		author.setBook(null);

		return author;
	}

}