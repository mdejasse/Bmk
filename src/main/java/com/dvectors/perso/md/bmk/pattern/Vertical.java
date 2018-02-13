package com.dvectors.perso.md.bmk.pattern;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPatternPainter;

public class Vertical extends BmkPattern {

    public Vertical(BaseColor color, float width, float height, float xstep, float lineWidth) {
        super(color, width, height, xstep, height, lineWidth);
    }

    @Override
    public PdfPatternPainter getPattern(PdfContentByte cb) {
        PdfPatternPainter vertical = cb.createPattern(width,height,xstep,ystep);
        vertical.setLineWidth(lineWidth);
        vertical.setColorStroke(color);
        vertical.moveTo(width / 2,0);
        vertical.lineTo(width/2,height);
        vertical.stroke();
        return vertical;
    }
}
