// 
// Decompiled by Procyon v0.5.36
// 

package pl.wipb.ztp.chess;

import java.io.IOException;
import java.awt.image.ImageObserver;
import java.awt.Graphics2D;
import java.awt.Image;

class Piece implements IPiece
{
	private static Image image;
	private int index;
	private int x;
	private int y;

	public Piece(final int idx, final int xx, final int yy) {
		this.index = idx;
		this.x = xx;
		this.y = yy;
	}

	@Override
	public void draw(final Graphics2D g) {
		g.drawImage(Piece.image, this.x, this.y, this.x + 1, this.y + 1, this.index * 32, 0, (this.index + 1) * 32, 32, null);
	}

	@Override
	public int getX() {
		return this.x;
	}

	@Override
	public int getY() {
		return this.y;
	}

	@Override
	public void moveTo(final int xx, final int yy) {
		this.x = xx;
		this.y = yy;
	}

	@Override
	public IPiece getDecorated() {
		return null;
	}

	static {
		try {
			Piece.image = ChessboardDone.getImage("pieces4.png");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
