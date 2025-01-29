package fr.bloomyindev.cgj2024;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import fr.bloomyindev.cgj2024.CoordinateSystems.*;

public class Star {
    private Color color;
    private AbsoluteCoords3D coords;
    private float angularSize;
    private int absoluteRadius;
    private boolean visited;
    private boolean isVisitable;
    private ArrayList<Color> listeCouleur;

    public Star(AbsoluteCoords3D coords, int absoluteRadius, boolean isVisitable, boolean isDecorative) {
        this.coords = coords;
        this.absoluteRadius = absoluteRadius;
        this.visited = false;
        this.isVisitable = isVisitable;
        construitListeCouleur();
        Random random = new Random();
        int randomNumber = random.nextInt(3 - 0 + 1) + 0;
        this.color = !isDecorative ? listeCouleur.get(randomNumber) : Color.WHITE;
    }

    public void construitListeCouleur() {
        listeCouleur = new ArrayList<>();
        listeCouleur.add(Color.BLUE);
        listeCouleur.add(Color.BROWN);
        listeCouleur.add(Color.RED);
        listeCouleur.add(Color.PURPLE);
        listeCouleur.add(Color.ORANGE);
    }

    public Color getColor() {
        return color;
    }

    public AbsoluteCoords3D getCoordinates() {
        return coords;
    }

    public int getAbsoluteRadius() {
        return absoluteRadius;
    }

    public long distanceBetween(Star star2) {
        float[] Delta3D = star2.getCoordinates().getDelta(this.coords);

        float xRel = Delta3D[0];
        float yRel = Delta3D[1];
        float zRel = Delta3D[2];

        return (long) Math.sqrt(Ut.pow(xRel, 2.0f) + Ut.pow(yRel, 2.0f) + Ut.pow(zRel, 2.0f));
    }


    /*
    * Please use `ShapeRenderer.begin()` before using this method
    */
    public void render(ShapeRenderer shapeRenderer, float x, float y, long distance, float fovAngleX) {
        float renderSize;

        renderSize = angularSize * (16 / fovAngleX);

        shapeRenderer.setColor(color);
        shapeRenderer.circle(x, y, renderSize, 64);
    }

    public void computeAngularSize(long distance, float fovAngleX) {
        angularSize = 2 * (float) Math.asin((float) (2 * (float)absoluteRadius / distance));
        angularSize /= fovAngleX; // Pour convertir en format normalisé
    }

    public void visit() {
        visited = true;
    }

    public boolean isVisited() {
        return visited;
    }

    public boolean isVisitable() {
        return isVisitable;
    }

    @Override
    public String toString() {
        return visited ? "Visited" : "Not Visited";
    }

    public boolean isCholletStar() {
        return false;
    }
}
