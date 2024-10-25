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


    // Benjamins ting der virker med database osv.


    private final BookService service;

    public BookController() {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        this.service = BookService.getInstance(emf);
    }

    @Override
    public void create(Context ctx) {
        //request
        BookDTO jsonRequest = ctx.bodyAsClass(BookDTO.class);

        //DTO
        BookDTO bookDTO = service.create(jsonRequest);
        if (bookDTO == null) {
            ctx.status(400);
            ctx.result("Invalid book data");
            return;
        }

        //response
        ctx.status(201);
        ctx.json(bookDTO, BookDTO.class);
    }

    @Override
    public void readAll(Context ctx) {
        //List of DTO's
        List<BookDTO> bookDTOS = service.readAll();
        //response
        ctx.status(200);
        ctx.json(bookDTOS, BookDTO.class);
    }

    @Override
    public void read(Context ctx) {
        //request
        int id = ctx.pathParamAsClass("id", Integer.class).check(this::validatePrimaryKey, "Not a valid id").get();
        //DTO
        BookDTO bookDTO = service.read(id);
        //response
        ctx.status(200);
        ctx.json(bookDTO, BookDTO.class);
    }

    public void readGenre(Context ctx) {
        //request
        Book.Genre genre = ctx.pathParamAsClass("genre", Book.Genre.class).get();
        //DTO
        List<BookDTO> bookDTO = service.readGenre(genre);
        //response
        ctx.status(200);
        ctx.json(bookDTO, BookDTO.class);
    }

    public void readTitle(Context ctx) {
        //request
        String title = ctx.pathParamAsClass("title", String.class).get();
        //DTO
        BookDTO bookDTO = service.readTitle(title);
        //response
        ctx.status(200);
        ctx.json(bookDTO, BookDTO.class);
    }

    public void readYear(Context ctx) {
        //request
        int year = ctx.pathParamAsClass("year", Integer.class).get();

        //DTO
        List<BookDTO> bookDTO = service.readYear(year);
        //response
        ctx.status(200);
        ctx.json(bookDTO, BookDTO.class);
    }

    public void readAuthor(Context ctx) {
        //request
        String author = ctx.pathParamAsClass("author", String.class).get();
        //DTO
        List<BookDTO> bookDTO = service.readAuthor(author);
        //response
        ctx.status(200);
        ctx.json(bookDTO, BookDTO.class);
    }

    @Override
    public void update(Context ctx) {
        //request
        int id = ctx.pathParamAsClass("id", Integer.class).check(this::validatePrimaryKey, "Not a valid id").get();
        //DTO
        BookDTO bookDTO = service.update(id, validateEntity(ctx));
        //response
        ctx.status(200);
        ctx.json(bookDTO, BookDTO.class);
    }

    @Override
    public void delete(Context ctx) {
        //request
        int id = ctx.pathParamAsClass("id", Integer.class).check(this::validatePrimaryKey, "Not a valid id").get();

        service.delete(id);
        //response
        ctx.status(204);
    }

    @Override
    public boolean validatePrimaryKey(Integer integer) {
        return service.validatePrimaryKey(integer);
    }

    @Override
    public BookDTO validateEntity(Context ctx) {
        return ctx.bodyValidator(BookDTO.class)
                .check(b -> b.getTitle() != null && !b.getTitle().isEmpty(), "Book titel must be set")
                .check(b -> b.getGenre() != null, "Book genre must be set")
                .check(b -> b.getYear() != null, "Book year must be set")
                .get();
    }

/*
  // Mahdis ting der virker med service han selv har lavet 
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

 */
}





