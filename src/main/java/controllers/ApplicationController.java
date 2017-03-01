package controllers;

import ninja.Result;
import ninja.Results;

import java.util.List;
import entity.*;
import javax.persistence.EntityManager;
import com.google.inject.*;
import com.google.inject.persist.*;

@Singleton
public class ApplicationController {

	@Inject
	Provider<EntityManager> entitiyManagerProvider;
	
    public Result index() {

        return Results.html();

    }
	public Result home() {

        return Results.html();

    }
	public Result view() {

        return Results.html();

    }
   
    public Result helloWorldJson() {
        
        SimplePojo simplePojo = new SimplePojo();
        simplePojo.content = "Hello World! Hello Json!";

        return Results.json().render(simplePojo);

    }
    
    public static class SimplePojo {

        public String content;
        
    }
    
    
    @Transactional
	public Result getLastPost() {
    	EntityManager entityManager = entitiyManagerProvider.get();
 		List<Post> l=entityManager.createQuery("from Post order by id DESC").setMaxResults(1).getResultList();		
 		return Results.json().render(l);
	}
    
    @Transactional
	public Result getImageUrl(String imageEncoded64) {
    	System.out.println(imageEncoded64);
	    //add method to store this image on google cloud storage and return the serving image url back		
		return Results.json().render("http://cdn.media.abc.com/m/images/global/butterscotch/abccom_logo.png");
	}
    
    @Transactional
	public Result getPosts() {
	    EntityManager entityManager = entitiyManagerProvider.get();
		List<Post> l=entityManager.createQuery("from Post").getResultList();		
		return Results.json().render(l);
	}
    
    @Transactional
	public Result newPost(Post post) {
	    EntityManager entityManager = entitiyManagerProvider.get();
		entityManager.persist(post);	
		return Results.json().render("success");
	}
}
