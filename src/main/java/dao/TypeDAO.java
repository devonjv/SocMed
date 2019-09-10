package dao;

import model.PostType;

public interface TypeDAO {

	public void setPostTypes();

	public PostType getTextPost();

	public PostType getPicturePost();

	public PostType getVideoPost();
}
