package zadatak4.DAO;

import org.hibernate.Session;
import zadatak4.Utils.HibernateUtil;
import zadatak4.entities.Fakultet;
import zadatak4.interfaces.FakultetDAO;
import zadatak4.interfaces.SimpleCache;

import java.util.List;

public class FakultetDAOImpl implements FakultetDAO, SimpleCache {
    private boolean validCache = false;

    @Override
    public void addFakultet(Fakultet fakultet) {
        Session s = HibernateUtil.getCurrentSession();
        s.beginTransaction();
        s.save(fakultet);
        s.getTransaction().commit();
        setValidCache(false);
    }

    @Override
    public List<Fakultet> listFakultetData() {
        Session s = HibernateUtil.getCurrentSession();
        s.beginTransaction();

        List<Fakultet> fakultetData = s.createQuery("from Fakultet").getResultList();
        s.getTransaction().commit();

        setValidCache(true);

        return fakultetData;
    }

    @Override
    public void removeFakultet(Integer id) {
        Session s = HibernateUtil.getCurrentSession();
        s.beginTransaction();
        Fakultet f = s.load(Fakultet.class, id);
        s.delete(f);
        s.getTransaction().commit();
        setValidCache(false);
    }

    @Override
    public void updateFakultet(Fakultet fakultet) {
        Session s = HibernateUtil.getCurrentSession();
        s.beginTransaction();
        s.update(fakultet);
        s.getTransaction().commit();
        setValidCache(false);
    }

    public boolean isValidCache() {
        return validCache;
    }

    public void setValidCache(boolean validCache) {
        this.validCache = validCache;
    }
}
