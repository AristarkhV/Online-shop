package service.serviceImpl;

import dao.CodeDao;
import factory.CodeFactory;
import model.Code;
import service.CodeService;

import java.util.Optional;

public class CodeServiceImpl implements CodeService {

    private static final CodeDao codeDao = CodeFactory.getInstance();

    @Override
    public void addCode(Code value) {
        codeDao.addCode(value);
    }

    @Override
    public Optional<Code> getCode(String email) {
        return codeDao.getCode(email);
    }
}
