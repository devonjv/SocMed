package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import dao.GroupDAO;

@RestController
public class GroupController {
	
	@Autowired
	private GroupDAO gdao;
	
	public GroupController() {
	}
	
	
}
