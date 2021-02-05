package easybuy.model;

public class Category {
private int categoryid;
private String name;
private int parentid;
public int getCategoryid() {
	return categoryid;
}
public void setCategoryid(int categoryid) {
	this.categoryid = categoryid;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getParentid() {
	return parentid;
}
public void setParentid(int parentid) {
	this.parentid = parentid;
}
@Override
public String toString() {
	return "category [categoryid=" + categoryid + ", name=" + name + ", parentid=" + parentid + "]";
}

}
