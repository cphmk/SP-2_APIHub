package dat.controllers.impl;

import dat.controllers.IController;
import dat.dtos.EmployeeDTO;
import io.javalin.http.Context;

public class EmployeeController implements IController<EmployeeDTO, Integer> {
    @Override
    public void read(Context ctx) {

    }

    @Override
    public void readAll(Context ctx) {

    }

    @Override
    public void create(Context ctx) {

    }

    @Override
    public void update(Context ctx) {

    }

    @Override
    public void delete(Context ctx) {

    }

    @Override
    public boolean validatePrimaryKey(Integer integer) {
        return false;
    }

    @Override
    public EmployeeDTO validateEntity(Context ctx) {
        return null;
    }
}
