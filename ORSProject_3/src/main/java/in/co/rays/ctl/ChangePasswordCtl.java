package in.co.rays.ctl;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.co.rays.dto.UserDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.RecordNotFoundException;
import in.co.rays.model.ModelFactory;
import in.co.rays.model.UserModelInt;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;


/**
 * change password operation functionality perform
 * @author Digamber
 *
 */

@WebServlet(urlPatterns = {"/ctl/ChangePasswordCtl"})
public class ChangePasswordCtl extends BaseCtl{

	
	private static final long serialVersionUID = 1L;
	
	
	protected boolean validate(HttpServletRequest request) {
		System.out.println("validate.......");
//		log.debug("change password validate method start");
		boolean pass = true;
		String op = request.getParameter("operation");
		if (OP_CHANGE_MY_PROFILE.equalsIgnoreCase(op)) {
			return pass;
		}
		
		if (DataValidator.isNull(request.getParameter("oldpassword"))) {
			request.setAttribute("oldpassword",  PropertyReader.getValue("error.require", "Old password"));
			pass = false;
		} else if (!DataValidator.isPassword(request.getParameter("oldpassword"))) {
			request.setAttribute("oldpassword", "Please Enter valid Password");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("newpassword"))) {
			request.setAttribute("newpassword", PropertyReader.getValue("error.require", "New Password"));
			pass = false;
		} else if (!DataValidator.isPassword(request.getParameter("newpassword"))) {
			request.setAttribute("newpassword", "Please Enter vaild Password");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("confirmpassword"))) {
			request.setAttribute("confirmpassword", PropertyReader.getValue("error.require", "Confirm Password"));
			pass = false;
		}
		if (!request.getParameter("newpassword").equals(request.getParameter("confirmpassword"))
				&& !"".equals(request.getParameter("confirmpassword"))) {
			ServletUtility.setErrorMessage("New and confirm passwords not matched", request);
			pass = false;

		}
//		log.debug("validate method end");
		return pass;
	}

	
	 /**
     * Display Logics inside this method
     */
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException
	{ System.out.println("do get ............");
		ServletUtility.forward(getView(), request, response);	
		
	}
	
	
	/**
     * Submit logic inside it
     */
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		
		HttpSession session=request.getSession(true);
//		log.debug("change password do post start");
		String op=DataUtility.getString(request.getParameter("operation"));
		UserModelInt model=ModelFactory.getInstance().getUserModel();
		
		UserDTO UserBean=(UserDTO)session.getAttribute("user");
		String newPassword=request.getParameter("newpassword");
		String oldPassword=request.getParameter("oldpassword");
		long id=UserBean.getId();
		System.out.println("do post id..."+id+"...."+UserBean.getPassword()+";;;;;;;;;"+UserBean.getId()+"....."+newPassword+"...."+oldPassword);
		if(OP_SAVE.equalsIgnoreCase(op)){
			try{
				boolean flag = true;
			   flag=model.changePassword(id,newPassword,oldPassword);
			if(flag==true)	{
				//model.findByLogin(UserBean.getLogin());
				
				ServletUtility.setSuccessMessage("Password has been change successfully", request);
				ServletUtility.forward(getView(), request, response);
				return;
			}
			}catch (ApplicationException e) {
//                log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
                
            } catch (RecordNotFoundException e) {
                ServletUtility.setErrorMessage("Old PassWord is Invalid",
                        request);
            }
			
		}  else if (OP_CHANGE_MY_PROFILE.equalsIgnoreCase(op)) {
            ServletUtility.redirect(ORSView.MY_PROFILE_CTL, request, response);
            return;
        }

        ServletUtility.forward(ORSView.CHANGE_PASSWORD_VIEW, request, response);
//        log.debug("ChangePasswordCtl Method doGet Ended");
		
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.CHANGE_PASSWORD_VIEW;
	}

}
