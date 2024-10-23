package dat.controllers.impl;

import dat.config.HibernateConfig;
import dat.controllers.IController;
import dat.daos.BookDAO;
import dat.daos.EmployeeDAO;
import dat.dtos.EmployeeDTO;
import io.javalin.http.Context;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class EmployeeController implements IController<EmployeeDTO, Integer> {

    private final EmployeeDAO dao;

    public EmployeeController() {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        this.dao = EmployeeDAO.getInstance(emf);
    }

    @Override
    public void read(Context ctx) {
        int id = ctx.pathParamAsClass("id", Integer.class).check(this::validatePrimaryKey,"Not a valid id").get();

        EmployeeDTO employeeDTO = dao.read(id);

        ctx.status(200);
        ctx.json(employeeDTO, EmployeeDTO.class);

    }

    @Override
    public void readAll(Context ctx) {
        List<EmployeeDTO> employeeDTOList = dao.readAll();
        ctx.status(200);
        ctx.json(employeeDTOList, EmployeeDTO.class);
    }

    @Override
    public void create(Context ctx) {
        EmployeeDTO jsonRequest = ctx.bodyAsClass(EmployeeDTO.class);

        EmployeeDTO employeeDTO = dao.create(jsonRequest);

        ctx.status(201);
        ctx.json(employeeDTO, EmployeeDTO.class);

    }

    @Override
    public void update(Context ctx) {
        int id = ctx.pathParamAsClass("id",Integer.class).check(this::validatePrimaryKey,"Not a valid id").get();

        EmployeeDTO employeeDTO = dao.update(id);

        ctx.status(200);
        ctx.json(employeeDTO, EmployeeDTO.class);

    }

    @Override
    public void delete(Context ctx) {
        int id = ctx.pathParamAsClass("id", Integer.class).check(this::validatePrimaryKey,"Not a valid id").get();

        dao.delete(id);

        ctx.status(204);

    }

    @Override
    public boolean validatePrimaryKey(Integer integer) {
        return dao.validatePrimaryKey(integer);
    }

    @Override
    public EmployeeDTO validateEntity(Context ctx) {
        return null;
    }
}
