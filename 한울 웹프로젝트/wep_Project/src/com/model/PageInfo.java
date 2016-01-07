package com.model;

import java.util.*;
public class PageInfo {
	final public int pageList = 10;//페이지당 보여질 게시글수 getter 
	final public int pageBlock = 5;//블럭당 보여질 페이지수 getter
	public int totalList;//전체 게시글 수 getter,setter
	public int totalPage;//전체 페이지 수 계산
	public int totalBlock;//전체 블럭 수 계산
	public int curPage;//현재 페이지번호 getter,setter
	public int curBlock;//현재 블럭번호 getter,setter
	public int beginPage;//현재페이지의 시작페이지번호 계산
	public int endPage;//현재페이지의 끝페이지번호 계산
	
	ArrayList<NoticeBoardDTO> list;//보여질 게시글 목록
	
	public int getPageList() {
		return pageList;
	}
	public int getPageBlock() {
		return pageBlock;
	}

	public int getTotalList() {
		return totalList;
	}

	public void setTotalList(int totalList) {
		this.totalList = totalList;
	}

	public int getTotalPage() {
		return totalPage;
	}

	//전체페이지수 계산
	public void setTotalPage() {
		//전체페이지수 = 총게시글수/page당 게시글 수
		//              나머지=0 => 몫, 나머지>0 => 몫+1 
		this.totalPage = totalList / pageList
				+ (totalList%pageList==0 ? 0 : 1) ;
	}

	public int getTotalBlock() {
		return totalBlock;
	}
	
	//전체블럭수 계산
	public void setTotalBlock() {
		// page당게시글수*블럭당페이지수 = 블럭당 총게시글의 수
		//전체블럭수 = 총게시글수/(page당게시글수*블럭당페이지수)
		//              나머지=0 => 몫, 나머지>0 => 몫+1
		if( totalList % (pageList*pageBlock) ==0)
			this.totalBlock = totalList/(pageList*pageBlock);
		else
			this.totalBlock = totalList/(pageList*pageBlock) + 1;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getCurBlock() {
		return curBlock;
	}

	public void setCurBlock(int curBlock) {
		this.curBlock = curBlock;
	}

	public int getBeginPage() {
		return beginPage;
	}

	//현재페이지에서 보여질 시작페이지 계산
	public void setBeginPage() {
		//3*(현재블럭-1)+1
		this.beginPage = pageBlock*(curBlock-1)+1;
	}

	public int getEndPage() {
		return endPage;
	}

	//현재페이지에서 보여질 끝페이지 계산
	public void setEndPage() {
		//3*(현재블럭)
		this.endPage = pageBlock*curBlock;
	}

	public ArrayList<NoticeBoardDTO> getList() {
		return list;
	}

	public void setList(ArrayList<NoticeBoardDTO> list) {
		this.list = list;
	}

	
	
	
}
