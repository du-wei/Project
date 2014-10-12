package com.webapp.module;

import com.opensymphony.xwork2.ActionSupport;

public class CkEditorAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private String editor;

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	@Override
	public String execute() {
		System.out.println(editor);
		return SUCCESS;
	}

}
