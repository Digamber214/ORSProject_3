package in.co.rays.dto;



/**
 * marksheet JavaBean encapsulates marksheet attributes
 * @author Digamber
 *
 */
public class MarksheetDTO extends BaseDTO{
	
	
	 /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
		 * rollNo of faculty
		 */
   private String rollNo;
   
   /**
  	 * studentId of faculty
  	 */
   private long studentId;
   
   /**
  	 * name of faculty
  	 */
   private String name;
   
   /**
  	 * physics of faculty
  	 */
   private Integer physics;
   
   /**
  	 * chemistry of faculty
  	 */
   private Integer chemistry;
   
   /**
  	 * maths of faculty
  	 */
   private Integer maths;
   
   
/*
 * accessor
 */
	public String getRollNo() {
	return rollNo;
}

public void setRollNo(String rollNo) {
	this.rollNo = rollNo;
}



public long getStudentId() {
	return studentId;
}

public void setStudentId(long studentId) {
	this.studentId = studentId;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public Integer getPhysics() {
	return physics;
}

public void setPhysics(Integer physics) {
	this.physics = physics;
}

public Integer getChemistry() {
	return chemistry;
}

public void setChemistry(Integer chemistry) {
	this.chemistry = chemistry;
}

public Integer getMaths() {
	return maths;
}

public void setMaths(Integer maths) {
	this.maths = maths;
}

	public String getKey() {
		// TODO Auto-generated method stub
		return id+"";
	}

	public String getValue() {
		// TODO Auto-generated method stub
		return rollNo+"";
	}

}
