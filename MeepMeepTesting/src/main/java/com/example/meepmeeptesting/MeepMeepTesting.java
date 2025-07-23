package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ProfileAccelConstraint;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.security.cert.Extension;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(50, 50, Math.toRadians(180), Math.toRadians(180), 11.061975)
                .setDimensions(13.41339, 16)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-10, 59, Math.toRadians(-90)))
                .splineTo(new Vector2d(-10, 32), Math.toRadians(-90))
                .splineTo(new Vector2d(-38, 34), Math.toRadians(-145))
                .splineToLinearHeading(new Pose2d(-38, 34, -235), Math.toRadians(-145))
                .splineTo(new Vector2d(-48, 34), Math.toRadians(-145))
                .splineToLinearHeading(new Pose2d(-48, 34, -235), Math.toRadians(-145))
                .splineTo(new Vector2d(-58, 34), Math.toRadians(-145))
                .splineToLinearHeading(new Pose2d(-58, 34, -235), Math.toRadians(-145))
                .splineToLinearHeading(new Pose2d(-40, 60, Math.toRadians(-90)), Math.toRadians(-255))
                .splineToLinearHeading(new Pose2d(-30, 46, Math.toRadians(-90)), Math.toRadians(-90))
                .splineToLinearHeading(new Pose2d(-10, 32, Math.toRadians(-90)), Math.toRadians(-90))
                .splineToLinearHeading(new Pose2d(-10, 38, Math.toRadians(-90)), Math.toRadians(-90))
                .splineToLinearHeading(new Pose2d(-32, 47, Math.toRadians(-90)), Math.toRadians(-90))
                .splineToLinearHeading(new Pose2d(-40, 60, Math.toRadians(-90)), Math.toRadians(-90))
                .splineToLinearHeading(new Pose2d(-30, 46, Math.toRadians(-90)), Math.toRadians(-90))
                .splineToLinearHeading(new Pose2d(-11, 32, Math.toRadians(-90)), Math.toRadians(-90))
                .splineToLinearHeading(new Pose2d(-11, 38, Math.toRadians(-90)), Math.toRadians(-90))
                .splineToLinearHeading(new Pose2d(-32, 47, Math.toRadians(-90)), Math.toRadians(-90))
                .splineToLinearHeading(new Pose2d(-40, 60, Math.toRadians(-90)), Math.toRadians(-90))
                .splineToLinearHeading(new Pose2d(-12, 32, Math.toRadians(-90)), Math.toRadians(-90))
                .splineToLinearHeading(new Pose2d(-12, 38, Math.toRadians(-90)), Math.toRadians(-90))
                .splineToLinearHeading(new Pose2d(-32, 47, Math.toRadians(-90)), Math.toRadians(-90))
                .splineToLinearHeading(new Pose2d(-40, 60, Math.toRadians(-90)), Math.toRadians(-90))
                .splineToLinearHeading(new Pose2d(-12, 32, Math.toRadians(-90)), Math.toRadians(-90))
                .splineToLinearHeading(new Pose2d(-12, 38, Math.toRadians(-90)), Math.toRadians(-90))
                .build());




        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}