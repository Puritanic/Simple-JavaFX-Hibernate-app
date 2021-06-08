package zadatak4.DAO;

import org.hibernate.Session;
import zadatak4.Utils.HibernateUtil;
import zadatak4.entities.Student;
import zadatak4.interfaces.SimpleCache;
import zadatak4.interfaces.StudentDAO;

import java.util.List;

public class StudentDAOImpl implements StudentDAO, SimpleCache {
    private boolean validCache = false;

    @Override
    public void addStudent(Student student) {
        Session s = HibernateUtil.getCurrentSession();
        s.beginTransaction();
        s.save(student);
        s.getTransaction().commit();
        setValidCache(false);
    }

    @Override
    public List<Student> listStudentData() {
        Session s = HibernateUtil.getCurrentSession();
        s.beginTransaction();

        List<Student> studentData = s.createQuery("from Student").getResultList();
        s.getTransaction().commit();

        setValidCache(true);

        return studentData;
    }

    @Override
    public void removeStudent(Integer id) {
        Session s = HibernateUtil.getCurrentSession();
        s.beginTransaction();
        Student student = s.load(Student.class, id);
        s.delete(student);
        s.getTransaction().commit();
        setValidCache(false);
    }

    @Override
    public void updateStudent(Student student) {
        Session s = HibernateUtil.getCurrentSession();
        s.beginTransaction();
        s.update(student);
        s.getTransaction().commit();
        setValidCache(false);
    }

    @Override
    public boolean isValidCache() {
        return validCache;
    }

    @Override
    public void setValidCache(boolean validCache) {
        this.validCache = validCache;
    }
}
