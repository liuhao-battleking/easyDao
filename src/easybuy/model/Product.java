package easybuy.model;

public class Product {
   private int productid;
   private String name;
   private String description;
   private double price;
   private int stock;
   private int cateid;
   private int catechildid;
   private String filename;
public int getProductid() {
	return productid;
}
public void setProductid(int productid) {
	this.productid = productid;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
public int getStock() {
	return stock;
}
public void setStock(int stock) {
	this.stock = stock;
}
public int getCateid() {
	return cateid;
}
public void setCateid(int cateid) {
	this.cateid = cateid;
}
public int getCatechildid() {
	return catechildid;
}
public void setCatechildid(int catechildid) {
	this.catechildid = catechildid;
}
public String getFilename() {
	return filename;
}
public void setFilename(String filename) {
	this.filename = filename;
}
@Override
public String toString() {
	return "product [productid=" + productid + ", name=" + name + ", description=" + description + ", price=" + price
			+ ", stock=" + stock + ", cateid=" + cateid + ", catechildid=" + catechildid + ", filename=" + filename
			+ "]";
}
   
   
}
