package conf;
import ninja.Result;
import ninja.Results;

import com.google.inject.Singleton;


import ninja.AssetsController;
import ninja.Router;
import ninja.application.ApplicationRoutes;
import controllers.ApplicationController;


public class Routes implements ApplicationRoutes {

    @Override
    public void init(Router router) {  
        
        router.GET().route("/").with(ApplicationController::index);
        router.GET().route("/hello_world.json").with(ApplicationController::helloWorldJson);
        router.GET().route("/home").with(ApplicationController::home);

        router.GET().route("/viewPosts").with(ApplicationController::view);
        router.GET().route("/getPosts").with(ApplicationController::getPosts);
        router.POST().route("/newPost").with(ApplicationController::newPost);
        router.POST().route("/getImageUrl").with(ApplicationController::getImageUrl);
        router.GET().route("/getLastPost").with(ApplicationController::getLastPost);

 
        ///////////////////////////////////////////////////////////////////////
        // Assets (pictures / javascript)
        ///////////////////////////////////////////////////////////////////////    
        router.GET().route("/assets/webjars/{fileName: .*}").with(AssetsController::serveWebJars);
        router.GET().route("/assets/{fileName: .*}").with(AssetsController::serveStatic);
        
        ///////////////////////////////////////////////////////////////////////
        // Index / Catchall shows index page
        ///////////////////////////////////////////////////////////////////////
        router.GET().route("/.*").with(ApplicationController::index);
    }

}
