package in.co.rays.model;

import java.util.List;

import in.co.rays.dto.SubjectDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;

public interface SubjectModelInt {

	
	public long add(SubjectDTO dto) throws ApplicationException, DuplicateRecordException;
	public void update(SubjectDTO dto) throws ApplicationException, DuplicateRecordException;
	public void delete(SubjectDTO dto) throws ApplicationException;
	public SubjectDTO findByPk(long pk) throws ApplicationException;
	public SubjectDTO findBySubjectName(String name) throws ApplicationException;
	public List list() throws ApplicationException;
	public List list(int pageNo,int pageSize) throws ApplicationException;
	public List search(SubjectDTO dto) throws ApplicationException;
	public List search(SubjectDTO dto,int pageNo,int pageSize) throws ApplicationException;
	
	
}
