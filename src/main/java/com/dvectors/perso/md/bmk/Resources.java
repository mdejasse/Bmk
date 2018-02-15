package com.dvectors.perso.md.bmk;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Utilities;

import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class Resources {

    private final float extRadius;
    private final float inRadius;
    private final Float guidelineRadius;
    private final String upText;
    private final String bottomImage;
    private final String fontUpText;
    private final String fontGuestF;
    private final String fontGuestM;
    private final String fontGuestTitle;
    private final BaseColor colorUpText;
    private final BaseColor colorGuestName;
    private final BaseColor colorGuestTitle;


    private final static String UP_TEXT = "UP_TEXT";
    private final static String BOTTOM_IMG = "BOTTOM_IMG";
    private final static String EXT_RADIUS = "EXT_RADIUS";
    private final static String IN_RADIUS = "IN_RADIUS";
    private final static String GUIDELINES_RADIUS = "GUIDELINES_RADIUS";
    private final static String FONT_UP_TEXT = "FONT_UP_TEXT";
    private final static String FONT_GUEST_F = "FONT_GUEST_F";
    private final static String FONT_GUEST_M = "FONT_GUEST_M";
    private final static String FONT_GUEST_TITLE = "FONT_GUEST_TITLE";
    private final static String COLOR_UP_TEXT = "COLOR_UP_TEXT";
    private final static String COLOR_GUEST_NAME = "COLOR_GUEST_NAME";
    private final static String COLOR_GUEST_TITLE = "COLOR_GUEST_TITLE";

    private static Resources INSTANCE = null;


    public final static BaseColor CUSTOM_DARK_GREY = new BaseColor(100, 100, 100);
    public final static BaseColor CUSTOM_LIGHT_GRAY = new BaseColor(135, 135, 135);


    private Resources(Properties props) {
        this.upText = props.getProperty(UP_TEXT);

        this.bottomImage = props.getProperty(BOTTOM_IMG);

        if (props.getProperty(EXT_RADIUS) != null) {
            this.extRadius = Utilities.millimetersToPoints(Float.parseFloat(props.getProperty(EXT_RADIUS)));
        } else {
            this.extRadius = Utilities.millimetersToPoints(45f / 2f);
        }

        if (props.getProperty(IN_RADIUS) != null) {
            this.inRadius = Utilities.millimetersToPoints(Float.parseFloat(props.getProperty(IN_RADIUS)));
        } else {
            this.inRadius = Utilities.millimetersToPoints(24f / 2f);
        }

        if (props.getProperty(GUIDELINES_RADIUS) != null) {
            this.guidelineRadius = Utilities.millimetersToPoints(Float.parseFloat(props.getProperty(GUIDELINES_RADIUS)));
        } else {
            this.guidelineRadius = null;
        }

        this.fontUpText = props.getProperty(FONT_UP_TEXT) != null ? props.getProperty(FONT_UP_TEXT):"AbadiMTCondensed.ttf";
        this.fontGuestF = props.getProperty(FONT_GUEST_F) != null ? props.getProperty(FONT_GUEST_F):"Pacifico-Regular.ttf";
        this.fontGuestM = props.getProperty(FONT_GUEST_M) != null ? props.getProperty(FONT_GUEST_M):"VastShadow-Regular.ttf";
        this.fontGuestTitle = props.getProperty(FONT_GUEST_TITLE) != null ? props.getProperty(FONT_GUEST_TITLE):"AmaticSC-Regular.ttf";

        this.colorUpText = props.getProperty(COLOR_UP_TEXT) != null ? getBaseColor(props.getProperty(COLOR_UP_TEXT)):BaseColor.GRAY;
        this.colorGuestName = props.getProperty(COLOR_GUEST_NAME) != null ? getBaseColor(props.getProperty(COLOR_GUEST_NAME)):CUSTOM_DARK_GREY;
        this.colorGuestTitle = props.getProperty(COLOR_GUEST_TITLE) != null ? getBaseColor(props.getProperty(COLOR_GUEST_TITLE)):CUSTOM_LIGHT_GRAY;
    }

    private BaseColor getBaseColor(String rgb) {
        String colors[] = rgb.split(";");
        return new BaseColor(Integer.parseInt(colors[0]),Integer.parseInt(colors[1]),Integer.parseInt(colors[2]));
    }

    public static Resources getInstance() {
        if (INSTANCE != null) {
            return INSTANCE;
        } else {
            Properties props = loadProperties();
            INSTANCE = new Resources(props);
            return INSTANCE;
        }
    }

    public float getExtRadius() {
        return extRadius;
    }

    public float getInRadius() {
        return inRadius;
    }

    public Float getGuidelineRadius() {
        return guidelineRadius;
    }

    public String getUpText() {
        return upText;
    }

    public String getBottomImage() {
        return bottomImage;
    }

    public String getFontUpText() {
        return fontUpText;
    }

    public String getFontGuestF() {
        return fontGuestF;
    }

    public String getFontGuestM() {
        return fontGuestM;
    }

    public String getFontGuestTitle() {
        return fontGuestTitle;
    }

    public BaseColor getColorUpText() {
        return colorUpText;
    }

    public BaseColor getColorGuestName() {
        return colorGuestName;
    }

    public BaseColor getColorGuestTitle() {
        return colorGuestTitle;
    }

    private static Properties loadProperties() {
        try {
            Properties props = new Properties();
            props.load(Resources.class.getResourceAsStream("/bmk.properties"));
            return props;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
