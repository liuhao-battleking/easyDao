package easybuy.model;

import java.util.List;

public class MyCateItem {
    private int cateid;
    private String catename;
    private List<MyCateItem> child;
	public int getCateid() {
		return cateid;
	}
	public void setCateid(int cateid) {
		this.cateid = cateid;
	}
	public String getCatename() {
		return catename;
	}
	public void setCatename(String catename) {
		this.catename = catename;
	}
	public List<MyCateItem> getChild() {
		return child;
	}
	public void setChild(List<MyCateItem> child) {
		this.child = child;
	}
	@Override
	public String toString() {
		return "MyCateItem [cateid=" + cateid + ", catename=" + catename + ", child=" + child + "]";
	}
    
}
