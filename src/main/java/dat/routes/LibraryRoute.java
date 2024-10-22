package dat.routes;

import dat.controllers.impl.EmployeeController;
import dat.controllers.impl.LibraryController;
import dat.security.enums.Role;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;
import static io.javalin.apibuilder.ApiBuilder.delete;

public class LibraryRoute {

    private final LibraryController libraryController = new LibraryController();

    protected EndpointGroup getRoutes() {
        return () -> {
            post("/", libraryController::create, Role.ANYONE);
            get("/", libraryController::readAll, Role.ANYONE);
            get("/{id}", libraryController::read, Role.ANYONE);
            put("/{id}", libraryController::update, Role.ADMIN);
            delete("/{id}", libraryController::delete, Role.ADMIN);
        };
    }
}
