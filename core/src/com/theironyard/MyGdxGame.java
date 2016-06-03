package com.theironyard;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	TextureRegion down, up, stand, right;

	boolean faceLeft = true;

	float x, y, xv, yv;

	static final float MAX_VELOCITY = 100;
	static final float DECELERATION = 0.95f;
	static final int WIDTH =  16;
	static final int HEIGHT = 16;



	@Override
	public void create () {
		batch = new SpriteBatch();
		Texture sheet = new Texture("tiles.png");
		TextureRegion[][] tiles = TextureRegion.split(sheet, WIDTH, HEIGHT);
		stand = tiles [6] [2];
		down = tiles [6][0];
		up = tiles [6][1];
		right = tiles [6][3];
		faceLeft = new TextureRegion(right);
		faceLeft.flip(true, false);

	}


	@Override
	public void render () {
		time += Gdx.graphics.getDeltaTime();


		TextureRegion img;
		Gdx.gl.glClearColor(0, 0.5f, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		if (right) {
			batch.draw(img, x, y, WIDTH * 3, HEIGHT * 3);
		}
		else {
			batch.draw(img, x + WIDTH * 3, y, WIDTH * -3, HEIGHT * 3);

		}

		batch.draw(img, x, y, WIDTH * 3, HEIGHT * 3);

		batch.end();

		move();
	}

	public void move (){
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			yv = MAX_VELOCITY;


		}
		else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			yv = MAX_VELOCITY * -1;


		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			xv = MAX_VELOCITY;

		}
		else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			xv = MAX_VELOCITY * -1;


		}
	}
	public float decelerate(float velocity) {
		velocity *= DECELERATION;
		if (Math.abs(velocity) < 1) {
			velocity = 0;
		}
		return velocity;
	}

}
