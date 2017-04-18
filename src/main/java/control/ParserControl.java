package control;

import java.util.ArrayList;
import java.util.List;

import dao.Musicinfo;
import parser.BookParser;
import parser.DouListParser;
import parser.FocusInfoParser;
import parser.GroupInfoParser;
import parser.LikeInfoParser;
import parser.MinSiteParser;
import parser.MovieParser;
import parser.MusicParser;
import parser.RevLinkParser;
import utils.MonitorHelper;
import utils.ParserThread;

public class ParserControl {
	
	List<ParserThread>  parserThreads;
	MonitorHelper mh;

	public ParserControl() {
		parserThreads=new ArrayList<>();
		parserThreads.add(new ParserThread(new BookParser(7)));
		parserThreads.add(new ParserThread(new LikeInfoParser(5)));
		parserThreads.add(new ParserThread(new RevLinkParser(3)));
		mh=new MonitorHelper(parserThreads);
	}
	
	
	public void startParser(){
		for(ParserThread t:parserThreads){
			t.start();
		}
		mh.init();
	}
	
	public static void main(String[] args) {
		ParserControl pc=new ParserControl();
		pc.startParser();
	}

}
