package shabtay.coupon.system.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginServlet {

	@RequestMapping( value = "/loginservlet", method = RequestMethod.GET)
	public @ResponseBody String doGet( @RequestParam String username, @RequestParam String password , String usertype)
	{
		return "hello " + username + " your password " + password + "hello" + usertype;
	}
}
