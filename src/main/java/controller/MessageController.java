package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dao.MessageDAO;

@RestController
@RequestMapping(value="/Messages")
public class MessageController {

	@Autowired
	MessageDAO mdao;
	
	public MessageController() {
	}
}
