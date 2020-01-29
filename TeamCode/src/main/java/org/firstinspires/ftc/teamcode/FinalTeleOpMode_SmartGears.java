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

    // For Gamepad 2
    private DcMotor hangArm = null;
    private Servo leftArm = null;
    private Servo rightArm = null;

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
        leftArm = hardwareMap.get(Servo.class, "left_arm");
        rightArm = hardwareMap.get(Servo.class, "right_arm");



        leftBDrive.setDirection(DcMotor.Direction.REVERSE);
        leftFDrive.setDirection(DcMotor.Direction.REVERSE);
        hangArm.setDirection(DcMotor.Direction.REVERSE);
        leftArm.setDirection((Servo.Direction.REVERSE));


        waitForStart();
        runtime.reset();

        //gamepad 1
        while (opModeIsActive()) {
            double leftDrivePower = gamepad1.left_stick_y;
            double rightDrivePower = gamepad1.right_stick_y;
            boolean slowLeftDrive = gamepad1.x;
            boolean slowRightDrive = gamepad1.b;
            boolean slowBack = gamepad1.a;
            boolean slowForward = gamepad1.y;

            //gamepad 2
            boolean rightBPressed = gamepad2.right_bumper;
            boolean leftBPressed = gamepad2.left_bumper;
            //execution
            motorDrive(leftDrivePower, rightDrivePower);
            slowMovements(slowLeftDrive, slowRightDrive, slowBack, slowForward);
            mecccanumFinal();
            hangArm.setPower(gamepad2.right_stick_y);
            leftArm.setPosition(1-gamepad2.right_trigger);
            rightArm.setPosition(gamepad2.right_trigger);
            }
        }


    public void motorDrive(double leftDrivePower, double rightDrivePower) {
        leftBDrive.setPower(leftDrivePower/.75);
        rightBDrive.setPower(rightDrivePower/.75);
        leftFDrive.setPower (leftDrivePower/.75);
        rightFDrive.setPower(rightDrivePower/.75);
    }
    public void mecccanumFinal (){
        //Divider for Left and Right Strafe
       if(gamepad1.right_bumper) {
           leftBDrive.setPower(-1);
           leftFDrive.setPower(1);
           rightBDrive.setPower(1);
           rightFDrive.setPower(-1);
          
       }
        if(gamepad1.left_bumper) {
            leftFDrive.setPower(-1);
            leftBDrive.setPower(1);
            rightFDrive.setPower(1);
            rightBDrive.setPower(-1);
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
            rightFDrive.setPower(.5);
            
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
}


