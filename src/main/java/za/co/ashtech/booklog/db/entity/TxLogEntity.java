package za.co.ashtech.booklog.db.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tx_log database table.
 * 
 */
@Entity
@Table(name="tx_log")
@NamedQuery(name="TxLogEntity.findAll", query="SELECT t FROM TxLogEntity t")
public class TxLogEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String action;

	@Column(name="ACTION_DATE")
	private Date actionDate;

	@Column(name="ACTION_RESULT")
	private String actionResult;

	private String username;

	public TxLogEntity() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Date getActionDate() {
		return this.actionDate;
	}

	public void setActionDate(Date actionDate) {
		this.actionDate = actionDate;
	}

	public String getActionResult() {
		return this.actionResult;
	}

	public void setActionResult(String actionResult) {
		this.actionResult = actionResult;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}