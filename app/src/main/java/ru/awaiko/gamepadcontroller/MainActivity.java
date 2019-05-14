/**
 * Author: Tahoma
 */
package ru.awaiko.gamepadcontroller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements View.OnTouchListener {

    TextView l_info_x, l_info_y, l_info_align, l_info_distance, l_info_direction;
    TextView r_info_x, r_info_y, r_info_align, r_info_distance, r_info_direction;
    TextView info_array_button, info_pad_button;

    GamePadController js_left, js_right;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();

        info_array_button = findViewById(R.id.id_info_array_button);
        info_pad_button = findViewById(R.id.id_info_pad_button);
        l_info_x = findViewById(R.id.id_info_l_x);
        l_info_y = findViewById(R.id.id_info_l_y);
        l_info_align = findViewById(R.id.id_info_l_align);
        l_info_distance = findViewById(R.id.id_info_l_distance);
        l_info_direction = findViewById(R.id.id_info_l_direction);
        r_info_x = findViewById(R.id.id_info_r_x);
        r_info_y = findViewById(R.id.id_info_r_y);
        r_info_align = findViewById(R.id.id_info_r_align);
        r_info_distance = findViewById(R.id.id_info_r_distance);
        r_info_direction = findViewById(R.id.id_info_r_direction);

        js_left = new GamePadController(context,
                (RelativeLayout) findViewById(R.id.layout_joystick_left),
                R.drawable.image_button);

        js_right = new GamePadController(context,
                (RelativeLayout) findViewById(R.id.layout_joystick_right),
                R.drawable.image_button);

        setGamePadListeners();
    }

    @SuppressLint("ClickableViewAccessibility")
    public void setGamePadListeners() {
        findViewById(R.id.layout_joystick_left).setOnTouchListener(this);
        findViewById(R.id.layout_joystick_right).setOnTouchListener(this);

        findViewById(R.id.id_btnX).setOnTouchListener(this);
        findViewById(R.id.id_btnY).setOnTouchListener(this);
        findViewById(R.id.id_btnA).setOnTouchListener(this);
        findViewById(R.id.id_btnB).setOnTouchListener(this);

        findViewById(R.id.id_btnUp).setOnTouchListener(this);
        findViewById(R.id.id_btnDown).setOnTouchListener(this);
        findViewById(R.id.id_btnLeft).setOnTouchListener(this);
        findViewById(R.id.id_btnRight).setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        String msg = "";
        TextView out = null;

        if (event.getAction() == MotionEvent.ACTION_DOWN ||
                event.getAction() == MotionEvent.ACTION_MOVE) {

            switch (v.getId()) {

                case R.id.layout_joystick_left:
                    js_left.drawStick(event);
                    onTouchJoystick_Left(v, event);
                    break;

                case R.id.layout_joystick_right:
                    js_right.drawStick(event);
                    onTouchJoystick_Right(v, event);
                    break;

                case R.id.id_btnA:
                    msg = "Нажата: padA";
                    out = info_pad_button;
                    break;
                case R.id.id_btnB:
                    msg = "Нажата: padB";
                    out = info_pad_button;
                    break;
                case R.id.id_btnX:
                    msg = "Нажата: padX";
                    out = info_pad_button;
                    break;
                case R.id.id_btnY:
                    msg = "Нажата: padY";
                    out = info_pad_button;
                    break;

                case R.id.id_btnUp:
                    msg = "Нажат: UP";
                    out = info_array_button;
                    break;
                case R.id.id_btnDown:
                    msg = "Нажата: Down";
                    out = info_array_button;
                    break;
                case R.id.id_btnLeft:
                    msg = "Нажата: Left";
                    out = info_array_button;
                    break;
                case R.id.id_btnRight:
                    msg = "Нажата: Right";
                    out = info_array_button;
                    break;
            }

            if (out != null) {
                out.setText(msg);
            }

        } else {
            switch (v.getId()) {
                case R.id.layout_joystick_left:
                    js_left.drawStick(event);
                    break;
                case R.id.layout_joystick_right:
                    js_right.drawStick(event);
                    break;

            }
            info_pad_button.setText("Нажата:");
            info_array_button.setText("Нажата:");

            l_info_x.setText("X:");
            l_info_y.setText("Y:");
            l_info_align.setText("Угол:");
            l_info_distance.setText("Дистанция :");
            l_info_direction.setText("Направление :");

            r_info_x.setText("X:");
            r_info_y.setText("Y:");
            r_info_align.setText("Угол:");
            r_info_distance.setText("Дистанция :");
            r_info_direction.setText("Направление :");
        }
        return true;
    }

    public void onTouchJoystick_Left(View arg0, MotionEvent arg1) {
        l_info_x.setText("X: " + String.valueOf(js_left.getX()));
        l_info_y.setText("Y: " + String.valueOf(js_left.getY()));
        l_info_align.setText("Угол: " + String.valueOf(js_left.getAngle()));
        l_info_distance.setText("Дистанция: " + String.valueOf(js_left.getDistance()));

        int direction = js_left.get8Direction();
        if (direction == GamePadController.STICK_UP) {
            l_info_direction.setText("Направление: Up");
        } else if (direction == GamePadController.STICK_UPRIGHT) {
            l_info_direction.setText("Направление: Up Right");
        } else if (direction == GamePadController.STICK_RIGHT) {
            l_info_direction.setText("Направление: Right");
        } else if (direction == GamePadController.STICK_DOWNRIGHT) {
            l_info_direction.setText("Направление: Down Right");
        } else if (direction == GamePadController.STICK_DOWN) {
            l_info_direction.setText("Направление: Down");
        } else if (direction == GamePadController.STICK_DOWNLEFT) {
            l_info_direction.setText("Направление: Down Left");
        } else if (direction == GamePadController.STICK_LEFT) {
            l_info_direction.setText("Направление: Left");
        } else if (direction == GamePadController.STICK_UPLEFT) {
            l_info_direction.setText("Направление: Up Left");
        } else if (direction == GamePadController.STICK_NONE) {
            l_info_direction.setText("Направление: Center");
        }
    }

    public void onTouchJoystick_Right(View arg0, MotionEvent arg1) {
        r_info_x.setText("X: " + String.valueOf(js_right.getX()));
        r_info_y.setText("Y: " + String.valueOf(js_right.getY()));
        r_info_align.setText("Угол: " + String.valueOf(js_right.getAngle()));
        r_info_distance.setText("Дистанция: " + String.valueOf(js_right.getDistance()));

        int direction = js_right.get8Direction();
        if (direction == GamePadController.STICK_UP) {
            r_info_direction.setText("Направление: Up");
        } else if (direction == GamePadController.STICK_UPRIGHT) {
            r_info_direction.setText("Направление: Up Right");
        } else if (direction == GamePadController.STICK_RIGHT) {
            r_info_direction.setText("Направление: Right");
        } else if (direction == GamePadController.STICK_DOWNRIGHT) {
            r_info_direction.setText("Направление: Down Right");
        } else if (direction == GamePadController.STICK_DOWN) {
            r_info_direction.setText("Направление: Down");
        } else if (direction == GamePadController.STICK_DOWNLEFT) {
            r_info_direction.setText("Направление: Down Left");
        } else if (direction == GamePadController.STICK_LEFT) {
            r_info_direction.setText("Направление: Left");
        } else if (direction == GamePadController.STICK_UPLEFT) {
            r_info_direction.setText("Направление: Up Left");
        } else if (direction == GamePadController.STICK_NONE) {
            r_info_direction.setText("Направление: Center");
        }
    }
}

