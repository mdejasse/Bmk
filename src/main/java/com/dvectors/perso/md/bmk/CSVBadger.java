package com.dvectors.perso.md.bmk;

import com.dvectors.perso.md.bmk.csv.CSVParser;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CSVBadger {

    public static void main(String[] args) {
        CSVParser parser = null;
        Document document = new Document();
        try {
            List<Guest> guests = new ArrayList<Guest>();

            //File csv = new File(CSVBadger.class.getResource("/guests.csv").getFile());
            parser = new CSVParser(CSVBadger.class.getResourceAsStream("/guests.csv"));
            parser.init();
            boolean csvEnd = false;
            while (!csvEnd) {
                List<String> line = parser.nextLine();
                if (line != null) {
                    guests.add(new Guest(line.get(0),line.get(2),Gender.valueOf(line.get(1).toUpperCase()),line.get(3) != null && line.get(3).trim().equals("1")));
                }
                else {
                    csvEnd = true;
                }
            }

            String genFolder = FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + File.separator + "BMK";
            String filePath = genFolder + File.separator + "bmk_"+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) +".pdf";


            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            new PageBuilder().run(document,writer,guests);

            System.out.println("Badges created in "+filePath);

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (parser != null) {
                parser.close();
            }
            document.close();
        }

    }
}
