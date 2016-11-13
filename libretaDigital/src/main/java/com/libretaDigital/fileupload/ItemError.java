package com.libretaDigital.fileupload;

import java.io.Serializable;

public class ItemError implements Serializable {

	private static final long serialVersionUID = 1L;

	private String itemId;

	private String messageError;

	public ItemError(String itemId, String messageError) {
		super();
		this.itemId = itemId;
		this.messageError = messageError;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getMessageError() {
		return messageError;
	}

	public void setMessageError(String messageError) {
		this.messageError = messageError;
	}

}