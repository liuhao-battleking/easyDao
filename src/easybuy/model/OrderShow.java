package easybuy.model;

import java.util.Date;

public class OrderShow {
private int ordershowid;
private int userid;
private String username;
private String useraddress;
private Date createtime;
private double cost;
private int status;
private int type;
public int getOrdershowid() {
	return ordershowid;
}
public void setOrdershowid(int ordershowid) {
	this.ordershowid = ordershowid;
}
public int getUserid() {
	return userid;
}
public void setUserid(int userid) {
	this.userid = userid;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getUseraddress() {
	return useraddress;
}
public void setUseraddress(String useraddress) {
	this.useraddress = useraddress;
}
public Date getCreatetime() {
	return createtime;
}
public void setCreatetime(Date createtime) {
	this.createtime = createtime;
}
public double getCost() {
	return cost;
}
public void setCost(double cost) {
	this.cost = cost;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}
public int getType() {
	return type;
}
public void setType(int type) {
	this.type = type;
}
@Override
public String toString() {
	return "OrderShow [ordershowid=" + ordershowid + ", userid=" + userid + ", username=" + username + ", useraddress="
			+ useraddress + ", createtime=" + createtime + ", cost=" + cost + ", status=" + status + ", type=" + type
			+ "]";
}

}
