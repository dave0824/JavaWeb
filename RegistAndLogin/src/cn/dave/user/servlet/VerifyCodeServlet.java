package cn.dave.user.servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.vcode.utils.VerifyCode;

/**
 * Servlet implementation class VerifyCodeServlet
 */
@WebServlet(description = "verifyCodeServlet", urlPatterns = { "/VerifyCodeServlet" })
public class VerifyCodeServlet extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		VerifyCode verifyCode = new VerifyCode();
		BufferedImage img = verifyCode.getImage();
		request.getSession().setAttribute("session_vcode", verifyCode.getText());
		
		verifyCode.output(img, response.getOutputStream());
		
	}

}
