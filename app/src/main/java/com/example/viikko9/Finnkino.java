package com.example.viikko9;

import android.view.View;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Finnkino {
    private ArrayList<FinnkinoTheather> finarray = new ArrayList<>();

    private static Finnkino finnkino = new Finnkino();

    private Finnkino() {
    };

    public static Finnkino getInstance() {
        return finnkino;
    }

    public ArrayList<FinnkinoTheather> getTheathers(){
        return finarray;
    }

    public void readXML() {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            String url = "https://www.finnkino.fi/xml/TheatreAreas/";
            Document doc = builder.parse(url);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getDocumentElement().getElementsByTagName("TheatreArea");
            for(int i = 0; i < nodeList.getLength(); i++){
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String id = element.getElementsByTagName("ID").item(0).getTextContent();
                    String place = element.getElementsByTagName("Name").item(0).getTextContent();
                    finarray.add(new FinnkinoTheather(id, place));
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } finally {
            System.out.println("##########");
        }
    }
}
