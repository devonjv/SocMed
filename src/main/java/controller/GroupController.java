package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dao.GroupDAO;

@RestController
@RequestMapping(value="/Group")
public class GroupController {
	
	@Autowired
	private GroupDAO gdao;
	
	public GroupController() {
	}
	
	
}
