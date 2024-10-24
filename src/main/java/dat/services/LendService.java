package dat.services;

import dat.config.HibernateConfig;
import dat.daos.LendDAO;
import dat.dtos.LentBookDTO;
import dk.bugelhartmann.UserDTO;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class LendService {

    private static LendDAO dao;
    private static LendService instance;

    public static LendService getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            instance = new LendService();
            dao = LendDAO.getInstance(_emf);
        }
        return instance;
    }

    public LentBookDTO lendBook(UserDTO userDTO, int bookId) {
        return dao.lendBook(userDTO, (long) bookId);
    }

    public List<LentBookDTO> readUserLends(UserDTO userDTO) {
        return dao.readUserLends(userDTO);
    }

    public List<LentBookDTO> readAllLends() {
        return dao.readAll();
    }

    public void delete(long id) {
        dao.delete(id);
    }

    public LentBookDTO updateLentBook(Integer integer, LentBookDTO lentBookDTO) { return dao.update(integer,lentBookDTO);}



}
