package com.dvectors.perso.md.bmk;

import com.dvectors.perso.md.bmk.csv.CSVParser;
import com.dvectors.perso.md.bmk.pattern.*;
import com.itextpdf.text.BaseColor;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVPatternReader {

    public CSVPatternReader() {
    }

    public List<BmkFiller> read() {
        List<BmkFiller> patterns = new ArrayList<>();
        CSVParser parser = new CSVParser(this.getClass().getResourceAsStream("/patterns.csv"));
        parser.init();
        boolean csvEnd = false;
        while (!csvEnd) {
            List<String> line = parser.nextLine();
            if (line != null) {
                BmkFiller pattern = null;
                String type = line.get(0);
                switch (type) {
                    case "CHEVRON" :
                        pattern = new Chevron(getColor(line.get(1),line.get(2),line.get(3)),Float.parseFloat(line.get(4)),Float.parseFloat(line.get(5)),Float.parseFloat(line.get(6)),Float.parseFloat(line.get(7)),Float.parseFloat(line.get(8)));
                        break;
                    case "DIAGONAL" :
                        pattern = new Diagonal(getColor(line.get(1),line.get(2),line.get(3)),Float.parseFloat(line.get(4)),Float.parseFloat(line.get(5)),Float.parseFloat(line.get(6)));
                        break;
                    case "HORIZONTAL" :
                        pattern = new Horizontal(getColor(line.get(1),line.get(2),line.get(3)),Float.parseFloat(line.get(4)),Float.parseFloat(line.get(5)),Float.parseFloat(line.get(6)),Float.parseFloat(line.get(7)));
                        break;
                    case "IMAGE" :
                        try {
                            pattern = new ImageFiller(IOUtils.toByteArray(this.getClass().getResourceAsStream(line.get(1))),Float.parseFloat(line.get(2)),Float.parseFloat(line.get(3)));
                        } catch (IOException e) {
                            e.printStackTrace();
                            pattern = null;
                        }
                        break;
                    case "PLAIN" :
                        pattern = new Plain(getColor(line.get(1),line.get(2),line.get(3)));
                        break;
                    case "POLKA" :
                        pattern = new Polka(getColor(line.get(1),line.get(2),line.get(3)),Float.parseFloat(line.get(4)),Float.parseFloat(line.get(5)));
                        break;
                    case "VERTICAL" :
                        pattern = new Vertical(getColor(line.get(1),line.get(2),line.get(3)),Float.parseFloat(line.get(4)),Float.parseFloat(line.get(5)),Float.parseFloat(line.get(6)),Float.parseFloat(line.get(7)));
                        break;
                    default :
                        pattern = null;
                }

                if (pattern != null) {
                    patterns.add(pattern);
                }

            }
            else {
                csvEnd = true;
            }
        }
        return patterns;
    }

    private BaseColor getColor(String r, String g, String b) {
        return new BaseColor(Integer.parseInt(r),Integer.parseInt(g),Integer.parseInt(b));
    }
}
