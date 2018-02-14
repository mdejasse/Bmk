package com.dvectors.perso.md.bmk;


import com.dvectors.perso.md.bmk.pattern.BmkFiller;
import com.dvectors.perso.md.bmk.pattern.Plain;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.util.Properties;

public class BadgeMaker {

    


    private final static Properties PROPERTIES = loadProperties();

    private static Properties loadProperties() {
        try {
            Properties props =  new Properties();
            props.load(BadgeMaker.class.getResourceAsStream("/bmk.properties"));
            return props;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private final static String DATE_STRING = PROPERTIES.getProperty("DATE");
    private final static String BOTTOM_IMAGE = PROPERTIES.getProperty("IMG_RESOURCE");

    private final static float ELEMENTS_GAP = 2.0f;

    private BaseFont ABADI;
    private BaseFont PACIFICO;
    private BaseFont VAST_SHADOW;
    private BaseFont AMATIC;

    private Image eucalyptus;

    private boolean ynEucalyptus = true;
    private boolean ynDate = true;
    private boolean ynInnerCircle = true;



    private float x;
    private float y;
    private float radius;
    private BmkFiller filler;

    private final static BmkFiller inFiller = new Plain(BaseColor.WHITE);


    public BadgeMaker(float x, float y, float radius, BmkFiller filler) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.filler = filler;

        try {
            this.eucalyptus = Image.getInstance(this.getClass().getResource(BOTTOM_IMAGE));
        } catch (BadElementException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            // init Fonts
            byte[] bytesABADI = IOUtils.toByteArray(this.getClass().getResourceAsStream("/AbadiMTCondensed.ttf"));
            ABADI = BaseFont.createFont("AbadiMTCondensed.ttf",BaseFont.WINANSI,true,true,bytesABADI,null);
            byte[] bytesPACIFICO = IOUtils.toByteArray(this.getClass().getResourceAsStream("/Pacifico-Regular.ttf"));
            PACIFICO = BaseFont.createFont( "Pacifico-Regular.ttf",BaseFont.WINANSI,true,true,bytesPACIFICO,null);
            byte[] bytesVAST_SHADOW = IOUtils.toByteArray(this.getClass().getResourceAsStream("/VastShadow-Regular.ttf"));
            VAST_SHADOW = BaseFont.createFont( "VastShadow-Regular.ttf",BaseFont.WINANSI,true,true,bytesVAST_SHADOW,null);
            byte[] bytesAMATIC = IOUtils.toByteArray(this.getClass().getResourceAsStream("/AmaticSC-Regular.ttf"));
            AMATIC = BaseFont.createFont( "AmaticSC-Regular.ttf",BaseFont.WINANSI,true,true,bytesAMATIC,null);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public void run(PdfContentByte cb, Guest guest) throws Exception {
        // Outer circle
        filler.setFilling(cb);
        cb.circle(x, y, radius);
        cb.fillStroke();

        // Inner circle
        if (isYnInnerCircle()) {
            inFiller.setFilling(cb);
            cb.circle(x, y, Resources.IN_RADIUS);
            cb.fill();
        }

        // Guidelines circle
        cb.circle(x,y,Resources.GUIDELINES_RADIUS);
        cb.stroke();


       float elementsPctY = 0.25f;


        // Eucalyptus image
        float eucalyptusY = y - Resources.IN_RADIUS;
        if (isYnEucalyptus()) {
            eucalyptus.scalePercent(((Resources.IN_RADIUS * 2) - Resources.IN_RADIUS * 0.6f) / eucalyptus.getWidth() * 100);
            eucalyptusY = y - Resources.IN_RADIUS + (Resources.IN_RADIUS * elementsPctY);
            eucalyptus.setAbsolutePosition(x - (eucalyptus.getScaledWidth() / 2), eucalyptusY);
            //eucalyptus.setAbsolutePosition(x - (eucalyptus.getScaledWidth()/2) , y - (eucalyptus.getScaledHeight() * (1 + eucalyptusPctY)));
            cb.addImage(eucalyptus);
        }

        // Date
        float dateY = y + Resources.IN_RADIUS;
        if (isYnDate()) {
            float dateHeight = getTextHeight(ABADI, 9, DATE_STRING, true);
            dateY = y + Resources.IN_RADIUS - dateHeight - (Resources.IN_RADIUS * elementsPctY);
            //writeText(cb,DATE_STRING,ABADI,9,x,y+Resources.IN_RADIUS*datePctY,BaseColor.GRAY,null, null);
            writeText(cb, DATE_STRING, ABADI, 9, x, dateY, BaseColor.GRAY);
        }


        if (guest != null) {
            float maxNamesHeigth = dateY - (eucalyptusY + eucalyptus.getScaledHeight()) - ELEMENTS_GAP*2;
            // Name
            BaseFont nameFont = guest.getGender() == Gender.F ? PACIFICO : VAST_SHADOW;
            if (guest.getTitle() == null || guest.getTitle().length() == 0) {
                //writeText(cb, guest.getName(), guest.getGender() == Gender.F ? PACIFICO : VAST_SHADOW, 10, x, y - (Resources.IN_RADIUS *  (1 - eucalyptusPctY)), Resources.CUSTOM_DARK_GREY, Resources.IN_RADIUS * 1.85f, Resources.IN_RADIUS * (datePctY + eucalyptusPctY));
                float fontSize = optimizeFontSize(nameFont, guest.getName(), Resources.IN_RADIUS * 1.85f, maxNamesHeigth, 10.0f);
                float nameHeight = getTextHeight(nameFont, fontSize, guest.getName(), false);
                float nameY = dateY - ELEMENTS_GAP - (maxNamesHeigth / 2f) - (nameHeight / 2f);
                writeText(cb, guest.getName(), nameFont, fontSize, x, nameY, Resources.CUSTOM_DARK_GREY);
            } else { // Name and title
                //Name
                float nFontSize = optimizeFontSize(nameFont, guest.getName(), Resources.IN_RADIUS * 1.85f, maxNamesHeigth * (2f / 3f) - ELEMENTS_GAP, 10.0f);
                float nameHeight = getTextHeight(nameFont, nFontSize, guest.getName(), false);
                float nameY = dateY - ELEMENTS_GAP - (maxNamesHeigth * (1f / 3f)) - (nameHeight / 2f);
                writeText(cb, guest.getName(), nameFont, nFontSize, x, nameY, Resources.CUSTOM_DARK_GREY);

                //Title
                float tFontSize = optimizeFontSize(AMATIC, guest.getTitle(), Resources.IN_RADIUS * 1.85f, maxNamesHeigth * (1f / 3f), 10.0f);
                float titleHeight = getTextHeight(AMATIC, tFontSize, guest.getTitle(), false);
                float titleY = dateY - (ELEMENTS_GAP * 2f) - (maxNamesHeigth * (2f / 3f)) - (maxNamesHeigth * (1f / 6f) - (ELEMENTS_GAP / 2)) - (titleHeight / 2f);
                writeText(cb, guest.getTitle(), AMATIC, tFontSize, x, titleY, Resources.CUSTOM_LIGHT_GRAY);

                // writeText(cb,guest.getTitle(),AMATIC,10,x,y - (Resources.IN_RADIUS * 2 *  (1 - eucalyptusPctY)),BaseColor.LIGHT_GRAY,Resources.IN_RADIUS * 1.85f, (Resources.IN_RADIUS * (datePctY + eucalyptusPctY))/2);
            }
        }

    }


    private void writeText(PdfContentByte cb,String text, BaseFont font, float fontSize,float x, float y, BaseColor color) {
        cb.beginText();
        cb.setFontAndSize(font,fontSize);
        cb.setColorFill(color);
        cb.showTextAligned(Element.ALIGN_CENTER ,text,x,y,0f);
        cb.endText();
    }

    private float getTextHeight(BaseFont font,float fontSize, String text, boolean includeDescent) {
        return font.getAscentPoint(text, fontSize) - (includeDescent?font.getDescentPoint(text, fontSize):0f);
    }

    private float optimizeFontSize(BaseFont font, String text, Float maxWidth, Float maxHeight, float initialFontSize) {
        float fs = initialFontSize;
        float currentWidth = font.getWidthPoint(text,fs);
        float currentHeight = font.getAscentPoint(text,fs) - font.getDescentPoint(text,fs);

        while ((maxWidth != null && currentWidth > maxWidth) || (maxHeight != null && currentHeight > maxHeight) ){
            fs--;
            currentWidth = font.getWidthPoint(text,fs);
            currentHeight = getTextHeight(font,fs,text,true);
        }

        boolean raised = false;
        while ((maxWidth == null || currentWidth < maxWidth) && (maxHeight == null || currentHeight < maxHeight)) {
            fs++;
            currentWidth = font.getWidthPoint(text,fs);
            currentHeight = font.getAscentPoint(text,fs) - font.getDescentPoint(text,fs);
            raised = true;
        }
        if (raised) { // Last iteration exceeds the limits
            fs--;
        }

        if (fs == 0)
           fs = 1;
        return fs;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public void setFiller(BmkFiller filler) {
        this.filler = filler;
    }

    public boolean isYnEucalyptus() {
        return ynEucalyptus;
    }

    public void setYnEucalyptus(boolean ynEucalyptus) {
        this.ynEucalyptus = ynEucalyptus;
    }

    public boolean isYnDate() {
        return ynDate;
    }

    public void setYnDate(boolean ynDate) {
        this.ynDate = ynDate;
    }

    public boolean isYnInnerCircle() {
        return ynInnerCircle;
    }

    public void setYnInnerCircle(boolean ynInnerCircle) {
        this.ynInnerCircle = ynInnerCircle;
    }
}
