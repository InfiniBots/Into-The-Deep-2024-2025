package org.firstinspires.ftc.teamcode.AutonSubsystemsAndCrap;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class LiftSubsystem {

    public DcMotorEx rightLift;
    public DcMotorEx leftLift;
    public int setPosition;

    public LiftSubsystem(HardwareMap hardwareMap)    {

        rightLift = hardwareMap.get(DcMotorEx.class, "rightLift");
        leftLift = hardwareMap.get(DcMotorEx.class, "leftLift");
        leftLift.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public class updatePID implements Action {
        public updatePID() {}
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            rightLift.setPower(ExtendoPIDClass.returnExtendoPID(setPosition, rightLift.getCurrentPosition()));
            leftLift.setPower(ExtendoPIDClass.returnExtendoPID(setPosition, rightLift.getCurrentPosition()));
            return true;
        }
    }
    public Action UpdatePID()
    {return new updatePID(); }

    public class setPosition implements Action {
        int set;
        public setPosition(int position)
        {set = position;}
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            setPosition = set;
            return false;
        }
    }
    public Action SetPosition(int pos)
    {return new setPosition(pos);}
}
