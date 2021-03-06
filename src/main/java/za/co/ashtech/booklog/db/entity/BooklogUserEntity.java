package za.co.ashtech.booklog.db.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;


/**
 * The persistent class for the booklog_user database table.
 * 
 */
@Entity
@Table(name="booklog_user")
@NamedQuery(name="BooklogUserEntity.findAll", query="SELECT b FROM BooklogUserEntity b")
public class BooklogUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="BOOKLOG_USER_ID")
	private int booklogUserId;

	private byte enabled;

	private String password;

	private String username;

	//bi-directional many-to-one association to UserRoleEntity
	@OneToMany(mappedBy="booklogUser", cascade = CascadeType.ALL)
	@Fetch(FetchMode.SUBSELECT)
	private List<UserRoleEntity> booklogRoles;

	public BooklogUserEntity() {
	}

	public int getBooklogUserId() {
		return this.booklogUserId;
	}

	public void setBooklogUserId(int booklogUserId) {
		this.booklogUserId = booklogUserId;
	}

	public byte getEnabled() {
		return this.enabled;
	}

	public void setEnabled(byte enabled) {
		this.enabled = enabled;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<UserRoleEntity> getBooklogRoles() {
		return this.booklogRoles;
	}

	public void setBooklogRoles(List<UserRoleEntity> booklogRoles) {
		this.booklogRoles = booklogRoles;
	}

	public UserRoleEntity addBooklogRole(UserRoleEntity booklogRole) {
		getBooklogRoles().add(booklogRole);
		booklogRole.setBooklogUser(this);

		return booklogRole;
	}

	public UserRoleEntity removeBooklogRole(UserRoleEntity booklogRole) {
		getBooklogRoles().remove(booklogRole);
		booklogRole.setBooklogUser(null);

		return booklogRole;
	}

}