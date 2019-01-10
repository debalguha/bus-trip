package org.progressivelifestyle.bustrip.web.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class FileData implements Serializable{
	
	private String fileName;
	private byte[] fileData;
	public FileData(String fileName, byte[] fileData) {
		this.fileName = fileName;
		this.fileData = fileData;
	}
	
	public FileData() {}

	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public byte[] getFileData() {
		return fileData;
	}
	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}
}
