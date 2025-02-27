package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import java.util.List;

public class LimelightSampleTracking {
    private Limelight3A limelight;
    private LLResultTypes.DetectorResult lockedSample = null;
    private Telemetry telemetry;

    public LimelightSampleTracking(HardwareMap hardwareMap, Telemetry telemetry) {
        limelight = hardwareMap.get(Limelight3A.class, "limelight");

    }

    public LLResultTypes.DetectorResult getClosestYellowSample() {
        LLResult result = limelight.getLatestResult();
        if (result == null || result.getDetectorResults().isEmpty()) {
            lockedSample = null;
            return null;
        }

        List<LLResultTypes.DetectorResult> detectorResults = result.getDetectorResults();
        LLResultTypes.DetectorResult closestSample = null;
        double minDistance = Double.MAX_VALUE;
        double threshold = 2;

        for (LLResultTypes.DetectorResult detection : detectorResults) {
            if (detection.getClassName().equalsIgnoreCase("yellow")) {
                double x = detection.getTargetXDegrees();
                double y = detection.getTargetYDegrees();
                double distance = Math.hypot(x, y);


                if (lockedSample == null) {
                    lockedSample = detection;
                    return lockedSample;
                }


                if (distance < minDistance - threshold) {
                    minDistance = distance;
                    closestSample = detection;
                }
            }
        }


        if (closestSample != null) {
            lockedSample = closestSample;
        }

        return lockedSample;
    }

    public void trackClosestYellowSample() {
        LLResultTypes.DetectorResult target = getClosestYellowSample();

        if (target != null) {
            telemetry.addData("Locked Sample", "X: %.2f, Y: %.2f, Distance: %.2f",
                    target.getTargetXDegrees(), target.getTargetYDegrees(),
                    Math.hypot(target.getTargetXDegrees(), target.getTargetYDegrees()));
        } else {
            telemetry.addData("No Yellow Sample found", "No Sample");
        }
        telemetry.update();
    }
}
