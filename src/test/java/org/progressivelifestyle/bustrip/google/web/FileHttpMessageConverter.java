package org.progressivelifestyle.bustrip.google.web;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.google.api.client.util.Lists;

public class FileHttpMessageConverter implements HttpMessageConverter<File> {

    public void write(BufferedImage image, HttpOutputMessage message) throws IOException {
        throw new UnsupportedOperationException("Not implemented");
    }

	@Override
	public boolean canRead(Class<?> clazz, MediaType mediaType) {
		return true;
	}

	@Override
	public boolean canWrite(Class<?> clazz, MediaType mediaType) {
		return false;
	}

	@Override
	public void write(File t, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public List<MediaType> getSupportedMediaTypes() {
		List<MediaType> types = Lists.newArrayList();
		types.add(MediaType.APPLICATION_OCTET_STREAM);
		return types;
	}

	@Override
	public File read(Class<? extends File> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
		HttpHeaders headers = inputMessage.getHeaders();
		String contentDispositionHeader = null;
		for(String header : headers.keySet()){
			if(header.contains("Content-disposition")){
				contentDispositionHeader = header;
				break;
			}
		}
		String fileName = contentDispositionHeader == null?"Unknown.file":headers.get(contentDispositionHeader).get(0).toString().split("\"")[1];
		File tempFile = File.createTempFile(FilenameUtils.getBaseName(fileName), ".".concat(FilenameUtils.getExtension(fileName)));
    	IOUtils.copy(inputMessage.getBody(), new FileOutputStream(tempFile));
        return tempFile;
	}

}