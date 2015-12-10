package com.xy.lr.java.xml.xmlParser.entity;

import java.util.ArrayList;

/**
 * @author xylr
 * 
 * ChineseWordSegmentation 类
 * */
public class ChineseWordSegment {
	//分词结果
	private ArrayList<XMLWord> xmlWords;
	
	/**
	 * 构造函数，初始化
	 * */
	public ChineseWordSegment() {
		this.xmlWords = new ArrayList<XMLWord>();
	}
	
	/**
	 * 根据一级的Token列表，得到分词结果
	 * */
	public void setWordSegmentList ( ArrayList<Token> tokenList ) {
		for ( Token token : tokenList ) {
			//如果Token的类型是Atom
			if ( token.getTokenType().equals("atom") ) {
				//设置分词结果
				setWordSegmentByAtomToken(token);
//				System.out.println( token.getTokenType() + "\t" + token.getTokenData() );
			}
			//如果Token的类型是Group
			else if( token.getTokenType().equals("group") ) {
				setWordSegmentByGroupToken(token);
			}
		}
	}
	
	/**
	 * 从Atom类型的Token中抽取出分词结果
	 * */
	private void setWordSegmentByAtomToken ( Token atomToken ) {
		XMLWord xmlword = new XMLWord();
		//设置新的分词结果
		boolean answer = xmlword.setXMLWordByToken(atomToken);
		//去除停用词的操作
		if( answer == true ) {
			xmlWords.add(xmlword);
		}
		else {
			xmlword = null;
		}
	}
	
	/**
	 * 从Group类型的Token中抽取出分词结果
	 * */
	public void setWordSegmentByGroupToken ( Token groupToken ) {
		XMLWord xmlword = new XMLWord();
		//TokenList
		ArrayList<Token> tokenList = groupToken.getTokens();
		//Feature
		Feature features = groupToken.getFeature();
		
		boolean flag = false;
		//遍历groupToken里面的所有TokenList
		for (Token token : tokenList) {
			if(token.getTokenType().equals("group")){
				flag = true;
			}
		}
		//表示这个Group类型的Token里面没有Group类型的Token
		if(!flag) {
			xmlword.setXMLWordByTokenList(tokenList, features);
			xmlWords.add(xmlword);
		}
		//表示这个Group类型的Token里面有Group类型的Token
		else {
			for (Token token : tokenList) {
				//发现Group类型的Token，递归遍历
				if(token.getTokenType().equals("group")){
					setWordSegmentByGroupToken(token);
				}
				//发现Atom类型的Token
				else {
					xmlword.setXMLWordByToken(token);
					xmlWords.add(xmlword);
				}
			}
		}
	}
	
	/**
	 * 输出结果
	 * */
	public void printAll () {
		for (XMLWord xmlWord : xmlWords) {
			xmlWord.printAll();
			System.out.println();
		}
	}

	public ArrayList<XMLWord> getXmlWords() {
		return xmlWords;
	}

	public void setXmlWords(ArrayList<XMLWord> xmlWords) {
		this.xmlWords = xmlWords;
	}
}
