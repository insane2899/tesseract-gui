package de.vorb.tesseract.gui.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import de.vorb.tesseract.gui.model.BoxFileModel;
import de.vorb.tesseract.util.Box;
import de.vorb.tesseract.util.Symbol;

public class BoxFileWriter {
    public static void writeBoxFile(BoxFileModel model) throws IOException {
        final BufferedWriter boxFileWriter = Files.newBufferedWriter(
                model.getFile(), StandardCharsets.UTF_8);

        final int pageHeight = model.getImage().getHeight();

        for (Symbol symbol : model.getBoxes()) {

            final Box bbox = symbol.getBoundingBox();
            final int x0 = bbox.getX();
            final int y0 = pageHeight - bbox.getY() - bbox.getHeight();
            final int x1 = x0 + bbox.getWidth();
            final int y1 = y0 + bbox.getHeight();

            boxFileWriter.write(String.format("%s %d %d %d %d 0\n",
                    symbol.getText(), x0, y0, x1, y1));
        }

        boxFileWriter.close();
    }
}
