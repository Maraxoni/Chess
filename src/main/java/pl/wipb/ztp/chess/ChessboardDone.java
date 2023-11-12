// 
// Decompiled by Procyon v0.5.36
// 

package pl.wipb.ztp.chess;

import java.awt.Component;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JToolBar;
import javax.swing.JFrame;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import java.awt.Image;

public class ChessboardDone
{
    public static Image getImage(final String imgName) throws IOException {
        final InputStream fileName = ChessboardDone.class.getResourceAsStream("/img/" + imgName);
        return ImageIO.read(fileName);
    }

    public static void main(final String[] args) throws IOException {
        final JFrame frame = new JFrame("Chess");
        frame.setDefaultCloseOperation(3);
        final Chessboard board = new Chessboard();
        final JToolBar bar = new JToolBar();
        board.undo = new JButton(new ImageIcon(getImage("undo.png")));
        board.redo = new JButton(new ImageIcon(getImage("redo.png")));
        board.undo.addActionListener(board.new UndoButton());
        board.redo.addActionListener(board.new RedoButton());
        board.undo.setEnabled(false);
        board.redo.setEnabled(false);
        bar.add(board.undo);
        bar.add(board.redo);
        frame.add(bar, "First");
        frame.add(board);
        frame.pack();
        frame.setVisible(true);
    }
}
