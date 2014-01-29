package com.example.actions;
 
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.InterceptorRef;
import com.opensymphony.xwork2.ActionSupport;
 
public class HelloWorld extends ActionSupport {
  private String message;
 
  public String getMessage() {
    return message;
  }
  

//------------------------------------------------------------------
//使用URL
//http://localhost:8080/conventionTest/hello-world.action
//返回zero時，轉發/WEB-INF/content/hello-world-zero.jsp
//返回success時，轉發  第一順位/WEB-INF/content/hello-world-success.jsp
//　　　　　　　　　　　　第二順位/WEB-INF/content/hello-world.jsp
//注意:使用@Action覆寫掉Convention預設的URL mapping之後，使用上面的URL就不會再呼叫這個execute函式
//而是直接轉發/WEB-INF/content/hello-world.jsp，從這一頁JSP不會打印message的值就能知道真的跳過了execute函式
//------------------------------------------------------------------
//使用URL
//http://localhost:8080/conventionTest/different/url.action
//返回zero時，轉發/WEB-INF/content/different/url-zero.jsp
//返回success時，轉發　第一順位/WEB-INF/content/different/url-success.jsp
//                   第二順位/WEB-INF/content/different/url.jsp
//------------------------------------------------------------------
//使用URL
//http://localhost:8080/conventionTest/another/url.action
//返回zero時，轉發/WEB-INF/content/another/url-zero.jsp
//返回success時，轉發  第一順位/WEB-INF/content/another/url-success.jsp
//                   第二順位/WEB-INF/content/another/url.jsp
  @Actions({
		@Action("/different/url"),
		@Action("/another/url"),
		@Action(interceptorRefs={@InterceptorRef("validation"), @InterceptorRef("defaultStack")})
  })  
  public String execute() {
	    if (System.currentTimeMillis() % 2 == 0) {
	      message = "It's 0";
	      return "zero";
	    }
	 
	    message = "It's 1";
	    return SUCCESS;
  }

//以下寫法在url的前面少了一根「/」
//這表示namespace是由HelloWorld類別所在的包名計算出來的
//本類別的全名是com.example.actions.HelloWorld
//Convention插件會把包名裡的「action」「actions」「struts」和「struts2」這四個字當成ROOT
//也就是你這個HelloWorld類別現在的namespace是/
//所以在這裡寫成@Action("url")和寫成@Action("/url")作用是一樣的
//使用URL
//http://localhost:8080/conventionTest/url.action
//返回success時，轉發  第一順位/WEB-INF/content/url-success.jsp
//                   第二順位/WEB-INF/content/url.jsp
@Action("url")
  public String doSomething() {
    return SUCCESS;
  }

}