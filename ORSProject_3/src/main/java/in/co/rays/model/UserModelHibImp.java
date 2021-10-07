package in.co.rays.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.dom4j.util.UserDataAttribute;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import in.co.rays.dto.UserDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.exception.RecordNotFoundException;
import in.co.rays.util.DataUtility;
import in.co.rays.util.EmailBuilder;
import in.co.rays.util.EmailMessage;
import in.co.rays.util.EmailUtility;
import in.co.rays.util.HibDataSource;

public class UserModelHibImp implements UserModelInt{
	public long add(UserDTO dto) throws ApplicationException, DuplicateRecordException {
		
		Session session=HibDataSource.getSession();
		Transaction tx = null;
		UserDTO existDto = findByLogin(dto.getLogin());
		if (existDto != null && existDto.getId() != dto.getId()) {
			throw new DuplicateRecordException("User is already exist");
			
		}
		try {
			tx = session.beginTransaction();
			session.save(dto);
			System.out.println("step 3");
			tx.commit();
			}catch (HibernateException e) {
				e.printStackTrace();
				System.out.println("step 5");
				if (tx != null) {
					tx.rollback();

				}
				System.out.println(e.getMessage());

				throw new ApplicationException("Application Exception arise" + e.getMessage());
			} finally {
				System.out.println("step 6");
				session.close();
			}
			return dto.getId();
         }
	public void delete(UserDTO dto) throws ApplicationException {
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

	public void update(UserDTO dto) throws ApplicationException, DuplicateRecordException {
		// TODO Auto-generated method stub
		Session session = HibDataSource.getSession();
		Transaction tx = null;
		UserDTO existDto = findByLogin(dto.getLogin());
		if (existDto != null && existDto.getId() != dto.getId()) {
			throw new DuplicateRecordException("LoginId is already exist");
		}
		try {
			tx = session.beginTransaction();

			session.update(dto);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();

		}
		} finally {
			session.close();
		}

	}

	public UserDTO findByPk(long pk) throws ApplicationException {
		// TODO Auto-generated method stub
		UserDTO dto = null;
		Session session = HibDataSource.getSession();

		try {

			dto = (UserDTO) session.get(UserDTO.class, pk);

		} catch (HibernateException e) {
			throw new ApplicationException("Exception : Exception in getting User by pk");
		} finally {
			session.close();
		}
		return dto;
	}

	public UserDTO findByLogin(String login) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = HibDataSource.getSession();
		UserDTO dto = null;

		try {

			Criteria criteria = session.createCriteria(UserDTO.class);
			criteria.add(Restrictions.eq("login", login));
			List<UserDTO> list = criteria.list();
			if (list.size() == 1) {
				dto = (UserDTO) list.get(0);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in getting User by Login " + e.getMessage());

		} finally {
			session.close();
		}
		return dto;
	}

	public List<UserDTO> list() throws ApplicationException {
		// TODO Auto-generated method stub
		return list(0, 0);
	}

	public List<UserDTO> list(int pageNo, int pageSize) throws ApplicationException {
		Session session = HibDataSource.getSession();
		List<UserDTO> list = null;
		try {
			Criteria criteria = session.createCriteria(UserDTO.class);
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);

			}
			list = criteria.list();

		} catch (HibernateException e) {
			throw new ApplicationException("Exception : Exception in  Users list");
		} finally {
			session.close();
		}

		return list;
	}

	public List<UserDTO> search(UserDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub

		return search(dto, 0, 0);
	}

	public List<UserDTO> search(UserDTO dto, int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = HibDataSource.getSession();
		ArrayList<UserDTO> list = null;
		try {
			Criteria criteria = session.createCriteria(UserDTO.class);

			if (dto != null) {
				if (dto.getId() != null) {
					criteria.add(Restrictions.like("id", dto.getId()));
				}
				if (dto.getFirstName() != null && dto.getFirstName().length() > 0) {
					criteria.add(Restrictions.like("firstName", dto.getFirstName() + "%"));
				}

				if (dto.getLastName() != null && dto.getLastName().length() > 0) {
					criteria.add(Restrictions.like("lastName", dto.getLastName() + "%"));
				}
				if (dto.getLogin() != null && dto.getLogin().length() > 0) {
					criteria.add(Restrictions.like("login", dto.getLogin() + "%"));
				}
				if (dto.getPassword() != null && dto.getPassword().length() > 0) {
					criteria.add(Restrictions.like("password", dto.getPassword() + "%"));
				}
				if (dto.getGender() != null && dto.getGender().length() > 0) {
					criteria.add(Restrictions.like("gender", dto.getGender() + "%"));
				}
				if (dto.getDob() != null && dto.getDob().getDate() > 0) {
					criteria.add(Restrictions.eq("dob", dto.getDob()));
				}
				/*
				 * if (dto.getLastLogin() != null && dto.getLastLogin().getTime() > 0) {
				 * criteria.add(Restrictions.eq("lastLogin", dto.getLastLogin())); }
				 */
				if (dto.getRoleId() > 0) {
					criteria.add(Restrictions.eq("roleId", dto.getRoleId()));
				}
				/*
				 * if (dto.getUnSuccessfullLogin() > 0) {
				 * criteria.add(Restrictions.eq("unSuccessfulLogin",
				 * dto.getUnSuccessfullLogin())); }
				 */
			}

			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}

			list = (ArrayList<UserDTO>) criteria.list();
		} catch (HibernateException e) {

			e.printStackTrace();
			throw new ApplicationException("Application Exception in search method " + e.getMessage());
		} finally {
			session.close();
		}
		return list;
	}
	
	public boolean changePassword(long pk, String newPassword, String oldPassword)
			throws ApplicationException, RecordNotFoundException {
		// TODO Auto-generated method stub
		Session session = HibDataSource.getSession();
		boolean flag = false;
		UserDTO dtoExist = null;
		dtoExist = findByPk(pk);
		if (dtoExist != null && dtoExist.getPassword().equals(oldPassword)) {

			dtoExist.setPassword(newPassword);
			dtoExist.setConfirmPassword(newPassword);
			try {
				update(dtoExist);
			} catch (DuplicateRecordException e) {

				throw new ApplicationException("LoginId is already exist");
			}
			
			flag = true;
			
		} else {
			throw new RecordNotFoundException("Login not exist");
		}

		HashMap<String, String> map = new HashMap<String, String>();

		map.put("login", dtoExist.getLogin());
		map.put("password", dtoExist.getPassword());
		map.put("firstName", dtoExist.getFirstName());
		map.put("lastName", dtoExist.getLastName());

		String message = EmailBuilder.getChangePasswordMessage(map);

		EmailMessage msg = new EmailMessage();

		msg.setTo(dtoExist.getLogin());
		msg.setSubject("Password has been changed Successfully.");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);

		EmailUtility.sendMail(msg);

		return flag;
	}

	public UserDTO authenticate(String login, String password) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = HibDataSource.getSession();
		UserDTO dto = null;
		Query q=session.createQuery("from UserDTO where login=?0 and password=?1");
		q.setString(0, login);
		q.setString(1, password);
		List list=q.list();
		if (list.size() > 0) {
			dto = (UserDTO) list.get(0);
		} else {
			dto = null;
		}
		return dto;
	}
	public boolean forgetPassword(String login) throws ApplicationException, RecordNotFoundException {
		// TODO Auto-generated method stub
		Session session = HibDataSource.getSession();
		System.out.println("in forgot user 1");

		UserDTO userData = findByLogin(login);
		Boolean flag = false;
		if (userData == null) {
			throw new RecordNotFoundException("no such record found");
		}
System.out.println("in forgot user" + userData.getLogin());


HashMap<String, String> map = new HashMap<String, String>();

		map.put("login", userData.getLogin());
		map.put("password", userData.getPassword());
		map.put("firstName", userData.getFirstName());
		map.put("lastName", userData.getLastName());

		String message = EmailBuilder.getForgetPasswordMessage(map);
		EmailMessage msg = new EmailMessage();
		msg.setMessage(message);
		msg.setSubject("Get Password");
		msg.setTo(userData.getLogin());
		msg.setMessageType(EmailMessage.HTML_MSG);
		
		EmailUtility.sendMail(msg);
		
        flag=true;
		return flag;
	
	}
	public boolean resetPassword(UserDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = HibDataSource.getSession();
		String newPassword = String.valueOf(new Date().getTime()).substring(0, 4);
		UserDTO userData = findByPk(dto.getId());
		userData.setPassword(newPassword);

		try {
			update(userData);
		} catch (DuplicateRecordException e) {
			return false;
		}

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("login", dto.getLogin());
		map.put("password", dto.getPassword());

		String message = EmailBuilder.getForgetPasswordMessage(map);

		EmailMessage msg = new EmailMessage();

		msg.setTo(dto.getLogin());
		msg.setSubject("Password has been reset");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);

		EmailUtility.sendMail(msg);

		return true;
		
	}
	public long registerUser(UserDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = HibDataSource.getSession();
		long pk=0;
		try {
		
		 pk = add(dto);
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("login", dto.getLogin());
		map.put("password", dto.getPassword());

		String message = EmailBuilder.getUserRegistrationMessage(map);

		EmailMessage msg = new EmailMessage();

		msg.setTo(dto.getLogin());
		msg.setSubject("Registration is successful for ORS Project SUNRAYS Technologies");
		msg.setMessage(message); 
		msg.setMessageType(EmailMessage.HTML_MSG);

		EmailUtility.sendMail(msg);
		}catch(ApplicationException e){
			System.out.println(e.getMessage());
			throw new ApplicationException("cannot register the record");
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return pk;
		
	}
	public List getRoles(UserDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[] args) throws ApplicationException, DuplicateRecordException {

     	UserModelHibImp m = new UserModelHibImp();
    	UserDTO dto = new UserDTO();
//		dto.setFirstName("Digamber");
//		dto.setLastName("Tiwari");
//		dto.setGender("male");
//		//dto.setDob(2/2/1991);
//		dto.setRoleId(1);
//		dto.setMobileNo("8765456753");
//		dto.setLogin("digambernath.tiwari@gmail.com");
//		dto.setPassword("Sava2142005@@");
//		dto.setConfirmPassword("Sava2142005@@");
//		dto.setCreatedBy("Digamber");
//		dto.setCreatedDatetime(DataUtility.getCurrentTimeStamp());
//		dto.setModifiedBy("Digamber");
//		dto.setModifiedDatetime(DataUtility.getCurrentTimeStamp());
//		//m.registerUser(dto);
	//	m.add(dto);
//			 UserDTO dto=	m.authenticate("digambernath.tiwari@gmail.com", "Sava2142005@@")	;
//			 
		 System.out.println(dto.getFirstName());

}
	}
