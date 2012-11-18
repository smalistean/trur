package md.trur.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ImageController {
	
	@RequestMapping(value="/image/{s}/{x}/{y}/{z}", method = RequestMethod.GET)
	@ResponseBody 
	public byte[] getImage(
			@PathVariable String s,
			@PathVariable String x,
			@PathVariable String y,
			@PathVariable String z) throws IOException {
		
		String dirName = "D://map/997/256/" + s + "/" + z;
		String fileName = x + "_" + y + ".png";
		String localPath = dirName + "/" + fileName;
		File file = new File(localPath);
		if (!file.exists()) {
			String url = "http://" + s + ".tile.cloudmade.com/BC9A493B41014CAABB98F0471D759707/997/256/" + z + "/" + x + "/" + y + ".png";
			createFile(loadImage(url), dirName, localPath);
			
		}
		
	    return IOUtils.toByteArray(new FileInputStream(localPath));
	}
	
	private InputStream loadImage(String url) throws IOException {
	    HttpGet get = new HttpGet(url);
	    get.setHeader(new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.1; rv:6.0) Gecko/20100101 Firefox/6.0"));
	    
	    HttpClient httpClient = new DefaultHttpClient();
	    HttpResponse response = httpClient.execute(get);
	    HttpEntity ent = response.getEntity();
	    return ent.getContent();
	}
	
	private void createFile(InputStream in, String dirName, String localPath) throws FileNotFoundException, IOException {
		File dir = new File(dirName);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		FileOutputStream out = new FileOutputStream(localPath);
		IOUtils.copy(in, out);
	}
}
