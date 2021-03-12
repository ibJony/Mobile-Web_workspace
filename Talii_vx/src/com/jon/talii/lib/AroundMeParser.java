package com.jon.talii.lib;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.util.Log;

public class AroundMeParser {
	private static final String tag = "AroundMeParser";
	private static final String FILE_EXTENSION= ".png";
	
	private DocumentBuilderFactory factory;
	private DocumentBuilder builder;
	private final HashMap<String, String> map;
	private final List<AroundMe> list;

	public AroundMeParser() {
		this.list = new ArrayList<AroundMe>();
		this.map = new HashMap<String, String>();
	}

	private String getNodeValue(NamedNodeMap map, String key) {
		String nodeValue = null;
		Node node = map.getNamedItem(key);
		if (node != null) {
			nodeValue = node.getNodeValue();
		}
		return nodeValue;
	}

	public List<AroundMe> getList() {
		return this.list;
	}

	public String getAbbreviation(String place) {
		return (String) this.map.get(place);
	}

	/**
	 * Parse XML file containing body part X/Y/Description
	 * 
	 * @param inStream
	 */
	public void parse(InputStream inStream) {
		try {
			// TODO: after we must do a cache of this XML!!!!
			this.factory = DocumentBuilderFactory.newInstance();
			this.builder = this.factory.newDocumentBuilder();
			this.builder.isValidating();
			Document doc = this.builder.parse(inStream, null);

			doc.getDocumentElement().normalize();

			NodeList placeList = doc.getElementsByTagName("place");
			final int length = placeList.getLength();

			for (int i = 0; i < length; i++) {
				final NamedNodeMap attr = placeList.item(i).getAttributes();
				final String listName = getNodeValue(attr, "name");
				final String listAbbr = getNodeValue(attr, "abbreviation");

				// Construct Country object
				AroundMe around = new AroundMe(listName, listAbbr, listAbbr + FILE_EXTENSION);
				
				// Add to list
				this.list.add(around);
				
				// Creat Map countrname-abbrev
				this.map.put(listName, listAbbr);
				Log.d(tag, around.toString());
			}
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
}

