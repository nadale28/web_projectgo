package com.example.slidingsimplesample;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.example.slidingsimplesample.R;

import android.content.Context;
import android.os.AsyncTask;
import android.provider.Telephony.Sms.Conversations;
import android.view.View;
import android.widget.ListView;

public class ParsingTask extends AsyncTask<String, Void, Void> {
	ListView lv;
	Context context;
	public ParsingTask(Context context, ListView lv) {
		this.context = context;
		this.lv = lv;
	}
	
	RSSHandler myRSSHandler=null;
    protected Void doInBackground(String... urls) {
        try {
             
            URL rssUrl = new URL(urls[0]);
            SAXParserFactory mySAXParserFactory = SAXParserFactory.newInstance();
            SAXParser mySAXParser = mySAXParserFactory.newSAXParser();
            XMLReader myXMLReader = mySAXParser.getXMLReader();
            myRSSHandler = new RSSHandler();
            myXMLReader.setContentHandler(myRSSHandler);
            InputSource myInputSource = new InputSource(rssUrl.openStream());
            myXMLReader.parse(myInputSource);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
     
    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        ArrayList<Book_info> book_info = new ArrayList<Book_info>();
        book_info = myRSSHandler.getBook_list();
        MyAdapter adapter = new MyAdapter(context, R.layout.list_layout_search, book_info);
        lv.setAdapter(adapter);
    }
}
