package dat.routes;

import dat.controllers.impl.LendController;
import dat.security.enums.Role;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;
import static io.javalin.apibuilder.ApiBuilder.delete;

public class LendRoute {

    private final LendController lendController = new LendController();

    protected EndpointGroup getRoutes() {
        return () -> {
            //Lend a book with id
            post("/{id}", lendController::create, Role.USER);
            //Get all lendings
            get("/", lendController::readAll, Role.ADMIN);
            //Get a lending with id
            get("/{id}", lendController::read, Role.ADMIN);
            //Get the user's lending
            get("/user", lendController::readUserLends, Role.USER);
            //Update a lending with id
            put("/{id}", lendController::update, Role.ADMIN);
            //Delete a lending with id
            delete("/{id}", lendController::delete, Role.ADMIN);
        };
    }

}
