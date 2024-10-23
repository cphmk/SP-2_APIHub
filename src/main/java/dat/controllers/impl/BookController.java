package dat.controllers.impl;

import dat.config.HibernateConfig;
import dat.controllers.IController;
import dat.daos.BookDAO;
import dat.dtos.BookDTO;
import dat.entities.Book;
import dat.services.BookService;
import io.javalin.http.Context;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class BookController implements IController<BookDTO, Integer> {

    /*
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

     */

    private BookService bookService;

    public BookController() {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        this.bookService = new BookService(emf);
    }

    @Override
    public void read(Context ctx) {
        int id = ctx.pathParamAsClass("id", Integer.class)
                .check(bookService::validatePrimaryKey, "Not a valid id")
                .get();
        BookDTO bookDTO = bookService.read(id);
        ctx.res().setStatus(200);
        ctx.json(bookDTO, BookDTO.class);
    }

    @Override
    public void readAll(Context ctx) {
        List<BookDTO> hotelDTOS = bookService.readAll();
        ctx.res().setStatus(200);
        ctx.json(hotelDTOS, BookDTO.class);
    }

    @Override
    public void create(Context ctx) {
        BookDTO jsonRequest = ctx.bodyAsClass(BookDTO.class);
        if (!bookService.validateHotelDTO(jsonRequest)) {
            ctx.res().setStatus(400);
            ctx.result("Invalid hotel data");
            return;
        }
        BookDTO hotelDTO = bookService.create(jsonRequest);
        ctx.res().setStatus(201);
        ctx.json(hotelDTO, BookDTO.class);
    }

    @Override
    public void update(Context ctx) {
        int id = ctx.pathParamAsClass("id", Integer.class)
                .check(bookService::validatePrimaryKey, "Not a valid id")
                .get();
        BookDTO hotelDTO = bookService.update(id, validateEntity(ctx));
        ctx.res().setStatus(200);
        ctx.json(hotelDTO, Book.class);
    }

    @Override
    public void delete(Context ctx) {
        int id = ctx.pathParamAsClass("id", Integer.class)
                .check(bookService::validatePrimaryKey, "Not a valid id")
                .get();
        bookService.delete(id);
        ctx.res().setStatus(204);
    }

    @Override
    public boolean validatePrimaryKey(Integer integer) {
        return bookService.validatePrimaryKey(integer);
    }

    @Override
    public BookDTO validateEntity(Context ctx) {
        return ctx.bodyValidator(BookDTO.class)
                .check(h -> h.getTitle() != null && !h.getTitle().isEmpty(), "Book title must be set")
                .check(h -> h.getLibraries() != null && !h.getLibraries().isEmpty(), "Libraries must be set")
                .check(h -> h.getYear() != null, "Book year must be set")
                .check(h -> h.getGenre() != null,  "Libraries must be set")
                .check(h -> h.getYear() != null, "Book year must be set")
                .check(h -> h.getLoanedOutDate() != null, "LoanedOutDate must be set")
                .get();
    }
}





