// 
// Decompiled by Procyon v0.5.36
// 

package pl.wipb.ztp.chess;

import java.awt.Graphics2D;

interface IPiece
{
    public static final int TILESIZE = 32;
    
    void draw(final Graphics2D p0);
    
    int getX();
    
    int getY();
    
    void moveTo(final int p0, final int p1);
    
    IPiece getDecorated();
}
