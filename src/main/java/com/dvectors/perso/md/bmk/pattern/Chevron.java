package com.dvectors.perso.md.bmk.pattern;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPatternPainter;

public class Chevron extends BmkPattern {


    public Chevron(BaseColor color, float width, float height, float xstep, float ystep, float lineWidth) {
        super(color,width,height,xstep,ystep,lineWidth);
    }

    public PdfPatternPainter getPattern(PdfContentByte cb) {
        PdfPatternPainter chevron = cb.createPattern(width,height,xstep,ystep);
        chevron.setLineWidth(lineWidth);
        chevron.setColorStroke(color);
        chevron.moveTo((0-width)/2.0f,lineWidth - 1);
        chevron.lineTo(width/2.0f,height - (lineWidth-1));
        chevron.lineTo(width + (width/2.0f),lineWidth-1);
        chevron.stroke();
        return chevron;
    }


}
