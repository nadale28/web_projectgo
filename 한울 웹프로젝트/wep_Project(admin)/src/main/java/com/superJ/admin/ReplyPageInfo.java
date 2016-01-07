package com.superJ.admin;

import java.util.ArrayList;

public class ReplyPageInfo {
	final public int pageList = 7;//�������� ������ �Խñۼ� 
	final public int pageBlock = 3;//���� ������ ��������
	public int getPageBlock() {
		return pageBlock;
	}

	private int totalList;//��ü �Խñ� ��
	private int totalPage;//��ü ������ �� ���
	private int totalBlock;//��ü �� �� ���
	private int curPage;//���� ��������ȣ
	private int curBlock;//���� ����ȣ
	private int beginPage;//������������ ������������ȣ ���
	private int endPage;//������������ ����������ȣ ���
	public int begin, end;
	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getPageList() {
		return pageList;
	}

	ArrayList<BoardDTO> list;//������ �Խñ� ���

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

	public ArrayList<BoardDTO> getList() {
		return list;
	}

	public void setList(ArrayList<BoardDTO> list) {
		this.list = list;
	}
}
