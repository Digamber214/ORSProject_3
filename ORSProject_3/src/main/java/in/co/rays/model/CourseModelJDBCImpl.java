package in.co.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.dto.CourseDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DatabaseException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.util.JDBCDataSource;

public class CourseModelJDBCImpl implements CourseModelInt {

	public long add(CourseDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return 0;
	}

	public void update(CourseDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

	public void delete(CourseDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

	public CourseDTO findByCourseName(String name) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	public CourseDTO findByPk(long pk) throws ApplicationException {
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

	public List search(CourseDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	public List search(CourseDTO dto, int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}
	



}
