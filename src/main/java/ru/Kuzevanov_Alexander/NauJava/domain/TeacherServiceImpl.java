package ru.Kuzevanov_Alexander.NauJava.domain;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.Kuzevanov_Alexander.NauJava.data.repositories.schedulecell.ScheduleCellRepository;
import ru.Kuzevanov_Alexander.NauJava.data.repositories.TeacherRepository;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final ScheduleCellRepository scheduleCellRepository;
    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(
            ScheduleCellRepository scheduleCellRepository,
            TeacherRepository teacherRepository
    ) {
        this.scheduleCellRepository = scheduleCellRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    @Transactional
    public void deleteTeacher(Long id, Boolean isNegative) {
        scheduleCellRepository.deleteByTeacher(id);
        if (isNegative) {
            throw new IllegalArgumentException("Illegal teacher id");
        }
        teacherRepository.deleteById(id);
    }
}
