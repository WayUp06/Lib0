package DAO;

import Entity.Usage;
import main.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.Optional;

public class UsageDAO extends ElementDAOImp<Usage> {
    public UsageDAO(Class<Usage> elementClass) {
        super(elementClass);
    }


    /**
     * @return count of all library usages during some period
     */
    public long getCountOfUsagesOfPeriod(String start, String end) {
        Session session = null;
        long result;
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            Query query = session.createQuery("select count(u.id) from Usage u" +
                    " where u.takeDate > :start and u.takeDate < :end");
            LocalDate s = LocalDate.parse(start);
            LocalDate e = LocalDate.parse(end);
            query.setParameter("start", s);
            query.setParameter("end", e);
            result = (long) query.uniqueResult();
        } finally {
            if ((session != null) && session.isOpen()) session.close();
        }
        return result;
    }
}
