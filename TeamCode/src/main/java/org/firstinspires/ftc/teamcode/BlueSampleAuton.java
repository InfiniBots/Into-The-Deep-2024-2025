package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;


import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import org.firstinspires.ftc.teamcode.MecanumDrive;

@Config
@Autonomous
public class BlueSampleAuton extends LinearOpMode {

    public class LeftSlide {
        private DcMotorEx leftslide;

        public LeftSlide(HardwareMap hardwareMap) {
            leftslide = hardwareMap.get(DcMotorEx.class,"ExpMotor0");
            leftslide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            leftslide.setDirection(DcMotorSimple.Direction.REVERSE);
        }
    }

    public class RightSlide {
        private DcMotorEx rightslide;

        public RightSlide(HardwareMap hardwareMap) {
            rightslide = hardwareMap.get(DcMotorEx.class,"ExpMotor1");
            rightslide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            rightslide.setDirection(DcMotorSimple.Direction.REVERSE);
        }
    }


    public class LeftPivot {
        private DcMotorEx leftpivot;

        public LeftPivot(HardwareMap hardwareMap) {
            leftpivot = hardwareMap.get(DcMotorEx.class,"ExpMotor2");
            leftpivot.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            leftpivot.setDirection(DcMotorSimple.Direction.REVERSE);
        }
    }

    public class RightPivot {
        private DcMotorEx rightpivot;

        public RightPivot(HardwareMap hardwareMap) {
            rightpivot = hardwareMap.get(DcMotorEx.class,"ExpMotor3");
            rightpivot.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            rightpivot.setDirection(DcMotorSimple.Direction.REVERSE);
        }
    }

    public class ClawRotate {
        private Servo clawrotate;

        public ClawRotate(HardwareMap hardwareMap) {
            clawrotate = hardwareMap.get(Servo.class, "Servo0");
        }
    }

    public class Claw {
        private Servo claw;

        public Claw(HardwareMap hardwareMap) {
            claw = hardwareMap.get(Servo.class, "Servo1");
        }
    }


    @Override
    public void runOpMode() throws InterruptedException {

    }

}
