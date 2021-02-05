package easybuy.model;

import java.util.Date;

public class Comment {
  private int commentid;
  private String reply;
  private String content;
  private Date createtime;
  private Date replytime;
  private String nickname;
  private int status;
  
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}
public int getCommentid() {
	return commentid;
}
public void setCommentid(int commentid) {
	this.commentid = commentid;
}
public String getReply() {
	return reply;
}
public void setReply(String reply) {
	this.reply = reply;
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
public Date getReplytime() {
	return replytime;
}
public void setReplytime(Date replytime) {
	this.replytime = replytime;
}
public String getNickname() {
	return nickname;
}
public void setNickname(String nickname) {
	this.nickname = nickname;
}
@Override
public String toString() {
	return "comment [commentid=" + commentid + ", reply=" + reply + ", content=" + content + ", createtime="
			+ createtime + ", replytime=" + replytime + ", nickname=" + nickname + "]";
}
  
}
