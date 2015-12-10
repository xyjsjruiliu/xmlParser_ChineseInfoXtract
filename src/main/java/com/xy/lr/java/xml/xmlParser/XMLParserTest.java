package com.xy.lr.java.xml.xmlParser;

import org.dom4j.DocumentException;

import com.xy.lr.java.xml.xmlParser.entity.ChineseWordSegment;
import com.xy.lr.java.xml.xmlParser.entity.XMLDocument;

/**
 * @author xylr
 * 
 * XMLParserTest 测试类
 * */
public class XMLParserTest {
	public static void main(String[] args) throws DocumentException{
		XMLParser xmlParser = new XMLParser();
		XMLDocument xmlDocument = xmlParser.xmlParser("data/document.xml");
		
		System.out.println("----------------------------分割线----------------------------");
//		xmlParser.printAll(xmlDocument);
		
		ChineseWordSegment chineseWordSegment = xmlParser.wordSegment(xmlDocument);
		chineseWordSegment.printAll();
	}
}
