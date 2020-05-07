package repository;

import model.Operation;

import java.sql.SQLException;
import java.util.List;

public interface OperationRepository {
    void save(Operation operation) ;
    List<Operation> getOperationsByUser(long id);

}
