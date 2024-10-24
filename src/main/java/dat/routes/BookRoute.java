package dat.routes;

import dat.controllers.impl.BookController;
import dat.security.enums.Role;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;

public class BookRoute {

    private final BookController bookController = new BookController();

    protected EndpointGroup getRoutes() {
        return () -> {
            post("/", bookController::create, Role.ADMIN);
            get("/", bookController::readAll, Role.ANYONE);
            get("/{id}", bookController::read, Role.ANYONE);
            get("genre/{genre}", bookController::readGenre, Role.ANYONE);
            get("title/{title}", bookController::readTitle, Role.ANYONE);
            get("year/{year}", bookController::readYear, Role.ANYONE);
            get("author/{author}", bookController::readAuthor, Role.ANYONE);
            put("/{id}", bookController::update, Role.ADMIN);
            delete("/{id}", bookController::delete, Role.ADMIN);
        };
    }
}
