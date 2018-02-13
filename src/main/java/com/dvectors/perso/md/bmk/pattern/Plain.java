package com.dvectors.perso.md.bmk.pattern;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.pdf.PdfContentByte;

public class Plain implements BmkFiller {


    private BaseColor color = BaseColor.GRAY;

    public Plain(BaseColor color) {
        this.color = color;
    }

    public void setFilling(PdfContentByte cb) {
        cb.setColorFill(color);
    }
}
