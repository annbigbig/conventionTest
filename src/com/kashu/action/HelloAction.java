package com.kashu.action;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;

public class HelloAction extends ActionSupport {
  @Action("foo")
  public String foo() {
    return "bar";
  }
  
  @Action("foo-bar")
  public String bar() {
    return SUCCESS;
  }
}