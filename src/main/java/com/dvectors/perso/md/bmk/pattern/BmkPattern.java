package com.dvectors.perso.md.bmk.pattern;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPatternPainter;


public abstract class BmkPattern implements BmkFiller {

    protected BaseColor color;
    protected float width;
    protected float height;
    protected float xstep;
    protected float ystep;
    protected float lineWidth;

    public BmkPattern(BaseColor color, float width, float height, float xstep, float ystep, float lineWidth) {
        this.color = color;
        this.width = width;
        this.height = height;
        this.xstep = xstep;
        this.ystep = ystep;
        this.lineWidth = lineWidth;
    }

    public abstract PdfPatternPainter getPattern(PdfContentByte cb);

    public void setFilling(PdfContentByte cb) {
        cb.setPatternFill(getPattern(cb));
    }
}
