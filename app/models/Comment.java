package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.avaje.ebean.Model;


/**
 * This class defines one comment
 * */
@Entity(name="post")
public class Comment extends Model {

    @Id
	public Long id;
    
    /* when does the comment created */
    public Date commentAt;
    
    /* who created this comment */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "authorId", referencedColumnName = "id")
    public User author;
    
    /* the content of this comment */
    public String content;
    
    /* when the post is a question,
     * if this comment is selected as answer
     **/
    public boolean isAnswer;
    
    public static Finder<Long, Comment> find = new Finder<Long, Comment>(Comment.class);
    
    public Comment() {
    }
    
    public Comment(
    		Date commentAt,
    		User author,
    		String content,
    		boolean isAnswer
    		) {
    	this.commentAt = commentAt;
    	this.author = author;
    	this.content = content;
    	this.isAnswer = isAnswer;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCommentAt() {
		return commentAt;
	}

	public void setCommentAt(Date commentAt) {
		this.commentAt = commentAt;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isAnswer() {
		return isAnswer;
	}

	public void setAnswer(boolean isAnswer) {
		this.isAnswer = isAnswer;
	}

	public static Finder<Long, Comment> getFind() {
		return find;
	}

	public static void setFind(Finder<Long, Comment> find) {
		Comment.find = find;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", commentAt=" + commentAt + ", author=" + author + ", content=" + content
				+ ", isAnswer=" + isAnswer + "]";
	}
}
