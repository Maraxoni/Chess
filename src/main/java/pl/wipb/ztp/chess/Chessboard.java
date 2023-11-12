//
// Decompiled by Procyon v0.5.36
//

package pl.wipb.ztp.chess;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Dimension;
import java.util.Iterator;
import java.awt.Graphics2D;
import java.util.Map;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import javax.swing.JButton;
import java.awt.geom.AffineTransform;
import java.awt.Image;
import java.awt.Point;
import java.util.HashMap;
import javax.swing.JPanel;

class Chessboard extends JPanel
{
	public static final int ZEROX = 23;
	public static final int ZEROY = 7;
	private HashMap<Point, IPiece> board;
	private Image image;
	private IPiece dragged;
	AffineTransform draggedTransform;
	JButton undo;
	JButton redo;
	private Point mouse;

	public void drop(final IPiece p, final int x, final int y) {
		this.repaint();
		p.moveTo(x, y);
		this.board.put(new Point(x, y), p);
	}

	public IPiece take(final int x, final int y) {
		this.repaint();
		return this.board.remove(new Point(x, y));
	}

	public void paintComponent(final Graphics g) {
		g.drawImage(this.image, 0, 0, null);
		for (final Map.Entry<Point, IPiece> e : this.board.entrySet()) {
			final Point pt = e.getKey();
			final IPiece pc = e.getValue();
			pc.draw((Graphics2D)g);
		}
		if (this.mouse != null && this.dragged != null) {
			this.dragged.draw((Graphics2D)g);
		}
	}

	Chessboard() throws IOException {
		this.board = new HashMap<Point, IPiece>();
		this.dragged = null;
		this.draggedTransform = null;
		this.mouse = null;
		final AffineTransform tr = new AffineTransform();
		tr.translate(23.0, 7.0);
		tr.scale(32.0, 32.0);
		this.board.put(new Point(0, 2), new Decorator(new Piece(11, 0, 2), tr));
		this.board.put(new Point(0, 6), new Decorator(new Piece(0, 0, 6), tr));
		this.board.put(new Point(1, 4), new Decorator(new Piece(6, 1, 4), tr));
		this.board.put(new Point(1, 5), new Decorator(new Piece(5, 1, 5), tr));
		this.board.put(new Point(3, 7), new Decorator(new Piece(1, 3, 7), tr));
		this.board.put(new Point(4, 3), new Decorator(new Piece(6, 4, 3), tr));
		this.board.put(new Point(4, 4), new Decorator(new Piece(7, 4, 4), tr));
		this.board.put(new Point(5, 4), new Decorator(new Piece(6, 5, 4), tr));
		this.board.put(new Point(5, 6), new Decorator(new Piece(0, 5, 6), tr));
		this.board.put(new Point(6, 5), new Decorator(new Piece(0, 6, 5), tr));
		this.board.put(new Point(7, 4), new Decorator(new Piece(0, 7, 4), tr));
		this.image = ChessboardDone.getImage("board3.png");
		this.setPreferredSize(new Dimension(this.image.getWidth(null), this.image.getHeight(null)));
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(final MouseEvent ev) {
				Chessboard.this.dragged = Chessboard.this.take((ev.getX() - 23) / 32, (ev.getY() - 7) / 32);
				if (Chessboard.this.dragged == null) {
					return;
				}
				Chessboard.this.draggedTransform = new AffineTransform();
				Chessboard.this.dragged = new Decorator(Chessboard.this.dragged, Chessboard.this.draggedTransform);
				Chessboard.this.mouse = ev.getPoint();
			}

			@Override
			public void mouseReleased(final MouseEvent ev) {
				if (Chessboard.this.dragged == null) {
					return;
				}
				Chessboard.this.drop(Chessboard.this.dragged.getDecorated(), (ev.getX() - 23) / 32, (ev.getY() - 7) / 32);
				Chessboard.this.dragged = null;
				Chessboard.this.undo.setEnabled(true);
			}
		});
		this.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(final MouseEvent ev) {
				if (Chessboard.this.draggedTransform == null) {
					return;
				}
				Chessboard.this.draggedTransform.setToTranslation(ev.getX() - Chessboard.this.mouse.getX(), ev.getY() - Chessboard.this.mouse.getY());
				Chessboard.this.repaint();
			}
		});
	}

	class UndoButton implements ActionListener
	{
		@Override
		public void actionPerformed(final ActionEvent ev) {
			System.out.println("UNDO");
			Chessboard.this.redo.setEnabled(true);
		}
	}

	class RedoButton implements ActionListener
	{
		@Override
		public void actionPerformed(final ActionEvent ev) {
			System.out.println("REDO");
		}
	}
}
