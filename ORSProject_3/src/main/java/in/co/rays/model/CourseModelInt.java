package in.co.rays.model;

import java.util.List;

import in.co.rays.dto.CourseDTO;
import in.co.rays.exception.ApplicationException;

public interface CourseModelInt {

	public long add(CourseDTO dto) throws ApplicationException;
	public void update(CourseDTO dto) throws ApplicationException;
	public void delete(CourseDTO dto) throws ApplicationException;
	public CourseDTO findByCourseName(String name) throws ApplicationException;
	public CourseDTO findByPk(long pk) throws ApplicationException;
	public List list() throws ApplicationException;
	public List list(int pageNo,int pageSize) throws ApplicationException;
	public List search(CourseDTO dto) throws ApplicationException;
	public List search(CourseDTO dto,int pageNo,int pageSize) throws ApplicationException;
}
