package com.friday.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.friday.service.PostService;

public class App {

    public static void main(String[] args) {
	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
//	UserService service = (UserService) applicationContext.getBean("userService");
//	Map<String, Object> userMap = new HashMap<>();
//	userMap.put("id", 1);
//	userMap.put("username", "wjj");
//	userMap.put("password", "123456");
//	try {
//	    service.doUpdateUser(userMap);
//	} catch (Exception e) {
//	    // TODO Auto-generated catch block
//	    e.printStackTrace();
//	}
	PostService bean = (PostService) applicationContext.getBean("postService");
//	PostInfo post=new PostInfo();
//
//	post.setPostTitle("���ɵ�ĵڶ�������");
//	post.setPostType("ˮһ��");
//	post.setSenderId(1);
//
//	PostContent pContent=new PostContent();
//	pContent.textContent="���ɵ�ĵڶ����������ģ����";
//	List<String> imgs = new ArrayList<>();
//	String url="C:/Users/10856/Desktop/images/pic";
//	for(int i=1;i<=10;i++){
//	    imgs.add(url+i+".png");
//	}
//	pContent.imgsContent=imgs;
//	post.setPostContent(pContent);

//	bean.doAddPost(post);

//	bean.doDeletePost(5);
	UserInfo userInfo=new UserInfo();
	userInfo.setUserId(1);
	bean.doDeletePosts(userInfo);
    }

}
