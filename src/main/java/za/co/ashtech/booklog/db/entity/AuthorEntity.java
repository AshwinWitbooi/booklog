package za.co.ashtech.booklog.db.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the author database table.
 * 
 */
@Entity
@NamedQuery(name="Author.findAll", query="SELECT a FROM Author a")
public class AuthorEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String firstname;

	private String lastname;

	//bi-directional many-to-one association to Book
	@ManyToOne
	private BookEntity book;

	public AuthorEntity() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public BookEntity getBook() {
		return this.book;
	}

	public void setBook(BookEntity book) {
		this.book = book;
	}

}