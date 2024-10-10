package ru.Kuzevanov_Alexander.NauJava.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import ru.Kuzevanov_Alexander.NauJava.data.repositories.schedulecell.ScheduleCellRepository;
import ru.Kuzevanov_Alexander.NauJava.data.repositories.TeacherRepository;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final ScheduleCellRepository scheduleCellRepository;
    private final TeacherRepository teacherRepository;
    private final PlatformTransactionManager transactionManager;

    @Autowired
    public TeacherServiceImpl(
            ScheduleCellRepository scheduleCellRepository,
            TeacherRepository teacherRepository,
            PlatformTransactionManager transactionManager
    ) {
        this.scheduleCellRepository = scheduleCellRepository;
        this.teacherRepository = teacherRepository;
        this.transactionManager = transactionManager;
    }

    @Override
    public void deleteTeacher(Long id, Boolean isNegative) {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            scheduleCellRepository.deleteByTeacher(id);
            if (isNegative) {
                throw new IllegalArgumentException("Illegal teacher id");
            }
            teacherRepository.deleteById(id);
            transactionManager.commit(status);
        } catch (Exception ex) {
            transactionManager.rollback(status);
            throw ex;
        }
    }
}
