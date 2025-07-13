package org.firstinspires.ftc.teamcode.Testing;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.Gamepad;

@Config
public class ArmTesting extends OpMode {
    Servo rightDeposit;
    Servo leftDeposit;
    public static double transferPos = 0.0;
    public static double highBucketPos = 0.0;
    public static double lowBucketPos = 0.0;
    public static double specimenWallPos = 0.0;
    public static double specimenChamberPos = 0.0;
    public static double extraPos = 0.0;

    @Override
    public void init() {
        rightDeposit = hardwareMap.get(Servo.class, "Servo2");
        leftDeposit = hardwareMap.get(Servo.class, "Servo3");
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
            rightDeposit.setPosition(transferPos);
            leftDeposit.setPosition(1 - transferPos);
        }

        if (currentGamepad1.b && !previousGamepad1.b) {
            rightDeposit.setPosition(highBucketPos);
            leftDeposit.setPosition(1 - highBucketPos);
        }

        if (currentGamepad1.x && !previousGamepad1.x) {
            rightDeposit.setPosition(lowBucketPos);
            leftDeposit.setPosition(1 - lowBucketPos);
        }

        if (currentGamepad1.y && !previousGamepad1.y) {
            rightDeposit.setPosition(specimenWallPos);
            leftDeposit.setPosition(1 - specimenWallPos);
        }

        if (currentGamepad1.right_bumper && !previousGamepad1.right_bumper) {
            rightDeposit.setPosition(specimenChamberPos);
            leftDeposit.setPosition(1 - specimenChamberPos);
        }

        if (currentGamepad1.dpad_up && !previousGamepad1.dpad_up) {
            rightDeposit.setPosition(extraPos);
            leftDeposit.setPosition(1 - extraPos);
        }




    }
}
