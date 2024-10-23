package dat.routes;

import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.path;

public class Routes {

    private final BookRoute bookRoute = new BookRoute();
    private final LendRoute lendRoute = new LendRoute();

    public EndpointGroup getRoutes() {
        return () -> {
            path("/books", bookRoute.getRoutes());
            path("/lendbooks", lendRoute.getRoutes());
        };
    }

}
