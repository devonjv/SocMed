package controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import utilities.Helper;

@RestController
public class UtilityController {

	@GetMapping(value = "/load")
	public String getActiveUsers() {
		/**
		 * Make this call when the server is started to load up the back end.
		 */
		 Helper.compile();
		 return "Ok, back end loaded";
	}
}
