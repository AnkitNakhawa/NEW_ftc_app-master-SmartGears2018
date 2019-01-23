package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Autonomous(name = "FinalOpMode_TFMeccanumNUEVA", group = "pushbot")
//@disabled
public class AutonomousTFMeccanumNEW extends LinearOpMode {
    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";

    DcMotor leftBDrive;
    DcMotor rightBDrive;
    DcMotor leftFDrive;
    DcMotor rightFDrive;
    DcMotor hangArm;
    //DcMotor grabArm;
     Servo leftArm;
     Servo rightArm;

    private static final String VUFORIA_KEY = "AZIw/h7/////AAABmXKlSvHFWEmwgei9zqbumhM1qDUxa8CfKM23a6LkPjsEaKSiLGjSRnvaqHDR2Gw3NYrbYN/kidMjhYFjBi8Bs5wzNLGa8NDKsIle5F/bN6xzmBqYFV7kj8OGOHm6EuBGXynM06Zs4Ufzioyqo1U1QQ1cMHgNo4q/y2+QxtJ/itFhYV9DDuqJhC8UFW2nQb6xCao6UultNNRJ19e0qaOLVr346bytP6CPyVoDCdGDD18W3t+mMlyyYhwaIZEqQwUhAtaRQmYUWAVonphDjpHQVaSJVQ+DKfk2+8rxPQUm81bvgde46L73zmXSD8ZzZ6xdavISEbkz0CEaP9IwIO/hz85lOIFkgRM4Yb6MniRFTGdH";
    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;


    @Override
    public void runOpMode() throws InterruptedException {

        hangArm = hardwareMap.get(DcMotor.class, "hang_arm");

        leftBDrive = hardwareMap.get(DcMotor.class, "left_bdrive");
        rightBDrive = hardwareMap.get(DcMotor.class, "right_bdrive");
        leftFDrive = hardwareMap.get(DcMotor.class, "left_fdrive");
        rightFDrive = hardwareMap.get(DcMotor.class, "right_fdrive");
        leftArm = hardwareMap.get(Servo.class, "left_arm");
        rightArm = hardwareMap.get(Servo.class, "right_arm");
        //grabArm = hardwareMap.get(DcMotor.class, "grab_arm");
        rightFDrive.setDirection(DcMotor.Direction.REVERSE);
        rightBDrive.setDirection(DcMotor.Direction.REVERSE);
        hangArm.setDirection(DcMotor.Direction.REVERSE);
        initVuforia();


        int motorPower = 1;
        long motorTimeValue = 1000;


        waitForStart();
        landing();
        TFsampling();
    //    parking();



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

    public void landing() {
        hangArm.setPower(-1);
        sleep(9250);
        hangArm.setPower(0);
        leftFDrive.setPower(0.3);
        leftBDrive.setPower(-0.3);
        rightFDrive.setPower(-.3);
        rightBDrive.setPower(0.3);
        sleep(500);
        leftFDrive.setPower(0);
        leftBDrive.setPower(0);
        rightFDrive.setPower(0);
        rightBDrive.setPower(0);
        hangArm.setPower(1);
        sleep(3000);
        hangArm.setPower(0);
        rightBDrive.setPower(-.3);
        rightFDrive.setPower(-.3);
        sleep(350);
        leftFDrive.setPower(.3);
        leftBDrive.setPower(.3);
        sleep(200);
        leftFDrive.setPower(0);
        leftBDrive.setPower(0);
        rightFDrive.setPower(0);
        rightBDrive.setPower(0);

        //        leftFDrive.setPower(-0.5);
//        leftBDrive.setPower(0.5);
//        rightFDrive.setPower(.4);
//        rightBDrive.setPower(-0.4);
//        sleep(250);
//        leftFDrive.setPower(0);
//        leftBDrive.setPower(0);
//        rightFDrive.setPower(0);
//        rightBDrive.setPower(0);
//        rightFDrive.setPower(-1);
//        rightBDrive.setPower(-1);
//        sleep(1000);
//        rightBDrive.setPower(0);
//        rightFDrive.setPower(0);
    }
    public void parking(){
        leftBDrive.setPower(1);
        leftFDrive.setPower(1);
        rightBDrive.setPower(1);
        rightFDrive.setPower(1);
        sleep(2500);
        leftBDrive.setPower(0);
        leftFDrive.setPower(0);
        rightBDrive.setPower(0);
        rightFDrive.setPower(0);
        rightArm.setPosition(-1);
        leftArm.setPosition(0);
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
        int counterA = 0;
        if (opModeIsActive()) {
            /** Activate Tensor Flow Object Detection. */
            if (tfod != null) {
                tfod.activate();
            }
            while (opModeIsActive()) {
                counterA = counterA + 1;
                if (tfod != null) {
                    // getUpdatedRecognitions() will return null if no new information is available since
                    // the last time that call was made.
                    List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                    if (updatedRecognitions != null) {
                        telemetry.addData("# Object Detected during " + counterA + " attempt : ", updatedRecognitions.size());
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
                            if ((goldMineralX != -1 && silverMineral1X != -1 && silverMineral2X != -1) ) {
                                if (goldMineralX < silverMineral1X && goldMineralX < silverMineral2X) {
                                    telemetry.addData("Gold Mineral Position", "Left");
                                    leftFDrive.setPower(-.5);
                                    leftBDrive.setPower(-.5);
                                    rightFDrive.setPower(.3);
                                    rightBDrive.setPower(.3);
                                    hangArm.setPower(1);
                                    sleep(300);
                                    leftFDrive.setPower(.3);
                                    leftBDrive.setPower(.3);
                                    sleep(1300);
                                    rightFDrive.setPower(0);
                                    rightBDrive.setPower(0);
                                    leftFDrive.setPower(0);
                                    leftBDrive.setPower(0);
                                    leftFDrive.setPower(.3);
                                    leftBDrive.setPower(.3);
                                    hangArm.setPower(0);
                                    sleep(1200);
                                    rightFDrive.setPower(.3);
                                    rightBDrive.setPower(.3);
                                    sleep(800);
                                    rightFDrive.setPower(0);
                                    rightBDrive.setPower(0);
                                    leftFDrive.setPower(0);
                                    leftBDrive.setPower(0);
                                    rightArm.setPosition(.7);
                                    leftArm.setPosition(.3);
                                    rightFDrive.setPower(-.5/2);
                                    rightBDrive.setPower(-.5/2);
                                    leftFDrive.setPower(-.3/2);
                                    leftBDrive.setPower(-.3/2);
                                    sleep(250);
                                    rightFDrive.setPower(0);
                                    rightBDrive.setPower(0);
                                    leftFDrive.setPower(0);
                                    leftBDrive.setPower(0);


                                } else if (goldMineralX > silverMineral1X && goldMineralX > silverMineral2X) {
                                    telemetry.addData("Gold Mineral Position", "Right");
                                    leftFDrive.setPower(.3);
                                    leftBDrive.setPower(.3);
                                    rightFDrive.setPower(-.5);
                                    rightBDrive.setPower(-.5);
                                    hangArm.setPower(1);
                                    sleep(100);
                                    rightFDrive.setPower(.3);
                                    rightBDrive.setPower(.3);
                                    sleep(1000);
                                    rightFDrive.setPower(0);
                                    rightBDrive.setPower(0);
                                    leftFDrive.setPower(0);
                                    leftBDrive.setPower(0);
                                    rightFDrive.setPower(.3);
                                    rightBDrive.setPower(.3);
                                    hangArm.setPower(0);
                                    sleep(1200);
                                    leftFDrive.setPower(.3);
                                    leftBDrive.setPower(.3);
                                    sleep(800);
                                    rightFDrive.setPower(0);
                                    rightBDrive.setPower(0);
                                    leftFDrive.setPower(0);
                                    leftBDrive.setPower(0);
                                    rightArm.setPosition(.7);
                                    leftArm.setPosition(.3);
                                    rightFDrive.setPower(-.5/2);
                                    rightBDrive.setPower(-.5/2);
                                    leftFDrive.setPower(-.3/2);
                                    leftBDrive.setPower(-.3/2);
                                    sleep(250);
                                    rightFDrive.setPower(0);
                                    rightBDrive.setPower(0);
                                    leftFDrive.setPower(0);
                                    leftBDrive.setPower(0);


                                } else {
                                    telemetry.addData("Gold Mineral Position", "Center");
                                    leftFDrive.setPower(.3);
                                    leftBDrive.setPower(.3);
                                    rightFDrive.setPower(.3);
                                    rightBDrive.setPower(.3);
                                    hangArm.setPower(1);
                                    sleep(2000);
                                    leftFDrive.setPower(0);
                                    leftBDrive.setPower(0);
                                    rightFDrive.setPower(0);
                                    rightBDrive.setPower(0);
                                    rightArm.setPosition(.7);
                                    leftArm.setPosition(.3);
                                    rightFDrive.setPower(-.5/2);
                                    rightBDrive.setPower(-.5/2);
                                    leftFDrive.setPower(-.3/2);
                                    leftBDrive.setPower(-.3/2);
                                    hangArm.setPower(0);
                                    sleep(250);
                                    rightFDrive.setPower(0);
                                    rightBDrive.setPower(0);
                                    leftFDrive.setPower(0);
                                    leftBDrive.setPower(0);
                                }

                            }
//                        } else{//moving forward after 4 attempts of object detection.
//                            telemetry.addData("Gold Mineral Position", "Not Found; Going Forward");
//                            sleep(2000);
//                            leftFDrive.setPower(.31);
//                            leftBDrive.setPower(.31);
//                            rightFDrive.setPower(.3);
//                            rightBDrive.setPower(.3);
//                            hangArm.setPower(1);
//                            sleep(2000);
//                            hangArm.setPower(0);
//                            leftFDrive.setPower(0);
//                            leftBDrive.setPower(0);
//                            rightFDrive.setPower(0);
//                            rightBDrive.setPower(0);
//                            rightArm.setPosition(.7);
//                            leftArm.setPosition(.3);
//                            rightFDrive.setPower(-.5/2);
//                            rightBDrive.setPower(-.5/2);
//                            leftFDrive.setPower(-.3/2);
//                            leftBDrive.setPower(-.3/2);
//                            sleep(250);
//                            rightFDrive.setPower(0);
//                            rightBDrive.setPower(0);
//                            leftFDrive.setPower(0);
//                            leftBDrive.setPower(0);
//                            break;
                        }
                        telemetry.update();
            //            break;
                    }
        //            break;
                }
            }
        }
        if (tfod != null) {
            tfod.shutdown();
        }
    }
}


//finaa