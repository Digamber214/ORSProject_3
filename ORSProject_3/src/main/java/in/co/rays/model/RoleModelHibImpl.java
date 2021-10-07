package in.co.rays.model;

import java.security.Timestamp;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.dto.RoleDTO;
import in.co.rays.dto.UserDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.util.HibDataSource;

public class RoleModelHibImpl implements RoleModelInt {

	public long add(RoleDTO dto) throws ApplicationException {
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
				tx.rollback();
			}
			throw new ApplicationException("unable to add record "+e.getMessage());
		} finally {
			session.close();
		}
		return dto.getId();
	}

	public void update(RoleDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = HibDataSource.getSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.update(dto);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("unable to update record");
		} finally {
			session.close();
		}
	}

	public void delete(RoleDTO dto) throws ApplicationException {
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
			throw new ApplicationException("unable to delete record");
		} finally {
			session.close();
		}

	}

	public RoleDTO findByPk(long pk) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = HibDataSource.getSession();
		RoleDTO dto = null;

		try {

			dto = (RoleDTO) session.get(RoleDTO.class, pk);
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("unable to find record");
		} finally {
			session.close();
		}

		return dto;
	}

	
	
	public RoleDTO findByName(String name) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = HibDataSource.getSession();
		RoleDTO dto = null;
		List list = null;

		try {
			Criteria criteria = session.createCriteria(RoleDTO.class);
			criteria.add(Restrictions.eq("name", name));
			list = criteria.list();
			if (list.size() > 0) {
				dto = (RoleDTO) list.get(0);
			}

		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("unable to fetch data");
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
			
			Criteria criteria = session.createCriteria(RoleDTO.class);
			if (pageSize > 0) {
				pageNo = ((pageNo - 1) * pageSize)+1;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}
			list = criteria.list();
			
			Iterator itr=list.iterator();
			while(itr.hasNext()) {
				itr.next();
				System.out.println(list.get(0));
			}
//			System.out.println(list);
		} catch (HibernateException e) {

			throw new ApplicationException("Exception : Exception in  role list");
		} finally {
			session.close();
		}
		return list;
	}

	public List search(RoleDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return search(dto, 0, 0);
	}

	public List search(RoleDTO dto, int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = HibDataSource.getSession();
		List list = null;
		try {
			
			Criteria criteria = session.createCriteria(RoleDTO.class);
			
			if(dto!=null) {
				if(dto.getId()>0 ) {
					criteria.add(Restrictions.eq("id",dto.getId()));
				}
				if(dto.getName()!=null && dto.getName().length()>0) {
					criteria.add(Restrictions.like("name",dto.getName()));
				}
				if(dto.getDescription()!=null && dto.getDescription().length()>0) {
					criteria.add(Restrictions.like("description",dto.getDescription()));
				}
			}
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}
			list = criteria.list();
		} catch (HibernateException e) {

			throw new ApplicationException("Exception : Exception in  role list");
		} finally {
			session.close();
		}
		
		return list;
	}

	public static void main(String[] args) throws ApplicationException {
		RoleModelHibImpl m = new RoleModelHibImpl();
		RoleDTO dto = new RoleDTO();
		
//		dto=m.findByPk(1);
		dto = m.findByName("Digamber Tiwari");
		System.out.println(dto.getName());
	//System.out.println(m.search(dto, 0, 0));
	
//	dto.setId((long) (2));
//	dto.setName("student");
//	dto.setDescription("hcjashkv");
	//dto.setCreatedBy("DON");
	//dto.setModifiedBy("DON");
	//dto.setCreatedDatetime(createdDatetime);
	//dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
	
	
//     m.add(dto);
//		m.delete(dto);
//		m.list();
	}
}
