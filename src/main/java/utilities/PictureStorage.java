package utilities;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.GroupGrantee;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.Permission;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class PictureStorage {

	private PictureStorage() {
	}

	public static void post(byte[] file, String key) {
		AWSCredentials credentials = new BasicAWSCredentials("AKIAIOG7U2NO2MEQZNTA",
				"SVPyZnfzLh3xB+XmyKEOocDlxraadKS89jP+DjtV");
		AmazonS3 s3client = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.US_EAST_2).build();
		long size = file.length;
		InputStream content = new ByteArrayInputStream(file);
		ObjectMetadata md = new ObjectMetadata();
		md.setContentLength(size);
		PutObjectRequest por = new PutObjectRequest("devonvirdenprojects", key, content, md);
		AccessControlList acl = new AccessControlList();
		acl.grantPermission(GroupGrantee.AllUsers, Permission.Read);
		por.setAccessControlList(acl);
		s3client.putObject(por);
	}
}
