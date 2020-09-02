package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.characters.GameCharacter;
import com.mygdx.game.characters.Hero;
import com.mygdx.game.characters.Monster;

import java.util.*;

public class GameScreen {
    private SpriteBatch bath;
    private BitmapFont text20;
    private Hero hero;
    private List<GameCharacter> allCharacter;
    private List<Monster> allMonsters;
    private Comparator<GameCharacter> drawOrderComparator;
    private Map map;

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public List<Monster> getAllMonsters() {
        return allMonsters;
    }

    public GameScreen(SpriteBatch bath) {
        this.bath = bath;
    }

    public void create() {
        map = new Map();
        allCharacter = new ArrayList<>();
        allMonsters = new ArrayList<>();
        hero = new Hero(this);

           allCharacter.addAll(Arrays.asList(hero,
               new Monster(this), new Monster(this)));
        for (int i = 0; i < allCharacter.size(); i++) {
            if(allCharacter.get(i) instanceof Monster) {
                allMonsters.add((Monster)allCharacter.get(i));
            }

        }
        text20 = new BitmapFont(Gdx.files.internal("text20.fnt"));

        drawOrderComparator = new Comparator<GameCharacter>() {
            @Override
            public int compare(GameCharacter o1, GameCharacter o2) {
                return (int) (o2.getPosition().y - o1.getPosition().y);
            }
        };

    }

    public Map getMap() {
        return map;
    }

    public void render() { ;
        float dt = Gdx.graphics.getDeltaTime();
        update(dt);
        Gdx.gl.glClearColor(0, 0, 0 , 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        bath.begin();
        map.render(bath);
        Collections.sort(allCharacter,drawOrderComparator);
        for (int i = 0; i < allCharacter.size(); i++) {
            allCharacter.get(i).render(bath, text20);
        }
        bath.end();
    }
    public void update(float dt) {
        for (int i = 0; i < allCharacter.size(); i++) {
            allCharacter.get(i).update(dt);
        }
        for (int i = 0; i < allMonsters.size(); i++) {
            Monster currentMonster = allMonsters.get(i);
            if(!currentMonster.isAlive()) {
                allCharacter.remove(currentMonster);
                allMonsters.remove(currentMonster);
            }
        }
    }

}
