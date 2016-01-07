package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandController {
	void execute(HttpServletRequest request, 
			 HttpServletResponse response);
}
