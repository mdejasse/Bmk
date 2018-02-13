package com.dvectors.perso.md.bmk.pattern;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPatternPainter;

public class Horizontal extends BmkPattern {

    public Horizontal(BaseColor color,float width, float height, float ystep, float lineWidth) {
        super(color,width,height,width,ystep,lineWidth);
    }

    public PdfPatternPainter getPattern(PdfContentByte cb) {
        PdfPatternPainter horizontal = cb.createPattern(width,height,xstep,ystep);
        horizontal.setLineWidth(lineWidth);
        horizontal.setColorStroke(color);
        horizontal.moveTo(0,height/2);
        horizontal.lineTo(width,height / 2 );
        horizontal.stroke();
        return horizontal;
    }


}
