package com.dvectors.perso.md.bmk.pattern;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPatternPainter;

public class Polka extends BmkPattern {

    private float radius;

    public Polka(BaseColor color, float width, float height, float xstep, float ystep, float radius) {
        super(color, width, height, xstep, ystep, 0);
        this.radius = radius;
    }

    public Polka(BaseColor color, float sizeBox, float radius) {
        super(color, sizeBox, sizeBox, sizeBox, sizeBox, 0);
        this.radius = radius;
    }


    @Override
    public PdfPatternPainter getPattern(PdfContentByte cb) {
        PdfPatternPainter polka = cb.createPattern(width, height, xstep, ystep);
        polka.setColorFill(color);
        polka.circle(width / 2, height / 2, radius);
        polka.fill();
        return polka;
    }


}
