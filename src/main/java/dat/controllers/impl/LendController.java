package dat.controllers.impl;

import dat.config.HibernateConfig;
import dat.controllers.IController;
import dat.dtos.BookDTO;
import dat.dtos.LentBookDTO;
import dat.services.LendService;
import dk.bugelhartmann.UserDTO;
import io.javalin.http.Context;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public class LendController implements IController<BookDTO, Integer> {

    private final LendService service;

    public LendController() {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        this.service = LendService.getInstance(emf);
    }


    @Override
    public void read(Context ctx) {

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
        int id = ctx.pathParamAsClass("id", Integer.class).check(this::validatePrimaryKey, "Not a valid id").get();

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

    }

    @Override
    public void delete(Context ctx) {

    }

    @Override
    public boolean validatePrimaryKey(Integer integer) {
        //Check if the book exists using bookservice and then its find method with the id
        return true;
    }

    @Override
    public BookDTO validateEntity(Context ctx) {
        return null;
    }
}
