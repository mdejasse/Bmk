package com.dvectors.perso.md.bmk.pattern;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPatternPainter;

import java.io.IOException;


public class ImageFiller implements BmkFiller {


    private String path;
    private float width;
    private float height;

    public ImageFiller(String path,float width, float height) {
        this.path = path;
        this.width = width;
        this.height = height;
    }

    @Override
    public void setFilling(PdfContentByte cb) {
        try {
            Image img = Image.getInstance(path);
            img.scaleAbsolute(width, height);
            img.setAbsolutePosition(0, 0);
            PdfPatternPainter imgPattern = cb.createPattern(width, height, width, height);
            imgPattern.addImage(img);
            cb.setPatternFill(imgPattern);
        } catch (BadElementException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
