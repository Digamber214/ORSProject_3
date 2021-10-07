package in.co.rays.model;

import java.util.Date;
import java.util.List;

import in.co.rays.dto.TimetableDTO;
import in.co.rays.exception.ApplicationException;

public class TimeTableModelJDBCImpl implements TimeTableModelInt{

	public long add(TimetableDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return 0;
	}

	public void update(TimetableDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

	public void delete(TimetableDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

	public TimetableDTO findByPk(long pk) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	public List list() throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	public List list(int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	public List search(TimetableDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	public List search(TimetableDTO dto, int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	public TimetableDTO checkByCourseName(long courseId, Date examDate) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	public TimetableDTO checkBySubjectName(long courseId, long subjectId, Date examDate) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	public TimetableDTO checkBySemester(long courseId, long subjectId, String semester, Date examDate)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

}
