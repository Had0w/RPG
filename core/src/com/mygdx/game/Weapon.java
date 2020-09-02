package com.mygdx.game;

public class Weapon {
    String name;
    float attackRadius;
    float attackPeriod;
    float damage;

    public Weapon(String name, float attackRadius, float attackPeriod, float damage) {
        this.name = name;
        this.attackRadius = attackRadius;
        this.attackPeriod = attackPeriod;
        this.damage = damage;
    }

    public float getAttackRadius() {
        return attackRadius;
    }

    public float getAttackPeriod() {
        return attackPeriod;
    }

    public float getDamage() {
        return damage;
    }
}
