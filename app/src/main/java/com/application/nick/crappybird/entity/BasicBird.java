package com.application.nick.crappybird.entity;

import com.application.nick.crappybird.GameActivity;

import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.shape.IShape;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * Created by Nick on 4/5/2015.
 */
public class BasicBird extends AnimatedSprite {

    private static final float DEMO_VELOCITY = 150.0f;
    private static final float DEMO_POSITION = 1.1f* GameActivity.CAMERA_WIDTH;
    private static final float GRAVITY = 9.8f * 30;
    private static final float JUMP_VELOCITY = 5 * 30;



    private final PhysicsHandler mPhysicsHandler;

    public BasicBird(float x, float y, TiledTextureRegion pTiledTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
        super(x, y, pTiledTextureRegion, pVertexBufferObjectManager);

        mPhysicsHandler = new PhysicsHandler(this);
        registerUpdateHandler(mPhysicsHandler);

        setAcceleration(0, GRAVITY);
        setVelocity(0, 0);

        animate(200);

    }

    public void jump() {
        float currentYVelocity = mPhysicsHandler.getVelocityY();
        if(currentYVelocity > 0) {
            setYVelocity(-JUMP_VELOCITY);
        }

    }

    public float getVelocityY() {
        return mPhysicsHandler.getVelocityY();
    }

    public void setXVelocity(float x) {mPhysicsHandler.setVelocityX(x);}

    public void setYVelocity(float y) {mPhysicsHandler.setVelocityY(y);}

    public void setVelocity(float x, float y) {
        mPhysicsHandler.setVelocity(x, y);
    }

    public void setAcceleration(float x, float y) {
        mPhysicsHandler.setAcceleration(x, y);
    }

    @Override
    public boolean collidesWith(IShape pOtherShape) {

        Sprite sprite = (Sprite) pOtherShape;

        float spriteLeft = sprite.getX();
        float spriteRight = spriteLeft + sprite.getWidth();
        float spriteTop = sprite.getY();
        float spriteBottom = spriteTop + sprite.getHeight();
        float left = this.getX();
        float right = left + this.getWidth();
        float top = this.getY();
        float bottom = top + this.getHeight();

        if ((spriteRight > left && spriteLeft < right) && (spriteTop < bottom && spriteBottom > top)) {
            return true;
        }
        return false;
    }

    public void die() {
        unregisterUpdateHandler(mPhysicsHandler);
    }

    public void alive() {
        registerUpdateHandler(mPhysicsHandler);
    }



}