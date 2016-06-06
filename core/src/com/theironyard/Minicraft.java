package com.theironyard;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Minicraft extends ApplicationAdapter {

	SpriteBatch batch;
	TextureRegion down, up, right, left;
	TextureRegion standDown, standUp, standRight, standLeft;
	Animation walkUp, walkDown, walkLeft, walkRight;

	float x, y, xv, yv;
	float time;
	int movePlayer;

	static final float MAX_VELOCITY = 100;
	static final float DECELERATION = 0.80f;


	@Override
	public void create () {

		batch = new SpriteBatch();
		Texture tiles = new Texture("tiles.png");
		TextureRegion[][] grid = TextureRegion.split(tiles, 16, 16);

		down = grid[6][0];
		standDown = new TextureRegion(down);
		standDown.flip(true, false);

		up = grid[6][1];
		standUp = new TextureRegion(up);
		standUp.flip(true, false);

		right = grid[6][3];
		left = new TextureRegion(right);
		left.flip(true, false);

		standRight = grid[6][2];
		standLeft = new TextureRegion(standRight);
		standLeft.flip(true, false);

		walkRight = new Animation(0.2f, right, standRight);
		walkLeft = new Animation(0.2f, left, standLeft);
		walkUp = new Animation(0.2f, up, standUp);
		walkDown = new Animation(0.2f, down, standDown);

	}

	@Override
	public void render () {

		move();

		time += Gdx.graphics.getDeltaTime();

		if(xv>0){
			right = walkRight.getKeyFrame(time, true);
		}

		if(xv<0){
			left = (walkLeft.getKeyFrame(time, true));
		}

		if(yv>0){
			up = (walkUp.getKeyFrame(time, true));
		}

		if(yv<0){
			down = (walkDown.getKeyFrame(time, true));
		}

		Gdx.gl.glClearColor(0, 0.8f, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();

		TextureRegion img;

		if(movePlayer ==3){

			img = right;
		}
		else if (movePlayer ==4){
			img = left;
		}
		else if(movePlayer == 2){
			img = down;
		}
		else if(movePlayer == 1){
			img = up;
		}
		else{
			img = left;
		}

		batch.draw(img, x, y, 30, 40);

		batch.end();

	}

	public void move (){

		float run = MAX_VELOCITY + MAX_VELOCITY;

		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			yv = MAX_VELOCITY;
			if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
				yv = run;
			}

			movePlayer = 1;

		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			yv = MAX_VELOCITY * -1;
			if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
				yv = -run;
			}

			movePlayer = 2;

		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			xv = MAX_VELOCITY;
			if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
				xv = run;
			}

			movePlayer = 3;

		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			xv = MAX_VELOCITY * -1;
			if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
				xv = -run;
			}

			movePlayer = 4;
		}

		if(x  >Gdx.graphics.getWidth()){
			x = -5;
		}
		if(x <- 5){
			x = Gdx.graphics.getWidth();
		}
		if(y <- 5){
			y = Gdx.graphics.getHeight();
		}
		if (y > Gdx.graphics.getHeight()){
			y = -5;
		}

		float deltaTime = Gdx.graphics.getDeltaTime();
		y+= yv * deltaTime;
		x+= xv * deltaTime;
		xv = decelerate(xv);
		yv = decelerate(yv);

	}
	public float decelerate(float velocity) {
		velocity *= DECELERATION;
		if (Math.abs(velocity) < 1) {
			velocity = 0;
		}
		return velocity;
	}

}