package service.impl;

import model.Operation;
import repository.OperationRepository;
import repository.impl.OperationRepositoryImpl;
import service.OperationService;

import java.util.List;

public class OperationServiceImpl implements OperationService {
    private OperationRepository operationRepository;

    public OperationServiceImpl() {
        operationRepository=new OperationRepositoryImpl();
    }

    public OperationServiceImpl(OperationRepositoryImpl operationRepository) {
        this.operationRepository = operationRepository;
    }

    @Override
    public void save(Operation operation) {
        operationRepository.save(operation);
    }

    @Override
    public List<Operation> getOperationsByUser(long id) {
        return operationRepository.getOperationsByUser(id);
    }
}
