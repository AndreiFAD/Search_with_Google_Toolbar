/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.fadcafe;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import org.xml.sax.InputSource;
/**
 *
 * @author Fekete András Demeter 
 */
public class GoogleSuggest_Test {
static List<String> a = new ArrayList<String>();

static String myWordsCombinations = "iphone,iphone price"; //
    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) {
        
         try {
          String[] kulcsszoArray =myWordsCombinations.split(",");
          
            for (int i = 0; i < kulcsszoArray.length; i++) {
                
                String[] myStringArray = new String[]{
                     "","0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","ƒ","g","h","i","j","k","l","m","n","o","p","q","r","s","š","t","u","v","w","x","y","Ÿ","z","ž","ß","á","â","ä","ç","é","ë","í","î","ó","ô","ö","ú","ü","ý","ă","ą","ć","č","ď","đ","ę","ě","ĺ","ľ","ł","ń","ň","ő","ŕ","ř","ś","ş","š","ţ","ť","ů","ű","ź","ż","ž"
                    //   "","0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"
                };

              for (String myStringArray1 : myStringArray) {
                  String urlName = "http://google.hu/complete/search?hl=en&output=toolbar&q=";
                  String urlNamex;
                  if (myStringArray1 == "") {
                      urlNamex = URLEncoder.encode(kulcsszoArray[i] + myStringArray1, "UTF-8");
                  } else {
                      urlNamex = URLEncoder.encode(kulcsszoArray[i] + " " + myStringArray1, "UTF-8");
                  }
                  URL url = new URL(urlName+urlNamex);
                  URLConnection conn = url.openConnection();
                  conn.setRequestProperty(
                          "User-Agent",
                          "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
                  BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                  String line = in.readLine();
                  in.close();
                  DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                  DocumentBuilder builder = factory.newDocumentBuilder();
                  InputSource is = new InputSource(new StringReader(line));
                  Document doc = builder.parse(is);
                  if (doc.hasChildNodes()) {
                      
                      printNote(doc.getChildNodes());
                  }
                  Thread.sleep(100);
              }
              Thread.sleep(10000);
            }
            System.out.println(a.size());
             for (int i = 0; i < a.size()-1; i++) {
                 System.out.println(a.get(i));
             }
          
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    }
     private static void printNote(NodeList nodeList) {

    for (int count = 0; count < nodeList.getLength(); count++) {

	Node tempNode = nodeList.item(count);

	if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

		if (tempNode.hasAttributes()) {
			NamedNodeMap nodeMap = tempNode.getAttributes();

			for (int i = 0; i < nodeMap.getLength(); i++) {

				Node node = nodeMap.item(i);

                                if (a.contains(node.getNodeValue())) {
                                    
                                } else {
                                    a.add(node.getNodeValue());
                                }
			}
		}

		if (tempNode.hasChildNodes()) {
			printNote(tempNode.getChildNodes());
		}
	}
    }
  }

}
