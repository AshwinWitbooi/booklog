package za.co.ashtech.booklog.util;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.web.annotation.EndpointWebExtension;
import org.springframework.boot.actuate.info.InfoEndpoint;
import org.springframework.stereotype.Component;

import za.co.ashtech.booklog.BooklogApplication;

@Component
@EndpointWebExtension(endpoint = InfoEndpoint.class)
public class ManifestInfo {
	
	@ReadOperation
    public Map<String, String> manifestAttributes() throws IOException {
		
		Map<String, String> manifestAttributes = new HashMap<>();
		
		URLClassLoader urlClassLoader = (URLClassLoader) BooklogApplication.class.getClassLoader();
		URL url = urlClassLoader.findResource("META-INF/MANIFEST.MF");
		try {
			Manifest manifest = new Manifest(url.openStream());
			Attributes attributes = manifest.getMainAttributes();
			
			ArrayList<Entry<Object, Object>> attributeList = new ArrayList<>(attributes.entrySet());
			
			for(Entry<Object, Object> entry: attributeList) {
				manifestAttributes.put(entry.getKey().toString(), entry.getValue().toString());
			}
			
		} catch (IOException e) {
			throw e;
		}	
		
		
        return manifestAttributes;
    }

}
