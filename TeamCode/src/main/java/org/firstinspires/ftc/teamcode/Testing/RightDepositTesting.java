package org.firstinspires.ftc.teamcode.Testing;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.Gamepad;

@Config
@TeleOp
public class RightDepositTesting extends OpMode {
    Servo rightDeposit;
    Servo leftDeposit;
    public static double wallPos = 0.0;
    public static double chamberPreparePos = 0.0;
    public static double chamberPos = 0.0;

    @Override
    public void init() {
        rightDeposit = hardwareMap.get(Servo.class, "rightDeposit");
        leftDeposit = hardwareMap.get(Servo.class, "leftDeposit");
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
            rightDeposit.setPosition(wallPos);
        }

        if (currentGamepad1.b && !previousGamepad1.b) {
            rightDeposit.setPosition(chamberPreparePos);
        }
        if (currentGamepad1.y && !previousGamepad1.y) {
            rightDeposit.setPosition(chamberPos);
        }

    }
}
