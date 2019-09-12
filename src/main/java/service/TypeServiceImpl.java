package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dao.TypeDAO;
import model.PostType;

@Service("typeService")
public class TypeServiceImpl implements TypeService {

	@Autowired
	private TypeDAO tdao;

	private PostType textPost;

	private PostType picturePost;

	private PostType videoPost;

	public TypeServiceImpl() {
	}

	@Autowired
	public TypeServiceImpl(TypeDAO tdao) {
		this.tdao = tdao;
		textPost = tdao.getTextPost();
		picturePost = tdao.getPicturePost();
		videoPost = tdao.getVideoPost();
	}

	@Override
	public PostType getTextPost() {
		return textPost;
	}

	@Override
	public PostType getPicturePost() {
		return picturePost;
	}

	@Override
	public PostType getVideoPost() {
		return videoPost;
	}
}
