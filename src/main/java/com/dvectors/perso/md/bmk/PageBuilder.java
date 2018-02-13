package com.dvectors.perso.md.bmk;

import com.dvectors.perso.md.bmk.pattern.*;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class PageBuilder {
    

    private final static float GAP = 50;
    private final static float GAP_Y = 25;

    private final static float X1 = Resources.EXT_RADIUS + GAP;
    private final static float X2 =  X1 + Resources.EXT_RADIUS * 2 + GAP;
    private final static float X3 = X2 + Resources.EXT_RADIUS * 2 + GAP;
   // private final static float X4 = X3 + Resources.EXT_RADIUS * 2 + GAP;

    private final static float Y1 = PageSize.A4.getHeight() - (Resources.EXT_RADIUS + GAP_Y);
    private final static float Y2 = Y1 - (Resources.EXT_RADIUS * 2 + GAP_Y);
    private final static float Y3 = Y2 - (Resources.EXT_RADIUS * 2 + GAP_Y);
    private final static float Y4 = Y3 - (Resources.EXT_RADIUS * 2 + GAP_Y);
    private final static float Y5 = Y4 - (Resources.EXT_RADIUS * 2 + GAP_Y);
    //private final static float Y6 = Y5 - (Resources.EXT_RADIUS * 2 + GAP_Y);

    
    
    List<BadgeMaker> makers = Arrays.asList(new BadgeMaker(X1,Y1,Resources.EXT_RADIUS,new Chevron(BaseColor.GRAY, 10, 15, 10, 10, 4)),
            new BadgeMaker(X2,Y1,Resources.EXT_RADIUS,new Chevron(BaseColor.LIGHT_GRAY, 15, 15, 15, 10, 3)),

            new BadgeMaker(X3, Y1, Resources.EXT_RADIUS,new Horizontal(BaseColor.GRAY,10,10,10,5)),
         //   new BadgeMaker(X4, Y1, Resources.EXT_RADIUS,new Diagonal(BaseColor.GRAY,10,10,5)),
            new BadgeMaker(X1, Y2, Resources.EXT_RADIUS,new Vertical(BaseColor.GRAY,10,10,10,5)),
            new BadgeMaker(X2,Y2,Resources.EXT_RADIUS,new Diagonal(BaseColor.GRAY,10,10,2)),
            new BadgeMaker(X3,Y2,Resources.EXT_RADIUS,new Polka(BaseColor.GRAY,5,2)),
       //     new BadgeMaker(X4,Y2,Resources.EXT_RADIUS,new Polka(BaseColor.GRAY,2,0.5f)),
            new BadgeMaker(X1,Y3,Resources.EXT_RADIUS,new Plain(BaseColor.LIGHT_GRAY)),
            //new BadgeMaker(X2,Y3,Resources.EXT_RADIUS,new Plain(Resources.CUSTOM_DARK_GREY)),
            new BadgeMaker(X2,Y3,Resources.EXT_RADIUS,new Chevron(Resources.CUSTOM_DARK_GREY, 8, 15, 8, 10, 2)),
            new BadgeMaker(X3, Y3, Resources.EXT_RADIUS,new Plain(BaseColor.GRAY)),
       //     new BadgeMaker(X4,Y3,Resources.EXT_RADIUS,new Vertical(BaseColor.DARK_GRAY,8,10,8,1)),
            new BadgeMaker(X1,Y4,Resources.EXT_RADIUS,new ImageFiller("D:\\Temp\\ligne.jpg",Resources.EXT_RADIUS * 2, Resources.EXT_RADIUS*2)),
            new BadgeMaker(X2,Y4,Resources.EXT_RADIUS,new Horizontal(BaseColor.LIGHT_GRAY,10,10,10,5)),
            new BadgeMaker(X3,Y4,Resources.EXT_RADIUS,new Vertical(BaseColor.LIGHT_GRAY,10,10,10,5)),
       //     new BadgeMaker(X4,Y4,Resources.EXT_RADIUS,new Polka(BaseColor.LIGHT_GRAY,4,1f)),
            new BadgeMaker(X1,Y5,Resources.EXT_RADIUS,new Horizontal(BaseColor.LIGHT_GRAY,5,5,5,2)),
            new BadgeMaker(X2,Y5,Resources.EXT_RADIUS,new Vertical(BaseColor.LIGHT_GRAY,8,10,8,1)),
            new BadgeMaker(X3,Y5,Resources.EXT_RADIUS,new Polka(BaseColor.GRAY,8,3f)));//,
        //    new BadgeMaker(X4,Y5,Resources.EXT_RADIUS,new Diagonal(BaseColor.LIGHT_GRAY,20,20,5)),
       //     new BadgeMaker(X1,Y6,Resources.EXT_RADIUS,new Horizontal(BaseColor.GRAY,10,20,20,8)),
        //    new BadgeMaker(X2,Y6,Resources.EXT_RADIUS,new Chevron(Resources.CUSTOM_DARK_GREY, 8, 15, 8, 10, 2)),
         //   new BadgeMaker(X3,Y6,Resources.EXT_RADIUS,new Plain(BaseColor.GRAY)),
         //   new BadgeMaker(X4,Y6,Resources.EXT_RADIUS,new ImageFiller("D:\\Temp\\ligne.jpg",Resources.EXT_RADIUS * 2, Resources.EXT_RADIUS*2)));


    List<BadgeMaker> makers2 = Arrays.asList(new BadgeMaker(X1,Y1,Resources.EXT_RADIUS,new Chevron(BaseColor.GRAY, 10, 15, 10, 10, 4)),
            new BadgeMaker(X2,Y1,Resources.EXT_RADIUS,new Diagonal(BaseColor.LIGHT_GRAY,20,20,5)),
            new BadgeMaker(X3, Y1, Resources.EXT_RADIUS,new Diagonal(BaseColor.GRAY,10,10,5)),
            new BadgeMaker(X1, Y2, Resources.EXT_RADIUS,new Vertical(BaseColor.GRAY,10,10,10,5)),
            new BadgeMaker(X2,Y2,Resources.EXT_RADIUS,new Diagonal(BaseColor.GRAY,10,10,2)),
            new BadgeMaker(X3,Y2,Resources.EXT_RADIUS,new Polka(BaseColor.GRAY,5,2)),
            new BadgeMaker(X1,Y3,Resources.EXT_RADIUS,new Polka(BaseColor.GRAY,2,0.5f)),
            new BadgeMaker(X2,Y3,Resources.EXT_RADIUS,new Chevron(Resources.CUSTOM_DARK_GREY, 8, 15, 8, 10, 2)),
            new BadgeMaker(X3,Y3,Resources.EXT_RADIUS,new Chevron(BaseColor.LIGHT_GRAY, 15, 15, 15, 10, 3)),
            new BadgeMaker(X1,Y4,Resources.EXT_RADIUS,new ImageFiller("D:\\Temp\\ligne.jpg",Resources.EXT_RADIUS * 2, Resources.EXT_RADIUS*2)),
            new BadgeMaker(X2,Y4,Resources.EXT_RADIUS,new Plain(Resources.CUSTOM_DARK_GREY)),
            new BadgeMaker(X3,Y4,Resources.EXT_RADIUS,new Vertical(BaseColor.LIGHT_GRAY,10,10,10,5)),
            new BadgeMaker(X1,Y5,Resources.EXT_RADIUS,new Polka(BaseColor.LIGHT_GRAY,4,1f)),
            new BadgeMaker(X2,Y5,Resources.EXT_RADIUS,new Vertical(BaseColor.LIGHT_GRAY,8,10,8,1)),
            new BadgeMaker(X3,Y5,Resources.EXT_RADIUS,new Horizontal(BaseColor.GRAY,10,20,20,8)));//,



    public PageBuilder() {

    }

    public void run(Document document, PdfWriter writer, List<Guest> guests) throws Exception {
        PdfContentByte cb = writer.getDirectContent();
        Iterator<BadgeMaker> itBm = makers2.iterator();
        int cnt = 0;
        for (Guest guest : guests) {
            cnt++;
            if (guest.isToPrint()) {
                if (itBm.hasNext()) {
                    BadgeMaker badgeMaker = itBm.next();
                    badgeMaker.run(cb, guest);
                } else { //new page
                    document.newPage();
                    cb = writer.getDirectContent();
                    itBm = (cnt%2 == 0)?makers2.iterator():makers.iterator();
                    if (itBm.hasNext()) {
                        BadgeMaker badgeMaker = itBm.next();
                        badgeMaker.run(cb, guest);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Document document = new Document();
        try {

            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("D:\\Temp\\bmk_"+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) +".pdf"));
            document.open();
           // PdfContentByte cb = writer.getDirectContent();


            new PageBuilder().run(document,writer,Arrays.asList(new Guest("Mathieu","Mari�",Gender.M,true),
                                                   new Guest("Charline","Mari�e",Gender.F,true),
                                                   new Guest("Carla","Maman du mari�",Gender.F,true),
                                                    new Guest("Adrien",null,Gender.M,true)));



        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            document.close();
        }
    }
    
    
}