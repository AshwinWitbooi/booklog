package za.co.ashtech.booklog.model;

import java.util.Date;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Editing
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-01-31T22:30:54.422Z[GMT]")


public class Editing   {
  /**
   * The update action to perform
   */
  public enum ActionEnum {
    EAF("EAF"),
    
    EAL("EAL"),
    
    ET("ET"),
    
    AA("AA"),
    
    EP("EP"),
    
    EPD("EPD");

    private String value;

    ActionEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static ActionEnum fromValue(String text) {
      for (ActionEnum b : ActionEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("action")
  private ActionEnum action = null;

  @JsonProperty("newFirstname")
  private String newFirstname = null;

  @JsonProperty("oldFirstname")
  private String oldFirstname = null;

  @JsonProperty("newLastname")
  private String newLastname = null;

  @JsonProperty("oldLastname")
  private String oldLastname = null;

  @JsonProperty("newTitle")
  private String newTitle = null;

  @JsonProperty("newPublisher")
  private String newPublisher = null;

  @JsonProperty("newPublishDate")
  private Date newPublishDate = null;

  public Editing action(ActionEnum action) {
    this.action = action;
    return this;
  }

  /**
   * The update action to perform
   * @return action
   **/
  @Schema(example = "EA", required = true, description = "The update action to perform")
      @NotNull

    public ActionEnum getAction() {
    return action;
  }

  public void setAction(ActionEnum action) {
    this.action = action;
  }

  public Editing newFirstname(String newFirstname) {
    this.newFirstname = newFirstname;
    return this;
  }

  /**
   * New first name of author
   * @return newFirstname
   **/
  @Schema(example = "John", description = "New first name of author")
  
    public String getNewFirstname() {
    return newFirstname;
  }

  public void setNewFirstname(String newFirstname) {
    this.newFirstname = newFirstname;
  }

  public Editing oldFirstname(String oldFirstname) {
    this.oldFirstname = oldFirstname;
    return this;
  }

  /**
   * Old first name of author
   * @return oldFirstname
   **/
  @Schema(example = "Rye", description = "Old first name of author")
  
    public String getOldFirstname() {
    return oldFirstname;
  }

  public void setOldFirstname(String oldFirstname) {
    this.oldFirstname = oldFirstname;
  }

  public Editing newLastname(String newLastname) {
    this.newLastname = newLastname;
    return this;
  }

  /**
   * New last name of author
   * @return newLastname
   **/
  @Schema(example = "Langley", description = "New last name of author")
  
    public String getNewLastname() {
    return newLastname;
  }

  public void setNewLastname(String newLastname) {
    this.newLastname = newLastname;
  }

  public Editing oldLastname(String oldLastname) {
    this.oldLastname = oldLastname;
    return this;
  }

  /**
   * Old last name of author
   * @return oldLastname
   **/
  @Schema(example = "Dryer", description = "Old last name of author")
  
    public String getOldLastname() {
    return oldLastname;
  }

  public void setOldLastname(String oldLastname) {
    this.oldLastname = oldLastname;
  }

  public Editing newTitle(String newTitle) {
    this.newTitle = newTitle;
    return this;
  }

  /**
   * New title of book
   * @return newTitle
   **/
  @Schema(example = "You can't hurt me", description = "New title of book")
  
    public String getNewTitle() {
    return newTitle;
  }

  public void setNewTitle(String newTitle) {
    this.newTitle = newTitle;
  }

  public Editing newPublisher(String newPublisher) {
    this.newPublisher = newPublisher;
    return this;
  }

  /**
   * New publisher
   * @return newPublisher
   **/
  @Schema(example = "Dryer Co Publishing", description = "New publisher")
  
    public String getNewPublisher() {
    return newPublisher;
  }

  public void setNewPublisher(String newPublisher) {
    this.newPublisher = newPublisher;
  }

  public Editing newPublishDate(Date newPublishDate) {
    this.newPublishDate = newPublishDate;
    return this;
  }

  /**
   * New publish date
   * @return newPublishDate
   **/
  @Schema(example = "Mon Aug 29 00:00:00 GMT 2016", description = "New publish date")
  
    @Valid
    public Date getNewPublishDate() {
    return newPublishDate;
  }

  public void setNewPublishDate(Date newPublishDate) {
    this.newPublishDate = newPublishDate;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Editing editing = (Editing) o;
    return Objects.equals(this.action, editing.action) &&
        Objects.equals(this.newFirstname, editing.newFirstname) &&
        Objects.equals(this.oldFirstname, editing.oldFirstname) &&
        Objects.equals(this.newLastname, editing.newLastname) &&
        Objects.equals(this.oldLastname, editing.oldLastname) &&
        Objects.equals(this.newTitle, editing.newTitle) &&
        Objects.equals(this.newPublisher, editing.newPublisher) &&
        Objects.equals(this.newPublishDate, editing.newPublishDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(action, newFirstname, oldFirstname, newLastname, oldLastname, newTitle, newPublisher, newPublishDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Editing {\n");
    
    sb.append("    action: ").append(toIndentedString(action)).append("\n");
    sb.append("    newFirstname: ").append(toIndentedString(newFirstname)).append("\n");
    sb.append("    oldFirstname: ").append(toIndentedString(oldFirstname)).append("\n");
    sb.append("    newLastname: ").append(toIndentedString(newLastname)).append("\n");
    sb.append("    oldLastname: ").append(toIndentedString(oldLastname)).append("\n");
    sb.append("    newTitle: ").append(toIndentedString(newTitle)).append("\n");
    sb.append("    newPublisher: ").append(toIndentedString(newPublisher)).append("\n");
    sb.append("    newPublishDate: ").append(toIndentedString(newPublishDate)).append("\n");
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
