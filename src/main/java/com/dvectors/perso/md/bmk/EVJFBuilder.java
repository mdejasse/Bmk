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

    private final static float GAP = 50;
    private final static float GAP_Y = 25;

    private final static float X1 = Resources.EXT_RADIUS + GAP;
    private final static float X2 = X1 + Resources.EXT_RADIUS * 2 + GAP;
    private final static float X3 = X2 + Resources.EXT_RADIUS * 2 + GAP;
    // private final static float X4 = X3 + Resources.EXT_RADIUS * 2 + GAP;

    private final static float Y1 = PageSize.A4.getHeight() - (Resources.EXT_RADIUS + GAP_Y);
    private final static float Y2 = Y1 - (Resources.EXT_RADIUS * 2 + GAP_Y);
    private final static float Y3 = Y2 - (Resources.EXT_RADIUS * 2 + GAP_Y);
    private final static float Y4 = Y3 - (Resources.EXT_RADIUS * 2 + GAP_Y);
    private final static float Y5 = Y4 - (Resources.EXT_RADIUS * 2 + GAP_Y);

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
           /* PdfTemplate t = cb.createTemplate(Resources.EXT_RADIUS*2f,Resources.EXT_RADIUS*2f);
            t.circle(Resources.EXT_RADIUS,Resources.EXT_RADIUS,Resources.EXT_RADIUS);
            t.clip();
            t.newPath();
            t.addImage(img,Resources.EXT_RADIUS*2f,0,0, Resources.EXT_RADIUS*2f,c.getX()-Resources.EXT_RADIUS,c.getY()- Resources.EXT_RADIUS);

            cb.addTemplate(t,c.getX(),c.getY());*/


         /*   BadgeMaker maker = new BadgeMaker(c.getX(), c.getY(), Resources.EXT_RADIUS, new ImageFiller("D:\\Users\\md\\Documents\\bmk\\evjf.jpg", Resources.EXT_RADIUS * 2, Resources.EXT_RADIUS * 2,0 - Resources.EXT_RADIUS,0 - Resources.EXT_RADIUS));
            maker.setYnDate(false);
            maker.setYnEucalyptus(false);
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
