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
                .setConstraints(50, 50, Math.toRadians(180), Math.toRadians(180), 14.193)
                .setDimensions(17.193, 16.818)
                .build();

        Pose2d initialPose = new Pose2d(-8, 63.5, Math.toRadians(-90));

        Pose2d toBasket_lastPose = new Pose2d(0, 33, Math.toRadians(-90));
        Pose2d pushSample3_lastPose = new Pose2d(-50, 40, Math.toRadians(-180));
        Pose2d turnToSamplePose = new Pose2d(-48, 37, Math.toRadians(-90));
        Pose2d pickSpecimenPose = new Pose2d(-48, 35, Math.toRadians(-90));
        Pose2d scoreSpecimenPoseFirst = new Pose2d(3, 30, Math.toRadians(-90));
        Pose2d scoreSpecimenPoseSecond = new Pose2d(0, 30, Math.toRadians(-90));
        Pose2d scoreSpecimenPoseThird = new Pose2d(0, 33, Math.toRadians(-90));
        Pose2d scoreSpecimenPoseFourth = new Pose2d(0, 33, Math.toRadians(-90));

        final double minTransVel = 90;
        final double minProfAccel = -60;
        final double maxProfAccel = 80;

        final double minTransVelStraight = 120;
        final double minProfAccelStraight = -80;
        final double maxProfAccelStraight = 120;


        TrajectoryActionBuilder tab1 = myBot.getDrive().actionBuilder(initialPose)
                .splineToLinearHeading(toBasket_lastPose, Math.toRadians(-90), new TranslationalVelConstraint(minTransVel), new ProfileAccelConstraint(minProfAccel, maxProfAccel));

        TrajectoryActionBuilder tab2 = tab1.endTrajectory().fresh()
                .setTangent(Math.toRadians(0))
                .splineToLinearHeading(new Pose2d(-37, 38, Math.toRadians(-90)), Math.toRadians(0), new TranslationalVelConstraint(minTransVel), new ProfileAccelConstraint(minProfAccel, maxProfAccel))
                .setTangent(Math.toRadians(-90))
                .splineToLinearHeading(new Pose2d(-37, 6, Math.toRadians(-90)), Math.toRadians(-90), new TranslationalVelConstraint(minTransVel), new ProfileAccelConstraint(minProfAccel, maxProfAccel))
                .setTangent(Math.toRadians(0))
                .splineToLinearHeading(new Pose2d(-45,  6, Math.toRadians(-180)), Math.toRadians(0))
                .setTangent(Math.toRadians(-270))
                .splineToLinearHeading(new Pose2d(-45, 58, Math.toRadians(-180)), Math.toRadians(-270), new TranslationalVelConstraint(minTransVelStraight), new ProfileAccelConstraint(minProfAccelStraight, maxProfAccelStraight));
        //.splineToLinearHeading(new Pose2d(45, -40, Math.toRadians(180)), Math.toRadians(270), new TranslationalVelConstraint(minTransVelStraight), new ProfileAccelConstraint(minProfAccelStraight, maxProfAccelStraight));

        TrajectoryActionBuilder tab4 = tab2.endTrajectory().fresh()
                .setTangent(Math.toRadians(-90))
                .splineToLinearHeading(new Pose2d(-33, 4, Math.toRadians(-180)), Math.toRadians(-90), new TranslationalVelConstraint(minTransVel), new ProfileAccelConstraint(minProfAccel, maxProfAccel))
                .setTangent(Math.toRadians(0))
                .splineToLinearHeading(new Pose2d(-54,  4, Math.toRadians(-180)), Math.toRadians(0), new TranslationalVelConstraint(minTransVelStraight), new ProfileAccelConstraint(minProfAccelStraight, maxProfAccelStraight))
                .setTangent(Math.toRadians(-270))
                .splineToLinearHeading(new Pose2d(-54, 58, Math.toRadians(-180)), Math.toRadians(-270), new TranslationalVelConstraint(minTransVelStraight), new ProfileAccelConstraint(minProfAccelStraight, maxProfAccelStraight))
                .splineToLinearHeading(pushSample3_lastPose, Math.toRadians(-270));

//        TrajectoryActionBuilder tab4 = tab3.endTrajectory().fresh()
//                .setTangent(Math.toRadians(90))
//                .splineToLinearHeading(new Pose2d(50, -6, Math.toRadians(180)), Math.toRadians(90), new TranslationalVelConstraint(minTransVelStraight), new ProfileAccelConstraint(minProfAccelStraight, maxProfAccelStraight))
//                .setTangent(Math.toRadians(0))
//                .splineToLinearHeading(new Pose2d(62,  -6, Math.toRadians(180)), Math.toRadians(0), new TranslationalVelConstraint(minTransVelStraight), new ProfileAccelConstraint(minProfAccelStraight, maxProfAccelStraight))
//                .setTangent(Math.toRadians(270))
//                .splineToLinearHeading(new Pose2d(62, -48, Math.toRadians(180)), Math.toRadians(270), new TranslationalVelConstraint(minTransVelStraight), new ProfileAccelConstraint(minProfAccelStraight, maxProfAccelStraight))
//                .splineToLinearHeading(pushSample3_lastPose, Math.toRadians(270));

        TrajectoryActionBuilder tab5 = tab4.endTrajectory().fresh()
                .setTangent(Math.toRadians(-270))
                .splineToLinearHeading(turnToSamplePose, Math.toRadians(-270), new TranslationalVelConstraint(minTransVel), new ProfileAccelConstraint(minProfAccel, maxProfAccel));

        TrajectoryActionBuilder tab6 = tab5.endTrajectory().fresh()
                .lineToY(53);

        TrajectoryActionBuilder tab7 = tab6.endTrajectory().fresh()
                .setTangent(Math.toRadians(-135))
                .splineToLinearHeading(new Pose2d(-0, 40, Math.toRadians(-90)), Math.toRadians(-90), new TranslationalVelConstraint(minTransVel), new ProfileAccelConstraint(minProfAccel, maxProfAccel))
                .lineToY(33);

        TrajectoryActionBuilder tab8 = tab7.endTrajectory().fresh()
                .setTangent(Math.toRadians(-270))
                .splineToLinearHeading(pickSpecimenPose, Math.toRadians(-270), new TranslationalVelConstraint(minTransVel), new ProfileAccelConstraint(minProfAccel, maxProfAccel));

        TrajectoryActionBuilder tab9 = tab8.endTrajectory().fresh()
                .lineToY(53);

        TrajectoryActionBuilder tab10 = tab9.endTrajectory().fresh()
                .setTangent(Math.toRadians(-135))
                .splineToLinearHeading(new Pose2d(0, 40, Math.toRadians(-90)), Math.toRadians(-90), new TranslationalVelConstraint(minTransVel), new ProfileAccelConstraint(minProfAccel, maxProfAccel))
                .lineToY(33);

        TrajectoryActionBuilder tab11 = tab10.endTrajectory().fresh()
                .setTangent(Math.toRadians(-270))
                .splineToLinearHeading(pickSpecimenPose, Math.toRadians(-270), new TranslationalVelConstraint(minTransVel), new ProfileAccelConstraint(minProfAccel, maxProfAccel));

        TrajectoryActionBuilder tab12 = tab11.endTrajectory().fresh()
                .lineToY(51);

        TrajectoryActionBuilder tab13 = tab12.endTrajectory().fresh()
                .setTangent(Math.toRadians(-135))
                .splineToLinearHeading(new Pose2d(0, 40, Math.toRadians(-90)), Math.toRadians(-90), new TranslationalVelConstraint(minTransVel), new ProfileAccelConstraint(minProfAccel, maxProfAccel))
                .lineToY(33);

        TrajectoryActionBuilder tab14 = tab13.endTrajectory().fresh()
                .setTangent(Math.toRadians(-270))
                .splineToLinearHeading(pickSpecimenPose, Math.toRadians(-270), new TranslationalVelConstraint(minTransVel), new ProfileAccelConstraint(minProfAccel, maxProfAccel));

        TrajectoryActionBuilder tab15 = tab14.endTrajectory().fresh()
                .lineToY(51);

        TrajectoryActionBuilder tab16 = tab15.endTrajectory().fresh()
                .setTangent(Math.toRadians(-135))
                .splineToLinearHeading(new Pose2d(0, 40, Math.toRadians(-90)), Math.toRadians(-90), new TranslationalVelConstraint(minTransVel), new ProfileAccelConstraint(minProfAccel, maxProfAccel))
                .lineToY(33);



        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(32.5, 62.5, Math.toRadians(90)))
                .splineToLinearHeading(toBasket_lastPose, Math.toRadians(-90), new TranslationalVelConstraint(minTransVel), new ProfileAccelConstraint(minProfAccel, maxProfAccel))
                        .setTangent(Math.toRadians(0))
                .splineToLinearHeading(new Pose2d(-37, 38, Math.toRadians(-90)), Math.toRadians(0), new TranslationalVelConstraint(minTransVel), new ProfileAccelConstraint(minProfAccel, maxProfAccel))
                .setTangent(Math.toRadians(-90))
                .splineToLinearHeading(new Pose2d(-37, 6, Math.toRadians(-90)), Math.toRadians(-90), new TranslationalVelConstraint(minTransVel), new ProfileAccelConstraint(minProfAccel, maxProfAccel))
                .setTangent(Math.toRadians(0))
                .splineToLinearHeading(new Pose2d(-45,  6, Math.toRadians(-180)), Math.toRadians(0))
                .setTangent(Math.toRadians(-270))
                .splineToLinearHeading(new Pose2d(-45, 58, Math.toRadians(-180)), Math.toRadians(-270), new TranslationalVelConstraint(minTransVelStraight), new ProfileAccelConstraint(minProfAccelStraight, maxProfAccelStraight))
                .setTangent(Math.toRadians(-90))
                .splineToLinearHeading(new Pose2d(-33, 4, Math.toRadians(-180)), Math.toRadians(-90), new TranslationalVelConstraint(minTransVel), new ProfileAccelConstraint(minProfAccel, maxProfAccel))
                .setTangent(Math.toRadians(0))
                .splineToLinearHeading(new Pose2d(-54,  4, Math.toRadians(-180)), Math.toRadians(0), new TranslationalVelConstraint(minTransVelStraight), new ProfileAccelConstraint(minProfAccelStraight, maxProfAccelStraight))
                .setTangent(Math.toRadians(-270))
                .splineToLinearHeading(new Pose2d(-54, 58, Math.toRadians(-180)), Math.toRadians(-270), new TranslationalVelConstraint(minTransVelStraight), new ProfileAccelConstraint(minProfAccelStraight, maxProfAccelStraight))
                .splineToLinearHeading(pushSample3_lastPose, Math.toRadians(-270))
                .setTangent(Math.toRadians(-270))
                .splineToLinearHeading(turnToSamplePose, Math.toRadians(-270), new TranslationalVelConstraint(minTransVel), new ProfileAccelConstraint(minProfAccel, maxProfAccel))
                .lineToY(53)
                .setTangent(Math.toRadians(-135))
                .splineToLinearHeading(new Pose2d(0, 40, Math.toRadians(-90)), Math.toRadians(-90), new TranslationalVelConstraint(minTransVel), new ProfileAccelConstraint(minProfAccel, maxProfAccel))
                .lineToY(33)
                .setTangent(Math.toRadians(-270))
                .splineToLinearHeading(pickSpecimenPose, Math.toRadians(-270), new TranslationalVelConstraint(minTransVel), new ProfileAccelConstraint(minProfAccel, maxProfAccel))
                .lineToY(51)
                .setTangent(Math.toRadians(-135))
                .splineToLinearHeading(new Pose2d(0, 40, Math.toRadians(-90)), Math.toRadians(-90), new TranslationalVelConstraint(minTransVel), new ProfileAccelConstraint(minProfAccel, maxProfAccel))
                .lineToY(33)
                .setTangent(Math.toRadians(-270))
                .splineToLinearHeading(pickSpecimenPose, Math.toRadians(-270), new TranslationalVelConstraint(minTransVel), new ProfileAccelConstraint(minProfAccel, maxProfAccel))
                .lineToY(51)
                .setTangent(Math.toRadians(-135))
                .splineToLinearHeading(new Pose2d(0, 40, Math.toRadians(-90)), Math.toRadians(-90), new TranslationalVelConstraint(minTransVel), new ProfileAccelConstraint(minProfAccel, maxProfAccel))
                .lineToY(33)



                        .build());




        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}