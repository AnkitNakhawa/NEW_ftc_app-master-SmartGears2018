package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="FinalTeleOpMode", group="Linear Opmode")
//@Disabled
public class FinalTeleOpMode_SmartGears extends LinearOpMode {


    // For Gamepad 1
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftBDrive = null;
    private DcMotor rightBDrive = null;
    private DcMotor rightFDrive = null;
    private DcMotor leftFDrive = null;
    //private DcMotor grabArm = null;
    private DcMotor hangArm = null;
    private Servo leftarm = null;
    private Servo rightarm = null;
//    private DcMotor shoulderArm = null;
//    private DcMotor elbowArm = null;

    double clawPos = 0.5;
    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        //For Gamepad 1
        leftBDrive = hardwareMap.get(DcMotor.class, "left_bdrive");
        rightBDrive = hardwareMap.get(DcMotor.class, "right_bdrive");
        rightFDrive = hardwareMap.get(DcMotor.class, "right_fdrive");
        leftFDrive = hardwareMap.get(DcMotor.class, "left_fdrive");
        hangArm = hardwareMap.get(DcMotor.class, "hang_arm");
        leftarm = hardwareMap.get(Servo.class, "left_arm");
        rightarm = hardwareMap.get(Servo.class, "right_arm");
        //grabArm = hardwareMap.get(DcMotor.class, "grab_arm");
//        shoulderArm = hardwareMap.get(DcMotor.class, "shoulder_arm");
//        elbowArm = hardwareMap.get(DcMotor.class, "elbow_arm");


        leftBDrive.setDirection(DcMotor.Direction.REVERSE);
        leftFDrive.setDirection(DcMotor.Direction.REVERSE);
        hangArm.setDirection(DcMotor.Direction.REVERSE);
        rightarm.setDirection((Servo.Direction.REVERSE));


        waitForStart();
        runtime.reset();

        //gamepad 1
        while (opModeIsActive()) {
            double leftDrivePower = gamepad1.left_stick_y;
            double rightDrivePower = gamepad1.right_stick_y;
            double strafeValLeft = gamepad1.left_stick_x;
            double strafeValRight = gamepad1.right_stick_x;
            boolean slowLeftDrive = gamepad1.x;
            boolean slowRightDrive = gamepad1.b;
            boolean slowBack = gamepad1.a;
            boolean slowForward = gamepad1.y;

            //gamepad 2
            double hangArmPower = gamepad2.left_stick_y;
            boolean clawOpen = gamepad2.left_bumper;
            boolean clawClose = gamepad2.right_bumper;
            double shoulderPowerNeg = gamepad2.left_trigger;
            double shoulderPowerPos = gamepad2.right_trigger;
            double elbowPower = gamepad2.right_stick_y;
            //execution
            motorDrive(leftDrivePower, rightDrivePower);
            //hangArmControl(hangArmPower);
            slowMovements(slowLeftDrive, slowRightDrive, slowBack, slowForward);
//            clawMovement(clawOpen, clawClose);
            //mecanumMovements();
            mecccanumFinal(strafeValLeft, strafeValRight);
//            grabArm.setPower(gamepad2.right_trigger);
//            grabArm.setPower(-gamepad2.left_trigger);
//            shoulderMovementPos(shoulderPowerPos);
//            shoulderMovementNeg(shoulderPowerNeg);
//            elbowMovement(elbowPower);
            hangArm.setPower(gamepad2.left_stick_y);
        }
    }

    public void motorDrive(double leftDrivePower, double rightDrivePower) {
        leftBDrive.setPower(leftDrivePower);
        rightBDrive.setPower(rightDrivePower);
        leftFDrive.setPower (leftDrivePower);
        rightFDrive.setPower(rightDrivePower);
    }




    //public void hangArmControl(double hangArmPower) {
//        hangArm.setPower(hangArmPower);
//    }
//    public void mecanumMovements(){
//        if (gamepad1.left_bumper){
//            leftFDrive.setPower(.5);
//            leftBDrive.setPower(-.5);
//            rightFDrive.setPower(-.5);
//            rightBDrive.setPower(.5);
//        } else if (gamepad1.right_bumper){
//            leftFDrive.setPower(-.5);
//            leftBDrive.setPower(.5);
//            rightFDrive.setPower(.5);
//            rightBDrive.setPower(-.5);
//        }
//    }
    public void mecccanumFinal (double strafeValLeft, double strafeValRight){

        //Divider for Left and Right Strafe
        leftFDrive.setPower(-strafeValRight);
        leftBDrive.setPower(strafeValRight);
        rightFDrive.setPower(strafeValRight);
        rightBDrive.setPower(-strafeValRight);
    }
    public void armMoves(){
        while (gamepad2.dpad_down){
            rightarm.setPosition(1);
            leftarm.setPosition(0);
        }
        while (gamepad2.dpad_up){
            rightarm.setPosition(0);
            leftarm.setPosition(1);
        }
    }



    public void slowMovements(boolean slowLeftDrive, boolean slowRightDrive, boolean slowBack, boolean slowForward) {
        while (slowLeftDrive) {
            leftBDrive.setPower(.5);
            leftFDrive.setPower(.5);
            rightBDrive.setPower(-.5);
            rightFDrive.setPower(-.5);
            break;
        }
        while (slowRightDrive) {
            leftBDrive.setPower(-.5);
            leftFDrive.setPower(-.5);
            rightFDrive.setPower(.5);
            leftFDrive.setPower(.5);
            break;
        }

        while (slowBack) {
            leftBDrive.setPower(.5);
            rightBDrive.setPower(.5);
            leftFDrive.setPower(.5);
            rightFDrive.setPower(.5);
            break;
        }
        while (slowForward) {
            leftBDrive.setPower(-.5);
            rightBDrive.setPower(-.5);
            leftFDrive.setPower(-.5);
            rightFDrive.setPower(-.5);
            break;
        }


    }


//    public void clawMovement ( boolean clawOpen, boolean clawClose) throws InterruptedException{
//        while(clawClose){
//            leftClaw.setPosition(clawPos);
//            rightClaw.setPosition(clawPos);
//            clawPos = clawPos + 0.03;
//            break;
//        }
//        while(clawOpen){
//            leftClaw.setPosition(clawPos);
//            rightClaw.setPosition(clawPos);
//            clawPos = clawPos - 0.03;
//            break;
//        }
//    }

//    public void shoulderMovementPos (double shoulderPowerPos){
//        shoulderArm.setPower(shoulderPowerPos);
//
//    }
//    public void shoulderMovementNeg (double shoulderPowerNeg){
//        shoulderArm.setPower(-shoulderPowerNeg);
//
//    }
//    public void elbowMovement (double elbowPower){
//        elbowArm.setPower(elbowPower/5);

//    }



}

