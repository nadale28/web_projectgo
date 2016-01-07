package com.book.test1122;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class RSSHandler extends DefaultHandler {
	
	final int stateUnknown = 0;
    final int stateTitle = 1;
    final int stateAuthor = 2;
    final int stateImg = 3;
    int state = stateUnknown;

    ArrayList<String> title_list = new ArrayList<String>();
    ArrayList<String> author_list = new ArrayList<String>();
    ArrayList<String> img_list = new ArrayList<String>();
    
    ArrayList<Book_info> book_list = new ArrayList<Book_info>();
    
    String title_str="";
    String author_str="";
    String img_str="";
    
    @Override
    public void startDocument() throws SAXException {
    }

    @Override
    public void endDocument() throws SAXException {
    	send_info();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (localName.equalsIgnoreCase("title")) {
            state = stateTitle;
        }else if(localName.equalsIgnoreCase("author")){
        	state = stateAuthor;
        }else if(localName.equalsIgnoreCase("image")){
        	state = stateImg;
        }else {
        	state = stateUnknown;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (localName.equalsIgnoreCase("title")) {
        	//제목이 <b>삼국지</b> 이렇게 와서 제거 해줌
        	String title_str1 = title_str.replaceAll("<b>", "");
        	String title_str2 = title_str1.replaceAll("</b>", " ");
        	title_list.add(title_str2);
        	title_str="";
        }else if(localName.equalsIgnoreCase("author")){
        	author_list.add(author_str);
        	author_str="";
        }else if(localName.equalsIgnoreCase("image")){
        	img_list.add(img_str);
        	img_str="";
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String strCharacters = new String(ch, start, length);
        
        if (state == stateTitle) {
        	title_str += strCharacters;
        }else if(state == stateAuthor){
        	author_str += strCharacters;
        }else if(state == stateImg){
        	img_str += strCharacters;
        }
    }
    
    public void send_info(){
    	for(int i=0; i<author_list.size(); i++){
    		Book_info book = new Book_info();
    		book.setTitle(title_list.get(i+1));
    		book.setAuthor(author_list.get(i));
    		book.setImage_source(img_list.get(i));
    		book_list.add(book);
    	}
    }

	public ArrayList<Book_info> getBook_list() {
		return book_list;
	}
    
}
