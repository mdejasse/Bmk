package com.dvectors.perso.md.bmk.pattern;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPatternPainter;

public class Diagonal extends BmkPattern {

    public Diagonal(BaseColor color, float width, float height, float lineWidth) {
        super(color, width, height, width, height, lineWidth);
    }

    public PdfPatternPainter getPattern(PdfContentByte cb) {
        PdfPatternPainter diagonal = cb.createPattern(width,height,xstep,ystep);
        diagonal.setLineWidth(lineWidth);
        diagonal.setColorStroke(color);
        diagonal.moveTo(0,0);
        diagonal.lineTo(width,height );
        diagonal.moveTo(width,height * 2);
        diagonal.lineTo(0 - width, 0);
        diagonal.moveTo(width * 2, height);
        diagonal.lineTo(0,0 - height);
        diagonal.stroke();
        return diagonal;
    }
}
