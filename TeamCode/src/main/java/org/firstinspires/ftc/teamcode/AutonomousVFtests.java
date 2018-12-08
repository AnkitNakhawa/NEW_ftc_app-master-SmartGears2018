package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

@Autonomous(name = "FinalOpMode_TF", group = "pushbot")
//@disabled
public class AutonomousVFtests extends LinearOpMode {
    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";

    DcMotor leftDrive;
    DcMotor rightDrive;
    DcMotor frontLeft;
    DcMotor frontRight;
    Servo rightClaw;
    Servo leftClaw;
    DcMotor hangArm;
    DcMotor shoulderArm;
    DcMotor elbowArm;

    private static final String VUFORIA_KEY = "AZIw/h7/////AAABmXKlSvHFWEmwgei9zqbumhM1qDUxa8CfKM23a6LkPjsEaKSiLGjSRnvaqHDR2Gw3NYrbYN/kidMjhYFjBi8Bs5wzNLGa8NDKsIle5F/bN6xzmBqYFV7kj8OGOHm6EuBGXynM06Zs4Ufzioyqo1U1QQ1cMHgNo4q/y2+QxtJ/itFhYV9DDuqJhC8UFW2nQb6xCao6UultNNRJ19e0qaOLVr346bytP6CPyVoDCdGDD18W3t+mMlyyYhwaIZEqQwUhAtaRQmYUWAVonphDjpHQVaSJVQ+DKfk2+8rxPQUm81bvgde46L73zmXSD8ZzZ6xdavISEbkz0CEaP9IwIO/hz85lOIFkgRM4Yb6MniRFTGdH";
    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;

    @Override
    public void runOpMode() throws InterruptedException {

        rightClaw = hardwareMap.get(Servo.class, "right_claw");
        leftClaw = hardwareMap.get(Servo.class, "left_claw");
        hangArm = hardwareMap.get(DcMotor.class, "hang_arm");
        shoulderArm = hardwareMap.get(DcMotor.class, "shoulder_arm");
        elbowArm = hardwareMap.get(DcMotor.class, "elbow_arm");
        leftDrive = hardwareMap.get(DcMotor.class, "left_bdrive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_bdrive");
        frontLeft = hardwareMap.get(DcMotor.class, "left_fdrive");
        frontRight = hardwareMap.get(DcMotor.class, "right_fdrive");
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        rightClaw.setDirection(Servo.Direction.REVERSE);
        initVuforia();


        int motorPower = 1;
        long motorTimeValue = 1000;


        waitForStart();
        landing();
        TFsampling();


    }

    public void MoveForward(double motorPower, long motorTimeValue) throws InterruptedException {
        leftDrive.setPower(motorPower);
        rightDrive.setPower(-motorPower);
        Thread.sleep(motorTimeValue);
        leftDrive.setPower(0);
        rightDrive.setPower(0);
    }

    public void TurnRight(double motorPower, long motorTimeValue) throws InterruptedException {
        leftDrive.setPower(motorPower);
        rightDrive.setPower(motorPower);
        Thread.sleep(motorTimeValue);
        leftDrive.setPower(0);
        rightDrive.setPower(0);
    }

    public void TurnLeft(double motorPower, long motorTimeValue) throws InterruptedException {
        leftDrive.setPower(-motorPower);
        rightDrive.setPower(-motorPower);
        Thread.sleep(motorTimeValue);
        leftDrive.setPower(0);
        rightDrive.setPower(0);
    }

    public void MoveBackward(double motorPower, long motorTimeValue) throws InterruptedException {
        leftDrive.setPower(-motorPower);
        rightDrive.setPower(-motorPower);
        Thread.sleep(motorTimeValue);
        leftDrive.setPower(0);
        rightDrive.setPower(0);
    }

    public void ServoOpen() {
        leftClaw.setPosition(1);
        rightClaw.setPosition(0);

    }

    public void ServoClose() {
        leftClaw.setPosition(0.7);
        rightClaw.setPosition(0.3);

    }

    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();
        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = CameraDirection.BACK;
        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);
        // Loading trackables is not necessary for the Tensor Flow Object Detection engine.
    }

    //    public void Landing(){
////        telemetry.addData("Status", "Performing Landing Operation");
////        liftArm.setPower(1);
////        sleep(12000);
////        liftArm.setPower(0);
////        rightClaw.setPosition(0);
////        leftClaw.setPosition(0);
////        leftDrive.setPower(-1);
////        rightDrive.setPower(-1);
////        sleep(1500);
////        leftDrive.setPower(0);
////        rightDrive.setPower(0);
////        telemetry.update();
//        liftArm.setPower(1);
//        sleep(17000);
//        liftArm.setPower(0);
//        leftClaw.setPosition(0);
//        rightClaw.setPosition(0);
//        rightDrive.setPower(-1);
//        leftDrive.setPower(-1);
//        sleep(2300);
//        leftDrive.setPower(0.25);
//        rightDrive.setPower(0.25);
//        sleep(550);
//        leftDrive.setPower(0);
//        rightDrive.setPower(0);
//
//    }
//    public void Sampling() {
////        telemetry.addData("Status", "Performing Sampling Operation");
////        leftDrive.setPower(-1);
////        rightDrive.setPower(-1);
////        sleep(1000);
////        leftDrive.setPower(0);
////        rightDrive.setPower(0);
////        telemetry.update();
//        liftArm.setPower(1);
//        sleep(17000);
//        liftArm.setPower(0);
//        leftClaw.setPosition(0);
//        rightClaw.setPosition(0);
//        rightDrive.setPower(-1);
//        leftDrive.setPower(-1);
//        sleep(2300);
//        leftDrive.setPower(0.25);
//        rightDrive.setPower(0.25);
//        sleep(550);
//        leftDrive.setPower(0);
//        rightDrive.setPower(0);
//        }
//    public void Claiming(){
////         leftDrive.setPower(1);
////         sleep(700);
////         rightDrive.setPower(1);
////         sleep(1850);
////         rightDrive.setPower(0);
////         leftDrive.setPower(0);
////         claimArm.setPower(.7);
////         sleep(1200);
////       claimArm.setPower(0);
//        rightDrive.setPower(1);
//        leftDrive.setPower(-1);
//        sleep(925);
//        leftDrive.setPower(.7);
//        sleep(1750);
//        rightDrive.setPower(0);
//        leftDrive.setPower(0);
//    }
//   public void Parking(){
//    leftDrive.setPower(1);
//    rightDrive.setPower(1);
//    sleep(750);
//       leftDrive.setPower(1);
//       rightDrive.setPower(.95);
//       sleep(2000);
//       leftDrive.setPower(0);
//       rightDrive.setPower(0);
//    }
//   public void armTest(){
////       claimArm.setPower(-1);
////       sleep(700);
////       claimArm.setPower(0);
////        claimArm.setPower(1);
////        sleep(700);
////        claimArm.setPower(0);
//    }
    public void landing() {
        hangArm.setPower(1);
        sleep(10060);
        hangArm.setPower(0);
        leftClaw.setPosition(0);
        rightClaw.setPosition(0);
        hangArm.setPower(-1);
        sleep(950);
        hangArm.setPower(0);
        leftClaw.setPosition(-1);
        hangArm.setPower(-1);
        sleep(1250);
        hangArm.setPower(0);
        rightClaw.setPosition(-1);
    }

    public void sampling() {
        leftDrive.setPower(.9);
        rightDrive.setPower(1);
        sleep(2750);
        leftDrive.setPower(0);
        rightDrive.setPower(0);
        elbowArm.setPower(.75);
        sleep(7500);
        elbowArm.setPower(0);
    }

    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
    }

    public void TFsampling() {
        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            initTfod();
        } else {
            telemetry.addData("Sorry!", "This device is not compatible with TFOD");
        }
        /** Wait for the game to begin */
        telemetry.addData(">", "Press Play to start tracking");
        telemetry.update();
        waitForStart();
        if (opModeIsActive()) {
            /** Activate Tensor Flow Object Detection. */
            if (tfod != null) {
                tfod.activate();
            }
            while (opModeIsActive()) {
                if (tfod != null) {
                    // getUpdatedRecognitions() will return null if no new information is available since
                    // the last time that call was made.
                    List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                   long startTime = System.currentTimeMillis();
                    if (updatedRecognitions != null) {
                        telemetry.addData("# Object Detected", updatedRecognitions.size());
                        if (updatedRecognitions.size() == 3) {
                            int goldMineralX = -1;
                            int silverMineral1X = -1;
                            int silverMineral2X = -1;
                            for (Recognition recognition : updatedRecognitions) {
                                if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                                    goldMineralX = (int) recognition.getLeft();
                                } else if (silverMineral1X == -1) {
                                    silverMineral1X = (int) recognition.getLeft();
                                } else {
                                    silverMineral2X = (int) recognition.getLeft();
                                }
                            }
                            if ((goldMineralX != -1 && silverMineral1X != -1 && silverMineral2X != -1) || System.currentTimeMillis() > (startTime + 15000)  ) {
                                if (goldMineralX < silverMineral1X && goldMineralX < silverMineral2X) {
                                    telemetry.addData("Gold Mineral Position", "Left");
                                    frontRight.setPower(1);
                                    rightDrive.setPower(1);
                                    sleep(1500);
                                    leftDrive.setPower(1);
                                    frontLeft.setPower(1);
                                    sleep(2000);
                                    leftDrive.setPower(0);
                                    rightDrive.setPower(0);
                                    frontLeft.setPower(0);
                                    frontRight.setPower(0);

                                } else if (goldMineralX > silverMineral1X && goldMineralX > silverMineral2X) {
                                    telemetry.addData("Gold Mineral Position", "Right");
                                    frontLeft.setPower(1);
                                    leftDrive.setPower(1);
                                    sleep(1500);
                                    rightDrive.setPower(1);
                                    frontRight.setPower(1);
                                    sleep(2000);
                                    leftDrive.setPower(0);
                                    rightDrive.setPower(0);
                                    frontLeft.setPower(0);
                                    frontRight.setPower(0);
                                } else {
                                    telemetry.addData("Gold Mineral Position", "Center");
                                    leftDrive.setPower(1);
                                    rightDrive.setPower(1);
                                    frontLeft.setPower(1);
                                    frontRight.setPower(1);
                                    sleep(4000);
                                    rightDrive.setPower(0);
                                    frontLeft.setPower(0);
                                    frontRight.setPower(0);
                                    leftDrive.setPower(0);
                                }

                            }
                        }
                        telemetry.update();
                    }
                }
            }
        }
        if (tfod != null) {
            tfod.shutdown();
        }
    }
}


//fina