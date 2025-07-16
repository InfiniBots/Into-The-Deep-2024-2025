package org.firstinspires.ftc.teamcode.AutonSubsystemsAndCrap;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ExtendoSubsystem {

    public DcMotorEx Extendo;
    public int setPosition;

    public ExtendoSubsystem(HardwareMap hardwareMap)    {

        Extendo = hardwareMap.get(DcMotorEx.class, "Extendo");
        Extendo.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public class updatePID implements Action {
        public updatePID() {}
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            Extendo.setPower(ExtendoPIDClass.returnExtendoPID(setPosition, Extendo.getCurrentPosition()));
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
