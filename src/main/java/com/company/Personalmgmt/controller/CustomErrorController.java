package com.company.Personalmgmt.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomErrorController  implements ErrorController{
	
	@Autowired
	HttpSession httpsession;
	
	@GetMapping("/error")
    public String handleError(HttpServletRequest request) {
        String errorPage = "errorPage"; // default
         
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        
        ModelAndView errorPage1 = new ModelAndView("errorPage");
        
        String errorMsg = "";
        
         
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
             
			if (statusCode == HttpStatus.NOT_FOUND.value()) {

				httpsession.setAttribute("errorMsg", "Http Error Code: 404. Resource not found");
				
			//	errorMsg = "Http Error Code: 404. Resource not found";

			} else if (statusCode == HttpStatus.FORBIDDEN.value()) {

				httpsession.setAttribute("errorMsg", "Http Error Code: 403. Forbidden");
				
			//	errorMsg = "Http Error Code: 403. Forbidden";

			} else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {

				httpsession.setAttribute("errorMsg", "Http Error Code: 500. Internal Server Error");
				
			//	errorMsg = "Http Error Code: 500. Internal Server Error";

			} else{
				
				httpsession.setAttribute("errorMsg", "Response timeout. Please check after some time");
				
			}
        }
         
        return errorPage;
    }

	/*@RequestMapping(value = "errors", method = RequestMethod.GET)
	public ModelAndView renderErrorPage(HttpServletRequest httpRequest) {

		ModelAndView errorPage = new ModelAndView("errorPage");
		String errorMsg = "";
		int httpErrorCode = getErrorCode(httpRequest);

		switch (httpErrorCode) {
		case 400: {
			errorMsg = "Http Error Code: 400. Bad Request";
			break;
		}
		case 401: {
			errorMsg = "Http Error Code: 401. Unauthorized";
			break;
		}
		case 404: {
			errorMsg = "Http Error Code: 404. Resource not found";
			break;
		}
		case 500: {
			errorMsg = "Http Error Code: 500. Internal Server Error";
			break;
		}
		}
		errorPage.addObject("errorMsg", errorMsg);
		return errorPage;
	}

	*/
	public String getErrorPath() {
		return "/error";
	}

}
