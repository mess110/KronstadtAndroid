package org.kronstadt.model;

import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.kronstadt.network.UDPClient;

public class SquareButton extends Sprite {

	protected UDPClient udp;
	private int kind;

	public SquareButton(float pX, float pY, TextureRegion pTextureRegion,
			UDPClient udp, int kind) {
		super(pX, pY, pTextureRegion);
		this.udp = udp;
		this.kind = kind;
	}

	public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
			final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
		if (pSceneTouchEvent.isActionUp()) {
			switch (kind) {
			case 1:
				udp.click();
				break;
			case 2:
				udp.middleClick();
				break;
			case 3:
				udp.rightClick();
				break;
			case 4:
				udp.doubleClick();
				break;

			default:
				break;
			}

		}
		return true;
	}
}
