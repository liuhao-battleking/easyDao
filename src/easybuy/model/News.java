package easybuy.model;

import java.util.Date;

public class News {
   private int newsid;
   private String title;
   private String content;
   private Date createtime;
public int getNewsid() {
	return newsid;
}
public void setNewsid(int newsid) {
	this.newsid = newsid;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public Date getCreatetime() {
	return createtime;
}
public void setCreatetime(Date createtime) {
	this.createtime = createtime;
}
@Override
public String toString() {
	return "news [newsid=" + newsid + ", title=" + title + ", content=" + content + ", createtime=" + createtime + "]";
}
   
}
