package dat.controllers.impl;

import dat.config.HibernateConfig;
import dat.controllers.IController;
import dat.daos.BookDAO;
import dat.dtos.BookDTO;
import dat.entities.Book;
import io.javalin.http.Context;
import jakarta.persistence.EntityManagerFactory;

public class BookController implements IController<BookDTO, Integer> {

    private final BookDAO dao;

    public BookController() {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        this.dao = BookDAO.getInstance(emf);
    }

    @Override
    public void create(Context ctx) {
        BookDTO jsonRequest = ctx.bodyAsClass(BookDTO.class);
        BookDTO bookDTO = dao.create(jsonRequest);

        ctx.status(201);
        ctx.json(bookDTO, BookDTO.class);
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
