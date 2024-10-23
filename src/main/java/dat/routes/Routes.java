package dat.routes;

import dat.entities.Library;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.path;

public class Routes {

    private final BookRoute bookRoute = new BookRoute();
    private final LendRoute lendRoute = new LendRoute();
    private final UserRoute userRoute = new UserRoute();
    private final EmployeeRoute employeeRoute = new EmployeeRoute();
    private final LibraryRoute libraryRoute = new LibraryRoute();

    public EndpointGroup getRoutes() {
        return () -> {
            path("/books", bookRoute.getRoutes());
            path("/lendbooks", lendRoute.getRoutes());
            path("/users", userRoute.getRoutes());
            path("/employees", employeeRoute.getRoutes());
            path("/libraries", libraryRoute.getRoutes());
        };
    }

}
