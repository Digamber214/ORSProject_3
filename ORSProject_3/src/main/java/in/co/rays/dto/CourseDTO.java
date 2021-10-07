package in.co.rays.dto;


/**
 * Course JavaDTO encapsulates College attributes
 * @author Digamber
 *
 */
public class CourseDTO extends BaseDTO {
   
	
	/**
	 * CourseName of college
	 */
	private String courseName;
	
	/**
	 * description of college
	 */
	private String description;
	
	/**
	 * duration of college
	 */
	private String duration;
	
	
	
	/*
	 * accessor
	 */
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getKey() {
		// TODO Auto-generated method stub
		return id+"";
	}

	public String getValue() {
		// TODO Auto-generated method stub
		return courseName;
	}

}
