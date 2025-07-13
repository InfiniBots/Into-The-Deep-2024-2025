package org.firstinspires.ftc.teamcode.Testing;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.Gamepad;

@Config
public class WristTesting extends OpMode {
    Servo Wrist;
    public static double transferPos = 0.0;
    public static double highBucketPos = 0.0;
    public static double lowBucketPos = 0.0;
    public static double specimenWallPos = 0.0;
    public static double specimenChamberPos = 0.0;
    public static double extraPos = 0.0;

    @Override
    public void init() {
        Wrist = hardwareMap.get(Servo.class, "Servo7");
    }
    Gamepad currentGamepad1 = new Gamepad();
    Gamepad currentGamepad2 = new Gamepad();

    Gamepad previousGamepad1 = new Gamepad();
    Gamepad previousGamepad2 = new Gamepad();

    @Override
    public void loop() {
        previousGamepad1.copy(currentGamepad1);
        previousGamepad2.copy(currentGamepad2);

        currentGamepad1.copy(gamepad1);
        currentGamepad2.copy(gamepad2);

        if (currentGamepad1.a && !previousGamepad1.a) {
            Wrist.setPosition(transferPos);
        }

        if (currentGamepad1.b && !previousGamepad1.b) {
            Wrist.setPosition(highBucketPos);
        }

        if (currentGamepad1.x && !previousGamepad1.x) {
            Wrist.setPosition(lowBucketPos);
        }

        if (currentGamepad1.y && !previousGamepad1.y) {
            Wrist.setPosition(specimenWallPos);
        }

        if (currentGamepad1.right_bumper && !previousGamepad1.right_bumper) {
            Wrist.setPosition(specimenChamberPos);
        }

        if (currentGamepad1.dpad_up && !previousGamepad1.dpad_up) {
            Wrist.setPosition(extraPos);
        }




    }
}
