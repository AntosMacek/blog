package models;

import play.db.ebean.*;
import javax.persistence.*;

/**
 * Created by antoha on 10/17/16.
 */
public class Post extends Model {

    @Id
    public Long id;
    public String title;
    public String author;
//    @ManyToMany(cascade = CascadeType.REMOVE)
//    public List<KeyWord> keywords = new ArrayList<KeyWord>();


    public Post(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public static Model.Finder<Long, Post> find = new Model.Finder(Long.class, Post.class);

    public static Post create(String title, String author) {
        Post post = new Post(title, author);
        post.save();
        return post;
    }

}
