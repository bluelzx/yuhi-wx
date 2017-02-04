package org.jeecgframework.core.common.model.json;

import java.util.Collection;
import java.util.Map;

public class Tree {
private int id;
private String text;
private static String state = "closed";
private Map<String, String> attributes;
private Collection<Tree> children;
public int getId() {
return id;
}
public void setId(int id) {
this.id = id;
}
public String getText() {
return text;
}
public void setText(String text) {
this.text = text;
}
public static String getState() {
return state;
}
public static void setState(String state) {
Tree.state = state;
}
public Map<String, String> getAttributes() {
return attributes;
}
public void setAttributes(Map<String, String> attributes) {
this.attributes = attributes;
}
}