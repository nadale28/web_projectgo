package com.model;

import java.util.*;
public class PageInfo {
	final public int pageList = 10;//�������� ������ �Խñۼ� getter 
	final public int pageBlock = 5;//���� ������ �������� getter
	public int totalList;//��ü �Խñ� �� getter,setter
	public int totalPage;//��ü ������ �� ���
	public int totalBlock;//��ü �� �� ���
	public int curPage;//���� ��������ȣ getter,setter
	public int curBlock;//���� ����ȣ getter,setter
	public int beginPage;//������������ ������������ȣ ���
	public int endPage;//������������ ����������ȣ ���
	
	ArrayList<NoticeBoardDTO> list;//������ �Խñ� ���
	
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

	//��ü�������� ���
	public void setTotalPage() {
		//��ü�������� = �ѰԽñۼ�/page�� �Խñ� ��
		//              ������=0 => ��, ������>0 => ��+1 
		this.totalPage = totalList / pageList
				+ (totalList%pageList==0 ? 0 : 1) ;
	}

	public int getTotalBlock() {
		return totalBlock;
	}
	
	//��ü���� ���
	public void setTotalBlock() {
		// page��Խñۼ�*������������ = ���� �ѰԽñ��� ��
		//��ü���� = �ѰԽñۼ�/(page��Խñۼ�*������������)
		//              ������=0 => ��, ������>0 => ��+1
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

	//�������������� ������ ���������� ���
	public void setBeginPage() {
		//3*(�����-1)+1
		this.beginPage = pageBlock*(curBlock-1)+1;
	}

	public int getEndPage() {
		return endPage;
	}

	//�������������� ������ �������� ���
	public void setEndPage() {
		//3*(�����)
		this.endPage = pageBlock*curBlock;
	}

	public ArrayList<NoticeBoardDTO> getList() {
		return list;
	}

	public void setList(ArrayList<NoticeBoardDTO> list) {
		this.list = list;
	}

	
	
	
}
