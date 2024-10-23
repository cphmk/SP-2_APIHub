package dat.controllers.impl;

import dat.config.HibernateConfig;
import dat.controllers.IController;
import dat.daos.BookDAO;
import dat.dtos.BookDTO;
import dat.entities.Book;
import io.javalin.http.Context;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class BookController implements IController<BookDTO, Integer> {

    private final BookDAO dao;

    public BookController() {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        this.dao = BookDAO.getInstance(emf);
    }

    @Override
    public void create(Context ctx) {
        //request
        BookDTO jsonRequest = ctx.bodyAsClass(BookDTO.class);
        //DTO
        BookDTO bookDTO = dao.create(jsonRequest);
        //response
        ctx.status(201);
        ctx.json(bookDTO, BookDTO.class);
    }

    @Override
    public void readAll(Context ctx) {
        //List of DTO's
        List<BookDTO>bookDTOS = dao.readAll();
        //response
        ctx.status(200);
        ctx.json(bookDTOS, BookDTO.class);
    }

    @Override
    public void read(Context ctx) {
        //request
        int id = ctx.pathParamAsClass("id", Integer.class).check(this::validatePrimaryKey, "Not a valid id").get();
        //DTO
        BookDTO bookDTO = dao.read(id);
        //response
        ctx.status(200);
        ctx.json(bookDTO,BookDTO.class);
    }

    @Override
    public void update(Context ctx) {
        //request
        int id = ctx.pathParamAsClass("id", Integer.class).check(this::validatePrimaryKey, "Not a valid id").get();
        //DTO
        BookDTO bookDTO = dao.update(id,validateEntity(ctx));
        //response
        ctx.status(200);
        ctx.json(bookDTO, BookDTO.class);
    }

    @Override
    public void delete(Context ctx) {
        //request
        int id = ctx.pathParamAsClass("id", Integer.class).check(this::validatePrimaryKey, "Not a valid id").get();

        dao.delete(id);
        //response
        ctx.status(204);
    }

    @Override
    public boolean validatePrimaryKey(Integer integer) {
        return dao.validatePrimaryKey(integer);
    }

    @Override
    public BookDTO validateEntity(Context ctx) {
        return ctx.bodyValidator(BookDTO.class)
                .check( b -> b.getTitle() != null && !b.getTitle().isEmpty(),"Book titel must be set")
                .check( b -> b.getGenre() !=null ,"Book genre must be set")
                .check( b -> b.getYear() !=null ,"Book year must be set")
                .get();
    }

}
