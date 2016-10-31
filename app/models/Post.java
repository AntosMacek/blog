package models;

import play.db.ebean.*;
import javax.persistence.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by antoha on 10/17/16.
 */
@Entity
public class Post extends Model {

    @Id
    public Long id;
    public String title;
    public String author;
    @Column(columnDefinition = "TEXT")
    public String content;
    public String date;
//    @ManyToMany(cascade = CascadeType.REMOVE)
//    public List<KeyWord> keywords = new ArrayList<KeyWord>();


    public Post(String title, String author, String content) {
        this.title = title;
        this.author = author;
        this.content = content;
        Date d = new Date();
        date = new Timestamp(d.getTime()).toString();
    }

    public static Model.Finder<Long, Post> find = new Model.Finder(Long.class, Post.class);

    public static Post create(Post post) {

        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yy");
        Date today = new Date();
        String output = formatter.format(today);

        post.date = output;
        post.author = "Antoha";
        post.save();
        return post;
    }

}
