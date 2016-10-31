package controllers;

import play.*;
import play.mvc.*;
import play.data.*;

import static play.data.Form.*;

import models.*;
import views.html.*;

import java.util.Collections;
import java.util.List;

public class Application extends Controller {

    public static Result index() {
        List<Post> posts = Post.find.all();
        Collections.reverse(posts);
        return ok(index.render(
                posts
        ));
    }

    public static Result bio() {
        return ok(bio.render());
    }

    @Security.Authenticated(Secured.class)
    public static Result write() {
        return ok(
                write.render(
                        Form.form(Post.class),
                        User.find.byId(request().username())
                ));
    }

    public static Result writing() {
        return ok(
                write.render(
                        Form.form(Post.class),
                        User.find.byId(request().username())
                ));
    }

    public static Result login() {
        return ok(
                login.render(
                        Form.form(Login.class)
                ));
    }

    public static Result logout() {
        session().clear();
        flash("success", "You've been logged out");
        return redirect(
                routes.Application.login()
        );
    }

    public static Result authenticate() {
        Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
        if (loginForm.hasErrors()) {
            return badRequest(
                    login.render(
                            loginForm
                    ));
        } else {
            session().clear();
            session("email", loginForm.get().email);
            return redirect(
                    routes.Application.write()
            );
        }
    }

    static Form<Post> postForm = Form.form(Post.class);

    public static Result newPost() {
        Form<Post> filledForm = postForm.bindFromRequest();
        if(filledForm.hasErrors()) {
            return badRequest(
                    write.render(
                            Form.form(Post.class),
                            User.find.byId(request().username())
                    ));
        } else {
            Post.create(filledForm.get());
            return redirect(routes.Application.index());
        }
    }


    public static class Login {

        public String email;
        public String password;

        public String validate() {
            if (User.authenticate(email, password) == null) {
                return "Invalid user or password";
            }
            return null;
        }

    }

}