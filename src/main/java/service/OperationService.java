package service;

import model.Operation;

import java.util.List;

public interface OperationService {
    void save(Operation operation);
    List<Operation> getOperationsByUser(long id);
}
