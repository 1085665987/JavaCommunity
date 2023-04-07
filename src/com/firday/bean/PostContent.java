package com.firday.bean;

import java.util.List;

/**
 * 帖子内容
 * @author Friday
 *
 */
public class PostContent {
    
    public PostContent() {
	super();
    }

    /**
     * 文本内容
     */
    public String textContent;
    
    /**
     * 图片内容
     */
    public List<String> imgsContent;

    public String getTextContent() {
		return textContent;
	}

	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}

	public List<String> getImgsContent() {
		return imgsContent;
	}

	public void setImgsContent(List<String> imgsContent) {
		this.imgsContent = imgsContent;
	}

	public PostContent(String textContent, List<String> imgsContent) {
	super();
	this.textContent = textContent;
	this.imgsContent = imgsContent;
    }

    public PostContent(String textContent) {
	super();
	this.textContent = textContent;
    }
    public static final String DB_POST_TEXT_CONTENT="post_text_content";
    public static final String DB_POST_IMG_CONTENT="pic_url";

	@Override
	public String toString() {
		return "PostContent [textContent=" + textContent + ", imgsContent=" + imgsContent + "]";
	}
}
