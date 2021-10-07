package in.co.rays.model;

import java.util.List;

import in.co.rays.dto.UserDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.exception.RecordNotFoundException;

public interface UserModelInt {

	public long add(UserDTO dto) throws ApplicationException , DuplicateRecordException;
	public void delete(UserDTO dto) throws ApplicationException;
	public void update(UserDTO dto) throws ApplicationException, DuplicateRecordException;
	public UserDTO findByPk(long pk) throws ApplicationException;
	public UserDTO findByLogin(String login) throws ApplicationException;
	public List list() throws ApplicationException;
	public List list(int pageNo,int PageSize) throws ApplicationException;
	public List search(UserDTO dto) throws ApplicationException;
	public List search(UserDTO dto,int pageNo,int PageSize) throws ApplicationException;
	public boolean changePassword(long pk,String newPassword,String oldPassword) throws ApplicationException, RecordNotFoundException;
	public UserDTO authenticate(String login,String password) throws ApplicationException;
	public boolean forgetPassword(String login) throws ApplicationException, RecordNotFoundException;
	public boolean resetPassword(UserDTO dto) throws ApplicationException;
	public long registerUser(UserDTO dto) throws ApplicationException;
	public List getRoles(UserDTO dto) throws ApplicationException;	
}