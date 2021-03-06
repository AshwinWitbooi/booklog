package za.co.ashtech.booklog.db.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the booklog_roles database table.
 * 
 */
@Entity
@Table(name="booklog_roles")
@NamedQuery(name="UserRoleEntity.findAll", query="SELECT u FROM UserRoleEntity u")
public class UserRoleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="BOOKLOG_ROLES_ID")
	private int booklogRolesId;

	private String authority;

	//bi-directional many-to-one association to BooklogUserEntity
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private BooklogUserEntity booklogUser;

	public UserRoleEntity() {
	}

	public int getBooklogRolesId() {
		return this.booklogRolesId;
	}

	public void setBooklogRolesId(int booklogRolesId) {
		this.booklogRolesId = booklogRolesId;
	}

	public String getAuthority() {
		return this.authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public BooklogUserEntity getBooklogUser() {
		return this.booklogUser;
	}

	public void setBooklogUser(BooklogUserEntity booklogUser) {
		this.booklogUser = booklogUser;
	}

}