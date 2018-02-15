package com.dvectors.perso.md.bmk;

import com.dvectors.perso.md.bmk.pattern.ImageFiller;
import com.dvectors.perso.md.bmk.util.Coordinate;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class EVJFBuilder {

    private final static Resources RESOURCES = Resources.getInstance();
    
    private final static float GAP = 50;
    private final static float GAP_Y = 25;

    private final static float X1 = RESOURCES.getExtRadius() + GAP;
    private final static float X2 = X1 + RESOURCES.getExtRadius() * 2 + GAP;
    private final static float X3 = X2 + RESOURCES.getExtRadius() * 2 + GAP;
    // private final static float X4 = X3 + RESOURCES.getExtRadius() * 2 + GAP;

    private final static float Y1 = PageSize.A4.getHeight() - (RESOURCES.getExtRadius() + GAP_Y);
    private final static float Y2 = Y1 - (RESOURCES.getExtRadius() * 2 + GAP_Y);
    private final static float Y3 = Y2 - (RESOURCES.getExtRadius() * 2 + GAP_Y);
    private final static float Y4 = Y3 - (RESOURCES.getExtRadius() * 2 + GAP_Y);
    private final static float Y5 = Y4 - (RESOURCES.getExtRadius() * 2 + GAP_Y);

    private final static List<Coordinate> coordinates = Arrays.asList(new Coordinate(X1, Y1), new Coordinate(X2, Y1), new Coordinate(X3, Y1),
            new Coordinate(X1, Y2), new Coordinate(X2, Y2), new Coordinate(X3, Y2),
            new Coordinate(X1, Y3), new Coordinate(X2, Y3), new Coordinate(X3, Y3),
            new Coordinate(X1, Y4), new Coordinate(X2, Y4), new Coordinate(X3, Y4),
            new Coordinate(X1, Y5));

    public EVJFBuilder() {
    }

    public void run(Document document, PdfWriter writer) throws Exception {
        PdfContentByte cb = writer.getDirectContent();
        Image img = Image.getInstance("D:\\Users\\md\\Documents\\bmk\\evjf.jpg");
        for (Coordinate c : coordinates) {
           /* PdfTemplate t = cb.createTemplate(RESOURCES.getExtRadius()*2f,RESOURCES.getExtRadius()*2f);
            t.circle(RESOURCES.getExtRadius(),RESOURCES.getExtRadius(),RESOURCES.getExtRadius());
            t.clip();
            t.newPath();
            t.addImage(img,RESOURCES.getExtRadius()*2f,0,0, RESOURCES.getExtRadius()*2f,c.getX()-RESOURCES.getExtRadius(),c.getY()- RESOURCES.getExtRadius());

            cb.addTemplate(t,c.getX(),c.getY());*/


         /*   BadgeMaker maker = new BadgeMaker(c.getX(), c.getY(), RESOURCES.getExtRadius(), new ImageFiller("D:\\Users\\md\\Documents\\bmk\\evjf.jpg", RESOURCES.getExtRadius() * 2, RESOURCES.getExtRadius() * 2,0 - RESOURCES.getExtRadius(),0 - RESOURCES.getExtRadius()));
            maker.setYnDate(false);
            maker.setYnBottomImage(false);
            maker.setYnInnerCircle(false);
            maker.run(cb, null);*/

        }
    }


    public static void main(String[] args) {
        Document document = new Document();
        try {

            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("D:\\Temp\\evjf_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".pdf"));
            document.open();
            // PdfContentByte cb = writer.getDirectContent();

            new EVJFBuilder().run(document, writer);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }
}
