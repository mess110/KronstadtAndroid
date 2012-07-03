package org.kronstadt;

import javax.microedition.khronos.opengles.GL10;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.camera.hud.controls.AnalogOnScreenControl;
import org.anddev.andengine.engine.camera.hud.controls.AnalogOnScreenControl.IAnalogOnScreenControlListener;
import org.anddev.andengine.engine.camera.hud.controls.BaseOnScreenControl;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;
import org.kronstadt.model.SquareButton;
import org.kronstadt.network.HTTPClient;
import org.kronstadt.network.UDPClient;

public class MouseInputActivity extends BaseGameActivity {

	private static final int CAMERA_WIDTH = 320;
	private static final int CAMERA_HEIGHT = 480;

	private Camera mCamera;

	private BitmapTextureAtlas mOnScreenControlTexture;
	private TextureRegion mOnScreenControlBaseTextureRegion;
	private TextureRegion mOnScreenControlKnobTextureRegion;

	private UDPClient udp;
	private HTTPClient http;
	private TextureRegion mSquare;
	private BitmapTextureAtlas mBitmapTextureAtlas;

	@Override
	public Engine onLoadEngine() {
		udp = new UDPClient(getBaseContext());
		http = new HTTPClient(getBaseContext());

		mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		EngineOptions eopts = new EngineOptions(true,
				ScreenOrientation.PORTRAIT, new RatioResolutionPolicy(
						CAMERA_WIDTH, CAMERA_HEIGHT), mCamera);
		return new Engine(eopts);
	}

	@Override
	public void onLoadResources() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");

		mBitmapTextureAtlas = new BitmapTextureAtlas(128, 128,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		mSquare = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mBitmapTextureAtlas, this, "square.png", 0, 0);

		mOnScreenControlTexture = new BitmapTextureAtlas(256, 128,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		mOnScreenControlBaseTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(mOnScreenControlTexture, this,
						"onscreen_control_base.png", 0, 0);
		mOnScreenControlKnobTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(mOnScreenControlTexture, this,
						"onscreen_control_knob.png", 128, 0);

		mEngine.getTextureManager().loadTextures(mOnScreenControlTexture);
		mEngine.getTextureManager().loadTextures(mBitmapTextureAtlas);
	}

	@Override
	public Scene onLoadScene() {
		final Scene scene = new Scene();
		scene.setBackground(new ColorBackground(0.0f, 0.0f, 0.0f));

		SquareButton click = new SquareButton(0, 0, mSquare, udp, 1);
		scene.registerTouchArea(click);
		scene.attachChild(click);

		SquareButton middleClick = new SquareButton(32 + 16, 0, mSquare, udp, 2);
		scene.registerTouchArea(middleClick);
		scene.attachChild(middleClick);

		SquareButton rightClick = new SquareButton(32 + 16 + 32 + 16, 0,
				mSquare, udp, 3);
		scene.registerTouchArea(rightClick);
		scene.attachChild(rightClick);

		SquareButton doubleClick = new SquareButton(
				32 + 16 + 32 + 16 + 32 + 16, 0, mSquare, udp, 4);
		scene.registerTouchArea(doubleClick);
		scene.attachChild(doubleClick);

		final AnalogOnScreenControl analogOnScreenControl = new AnalogOnScreenControl(
				CAMERA_WIDTH / 2 - 80, CAMERA_HEIGHT - 128 - 80, mCamera,
				mOnScreenControlBaseTextureRegion,
				mOnScreenControlKnobTextureRegion, 0.1f, 200,
				new IAnalogOnScreenControlListener() {
					@Override
					public void onControlChange(
							final BaseOnScreenControl pBaseOnScreenControl,
							final float pValueX, final float pValueY) {
						udp.move(pValueX, pValueY);
					}

					@Override
					public void onControlClick(
							final AnalogOnScreenControl pAnalogOnScreenControl) {
						udp.click();
					}
				});
		analogOnScreenControl.getControlBase().setBlendFunction(
				GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		analogOnScreenControl.getControlBase().setAlpha(0.5f);
		analogOnScreenControl.getControlBase().setScaleCenter(0, 128);
		analogOnScreenControl.getControlBase().setScale(1.25f);
		analogOnScreenControl.getControlKnob().setScale(1.25f);
		analogOnScreenControl.refreshControlKnobPosition();

		scene.setChildScene(analogOnScreenControl);

		return scene;
	}

	@Override
	public void onLoadComplete() {
		http.startJRobotServer();
		udp.connect();
	}
}