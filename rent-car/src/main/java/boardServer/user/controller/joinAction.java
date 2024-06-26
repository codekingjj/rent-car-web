package boardServer.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boardServer.user.model.UserDao;
import boardServer.user.model.UserRequestDto;
import boardServer.user.model.UserResponseDto;

public class joinAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public joinAction() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String birth = request.getParameter("birth");
		String gender = request.getParameter("gender");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		
		// Backend 에서 전달받은 데이터에 대한 유효성 검증 
		boolean isValid = true;
		
		if(id == null || id.equals(""))
			isValid = false;
		else if(password == null || password.equals(""))
			isValid = false;
		else if(name == null || name.equals(""))
			isValid = false;
		else if(birth == null || birth.equals(""))
			isValid = false;
		else if(gender == null || gender.equals(""))
			isValid = false;
		else if(phone == null || phone.equals(""))
			isValid = false;
		
		// Processing Page 에서는 사용자에게 보여주는 화면을 작성하지 않음 
		// 요청에 대한 응답 처리를 작성 
		// 1) 페이지 이동 처리 (흐름을 제어)
		if(isValid) {
			
			UserRequestDto userDto = new UserRequestDto(id, password, name, birth, gender, phone, address);
			UserDao userDao = UserDao.getInstance();
			UserResponseDto user = userDao.createUser(userDto);
			
			if (user == null) {
				System.out.println("유저x실패");
				response.sendRedirect("/join");
			}else {
				System.out.println("성공");
				response.sendRedirect("/welcome");
				
			}
		}
		else {
			System.out.println("isValid실패");
			response.sendRedirect("/join");
			
		}

	}
	}

