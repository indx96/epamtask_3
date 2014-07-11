package com.epam.airline.parsing;


import com.epam.airline.parsing.validation.PlaneValidator;
import com.epam.airline.planes.Airliner;
import com.epam.airline.planes.Fighter;
import com.epam.airline.planes.Plane;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by ivan on 7/7/14.
 */
class DOMParser implements PlaneParser {

    DOMParser(){}

    @Override
    public Iterable<Plane> parse(String pathToXML, String pathToXSD)
            throws IOException, SAXException, ParserConfigurationException {
        PlaneValidator.getInstance().validate(pathToXML, pathToXSD);

        File fXmlFile = new File(pathToXML);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);

        doc.getDocumentElement().normalize();

        NodeList nodeList = doc.getChildNodes().item(0).getChildNodes();
        LinkedList<Plane> planes = new LinkedList<>();
        for (int planeIndex = 0; planeIndex < nodeList.getLength(); planeIndex++) {
            Node node = nodeList.item(planeIndex);

            String nodeName = node.getNodeName();

            switch (nodeName) {

                case "plane": {
                    planes.add(parsePlane((Element) node));
                    break;
                }

                case "fighter": {
                    planes.add(parseFighter((Element) node));
                    break;
                }

                case "airliner": {
                    planes.add(parseAirliner((Element) node));
                    break;
                }
            }
        }
        return planes;
    }

    private Plane parsePlane(Element element) {
        String name = element.getElementsByTagName("name").item(0).getTextContent();
        float length = Float.parseFloat(element.getElementsByTagName("length").item(0).getTextContent());
        float height = Float.parseFloat(element.getElementsByTagName("height").item(0).getTextContent());
        float wingspan = Float.parseFloat(element.getElementsByTagName("wingspan").item(0).getTextContent());
        float flyRange = Float.parseFloat(element.getElementsByTagName("fly-range").item(0).getTextContent());
        float tankeSize = Float.parseFloat(element.getElementsByTagName("tanke-size").item(0).getTextContent());
        return new Plane.Builder(name, length, height, wingspan, flyRange, tankeSize).build();
    }

    private Fighter parseFighter(Element element) {
        Plane basePlane = parsePlane(element);
        Element weaponMap = (Element) element.getElementsByTagName("weapons").item(0);
        HashMap<Fighter.WeaponType, Integer> map = new HashMap<>();
        int bombsCount = Integer.parseInt(weaponMap.getElementsByTagName("bombs").item(0).getTextContent());
        int gunsCount = Integer.parseInt(weaponMap.getElementsByTagName("guns").item(0).getTextContent());
        map.put(Fighter.WeaponType.BOMBS, bombsCount);
        map.put(Fighter.WeaponType.GUNS, gunsCount);
        return new Fighter.Builder(basePlane, map).build();
    }

    private Airliner parseAirliner(Element element) {
        Plane basePlane = parsePlane(element);
        int crewAmount = Integer.parseInt(element.getElementsByTagName("crew-amount").item(0).getTextContent());
        int passengersAmount = Integer.parseInt(element.getElementsByTagName("passengers-amount").item(0).getTextContent());
        return new Airliner.Builder(basePlane, crewAmount, passengersAmount).build();
    }
}
