package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Map {
    private static final int CELLS_X = 16;
    private static final int CELLS_Y = 9;
    private static final int CELLS_SIZE = 80;
    byte[][] data = new byte[CELLS_X][CELLS_Y];
    private Texture grassTexture;
    private Texture wallTexture;

    public Map() {
        data[2][5] = 1;
        data[3][5] = 1;
        data[4][6] = 1;
        data[7][8] = 1;
        grassTexture = new Texture("grass1.jpg");
        wallTexture = new Texture("wall2.png");
    }
    public boolean isCellPossible(Vector2 position) {
        return data[(int)position.x/CELLS_SIZE][(int)position.y/CELLS_SIZE] == 0;
    }
    public void render(SpriteBatch batch) {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 9; j++) {
                batch.draw(grassTexture, i * 80, j * 80);
                if(data[i][j] == 1) batch.draw(wallTexture, i*80, j*80);
            }
        }
    }
}
