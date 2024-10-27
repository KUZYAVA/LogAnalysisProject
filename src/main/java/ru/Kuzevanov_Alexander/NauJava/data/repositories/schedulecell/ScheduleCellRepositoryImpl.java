package ru.Kuzevanov_Alexander.NauJava.data.repositories.schedulecell;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;
import ru.Kuzevanov_Alexander.NauJava.data.model.ScheduleCell;
import ru.Kuzevanov_Alexander.NauJava.data.model.Teacher;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class ScheduleCellRepositoryImpl implements ScheduleCellRepositoryCustom {

    private final EntityManager entityManager;

    public ScheduleCellRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<ScheduleCell> findByStartTimeOrEndTime(Timestamp startTime, Timestamp endTime) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ScheduleCell> criteriaQuery = criteriaBuilder.createQuery(ScheduleCell.class);
        Root<ScheduleCell> userRoot = criteriaQuery.from(ScheduleCell.class);
        Predicate startTimePredicate = criteriaBuilder.equal(userRoot.get("startTime"), startTime);
        Predicate endTimePredicate = criteriaBuilder.equal(userRoot.get("endTime"), endTime);
        Predicate startTimeOrEndTimePredicate = criteriaBuilder.or(startTimePredicate, endTimePredicate);
        criteriaQuery.select(userRoot).where(startTimeOrEndTimePredicate);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<ScheduleCell> findByTeacher(String teacherFullName) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ScheduleCell> criteriaQuery = criteriaBuilder.createQuery(ScheduleCell.class);
        Root<ScheduleCell> userRoot = criteriaQuery.from(ScheduleCell.class);
        Join<ScheduleCell, Teacher> role = userRoot.join("teacher", JoinType.INNER);
        Predicate namePredicate = criteriaBuilder.equal(role.get("fullName"), teacherFullName);
        criteriaQuery.select(userRoot).where(namePredicate);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
