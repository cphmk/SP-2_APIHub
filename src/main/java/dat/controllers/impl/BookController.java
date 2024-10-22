package dat.controllers.impl;

import dat.controllers.IController;
import dat.dtos.BookDTO;
import io.javalin.http.Context;

public class BookController implements IController<BookDTO, Integer> {

    @Override
    public void create(Context ctx) {
        ctx.json("Create book");
    }

    @Override
    public void readAll(Context ctx) {
        ctx.json("Read all books");
    }

    @Override
    public void read(Context ctx) {
        ctx.json("Read book");
    }

    @Override
    public void update(Context ctx) {
        ctx.json("Update book");
    }

    @Override
    public void delete(Context ctx) {
        ctx.json("Delete book");
    }

    @Override
    public boolean validatePrimaryKey(Integer integer) {
        return false;
    }

    @Override
    public BookDTO validateEntity(Context ctx) {
        return null;
    }

}
