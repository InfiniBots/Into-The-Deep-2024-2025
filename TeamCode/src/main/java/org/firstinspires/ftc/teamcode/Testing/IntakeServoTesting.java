package org.firstinspires.ftc.teamcode.Testing;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.Gamepad;

@Config
@TeleOp
public class IntakeServoTesting extends OpMode {
    Servo rightIntake;
    Servo leftIntake;
    public static double transferPos = 0.0;
    public static double submersiblePos = 0.0;
    public static double staticPos = 0.0;
    public static double extraPos = 0.0;

    @Override
    public void init() {
        rightIntake = hardwareMap.get(Servo.class, "rightIntakeServo");
        leftIntake = hardwareMap.get(Servo.class, "leftIntakeServo");
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
            rightIntake.setPosition(transferPos);
            leftIntake.setPosition(1 - transferPos);
        }

        if (currentGamepad1.b && !previousGamepad1.b) {
            rightIntake.setPosition(submersiblePos);
            leftIntake.setPosition(1 - submersiblePos);
        }

        if (currentGamepad1.x && !previousGamepad1.x) {
            rightIntake.setPosition(staticPos);
            leftIntake.setPosition(1 - staticPos);
        }

        if (currentGamepad1.dpad_up && !previousGamepad1.dpad_up) {
            rightIntake.setPosition(extraPos);
            leftIntake.setPosition(1 - extraPos);
        }




    }
}
