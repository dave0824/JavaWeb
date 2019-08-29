package cn.dave.testSession;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.dave.img.VerifyCode;

/**
 * Servlet implementation class VerifyCodeServlet
 */
@WebServlet(
		description = "VerifyCodeServlet1", 
		urlPatterns = { 
				"/VerifyCodeServlet", 
				"/verifyCodeServlet"
		})
public class VerifyCodeServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		VerifyCode verifyCode = new VerifyCode();
		BufferedImage image = verifyCode.getImage();
		request.getSession().setAttribute("session_vcode", verifyCode.getText());
		
		VerifyCode.output(image, response.getOutputStream());
	}

}
