package in.co.rays.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.dto.CollegeDTO;
import in.co.rays.dto.UserDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.util.HibDataSource;

public class CollegeModelHibImp implements CollegeModelInt {

	

	public long add(CollegeDTO dto) throws ApplicationException, DuplicateRecordException {
		// TODO Auto-generated method stub
		//Session object is lightweight and designed to be instantiated each time an interaction is needed with the database. Persistent objects are saved and retrieved through a Session object.
		Session session = HibDataSource.getSession();
		Transaction tx = null;
		try {
			CollegeDTO existDto = findByName(dto.getName());
			if (existDto != null) {
				throw new DuplicateRecordException("Duplicate Entry Found ");
			}
			tx = session.beginTransaction();
			session.save(dto);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();

			if (tx != null) {
				tx.rollback();

			}

			throw new ApplicationException("application exception id add method " + e.getMessage());
		} finally {
			session.close();
		}
		return dto.getId();
	}

	public void delete(CollegeDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = HibDataSource.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			session.delete(dto);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();

			}

			throw new ApplicationException("Application exception id delete " + e.getMessage());
		} finally {
			session.close();
		}
	}

	public void update(CollegeDTO dto) throws DuplicateRecordException, ApplicationException {
		// TODO Auto-generated method stub
		Session session = HibDataSource.getSession();
		Transaction tx = null;
		try {
			CollegeDTO existDto = findByName(dto.getName());
			if (existDto != null) {
				throw new DuplicateRecordException("Duplicate Entry Found ");
			}
			tx = session.beginTransaction();
			session.update(dto);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("Application Exception in update method " + e.getMessage());
		} finally {
			session.close();
		}

	}

	public CollegeDTO findByPk(long pk) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = HibDataSource.getSession();
		CollegeDTO dto = null;
		try {

			dto = (CollegeDTO) session.get(CollegeDTO.class, pk);
			

		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in getting DTO FindByName " + e.getMessage());

		} finally {
			session.close();
		}
		return dto;
	}

	public CollegeDTO findByName(String name) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = HibDataSource.getSession();
		CollegeDTO dto = null;
		try {
			Criteria criteria = session.createCriteria(CollegeDTO.class);
			criteria.add(Restrictions.eq("name", name));

			List list = criteria.list();
			if (list.size() == 1) {
				dto = (CollegeDTO) list.get(0);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("unable to find the result in collegeDTO " + e.getMessage());

		} finally {
			session.close();
		}

		return dto;
	}

	public List list() throws ApplicationException {
		// TODO Auto-generated method stub
		return list(0, 0);
	}

	public List<CollegeDTO> list(int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = null;
		List<CollegeDTO> list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(CollegeDTO.class);
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}
			list = criteria.list();

		} catch (HibernateException e) {

			throw new ApplicationException("Exception : Exception in  College list");
		} finally {
			session.close();
		}

		return list;
	}

	public List search(CollegeDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return search(dto, 0, 0);
	}

	
	public List search(CollegeDTO dto, int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = HibDataSource.getSession();
		List list = null;
		try {
			Criteria criteria = session.createCriteria(CollegeDTO.class);

			if (dto != null) {
				if (dto.getId() >0) {
					criteria.add(Restrictions.eq("id", dto.getId()));
				}
				if (dto.getName() != null && dto.getName().length() > 0) {
					criteria.add(Restrictions.like("name", dto.getName() + "%"));
				}
				if (dto.getAddress() != null && dto.getAddress().length() > 0) {
					criteria.add(Restrictions.like("address", dto.getAddress() + "%"));
				}
				if (dto.getCity() != null && dto.getCity().length() > 0) {
					criteria.add(Restrictions.like("city", dto.getCity() + "%"));
				}
				if (dto.getPhoneNo() != null && dto.getPhoneNo().length() > 0) {
					criteria.add(Restrictions.like("phoneNo", dto.getPhoneNo() + "%"));
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
			System.out.println(e);
			throw new ApplicationException("Unable to search the list " + e.getMessage());
		} finally {
			session.close();
		}

		return list;
	}

	
	public static void main(String[] args) throws ApplicationException {
		CollegeModelHibImp m = new CollegeModelHibImp();
	CollegeDTO dto = new CollegeDTO();
//	m.findByName("student");
//	System.out.println(m.list());
	//List l=m.search(dto);
//		System.out.println(l);
	//System.out.println(m.search(dto, 1, 10));
     //System.out.println(m.list());
	}

}
