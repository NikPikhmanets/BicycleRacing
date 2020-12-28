package com.bicycle.racing.gpx;

import com.bicycle.racing.gpx.data.GPX;
import com.bicycle.racing.gpx.data.Route;
import com.bicycle.racing.gpx.data.Waypoint;
import com.bicycle.racing.gpx.data.Track;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class GPXParser {

    public GPX parseGPX(InputStream in) throws Exception {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse(in);
        Node firstChild = doc.getFirstChild();

        if (firstChild != null && GPXConstants.GPX_NODE.equals(firstChild.getNodeName())) {
            GPX gpx = new GPX();
            NamedNodeMap attributes = firstChild.getAttributes();

            for (int i = 0; i < attributes.getLength(); i++) {
                Node attribute = attributes.item(i);

                if (GPXConstants.VERSION_ATTR.equals(attribute.getNodeName())) {
                    gpx.setVersion(attribute.getNodeValue());
                } else if (GPXConstants.CREATOR_ATTR.equals(attribute.getNodeName())) {
                    gpx.setCreator(attribute.getNodeValue());
                }
            }
            NodeList nodes = firstChild.getChildNodes();

            for (int i = 0; i < nodes.getLength(); i++) {
                Node currentNode = nodes.item(i);

                switch (currentNode.getNodeName()) {
                    case GPXConstants.WPT_NODE:
                        Waypoint waypoint = parseWaypoint(currentNode);

                        if (waypoint != null) {
                            gpx.addWaypoint(waypoint);
                        }
                        break;
                    case GPXConstants.TRK_NODE:
                        Track track = parseTrack(currentNode);

                        if (track != null) {
                            gpx.addTrack(track);
                        }
                        break;
                    case GPXConstants.RTE_NODE:
                        Route route = parseRoute(currentNode);

                        if (route != null) {
                            gpx.addRoute(route);
                        }
                        break;
                }
            }
            return gpx;
        }
        return null;
    }

    private LocalDateTime getNodeValueAsDate(Node node) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'kk:mm:ss'Z'");

        return LocalDateTime.parse(node.getFirstChild().getNodeValue(), formatter);
    }

    private Double getNodeValueAsDouble(Node node) {
        return Double.parseDouble(node.getFirstChild().getNodeValue());
    }

    private Integer getNodeValueAsInteger(Node node) {
        return Integer.parseInt(node.getFirstChild().getNodeValue());
    }

    private String getNodeValueAsString(Node node) {
        if (node == null) {
            return null;
        }
        Node child = node.getFirstChild();

        if (child == null) {
            return null;
        }
        return child.getNodeValue();
    }

    private Route parseRoute(Node node) throws Exception {
        if (node == null) {
            return null;
        }
        Route route = new Route();
        NodeList nodes = node.getChildNodes();

        if (nodes != null) {
            for (int i = 0; i < nodes.getLength(); i++) {
                Node currentNode = nodes.item(i);

                switch (currentNode.getNodeName()) {
                    case GPXConstants.NAME_NODE:
                        route.setName(getNodeValueAsString(currentNode));
                        break;
                    case GPXConstants.CMT_NODE:
                        route.setComment(getNodeValueAsString(currentNode));
                        break;
                    case GPXConstants.DESC_NODE:
                        route.setDescription(getNodeValueAsString(currentNode));
                        break;
                    case GPXConstants.SRC_NODE:
                        route.setSrc(getNodeValueAsString(currentNode));
                        break;
                    case GPXConstants.NUMBER_NODE:
                        route.setNumber(getNodeValueAsInteger(currentNode));
                        break;
                    case GPXConstants.TYPE_NODE:
                        route.setType(getNodeValueAsString(currentNode));
                        break;
                    case GPXConstants.RTEPT_NODE:
                        Waypoint waypoint = parseWaypoint(currentNode);

                        if (waypoint != null) {
                            route.addRoutePoint(waypoint);
                        }
                        break;
                }
            }
        }
        return route;
    }

    private Track parseTrack(Node node) throws Exception {
        if (node == null) {
            return null;
        }
        Track track = new Track();
        NodeList nodes = node.getChildNodes();

        if (nodes != null) {
            for (int i = 0; i < nodes.getLength(); i++) {
                Node currentNode = nodes.item(i);

                switch (currentNode.getNodeName()) {
                    case GPXConstants.NAME_NODE:
                        track.setName(getNodeValueAsString(currentNode));
                        break;
                    case GPXConstants.CMT_NODE:
                        track.setComment(getNodeValueAsString(currentNode));
                        break;
                    case GPXConstants.DESC_NODE:
                        track.setDescription(getNodeValueAsString(currentNode));
                        break;
                    case GPXConstants.SRC_NODE:
                        track.setSrc(getNodeValueAsString(currentNode));
                        break;
                    case GPXConstants.NUMBER_NODE:
                        track.setNumber(getNodeValueAsInteger(currentNode));
                        break;
                    case GPXConstants.TYPE_NODE:
                        track.setType(getNodeValueAsString(currentNode));
                        break;
                    case GPXConstants.TRKSEG_NODE:
                        List<Waypoint> segment = parseTrackSegment(currentNode);
                        track.addTrackPoints(segment);
                        track.addTrackSegment(segment);
                        break;
                }
            }
        }
        return track;
    }

    private List<Waypoint> parseTrackSegment(Node node) throws Exception {
        if (node == null) {
            return null;
        }
        List<Waypoint> waypoints = new ArrayList<>();

        NodeList nodes = node.getChildNodes();

        if (nodes != null) {
            for (int i = 0; i < nodes.getLength(); i++) {
                Node currentNode = nodes.item(i);

                if (GPXConstants.TRKPT_NODE.equals(currentNode.getNodeName())) {
                    Waypoint waypoint = parseWaypoint(currentNode);

                    if (waypoint != null) {
                        waypoints.add(waypoint);
                    }
                }
            }
        }
        return waypoints;
    }

    private Waypoint parseWaypoint(Node node) throws Exception {
        if (node == null) {
            return null;
        }
        Waypoint waypoint = new Waypoint();
        NamedNodeMap attributes = node.getAttributes();
        Node latitudeNode = attributes.getNamedItem(GPXConstants.LAT_ATTR);

        if (latitudeNode != null) {
            Double latitude = Double.parseDouble(latitudeNode.getNodeValue());
            waypoint.setLatitude(latitude);
        } else {
            throw new Exception("no lat value in waypoint data.");
        }
        Node longitudeNode = attributes.getNamedItem(GPXConstants.LON_ATTR);

        if (longitudeNode != null) {
            Double longitude = Double.parseDouble(longitudeNode.getNodeValue());
            waypoint.setLongitude(longitude);
        } else {
            throw new Exception("no lon value in waypoint data.");
        }
        NodeList childNodes = node.getChildNodes();

        if (childNodes != null) {
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node currentNode = childNodes.item(i);

                switch (currentNode.getNodeName()) {
                    case GPXConstants.ELE_NODE:
                        waypoint.setElevation(getNodeValueAsDouble(currentNode));
                        break;
                    case GPXConstants.TIME_NODE:
                        waypoint.setTime(getNodeValueAsDate(currentNode));
                        break;
                    case GPXConstants.NAME_NODE:
                        waypoint.setName(getNodeValueAsString(currentNode));
                        break;
                    case GPXConstants.CMT_NODE:
                        waypoint.setComment(getNodeValueAsString(currentNode));
                        break;
                    case GPXConstants.DESC_NODE:
                        waypoint.setDescription(getNodeValueAsString(currentNode));
                        break;
                    case GPXConstants.SRC_NODE:
                        waypoint.setSrc(getNodeValueAsString(currentNode));
                        break;
                    case GPXConstants.MAGVAR_NODE:
                        waypoint.setMagneticDeclination(getNodeValueAsDouble(currentNode));
                        break;
                    case GPXConstants.GEOIDHEIGHT_NODE:
                        waypoint.setGeoidHeight(getNodeValueAsDouble(currentNode));
                        break;
                    case GPXConstants.SYM_NODE:
                        waypoint.setSym(getNodeValueAsString(currentNode));
                        break;
                    case GPXConstants.TYPE_NODE:
                        waypoint.setType(getNodeValueAsString(currentNode));
                        break;
                    case GPXConstants.SAT_NODE:
                        waypoint.setSat(getNodeValueAsInteger(currentNode));
                        break;
                    case GPXConstants.HDOP_NODE:
                        waypoint.setHdop(getNodeValueAsDouble(currentNode));
                        break;
                    case GPXConstants.VDOP_NODE:
                        waypoint.setVdop(getNodeValueAsDouble(currentNode));
                        break;
                    case GPXConstants.PDOP_NODE:
                        waypoint.setPdop(getNodeValueAsDouble(currentNode));
                        break;
                    case GPXConstants.AGEOFGPSDATA_NODE:
                        waypoint.setAgeOfGPSData(getNodeValueAsDouble(currentNode));
                        break;
                    case GPXConstants.DGPSID_NODE:
                        waypoint.setDgpsid(getNodeValueAsInteger(currentNode));
                        break;
                }
            }
        }
        return waypoint;
    }
}
