package in.co.rays.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.dto.CourseDTO;
import in.co.rays.dto.UserDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.util.HibDataSource;

public class CourseModelHibImpl implements CourseModelInt {

	public long add(CourseDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = HibDataSource.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(dto);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null) {
				tx.commit();
			}
			throw new ApplicationException("unable to add in course ");
		} finally {
			session.close();
		}
		return dto.getId();
	}

	public void update(CourseDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub

		Session session = HibDataSource.getSession();
		Transaction tx = null;

		try {

			tx = session.beginTransaction();
			session.update(dto);
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null) {
				tx.commit();
			}
			throw new ApplicationException("unable to update in course ");
		} finally {
			session.close();
		}

	}

	public void delete(CourseDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub

		Session session = HibDataSource.getSession();
		Transaction tx = null;

		try {

			tx = session.beginTransaction();
			session.delete(dto);
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null) {
				tx.commit();
			}
			throw new ApplicationException("unable to update in course ");
		} finally {
			session.close();
		}

	}

	public CourseDTO findByCourseName(String courseName) throws ApplicationException {
		// TODO Auto-generated method stub
		CourseDTO dto = null;
		try {
			Session session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(CourseModelInt.class);

			criteria.add(Restrictions.eq("courseName", courseName));

			List list = criteria.list();
			if (list.size() == 1) {
				dto = (CourseDTO) list.get(0);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("unable to find the record");
		}
		return dto;
	}

	
	

	public CourseDTO findByPk(long pk) throws ApplicationException {
		// TODO Auto-generated method stub
		CourseDTO dto = null;
		Session session = HibDataSource.getSession();

		try {

			dto = (CourseDTO) session.get(CourseDTO.class, pk);
			
			

		} catch (HibernateException e) {
			throw new ApplicationException("Exception : Exception in getting User by pk");
		} finally {
			session.close();
		}
		return dto;
	}

	public List list() throws ApplicationException {
		// TODO Auto-generated method stub
		return list(0, 0);
	}

	public List list(int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = HibDataSource.getSession();
		List list = null;
		try {
			Criteria criteria = session.createCriteria(CourseDTO.class);
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}
			list = criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in Gettin list in student ");
		} finally {
			session.close();
		}

		return list;
	}

	public List search(CourseDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return search(dto, 0, 0);
	}

	public List search(CourseDTO dto, int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = HibDataSource.getSession();
		List list = null;
		try {
			Criteria criteria = session.createCriteria(CourseDTO.class);

			if (dto != null) {
				if (dto.getId() > 0) {
					criteria.add(Restrictions.eq("id", dto.getId()));
				}
				if (dto.getCourseName() != null && dto.getCourseName().length() > 0) {
					criteria.add(Restrictions.like("courseName", dto.getCourseName()));
				}
				if (dto.getDuration() != null && dto.getDuration().length() > 0) {
					criteria.add(Restrictions.like("duration", dto.getDuration()));
				}
			}
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}
			list = criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in Getting list in student ");
		} finally {
			session.close();
		}

		return list;
	}

	public static void main(String[] args) throws ApplicationException {
		CourseModelHibImpl m = new CourseModelHibImpl();

		CourseDTO dto = new CourseDTO();
		dto.setCourseName("mechanical engineering");
		

		m.add(dto);
		System.out.println(m.findByPk(2));

	}
}
