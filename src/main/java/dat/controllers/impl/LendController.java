package dat.controllers.impl;

import dat.config.HibernateConfig;
import dat.controllers.IController;
import dat.dtos.BookDTO;
import dat.dtos.LentBookDTO;
import dat.services.BookService;
import dat.services.LendService;
import dk.bugelhartmann.UserDTO;
import io.javalin.http.Context;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public class LendController implements IController<BookDTO, Long> {

    private final LendService service;
    private final BookService bookService;


    public LendController() {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        this.service = LendService.getInstance(emf);
        this.bookService = BookService.getInstance(emf);
    }


    @Override
    public void read(Context ctx) {
        UserDTO userDTO = ctx.attribute("user");
        try {
            List<LentBookDTO> lentBookDTO = service.readUserLends(userDTO);
            ctx.res().setStatus(200);
            ctx.json(lentBookDTO);
        } catch (EntityNotFoundException e) {

            ctx.res().setStatus(404);
            ctx.json(e.getMessage());
        } catch (Exception e) {

            ctx.res().setStatus(500);
            ctx.json(e.getMessage());
        }

    }

    @Override
    public void readAll(Context ctx) {
        try {
            List<LentBookDTO> lentBookDTOList = service.readAllLends();
            ctx.res().setStatus(200);
            ctx.json(lentBookDTOList);
        } catch (EntityNotFoundException e) {
            if (e.getMessage().equals("No lent books found")) {
                ctx.res().setStatus(404);
                ctx.json(e.getMessage());
            }
        }
    }

    public void readUserLends(Context ctx) {
        //Get the user from the context
        UserDTO userDTO = ctx.attribute("user");

        //Get the user's lends from the service
        try {
            List<LentBookDTO> lentBookDTOList = service.readUserLends(userDTO);
            System.out.println("test1");
            System.out.println(lentBookDTOList);
            System.out.println("test2");
            ctx.res().setStatus(200);
            ctx.json(lentBookDTOList);
        } catch (EntityNotFoundException e) {
            if (e.getMessage().equals("No lent books found")) {
                ctx.res().setStatus(404);
                ctx.json(e.getMessage());
            } else if (e.getMessage().equals("User not found")) {
                ctx.res().setStatus(500);
                ctx.json(e.getMessage());
            }
        }
    }

    @Override
    public void create(Context ctx) {
        //Get the user from the context
        UserDTO userDTO = ctx.attribute("user");

        //Get the book id from the context
        long id = ctx.pathParamAsClass("id", Long.class).check(this::validatePrimaryKey, "Not a valid id").get();

        //Lend the book
        try {
            LentBookDTO lentBookDTO = service.lendBook(userDTO, id);
            ctx.res().setStatus(201);
            ctx.json(lentBookDTO);
        } catch (Exception e) {
            ctx.res().setStatus(500);
            ctx.json(e.getMessage());
        }
    }

    @Override
    public void update(Context ctx) {
        UserDTO userDTO = ctx.attribute("user");

        String userRole = ctx.attribute("role");

        Long bookId = ctx.pathParamAsClass("id", Long.class)
                .check(this::validatePrimaryKey, "Not a valid book ID")
                .get();

        LentBookDTO lentBookDTO = ctx.bodyAsClass(LentBookDTO.class);

        try {
            LentBookDTO updatedLentBookDTO = service.updateLentBook(userDTO, bookId, lentBookDTO, userRole);
            ctx.res().setStatus(200);
            ctx.json(updatedLentBookDTO);
        } catch (EntityNotFoundException e) {
            ctx.res().setStatus(404);
            ctx.json(e.getMessage());
        } catch (IllegalArgumentException e) {
            ctx.res().setStatus(400);
            ctx.json(e.getMessage());
        } catch (Exception e) {
            ctx.res().setStatus(500);
            ctx.json(e.getMessage());
        }
    }

    @Override
    public void delete(Context ctx) {
        //request
        long id = ctx.pathParamAsClass("id", Long.class).check(this::validatePrimaryKey, "Not a valid id").get();

        service.delete(id);
        //response
        ctx.status(204);

    }

    @Override
    public boolean validatePrimaryKey(Long bookId) {
        return bookService.validatePrimaryKey(bookId.intValue());
    }

    @Override
    public BookDTO validateEntity(Context ctx) {
        BookDTO bookDTO = ctx.bodyAsClass(BookDTO.class);

        if (!bookService.validateBookDTO(bookDTO)) {
            throw new IllegalArgumentException("Invalid BookDTO: All required fields must be filled.");
        }

        return bookDTO;
    }

}
