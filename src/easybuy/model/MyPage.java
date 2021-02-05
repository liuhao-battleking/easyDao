package easybuy.model;

import java.util.List;

public class MyPage<T> {
  
	 private int pageNo;
	 private int pageSize = 8;
	 
	 private int totalNum;
	 
	 private int totalPages;
	
	 private List<T> list;
	 
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		if(pageNo<=0){
			this.pageNo = 1;
		}else if(pageNo>getTotalPages()){
			this.pageNo = this.getTotalPages();
		}else{
			this.pageNo = pageNo;
		}
		
	}
	public int getPageSize() {
		return 8;
	}
	
	
	
	
	
	public int getTotalPages() {
		return totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "MyPage [pageNo=" + pageNo + ", pageSize=" + pageSize + ", totalNum=" + totalNum + ", list=" + list
				+ "]";
	}
	
	
	 
}
