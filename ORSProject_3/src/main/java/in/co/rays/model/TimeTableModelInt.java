package in.co.rays.model;

import java.util.Date;
import java.util.List;

import in.co.rays.dto.TimetableDTO;
import in.co.rays.exception.ApplicationException;

public interface TimeTableModelInt {

	public long add(TimetableDTO dto) throws ApplicationException;

	public void update(TimetableDTO dto) throws ApplicationException;

	public void delete(TimetableDTO dto) throws ApplicationException;

	public TimetableDTO findByPk(long pk) throws ApplicationException;

	public List list() throws ApplicationException;

	public List list(int pageNo, int pageSize) throws ApplicationException;

	public List search(TimetableDTO dto) throws ApplicationException;

	public List search(TimetableDTO dto, int pageNo, int pageSize) throws ApplicationException;

	public TimetableDTO checkByCourseName(long courseId, java.util.Date examDate) throws ApplicationException;

	public TimetableDTO checkBySubjectName(long courseId, long subjectId, Date examDate) throws ApplicationException;

	public TimetableDTO checkBySemester(long courseId, long subjectId, String semester, java.util.Date examDate) throws ApplicationException;
}
