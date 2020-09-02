package com.mygdx.game.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameScreen;
import com.mygdx.game.Weapon;

public abstract class GameCharacter {
    GameScreen gameScreen;
    Texture texture;
    Texture textureHP;
    Vector2 position;
    float speed;
    float hp, hpMax;
    float damageEffectTimer;
    float attackTimer;
    Weapon weapon;
    Vector2 direction;
    Vector2 temp;
    public boolean isAlive(){
        return hp > 0;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void render(SpriteBatch batch, BitmapFont bitmapFont) {
        if(damageEffectTimer == 1.0f) {
            batch.setColor(1, 0 - damageEffectTimer,0 - damageEffectTimer,1);
        }
        batch.draw(texture, position.x - 40, position.y - 40);
        batch.setColor(0, 0,0,1);
        batch.draw(textureHP, position.x -40, position.y + 40, 84, 16);
        batch.setColor(1, 0,0,1);
        batch.draw(textureHP, position.x - 38, position.y + 42, 0, 0 , hp/hpMax*80, 12,
                1, 1, 0,0,0, 80, 20, false, false);
        batch.setColor(1, 1, 1, 1);
        bitmapFont.draw(batch, String.valueOf((int)hp), position.x - 40  , position.y + 56, 80, 1, false);
    }
    public abstract  void update(float dt);
    public void checkScreenBounds() {
        if (position.x > 1280.0f) {
            position.x = 1280.0f;
        }
        if (position.x < 0.0f) {
            position.x = 0.0f;
        }
        if (position.y > 720.0f) {
            position.y = 720.0f;
        }
        if (position.y < 0.0f) {
            position.y = 0.0f;
        }
    }
    public void takeDamage(float amount) {
        damageEffectTimer += 0.5f;
        if(damageEffectTimer > 1.0f) {
            damageEffectTimer = 1.0f;
        }
        hp -= amount;
    }
}
