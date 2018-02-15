package com.dvectors.perso.md.bmk;

import com.dvectors.perso.md.bmk.csv.CSVParser;
import com.dvectors.perso.md.bmk.pattern.*;
import com.dvectors.perso.md.bmk.util.Coordinate;
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
    
    private final static Resources RESOURCES = Resources.getInstance();

    private final static float X1 = RESOURCES.getExtRadius() + GAP;
    private final static float X2 =  X1 + RESOURCES.getExtRadius() * 2 + GAP;
    private final static float X3 = X2 + RESOURCES.getExtRadius() * 2 + GAP;
   // private final static float X4 = X3 + RESOURCES.getExtRadius() * 2 + GAP;

    private final static float Y1 = PageSize.A4.getHeight() - (RESOURCES.getExtRadius() + GAP_Y);
    private final static float Y2 = Y1 - (RESOURCES.getExtRadius() * 2 + GAP_Y);
    private final static float Y3 = Y2 - (RESOURCES.getExtRadius() * 2 + GAP_Y);
    private final static float Y4 = Y3 - (RESOURCES.getExtRadius() * 2 + GAP_Y);
    private final static float Y5 = Y4 - (RESOURCES.getExtRadius() * 2 + GAP_Y);
    //private final static float Y6 = Y5 - (RESOURCES.getExtRadius() * 2 + GAP_Y);

    private final static float[][] XYS = new float[][] {{X1,Y1},{X2,Y1},{X3,Y1},{X1,Y2},{X2,Y2},{X3,Y2},{X1,Y3},{X2,Y3},{X3,Y3},{X1,Y4},{X2,Y4},{X3,Y4},{X1,Y5},{X2,Y5},{X3,Y5}};

    
    
  /*  List<BadgeMaker> makers = Arrays.asList(new BadgeMaker(X1,Y1,RESOURCES.getExtRadius(),new Chevron(BaseColor.GRAY, 10, 15, 10, 10, 4)),
            new BadgeMaker(X2,Y1,RESOURCES.getExtRadius(),new Chevron(BaseColor.LIGHT_GRAY, 15, 15, 15, 10, 3)),

            new BadgeMaker(X3, Y1, RESOURCES.getExtRadius(),new Horizontal(BaseColor.GRAY,10,10,10,5)),
         //   new BadgeMaker(X4, Y1, RESOURCES.getExtRadius(),new Diagonal(BaseColor.GRAY,10,10,5)),
            new BadgeMaker(X1, Y2, RESOURCES.getExtRadius(),new Vertical(BaseColor.GRAY,10,10,10,5)),
            new BadgeMaker(X2,Y2,RESOURCES.getExtRadius(),new Diagonal(BaseColor.GRAY,10,10,2)),
            new BadgeMaker(X3,Y2,RESOURCES.getExtRadius(),new Polka(BaseColor.GRAY,5,2)),
       //     new BadgeMaker(X4,Y2,RESOURCES.getExtRadius(),new Polka(BaseColor.GRAY,2,0.5f)),
            new BadgeMaker(X1,Y3,RESOURCES.getExtRadius(),new Plain(BaseColor.LIGHT_GRAY)),
            //new BadgeMaker(X2,Y3,RESOURCES.getExtRadius(),new Plain(Resources.CUSTOM_DARK_GREY)),
            new BadgeMaker(X2,Y3,RESOURCES.getExtRadius(),new Chevron(Resources.CUSTOM_DARK_GREY, 8, 15, 8, 10, 2)),
            new BadgeMaker(X3, Y3, RESOURCES.getExtRadius(),new Plain(BaseColor.GRAY)),
       //     new BadgeMaker(X4,Y3,RESOURCES.getExtRadius(),new Vertical(BaseColor.DARK_GRAY,8,10,8,1)),
            new BadgeMaker(X1,Y4,RESOURCES.getExtRadius(),new ImageFiller(PageBuilder.class.getResource("/ligne.jpg").getFile(),RESOURCES.getExtRadius() * 2, RESOURCES.getExtRadius()*2)),
            new BadgeMaker(X2,Y4,RESOURCES.getExtRadius(),new Horizontal(BaseColor.LIGHT_GRAY,10,10,10,5)),
            new BadgeMaker(X3,Y4,RESOURCES.getExtRadius(),new Vertical(BaseColor.LIGHT_GRAY,10,10,10,5)),
       //     new BadgeMaker(X4,Y4,RESOURCES.getExtRadius(),new Polka(BaseColor.LIGHT_GRAY,4,1f)),
            new BadgeMaker(X1,Y5,RESOURCES.getExtRadius(),new Horizontal(BaseColor.LIGHT_GRAY,5,5,5,2)),
            new BadgeMaker(X2,Y5,RESOURCES.getExtRadius(),new Vertical(BaseColor.LIGHT_GRAY,8,10,8,1)),
            new BadgeMaker(X3,Y5,RESOURCES.getExtRadius(),new Polka(BaseColor.GRAY,8,3f)));//,
        //    new BadgeMaker(X4,Y5,RESOURCES.getExtRadius(),new Diagonal(BaseColor.LIGHT_GRAY,20,20,5)),
       //     new BadgeMaker(X1,Y6,RESOURCES.getExtRadius(),new Horizontal(BaseColor.GRAY,10,20,20,8)),
        //    new BadgeMaker(X2,Y6,RESOURCES.getExtRadius(),new Chevron(Resources.CUSTOM_DARK_GREY, 8, 15, 8, 10, 2)),
         //   new BadgeMaker(X3,Y6,RESOURCES.getExtRadius(),new Plain(BaseColor.GRAY)),
         //   new BadgeMaker(X4,Y6,RESOURCES.getExtRadius(),new ImageFiller("D:\\Temp\\ligne.jpg",RESOURCES.getExtRadius() * 2, RESOURCES.getExtRadius()*2)));


    List<BadgeMaker> makers2 = Arrays.asList(new BadgeMaker(X1,Y1,RESOURCES.getExtRadius(),new Chevron(BaseColor.GRAY, 10, 15, 10, 10, 4)),
            new BadgeMaker(X2,Y1,RESOURCES.getExtRadius(),new Diagonal(BaseColor.LIGHT_GRAY,20,20,5)),
            new BadgeMaker(X3, Y1, RESOURCES.getExtRadius(),new Diagonal(BaseColor.GRAY,10,10,5)),
            new BadgeMaker(X1, Y2, RESOURCES.getExtRadius(),new Vertical(BaseColor.GRAY,10,10,10,5)),
            new BadgeMaker(X2,Y2,RESOURCES.getExtRadius(),new Diagonal(BaseColor.GRAY,10,10,2)),
            new BadgeMaker(X3,Y2,RESOURCES.getExtRadius(),new Polka(BaseColor.GRAY,5,2)),
            new BadgeMaker(X1,Y3,RESOURCES.getExtRadius(),new Polka(BaseColor.GRAY,2,0.5f)),
            new BadgeMaker(X2,Y3,RESOURCES.getExtRadius(),new Chevron(Resources.CUSTOM_DARK_GREY, 8, 15, 8, 10, 2)),
            new BadgeMaker(X3,Y3,RESOURCES.getExtRadius(),new Chevron(BaseColor.LIGHT_GRAY, 15, 15, 15, 10, 3)),
            new BadgeMaker(X1,Y4,RESOURCES.getExtRadius(),new ImageFiller("D:\\Temp\\ligne.jpg",RESOURCES.getExtRadius() * 2, RESOURCES.getExtRadius()*2)),
            new BadgeMaker(X2,Y4,RESOURCES.getExtRadius(),new Plain(Resources.CUSTOM_DARK_GREY)),
            new BadgeMaker(X3,Y4,RESOURCES.getExtRadius(),new Vertical(BaseColor.LIGHT_GRAY,10,10,10,5)),
            new BadgeMaker(X1,Y5,RESOURCES.getExtRadius(),new Polka(BaseColor.LIGHT_GRAY,4,1f)),
            new BadgeMaker(X2,Y5,RESOURCES.getExtRadius(),new Vertical(BaseColor.LIGHT_GRAY,8,10,8,1)),
            new BadgeMaker(X3,Y5,RESOURCES.getExtRadius(),new Horizontal(BaseColor.GRAY,10,20,20,8)));//,*/



    public PageBuilder() {

    }

    public void run(Document document, PdfWriter writer, List<Guest> guests) throws Exception {
        PdfContentByte cb = writer.getDirectContent();

        List<BmkFiller> patterns =  new CSVPatternReader().read();
        Iterator<BmkFiller> itFillers = patterns.iterator();

        //Iterator<BadgeMaker> itBm = makers2.iterator();
        int cnt = 0;
        for (Guest guest : guests) {
            if (cnt == XYS.length) {
                cnt = 0;
                document.newPage();
                cb = writer.getDirectContent();
            }
            float xy[] = XYS[cnt];
            if (guest.isToPrint()) {
                cnt++;
                if (itFillers.hasNext()) {
                    BadgeMaker badgeMaker = new BadgeMaker(xy[0],xy[1],RESOURCES.getExtRadius(),itFillers.next());
                    badgeMaker.run(cb, guest);
                } else { //new page
                    itFillers = patterns.iterator();
                    if (itFillers.hasNext()) {
                        BadgeMaker badgeMaker = new BadgeMaker(xy[0],xy[1],RESOURCES.getExtRadius(),itFillers.next());
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


            new PageBuilder().run(document,writer,Arrays.asList(new Guest("Mathieu","Marié",Gender.M,true),
                                                   new Guest("Charline","Mariée",Gender.F,true),
                                                   new Guest("Carla","Maman du marié",Gender.F,true),
                                                    new Guest("Adrien",null,Gender.M,true)));



        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            document.close();
        }
    }
    
    
}
