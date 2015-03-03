package org.pxcbpmisframework.core.json;

/**
 * 后台向前台返回JSON，用于easyui的datagrid
 * 
 * @author
 * 
 */
public class ComboBox {

	private String id;
	private String text;
	private boolean selected;
	public ComboBox(){
		
	}
	public ComboBox(String id, String text, boolean selected) {
		super();
		this.id = id;
		this.text = text;
		this.selected = selected;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
