package in.co.rays.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.dto.MarksheetDTO;
import in.co.rays.dto.StudentDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.util.HibDataSource;

public class MarksheetModelHibImp implements MarksheetModelInt {

	public long add(MarksheetDTO dto) throws ApplicationException, DuplicateRecordException {
		// TODO Auto-generated method stub
		Session session = HibDataSource.getSession();
		Transaction tx = null;
		StudentModelInt sModel=ModelFactory.getInstance().getStudentModel();
		System.out.println(dto.getStudentId()+"==============================================");
		StudentDTO studentDTO = sModel.findByPk(dto.getStudentId());
		dto.setName(studentDTO.getFirstName() + " " + studentDTO.getLastName());

		MarksheetDTO existdto = findByRollNo(dto.getRollNo());
		if (existdto != null) {
			throw new DuplicateRecordException("record already exist ");
		}
		try {
			tx = session.beginTransaction();
			session.save(dto);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("exception in add in marksheet " + e.getMessage());
		} finally {
			session.close();
		}
		return dto.getId();
			
	}

	public void update(MarksheetDTO dto) throws ApplicationException, DuplicateRecordException {
		// TODO Auto-generated method stub
		Session session = HibDataSource.getSession();
		Transaction tx = null;
		
		
		
		MarksheetDTO existdto = findByRollNo(dto.getRollNo());
		
		
		if (existdto != null && existdto.getId() != dto.getId()) {
			throw new DuplicateRecordException("record already exist ");
		}
		

		StudentModelInt sModel = ModelFactory.getInstance().getStudentModel();
		System.out.println(dto.getStudentId()+"==============================================");
		StudentDTO studentDTO = sModel.findByPk(dto.getStudentId());
		dto.setName(studentDTO.getFirstName() + " " + studentDTO.getLastName());
		try {
			tx = session.beginTransaction();
			session.update(dto);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("exception in update in marksheet " + e.getMessage());
		} finally {
			session.close();
		}
		
	}

	public void delete(MarksheetDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = HibDataSource.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(dto);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("exception in delete in marksheet " + e.getMessage());
		} finally {
			session.close();
		}
		
	}

	public MarksheetDTO findByRollNo(String rollNo) throws ApplicationException {
		// TODO Auto-generated method stub
		Session s=HibDataSource.getSession();
		Transaction tx = null;
		MarksheetDTO dto = null;
		try {
			Criteria criteria = s.createCriteria(MarksheetDTO.class);
			criteria.add(Restrictions.eq("rollNo", rollNo));
			List list=criteria.list();
			if (list.size() > 0) {
				dto = (MarksheetDTO) list.get(0);
			}
		} catch (Exception e) {

			throw new ApplicationException("Exception in getting Marksheet by pk" + e.getMessage());

		} finally {
			s.close();
		}

		return dto;
	
	}

	public MarksheetDTO findByPk(long pk) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session=HibDataSource.getSession();
		Transaction tx = null;
		MarksheetDTO dto = null;
		try {
			Criteria criteria=session.createCriteria(MarksheetDTO.class);
			criteria.add(Restrictions.eq("id", pk));
			List list=criteria.list();
			if (list.size() == 1) {
				dto = (MarksheetDTO) list.get(0);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("unable to fetch data in find by pk (marksheet)");
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
		ArrayList list = null;
		try {
			Criteria criteria = session.createCriteria(MarksheetDTO.class);
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}
			list = (ArrayList) criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in Getting list in marksheet ");
		} finally {
			session.close();
		}

		return list;

	}

	public List search(MarksheetDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return search(dto, 0, 0);
	}

	public List search(MarksheetDTO dto, int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(MarksheetDTO.class);
			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));

			}
			if (dto.getRollNo() != null && dto.getRollNo().length() > 0) {
				criteria.add(Restrictions.like("rollNo", dto.getRollNo() + "%"));
			}
			if (dto.getName() != null && dto.getName().length() > 0) {
				criteria.add(Restrictions.like("name", dto.getName() + "%"));
			}
			if (dto.getPhysics() != null && dto.getPhysics() > 0) {
				criteria.add(Restrictions.eq("physics", dto.getPhysics()));
			}
			if (dto.getChemistry() != null && dto.getChemistry() > 0) {
				criteria.add(Restrictions.eq("chemistry", dto.getChemistry()));
			}
			if (dto.getMaths() != null && dto.getMaths() > 0) {
				criteria.add(Restrictions.eq("maths", dto.getMaths()));
			}
			if (pageSize > 0) {
				criteria.setFirstResult((pageNo - 1) * pageSize);
				criteria.setMaxResults(pageSize);
			}
			list = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in Marksheet Search " + e.getMessage());
		} finally {
			session.close();
		}
		return list;
	}

	public List getMeritList(int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			StringBuffer hql = new StringBuffer("from MarksheetDTO order by (physics+chemistry+maths) desc ");
			if (pageSize > 0) {
				pageNo = ((pageNo - 1) * pageSize);
//				hql.append(" limit" + pageNo + "," + pageSize);
			}
			Query query = session.createQuery(hql.toString());
			list = query.list();

		} catch (Exception e) {

			throw new ApplicationException("Exception in  marksheet list" + e.getMessage());
		} finally {
			session.close();
		}

		return list;
	}
	
	public static void main(String[] args) throws ApplicationException, DuplicateRecordException {
		MarksheetModelHibImp m = new MarksheetModelHibImp();
		MarksheetDTO dto;
//		dto.setRollNo("EX1234");
//		dto.setId((long) 3);
//		dto.setName("MAYANK");
//		dto.setMaths(78);
//		dto.setPhysics(87);
//		dto.setRollNo("CS24");
//		dto.setChemistry(34);
//		m.delete(dto);

//		dto=m.findByRollNo("CS14");

//		m.list();
//		dto = m.findByRollNo("EX1234");
//		System.out.println(dto.getName());
//		System.out.println(dto.getPhysics());
		
		System.out.println(m.getMeritList(0, 10));
	}

}
