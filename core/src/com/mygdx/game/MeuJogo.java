package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.BitmapFontLoader;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.ball.Ball;
import com.mygdx.game.ball.BallController;
import com.mygdx.game.shooterball.ShooterBall;
import com.mygdx.game.maps.Map;
import com.mygdx.game.shooterball.ShooterBallController;
import com.badlogic.gdx.graphics.Color;

import java.nio.ByteBuffer;
import java.util.ArrayList;

import static com.badlogic.gdx.math.MathUtils.random;

public class MeuJogo extends ApplicationAdapter {
	SpriteBatch batch;
	public static ShooterBall shooterBall;
	public static boolean menu=true,gameover=false;

	public static Map map;
	public static Ball ball;
	public static AssetManager manager;
	public static InputMultiplexer multiplexer;

	public OrthographicCamera cam;
	Pixmap pixmap;

	public static int startx, starty, endx, endy, lives;
	public static float score;
	public static String textLives;
	public static ArrayList<Vector2> path = new ArrayList<>();
	private static BitmapFontLoader.BitmapFontParameter bfp = new BitmapFontLoader.BitmapFontParameter();
	BitmapFont font;
	Vector2 nextpixel;

	Texture pixmaptex;

	public static void addInputProcessor(InputProcessor inputProcessor){
		if (multiplexer == null) multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(inputProcessor);
		Gdx.input.setInputProcessor(multiplexer);
	}

	public static void resetStats()
	{
		lives=1;
		score=0;
	}

	public static Vector2 findWalkablePix(int x, int y, Pixmap pixmap)
	{
		int pixel,green,blue,red;
		Vector2 ret = new Vector2(x,y);
		for(int i=0;i<=2;i++)
		{
			for(int j=0;j<=2;j++)
			{
				pixel = pixmap.getPixel(x+i-1, y+j-1);
				green = (pixel & 0xFF0000) >>> 16;
				blue = (pixel & 0xFF00) >>> 8;
				red = pixel >>> 24;
				if(red == 237 && green == 28 && blue == 36)
				{
					ret.set(x+i-1,y+j-1);
				}
				else if(red == 0 && green == 254 && blue== 0)
				{
					ret.set(x+i-1,y+j-1);
				}
			}
		}
		return ret;
	}




	@Override
	public void create () {

		batch = new SpriteBatch();

		manager = new AssetManager();
		manager.load("maps/map11.png", Texture.class);
		manager.load("balls/0.png", Texture.class);
		manager.load("balls/1.png", Texture.class);
		manager.load("balls/2.png", Texture.class);
		manager.load("balls/3.png", Texture.class);
		manager.load("gameover.png", Texture.class);
		manager.load("replay.png", Texture.class);
		manager.load("menu.png", Texture.class);
		manager.load("fonte.fnt", BitmapFont.class, bfp);
		manager.finishLoading();//trava o jogo
		//shooterBall = new ShooterBall(String.valueOf(random.nextInt(4)));

		MeuInputProcessor MeuInputProcessor = new MeuInputProcessor();
		addInputProcessor(MeuInputProcessor);
		map = new Map("11");
		//ball = new Ball("0");

		//pixmap = new Pixmap(map.getTexture());
		map.getTexture().getTextureData().prepare();
		pixmap = map.getTexture().getTextureData().consumePixmap();
		font = manager.get("fonte.fnt", BitmapFont.class);
		//pixmap = new Pixmap(map.getTexture().getWidth(), map.getTexture().getHeight(), Pixmap.Format.RGBA8888);


		for (int x = 0; x < pixmap.getWidth(); x++) {

			for (int y = 0; y < pixmap.getHeight(); y++) {


				int pixel = pixmap.getPixel(x, y);
				int green = (pixel & 0xFF0000) >>> 16;
				int blue = (pixel & 0xFF00) >>> 8;
				int red = pixel >>> 24;
				if(red == 0 && green == 255 && blue== 0)
				{
					startx = x;
					starty = y;
					//x = pixmap.getWidth();
					//y = pixmap.getHeight();
					System.out.println("start pixel (" + x + ", " + y + "): ");
				}
				if(red == 0 && green == 254 && blue== 0)
				{
					endx = x;
					endy = y;
					System.out.println("end pixel (" + x + ", " + y + "): ");
				}
//				if(red == 237 && green == 28 && blue == 36)
//				{
//					System.out.println("walkpixel" + x + ", " + y + "): ");
//				}
				//pixmap.getPixel()
				//PixelColor pColor = pixmap.getPixel(x,y);
				//int alpha =  pixmap.getPixel(x,y) & 0xFF;
				//if (alpha != 0 && alpha != 255) System.out.println("sim");
				//if (alpha == 0) {
//					pixmap.setColor(1, 0, 0, 1);
//					//ByteBuffer bbuffer = new ByteBuffer(0xFF00_00FF);
//					pixmap.drawPixel(x,y);
//				}else {
//					pixmap.setColor(0, 1, 0, 1);
//					pixmap.drawPixel(x,y);
//				}
				//if (pixelColor.r != 1.0f || pixelColor.g != 1.0f || pixelColor.b != 1.0f || pixelColor.a != 1.0f) {
				//	if(pixelColor.r != 0 && pixelColor.g != 0 && pixelColor.b != 0 && pixelColor.a != 1){
						//System.out.println("Non-white pixel at (" + x + ", " + y + "): " + pixelColor.);
					//}
				//}
			}
		}

		int x = startx;
		int y = starty;


		path.add(new Vector2(x,y));
		while(x != endx || y != endy){
			pixmap.setColor(0, 0, 0, 1);
			pixmap.drawPixel(x,y);
			nextpixel = findWalkablePix(x,y,pixmap);
			x =(int) nextpixel.x;
			y =(int) nextpixel.y;
			path.add(nextpixel);
			//System.out.println("Vector2: " + nextpixel.x + ", " + nextpixel.y + ")");
		}
		pixmap.setColor(0, 0, 0, 1);
		pixmap.drawPixel(x,y);

/*
		for (Vector2 vector : path) {
			System.out.println("Vector2: " + vector.x + ", " + vector.y + ")");
		}
*/
		//System.out.println("Vector2: " + path.get(0).x + ", " + path.get(0).y + ")");

		BallController.init();
		ShooterBallController.init();
		resetStats();

		cam = new OrthographicCamera();

		pixmaptex = new Texture(pixmap);
	}


	@Override
	public void resize(int width, int height) {
		cam.viewportWidth = map.getWidth();
		cam.viewportHeight = map.getHeight();


		cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
		cam.update();
	}

	@Override
	public void render() {
		batch.setProjectionMatrix(cam.combined);
		ScreenUtils.clear(1, 1, 1, 1);

		Gdx.graphics.setTitle(Gdx.graphics.getFramesPerSecond() + "");
		batch.begin();
		//System.out.println("lives: " + lives + "\tscore: " + score);
		//lives=1;
		if(menu==true)
		{
			menu=false;
		}
		else if(lives>0)
		{
			gameover=false;
			batch.draw(pixmaptex, 0, 0, map.getWidth(), map.getHeight());
			//shooterBall.draw(batch);
			//ball.draw(batch,Gdx.graphics.getDeltaTime());
			BallController.draw(batch,Gdx.graphics.getDeltaTime());
			ShooterBallController.draw(batch,Gdx.graphics.getDeltaTime());
			font.draw(batch, "Lives: " + lives, 1000, 630);
			font.draw(batch, "Score: " + (int)score, 0, 630);

			//batch.draw(shooterBall.getTexture(), (map.getWidth()/2)-shooterBall.getWidth()/2, (map.getHeight()/2)-shooterBall.getHeight()/2);
			//batch.draw(manager.<Texture>get("balls/1.png"), (map.getWidth()/2)-41, (map.getHeight()/2)-41, ((float)41/2), ((float)41/2));
		}
		else
		{
			if(!gameover)
			{
				gameover=true;
			}
			batch.draw(manager.<Texture>get("gameover.png"),0,0);
			batch.draw(manager.<Texture>get("replay.png"),(map.getWidth()/4),10,100,100);
			batch.draw(manager.<Texture>get("menu.png"),(map.getWidth()*3/4),10,100,100);
		}


		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
