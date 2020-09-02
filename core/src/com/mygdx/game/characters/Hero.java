package com.mygdx.game.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameScreen;
import com.mygdx.game.Weapon;

public class Hero extends GameCharacter {
    public Hero(GameScreen gameScreen) {
        this.direction = new Vector2(0,0);
        this.temp = new Vector2(0,0);
        this.gameScreen = gameScreen;
        this.texture = new Texture("Vampire.png");
        this.textureHP = new Texture("HP.png");
        this.position = new Vector2(200, 200);
        this.speed = 100f;
        this.hpMax = 100f;
        this.hp = hpMax;
        this.weapon = new Weapon("Sword", 30f, 0.2f, 5.0f);
    }

    @Override
    public void update (float dt) {
        damageEffectTimer -= dt;
        if(damageEffectTimer < 0.0f) {
            damageEffectTimer = 0.0f;
        }
      Monster nearestMonster = null;
        float minDist = Float.MIN_VALUE;
        for (int i = 0; i < gameScreen.getAllMonsters().size(); i++) {
            float dst = gameScreen.getAllMonsters().get(i).getPosition().dst(this.position);
                if(dst < minDist) {
                  minDist = dst;
                  nearestMonster = gameScreen.getAllMonsters().get(i);
            }
        }
        if(nearestMonster != null && minDist < weapon.getAttackRadius()) {
            attackTimer += dt;
            if(attackTimer > weapon.getAttackPeriod()) {
                attackTimer = 0.0f;
               nearestMonster.takeDamage(weapon.getDamage());
            }

        }
        direction.set(0,0);
       if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
           direction.x = 1f;
       }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            direction.x = - 1f;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            direction.y = 1f;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            direction.y = -1f;
        }
        temp.set(position).mulAdd(direction, speed * dt);
        if(gameScreen.getMap().isCellPossible(temp)){
            position.mulAdd(direction, speed*dt);
        }
        checkScreenBounds();
    }
}
