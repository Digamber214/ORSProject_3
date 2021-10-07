package in.co.rays.model;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.dto.CourseDTO;
import in.co.rays.dto.SubjectDTO;
import in.co.rays.dto.TimetableDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.util.HibDataSource;

public class TimeTableModelHibImpl implements TimeTableModelInt {

	public long add(TimetableDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
	
		  CourseModelInt Cmodel = ModelFactory.getInstance().getCourseModel();
		  CourseDTO Cbean = null; 
		  Cbean = Cmodel.findByPk(dto.getCourseId());
		  dto.setCourseName(Cbean.getCourseName());
		  
		  SubjectModelInt Smodel = ModelFactory.getInstance().getSubjectModel();
		  SubjectDTO Sbean = Smodel.findByPk(dto.getSubId());
		  dto.setSubName(Sbean.getSubjectName());
		
		Session session = null;
		Transaction tx = null;
		long pk = 0;

		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.save(dto);
//			pk = dto.getId();
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("Exception in timetable Add " + e.getMessage());
		} finally {
			session.close();
		}
		return pk;
	}

	public void update(TimetableDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub

		CourseModelInt Cmodel = ModelFactory.getInstance().getCourseModel();
		CourseDTO Cbean = null;
		Cbean = Cmodel.findByPk(dto.getCourseId());
		dto.setCourseName(Cbean.getCourseName());

		SubjectModelInt Smodel = ModelFactory.getInstance().getSubjectModel();
		SubjectDTO Sbean = Smodel.findByPk(dto.getSubId());
		dto.setSubName(Sbean.getSubjectName());

		Session session = null;
		Transaction tx = null;
		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.update(dto);
			tx.commit();

		} catch (HibernateException e) {
			e.printStackTrace();
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();

			}
			throw new ApplicationException("Exception in timetable update " + e.getMessage());
		} finally {
			session.close();
		}

	}

	public void delete(TimetableDTO dto) throws ApplicationException {

		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.delete(dto);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();

			}
			throw new ApplicationException("Exception in Timetable delete " + e.getMessage());
		} finally {
			session.close();
		}
	}

	public TimetableDTO findByPk(long pk) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = null;
		TimetableDTO dto = null;
		try {
			session = HibDataSource.getSession();

			dto = (TimetableDTO) session.get(TimetableDTO.class, pk);
		} catch (HibernateException e) {

			throw new ApplicationException("Exception : Exception in getting TimetableDTO by pk");
		} finally {
			session.close();
		}

		return dto;
	}

	public List list() {
		// TODO Auto-generated method stub
		return null;
	}

	public List list(int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(TimetableDTO.class);

			if (pageSize > 0) {
				pageNo = ((pageNo - 1) * pageSize) + 1;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}
			list = criteria.list();
		} catch (HibernateException e) {

			throw new ApplicationException("Exception : Exception in  TimetableDTO list");
		} finally {
			session.close();
		}
		return list;

	}

	public List search(TimetableDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return search(dto, 0, 0);
	}

	public List search(TimetableDTO dto, int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(TimetableDTO.class);
			if (dto != null) {
				if (dto.getId() != null) {
					criteria.add(Restrictions.eq("id", dto.getId()));
				}
				if (dto.getCourseName() != null && dto.getCourseName().length() > 0) {
					criteria.add(Restrictions.like("courseName", dto.getCourseName() + "%"));
				}
				if (dto.getSubName() != null && dto.getSubName().length() > 0) {
					criteria.add(Restrictions.like("subName", dto.getSubName() + "%"));
				}
				if (dto.getSemester() != null && dto.getSemester().length() > 0) {
					criteria.add(Restrictions.like("semester", dto.getSemester() + "%"));
				}
				if (dto.getExamDate() != null && dto.getExamDate().getDate() > 0) {
					criteria.add(Restrictions.eq("examDate", dto.getExamDate()));
				}
				if (dto.getSubId() > 0) {
					criteria.add(Restrictions.eq("subId", dto.getSubId()));
				}
				if (dto.getCourseId() > 0) {
					criteria.add(Restrictions.eq("courseId", dto.getCourseId()));
				}
			}
			if (pageSize > 0) {
				criteria.setFirstResult((pageNo - 1) * pageSize);
				criteria.setMaxResults(pageSize);
			}
			list = criteria.list();
		} catch (HibernateException e) {

			throw new ApplicationException("Exception in course search");
		} finally {
			session.close();
		}
		return list;
	}

	public TimetableDTO checkByCourseName(long courseId, Date examDate) throws ApplicationException {
		// TODO Auto-generated method stub
		
		long l = examDate.getTime();
		java.sql.Date date = new java.sql.Date(l);
		Session session = null;
		TimetableDTO dto = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(TimetableDTO.class);
			criteria.add(Restrictions.and(Restrictions.eq("courseId", courseId), Restrictions.eq("examDate", date)));
			List list = criteria.list();
			if (list.size() > 0) {
				dto = (TimetableDTO) list.get(0);
			}
		} catch (HibernateException e) {

			throw new ApplicationException("Exception : Exception in getting TimetableDTO by pk");
		} finally {
			session.close();
		}
		return dto;
//		return null;
	}

	public TimetableDTO checkBySubjectName(long courseId, long subjectId, Date examDate) throws ApplicationException {
		// TODO Auto-generated method stub
		long l = examDate.getTime();
		java.sql.Date date = new java.sql.Date(l);
		Session session = null;
		TimetableDTO dto = null;
		try {

			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(TimetableDTO.class);
			Disjunction dis = Restrictions.disjunction();
			dis.add(Restrictions.eq("courseId", courseId));
			dis.add(Restrictions.eq("subId", subjectId));
			dis.add(Restrictions.eq("examDate", date));
			criteria.add(dis);
			List list = criteria.list();
			if (list.size() > 0) {
				dto = (TimetableDTO) list.get(0);
			}
		} catch (HibernateException e) {
			e.printStackTrace();

			throw new ApplicationException("Exception : Exception in getting TimetableDTO by pk");
		} finally {
			session.close();
		}
		return dto;
//		return null;
	}

	public TimetableDTO checkBySemester(long courseId, long subjectId, String semester, Date examDate) throws ApplicationException {
		// TODO Auto-generated method stub
		long l = examDate.getTime();
		java.sql.Date date = new java.sql.Date(l);
		Session session = null;
		TimetableDTO dto = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(TimetableDTO.class);
			Disjunction dis = Restrictions.disjunction();
			dis.add(Restrictions.eq("courseId", courseId));
			dis.add(Restrictions.eq("subId", subjectId));
			dis.add(Restrictions.like("semester", semester));
			dis.add(Restrictions.eq("examDate", date));
			criteria.add(dis);
			List list = criteria.list();
			if (list.size() > 0) {
				dto = (TimetableDTO) list.get(0);
			}

		} catch (HibernateException e) {
			e.printStackTrace();

			throw new ApplicationException("Exception : Exception in getting TimetableDTO by pk");
		} finally {
			session.close();
		}
		return dto;
//		return null;
	}

	public static void main(String[] args) throws ApplicationException {

		TimeTableModelHibImpl t = new TimeTableModelHibImpl();

		TimetableDTO dto = new TimetableDTO();
		dto.setId((long) 1);
//		dto.setCourseName("electrical");
//		t.add(dto);
		t.delete(dto);

	}
}
