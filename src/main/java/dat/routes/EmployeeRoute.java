package dat.routes;

import dat.controllers.impl.EmployeeController;
import dat.security.enums.Role;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;
import static io.javalin.apibuilder.ApiBuilder.delete;

public class EmployeeRoute {

    private final EmployeeController employeeController = new EmployeeController();

    protected EndpointGroup getRoutes() {
        return () -> {
            post("/", employeeController::create, Role.ANYONE);
            get("/", employeeController::readAll, Role.ANYONE);
            get("/{id}", employeeController::read, Role.ANYONE);
            put("/{id}", employeeController::update, Role.ADMIN);
            delete("/{id}", employeeController::delete, Role.ADMIN);
        };
    }
}
