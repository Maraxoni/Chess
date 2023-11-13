package pl.wipb.ztp.chess;

import java.awt.*;
import java.awt.geom.AffineTransform;

class Decorator implements IPiece
{
    protected final IPiece piece;
    private AffineTransform transform;
    protected Decorator(final IPiece p, final AffineTransform tr) {
        this.piece = p;
        this.transform = tr;
    }

    @Override
    public int getX() {
        return this.piece.getX();
    }
    
    @Override
    public int getY() {
        return this.piece.getY();
    }
    
    @Override
    public void moveTo(final int xx, final int yy) {
        this.piece.moveTo(xx, yy);
    }
    
    @Override
    public IPiece getDecorated() {
        return this.piece;
    }

    @Override
    public void draw(final Graphics2D g) {
        final AffineTransform tmp = g.getTransform();
        g.transform(this.transform);
        this.piece.draw(g);
        g.setTransform(tmp);
    }
}