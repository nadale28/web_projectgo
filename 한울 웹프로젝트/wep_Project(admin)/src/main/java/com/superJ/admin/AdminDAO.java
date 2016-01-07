package com.superJ.admin;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
public class AdminDAO

{
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	private PageAdminInfo page;
	public void inserAdmin(AdminDTO AdminDTO){
		sqlSessionTemplate.insert("admin_insert",AdminDTO);
	}
	public List<AdminDTO> admin_select(){
		return sqlSessionTemplate.selectList("admin_select");
	}

	public List<AdminDTO> member_select(PageAdminInfo PageAdminInfo){
		
		return sqlSessionTemplate.selectList("pageAdminInfo",PageAdminInfo);
	}
	public int totalCount(){
		
		return sqlSessionTemplate.selectOne("cnt");
	}
	public List<AdminDTO> members_select(){
		return sqlSessionTemplate.selectList("members_select");
	}
	public void deleteAdmin(String id){
		 sqlSessionTemplate.delete("admin_delete",id);
	}

public int reply_cnt(){
		return sqlSessionTemplate.selectOne("reply_cnt");
	}
	public List<BoardDTO> reply_list(ReplyPageInfo ReplyPageInfo) {
		return sqlSessionTemplate.selectList("reply_list", ReplyPageInfo);
	}
	
	public void reply_delete(String rdate){
		sqlSessionTemplate.delete("reply_delete",rdate);
	}
	
}

