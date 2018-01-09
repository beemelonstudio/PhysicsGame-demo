package com.beemelon.physicsgame.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.beemelon.physicsgame.jann.JannScreen;
import com.beemelon.physicsgame.jann.Line;
import com.beemelon.physicsgame.jann.PolyLine;

/**
 * Created by Jann on 13.12.17.
 */
public class CustomInputListener implements GestureDetector.GestureListener, InputProcessor {

    private JannScreen screen;

    private Vector3 coordinates;
    private PolyLine polyLine;
    private Line straightLine;

    private float dx = 0f;
    private float dy = 0f;
    private float distance = 0f;

    public CustomInputListener(JannScreen screen) {

        this.screen = screen;
    }

    public InputProcessor createInputProcesser() {
        return this;
    }

    public GestureDetector createGestureListener() {
        return new GestureDetector(this);
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        coordinates = new Vector3(screenX, screenY, 0);
        screen.camera.unproject(coordinates);

        switch (screen.currentType) {

            case STRAIGHTLINE:
                straightLine = new Line(coordinates.x, coordinates.y);
                screen.straightLines.add(straightLine);
                break;

            case POLYLINE:
                polyLine = new PolyLine(coordinates.x, coordinates.y);
                screen.polyLines.add(polyLine);
                break;

            default: break;
        }

        Gdx.app.log("Touch down at", coordinates.x + " - " + coordinates.y);

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        coordinates.set(screenX, screenY, 0);
        screen.camera.unproject(coordinates);

        switch (screen.currentType) {

            case STRAIGHTLINE:
                straightLine.setEnd(coordinates.x, coordinates.y);
                straightLine.build();
                break;

            case POLYLINE:
                polyLine.insert(coordinates.x, coordinates.y);
                polyLine.build();
                break;

            default: break;
        }

        Gdx.app.log("Touch up at", coordinates.x + " - " + coordinates.y);

        /*
        float x = coordinates.x;
        float y = coordinates.y;
        float width = 0.01f;
        float height = 0.01f;
        float rotation = (float) -Math.toDegrees(Math.atan2(coordinates.x - coordinates.x, coordinates.y - coordinates[0].y));
        BodyDef.BodyType bodyType = BodyDef.BodyType.StaticBody;
        LineType lineType = LineType.SOLID;

        screen.polyLines.add(new Line(screen.bodyFactory.createLine(x, y, width, height, rotation, bodyType, lineType)));
        */

        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        coordinates.set(screenX, screenY, 0);
        screen.camera.unproject(coordinates);

        switch (screen.currentType) {

            case STRAIGHTLINE:
                straightLine.setEnd(coordinates.x, coordinates.y);
                break;

            case POLYLINE:
                dx = coordinates.x - polyLine.vertices.get(polyLine.vertices.size-2);
                dy = coordinates.y - polyLine.vertices.get(polyLine.vertices.size-1);
                distance = (float) Math.sqrt(dx*dx + dy*dy);

                // Only process points more than 1 mm distance to the last point
                if(distance > 0.05f)
                    polyLine.insert(coordinates.x, coordinates.y);
                break;

            default: break;
        }

        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
