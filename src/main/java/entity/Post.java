package entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "posts")
public class Post {
	
	@Id @GeneratedValue
	@Column(name = "id")
	private int id;
	@Column(name = "editorContent")
	private String editorContent;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEditorContent() {
		return editorContent;
	}
	public void setEditorContent(String editorContent) {
		this.editorContent = editorContent;
	}
}
