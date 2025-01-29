package fr.bloomyindev.cgj2024;

import com.badlogic.gdx.graphics.Color;
import fr.bloomyindev.cgj2024.CoordinateSystems.AbsoluteCoords3D;

public class DecorativeStar extends Star {
    public DecorativeStar(AbsoluteCoords3D coords) {
        super(coords, 1, false, Color.WHITE);
    }

    @Override
    public boolean isDecorativeStar() {
        return true;
    }
}
