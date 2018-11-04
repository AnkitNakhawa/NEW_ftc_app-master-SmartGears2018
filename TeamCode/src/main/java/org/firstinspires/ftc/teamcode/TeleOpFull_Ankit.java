package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="TeleOp Mode Double COntroller Ankit Nakhawa", group="Linear Opmode")
//@Disabled
public class TeleOpFull_Ankit extends LinearOpMode {


    // For Gamepad 1
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private DcMotor arm = null;
    private Servo rightClaw = null;
    private Servo leftClaw = null;
    //For Driver Gamepad 2
    private DcMotor grabberPos = null;
    private DcMotor grabberWheel = null;
    private DcMotor lifter1 = null;
    private DcMotor lifter2 = null;
    private DcMotor linearSlide = null;
    private Servo servo1 = null;
    private Servo servo2 = null;
    private Servo servo3 = null;
    private Servo servo4 = null;

    double Cpos = 0.01;
    int GrabState = 1;
    int grabberWheelCount = 1;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        //For Gamepad 1
        leftDrive = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
        arm = hardwareMap.get(DcMotor.class, "arm");
        rightClaw = hardwareMap.get(Servo.class, "right_claw");
        leftClaw = hardwareMap.get(Servo.class, "left_claw");
        //For Gamepad 2
        //grabberPos = hardwareMap.get(DcMotor.class, "grabber_pos");
//        grabberWheel = hardwareMap.get(DcMotor.class, "grabber_wheel");g
//        lifter1 = hardwareMap.get(DcMotor.class, "lift_1");
//        lifter2 = hardwareMap.get(DcMotor.class, "lift_2");
        linearSlide = hardwareMap.get(DcMotor.class, "linear_slide");
//        servo1 = hardwareMap.get(Servo.class, "servo_1");
//        servo2 = hardwareMap.get(Servo.class, "servo_2");
//        servo3 = hardwareMap.get(Servo.class, "servo_3");
//        servo4 = hardwareMap.get(Servo.class, "servo_4");

        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightClaw.setDirection(Servo.Direction.REVERSE);

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {

            //For Gamepad 1
            double leftWheelPwr = gamepad1.left_stick_y;
            double rightWheelPwr = gamepad1.right_stick_y;
            double armForward = gamepad1.right_trigger;
            double armBackward = gamepad1.left_trigger;
            boolean clawClose = gamepad1.right_bumper;
            boolean clawOpen = gamepad1.left_bumper;
            boolean goForward = gamepad1.a;
            boolean goBackward = gamepad1.y;
            boolean turnRight = gamepad1.x;
            boolean turnLeft = gamepad1.b;
            //For Gamepad 2
            boolean FunctionGrabber = gamepad2.b;
            boolean GrabberWheelToggle = gamepad2.x;
            boolean LifterControl = gamepad2.right_bumper;
            double HookingSlideUp = gamepad2.left_trigger;
            double HookingSlideDown = gamepad2.right_trigger;
            boolean Servo1 = gamepad2.a;
            boolean Servo2 = gamepad2.y;
            boolean Servo3 = gamepad2.dpad_down;
            boolean Servo4 = gamepad2.dpad_up;


            //Driver Gamepad 1
            moveMotors(leftWheelPwr, rightWheelPwr);
            moveArm(armForward, armBackward);
            moveClaws(clawOpen, clawClose);
            autoMove(goForward, goBackward);
            turning(turnRight, turnLeft);
            if (Cpos >= 0.90) {
                Cpos = Cpos - 0.01;
            }
            if (Cpos <= 0) {
                Cpos = Cpos + 0.01;
            }
            //Driver Gamepad 2
            HookControl(HookingSlideUp, HookingSlideDown);

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }
    }

    public void moveMotors(double leftWheelPwr, double rightWheelPwr) {

        leftDrive.setPower(leftWheelPwr);
        rightDrive.setPower(rightWheelPwr);
    }

    public void moveArm(double armForward, double armBackward) {
        arm.setPower(armForward);
        arm.setPower(-armBackward);
    }

    public void moveClaws(boolean clawOpen, boolean clawClose) throws InterruptedException {
        while (clawClose) {
            leftClaw.setPosition(Cpos);
            rightClaw.setPosition(Cpos);
            Cpos = Cpos + 0.01;
            break;
        }
        while (clawOpen) {
            leftClaw.setPosition(Cpos);
            rightClaw.setPosition(Cpos);
            Cpos = Cpos - 0.01;
            break;
        }
    }

    public void autoMove(boolean goForward, boolean goBackward) {
        while (goForward) {
            leftDrive.setPower(0.3);
            rightDrive.setPower(0.3);
            break;
        }
        while (!goForward) {
            leftDrive.setPower(0);
            rightDrive.setPower(0);
            break;
        }
        while (goBackward) {
            leftDrive.setPower(-0.3);
            rightDrive.setPower(-0.3);
            break;
        }
        while (!goBackward) {
            leftDrive.setPower(0);
            rightDrive.setPower(0);
            break;
        }

    }

    public void turning(boolean turnRight, boolean turnLeft) {
        while (turnRight) {
            leftDrive.setPower(0.3);
            rightDrive.setPower(-0.3);
            break;
        }
        while (turnLeft) {
            leftDrive.setPower(-0.3);
            rightDrive.setPower(0.3);
            break;
        }
        while (!turnRight) {
            leftDrive.setPower(0);
            rightDrive.setPower(0);
            break;
        }
        while (!turnLeft) {
            leftDrive.setPower(0);
            rightDrive.setPower(0);
            break;
        }
    }

    //Gamepad 2
    public void grabberPlacement(boolean FunctionGrabber, int Grabstate) {
        if (FunctionGrabber) {
            GrabState = Grabstate + 1;
        }
        while (GrabState % 2 == 0) {
            grabberPos.setPower(0.75);
            sleep(500);
            grabberPos.setPower(0);
        }
        while (GrabState % 2 == 1) {
            grabberPos.setPower(-0.75);
            sleep(500);
            grabberPos.setPower(0);
        }
    }

    public void grabberWheelEnable(boolean GrabberWheelToggle, int grabberWheelCount) {
        if (GrabberWheelToggle) {
            grabberWheelCount = grabberWheelCount + 1;
        }
        while (grabberWheelCount % 2 == 0) {
            grabberWheel.setPower(1);
        }
        while (grabberWheelCount % 2 == 1) {
            grabberWheel.setPower(0);
        }

    }

    public void HookControl(double HookingSlideUp, double HookingSlideDown) {

        while (HookingSlideUp >= 0.01) {
            linearSlide.setPower(HookingSlideUp/2);
            break;
        }
        while (HookingSlideDown >= 0.01) {
            linearSlide.setPower(-HookingSlideDown/2);
            break;
        }
    }
}
