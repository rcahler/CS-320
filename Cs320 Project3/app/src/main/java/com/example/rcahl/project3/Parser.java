package com.example.rcahl.project3;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    private static final String ns = null;

    public List parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readFeed(parser);
        } finally {
            in.close();
        }
    }

    private List readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
        List entries = new ArrayList();

        parser.require(XmlPullParser.START_TAG, ns, "feed");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the entry tag
            if (name.equals("entry")) {
                entries.add(readEntry(parser));
            } else {
                skip(parser);
            }
        }
        return entries;
    }

    private Object readEntry(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "entry");
        String event = null;
        String summary = null;
        String effective = null;
        String expires = null;
        String urgency = null;
        String severity = null;
        String certainty = null;
        String link = null;

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("cap:event")) {
                event = readEvent(parser);
            } else if (name.equals("summary")) {
                summary = readSummary(parser);
            } else if (name.equals("cap:effective")) {
                effective = readEffective(parser);
            } else if (name.equals("cap:expires")) {
                expires = readExpires(parser);
            } else if (name.equals("cap:urgency")) {
                urgency = readUrgency(parser);
            } else if (name.equals("cap:severity")) {
                severity = readSeverity(parser);
            } else if (name.equals("cap:certainty")) {
                certainty = readCert(parser);
            } else if (name.equals("link")) {
                link = readLink(parser);
            } else {
                skip(parser);
            }

        }
        return new Entry(event, summary, effective, expires, urgency, severity, certainty, link);
    }

    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }

    }

    private String readLink(XmlPullParser parser) throws IOException, XmlPullParserException {
        String link = "";
        parser.require(XmlPullParser.START_TAG, ns, "link");
        String tag = parser.getName();
        String relType = parser.getAttributeValue(null, "rel");
        if (tag.equals("link")) {
            if (relType.equals("alternate")) {
                link = parser.getAttributeValue(null, "href");
                parser.nextTag();
            }
        }
        parser.require(XmlPullParser.END_TAG, ns, "link");
        return link;

    }

    private String readCert(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "cap:certainty");
        String string = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "cap:certainty");
        return string;
    }

    private String readSeverity(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "cap:severity");
        String string = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "cap:severity");
        return string;
    }

    private String readUrgency(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "cap:urgency");
        String string = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "cap:urgency");
        return string;
    }

    private String readExpires(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "cap:expires");
        String string = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "cap:expires");
        return string;
    }

    private String readEffective(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "cap:effective");
        String string = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "cap:effective");
        return string;
    }

    private String readSummary(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "summary");
        String string = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "summary");
        return string;
    }

    private String readEvent(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "cap:event");
        String string = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "cap:event");
        return string;
    }

    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }
}
