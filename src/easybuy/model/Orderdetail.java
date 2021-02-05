package easybuy.model;

public class Orderdetail {
private int orderdetailid;
private int orderid;
private int productid;
private String productname;
private String productpic;
private int quantity;
private double cost;

public String getProductname() {
	return productname;
}
public void setProductname(String productname) {
	this.productname = productname;
}
public String getProductpic() {
	return productpic;
}
public void setProductpic(String productpic) {
	this.productpic = productpic;
}
public int getOrderdetailid() {
	return orderdetailid;
}
public void setOrderdetailid(int orderdetailid) {
	this.orderdetailid = orderdetailid;
}
public int getOrderid() {
	return orderid;
}
public void setOrderid(int orderid) {
	this.orderid = orderid;
}
public int getProductid() {
	return productid;
}
public void setProductid(int productid) {
	this.productid = productid;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
public double getCost() {
	return cost;
}
public void setCost(double cost) {
	this.cost = cost;
}
@Override
public String toString() {
	return "orderdetail [orderdetailid=" + orderdetailid + ", orderid=" + orderid + ", productid=" + productid
			+ ", quantity=" + quantity + ", cost=" + cost + "]";
}

}
