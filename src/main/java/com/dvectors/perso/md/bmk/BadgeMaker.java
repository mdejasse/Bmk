package com.dvectors.perso.md.bmk;


import com.dvectors.perso.md.bmk.pattern.BmkFiller;
import com.dvectors.perso.md.bmk.pattern.Plain;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import org.apache.commons.io.IOUtils;

import java.io.IOException;

public class BadgeMaker {


    private final static Resources RESOURCES = Resources.getInstance();


    /*private final static String DATE_STRING = RES.getProperty("DATE");
    private final static String BOTTOM_IMAGE = PROPERTIES.getProperty("BOTTOM_IMG");*/

    private final static float ELEMENTS_GAP = 2.0f;

    private final static float DEFAULT_GAP_UP = RESOURCES.getInRadius()/3f;
    private final static float DEFAULT_GAP_BOTTOM = RESOURCES.getInRadius()/3f;
    
//    private final Resources resources = Resources.getInstance(PROPERTIES);

    private BaseFont FONT_UP_TEXT;
    private BaseFont FONT_GUEST_F;
    private BaseFont FONT_GUEST_M;
    private BaseFont FONT_GUEST_TITLE;

    private Image botttomImage;

    private boolean ynBottomImage = true;
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
            if (RESOURCES.getBottomImage() != null) {
                this.botttomImage = Image.getInstance(this.getClass().getResource("/"+RESOURCES.getBottomImage()));
            }
            else {
                ynBottomImage = false;
            }
        } catch (BadElementException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            // init Fonts
            byte[] bytesFontUpText = IOUtils.toByteArray(this.getClass().getResourceAsStream("/fonts/"+RESOURCES.getFontUpText()));
            FONT_UP_TEXT = BaseFont.createFont(RESOURCES.getFontUpText(), BaseFont.WINANSI, true, true, bytesFontUpText, null);
            byte[] bytesFontGuestF = IOUtils.toByteArray(this.getClass().getResourceAsStream("/fonts/"+RESOURCES.getFontGuestF()));
            FONT_GUEST_F = BaseFont.createFont(RESOURCES.getFontGuestF(), BaseFont.WINANSI, true, true, bytesFontGuestF, null);
            byte[] bytesFontGuestM = IOUtils.toByteArray(this.getClass().getResourceAsStream("/fonts/"+RESOURCES.getFontGuestM()));
            FONT_GUEST_M = BaseFont.createFont(RESOURCES.getFontGuestM(), BaseFont.WINANSI, true, true, bytesFontGuestM, null);
            byte[] bytesFontGuestTitle = IOUtils.toByteArray(this.getClass().getResourceAsStream("/fonts/"+RESOURCES.getFontGuestTitle()));
            FONT_GUEST_TITLE = BaseFont.createFont(RESOURCES.getFontGuestTitle(), BaseFont.WINANSI, true, true, bytesFontGuestTitle, null);
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
            cb.circle(x, y, RESOURCES.getInRadius());
            cb.fill();
        }

        // Guidelines circle
        if (RESOURCES.getGuidelineRadius() != null) {
            cb.circle(x, y, RESOURCES.getGuidelineRadius());
            cb.stroke();
        }


        float elementsPctY = 0.25f;


        // Bottom image
        float bottomImageY = y - RESOURCES.getInRadius() + (RESOURCES.getInRadius() * elementsPctY);;
        if (RESOURCES.getBottomImage() != null) {
            botttomImage.scalePercent(((RESOURCES.getInRadius() * 2) - RESOURCES.getInRadius() * 0.6f) / botttomImage.getWidth() * 100);
            botttomImage.setAbsolutePosition(x - (botttomImage.getScaledWidth() / 2), bottomImageY);
            //botttomImage.setAbsolutePosition(x - (botttomImage.getScaledWidth()/2) , y - (botttomImage.getScaledHeight() * (1 + eucalyptusPctY)));
            cb.addImage(botttomImage);
        }

        // Up text
        float upTextY = y + RESOURCES.getInRadius() - DEFAULT_GAP_UP;
        if (RESOURCES.getUpText() != null) {
            float upTextHeight = getTextHeight(FONT_UP_TEXT, 9, RESOURCES.getUpText(), true);
            upTextY = y + RESOURCES.getInRadius() - upTextHeight - (RESOURCES.getInRadius() * elementsPctY);
            writeText(cb, RESOURCES.getUpText(), FONT_UP_TEXT, 9, x, upTextY, RESOURCES.getColorUpText());
        }


        if (guest != null) {
            float maxNamesHeigth = upTextY - (bottomImageY + (isYnBottomImage()?botttomImage.getScaledHeight():DEFAULT_GAP_BOTTOM)) - ELEMENTS_GAP * 2;
            // Name
            BaseFont nameFont = guest.getGender() == Gender.F ? FONT_GUEST_F : FONT_GUEST_M;
            if (guest.getTitle() == null || guest.getTitle().length() == 0) {
                //writeText(cb, guest.getName(), guest.getGender() == Gender.F ? FONT_GUEST_F : FONT_GUEST_M, 10, x, y - (RESOURCES.getInRadius() *  (1 - eucalyptusPctY)), Resources.CUSTOM_DARK_GREY, RESOURCES.getInRadius() * 1.85f, RESOURCES.getInRadius() * (datePctY + eucalyptusPctY));
                float fontSize = optimizeFontSize(nameFont, guest.getName(), RESOURCES.getInRadius() * 1.85f, maxNamesHeigth, 10.0f);
                float nameHeight = getTextHeight(nameFont, fontSize, guest.getName(), false);
                float nameY = upTextY - ELEMENTS_GAP - (maxNamesHeigth / 2f) - (nameHeight / 2f);
                writeText(cb, guest.getName(), nameFont, fontSize, x, nameY, RESOURCES.getColorGuestName());
            } else { // Name and title
                //Name
                float nFontSize = optimizeFontSize(nameFont, guest.getName(), RESOURCES.getInRadius() * 1.85f, maxNamesHeigth * (2f / 3f) - ELEMENTS_GAP, 10.0f);
                float nameHeight = getTextHeight(nameFont, nFontSize, guest.getName(), false);
                float nameY = upTextY - ELEMENTS_GAP - (maxNamesHeigth * (1f / 3f)) - (nameHeight / 2f);
                writeText(cb, guest.getName(), nameFont, nFontSize, x, nameY, RESOURCES.getColorGuestName());

                //Title
                float tFontSize = optimizeFontSize(FONT_GUEST_TITLE, guest.getTitle(), RESOURCES.getInRadius() * 1.85f, maxNamesHeigth * (1f / 3f), 10.0f);
                float titleHeight = getTextHeight(FONT_GUEST_TITLE, tFontSize, guest.getTitle(), false);
                float titleY = upTextY - (ELEMENTS_GAP * 2f) - (maxNamesHeigth * (2f / 3f)) - (maxNamesHeigth * (1f / 6f) - (ELEMENTS_GAP / 2)) - (titleHeight / 2f);
                writeText(cb, guest.getTitle(), FONT_GUEST_TITLE, tFontSize, x, titleY, RESOURCES.getColorGuestTitle());

                // writeText(cb,guest.getTitle(),FONT_GUEST_TITLE,10,x,y - (RESOURCES.getInRadius() * 2 *  (1 - eucalyptusPctY)),BaseColor.LIGHT_GRAY,RESOURCES.getInRadius() * 1.85f, (RESOURCES.getInRadius() * (datePctY + eucalyptusPctY))/2);
            }
        }

    }


    private void writeText(PdfContentByte cb, String text, BaseFont font, float fontSize, float x, float y, BaseColor color) {
        cb.beginText();
        cb.setFontAndSize(font, fontSize);
        cb.setColorFill(color);
        cb.showTextAligned(Element.ALIGN_CENTER, text, x, y, 0f);
        cb.endText();
    }

    private float getTextHeight(BaseFont font, float fontSize, String text, boolean includeDescent) {
        return font.getAscentPoint(text, fontSize) - (includeDescent ? font.getDescentPoint(text, fontSize) : 0f);
    }

    private float optimizeFontSize(BaseFont font, String text, Float maxWidth, Float maxHeight, float initialFontSize) {
        float fs = initialFontSize;
        float currentWidth = font.getWidthPoint(text, fs);
        float currentHeight = font.getAscentPoint(text, fs) - font.getDescentPoint(text, fs);

        while ((maxWidth != null && currentWidth > maxWidth) || (maxHeight != null && currentHeight > maxHeight)) {
            fs--;
            currentWidth = font.getWidthPoint(text, fs);
            currentHeight = getTextHeight(font, fs, text, true);
        }

        boolean raised = false;
        while ((maxWidth == null || currentWidth < maxWidth) && (maxHeight == null || currentHeight < maxHeight)) {
            fs++;
            currentWidth = font.getWidthPoint(text, fs);
            currentHeight = font.getAscentPoint(text, fs) - font.getDescentPoint(text, fs);
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

    public boolean isYnBottomImage() {
        return ynBottomImage;
    }

    public void setYnBottomImage(boolean ynBottomImage) {
        this.ynBottomImage = ynBottomImage;
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
