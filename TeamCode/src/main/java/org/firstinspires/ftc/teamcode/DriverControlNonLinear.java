package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

//adb connect 192.168.43.1

@TeleOp(name = "Driver Control", group = "Opmode")
public class DriverControlNonLinear extends OpMode {
    // Declare OpMode members
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor towerYaxis1;
    private DcMotor towerYaxis2;
    private Servo claw;
    //private Servo claw2;
    int debugTimes = 0;
    //@Override
    public void init() {

        telemetry.addData("Status", "Running");
        telemetry.update();
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        towerYaxis1 = hardwareMap.get(DcMotor.class, "towerYaxis1");
        towerYaxis2 = hardwareMap.get(DcMotor.class, "towerYaxis2");
        claw = hardwareMap.get(Servo.class, "claw");
        towerYaxis1.setDirection(DcMotor.Direction.FORWARD);
        towerYaxis2.setDirection(DcMotor.Direction.FORWARD);
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        claw.setDirection(Servo.Direction.REVERSE);

        //waitForStart();
        runtime.reset();
        towerYaxis1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        towerYaxis2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        towerYaxis1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        towerYaxis2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
       @Override
        public void loop() {

            double drive = -gamepad1.left_stick_y/2;
            double strafe = gamepad1.left_stick_x/2;
            double turn = gamepad1.right_stick_x/2;

            double frontLeftPower = -Range.clip(drive + strafe + turn, -1.0, 1.0);
            double frontRightPower = -Range.clip(drive - strafe - turn, -1.0, 1.0);
            double backLeftPower = -Range.clip(drive - strafe + turn, -1.0, 1.0);
            double backRightPower = Range.clip(drive + strafe - turn, -1.0, 1.0);

            frontLeft.setPower(frontLeftPower);
            frontRight.setPower(frontRightPower);
            backLeft.setPower(backLeftPower);
            backRight.setPower(-backRightPower);
            //towerYaxis1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            //towerYaxis2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            towerYaxis1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            towerYaxis2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//            towerYaxis1.setTargetPosition(0);
//            towerYaxis2.setTargetPosition(0);




            if(gamepad1.dpad_up){
                towerYaxis1.setPower(-1); //FIX direction
                towerYaxis2.setPower(1);
            }else if(gamepad1.dpad_down){
                towerYaxis1.setPower(1); //FIX direction
                towerYaxis2.setPower(-1);
            }else{
                towerYaxis1.setPower(0);
                towerYaxis2.setPower(0);
            }


//            if(gamepad1.dpad_up){
//                Runnable towerThread = new Thread(()->{
//                    towerYaxis2.setTargetPosition(towerYaxis2.getCurrentPosition() + 50);
//                    towerYaxis2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                    towerYaxis2.setPower(0.95);
//                });
//                towerThread.run();
//                towerYaxis1.setTargetPosition(towerYaxis1.getCurrentPosition() - 50); //FIX direction
//                towerYaxis1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                towerYaxis1.setPower(0.95);
//            } else if(gamepad1.dpad_down){
//                towerYaxis1.setTargetPosition(towerYaxis1.getCurrentPosition() + 50); //FIX direction
//                towerYaxis2.setTargetPosition(towerYaxis2.getCurrentPosition() - 50);
//                towerYaxis1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                towerYaxis2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                towerYaxis1.setPower(0.95);
//                towerYaxis2.setPower(0.95);
//            }else{
//                towerYaxis1.setPower(0);
//                towerYaxis2.setPower(0);
//                telemetry.addData("Left Trigger", gamepad1.left_trigger);
//            }


//            if(gamepad1.dpad_down){
//                towerYaxis1.setPower(1); //FIX direction
//                towerYaxis2.setPower(-1);
////                towerYaxis1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
////                towerYaxis2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            }

//            if(!gamepad1.dpad_down)
//            {
//                towerYaxis1.setPower(0);
//                towerYaxis2.setPower(0);
//            }

//            if(gamepad1.right_trigger > 0){
//                towerYaxis1.setTargetPosition(towerYaxis1.getCurrentPosition() + 200); //FIX direction
//                towerYaxis2.setTargetPosition(towerYaxis2.getCurrentPosition() - 200);
//                towerYaxis1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                towerYaxis2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                towerYaxis1.setPower(0.95);
//                towerYaxis2.setPower(0.95);
//            }
//        else {
//        towerYaxis1.setPower(0);
//        towerYaxis2.setPower(0);
//    }

//            while(gamepad1.right_bumper){ //closes
//                claw.setPosition(claw.getPosition() - 0.005);
//                //claw2.setPosition(claw2.getPosition() + 0.005);
//            }
//
//            while(gamepad1.left_bumper) {
//                claw.setPosition(claw.getPosition() + 0.005);
//                //claw2.setPosition(claw2.getPosition() - 0.005);
//            }

           if(gamepad1.right_bumper){ //closes
               claw.setPosition(0.55);
               //claw2.setPosition(claw2.getPosition() + 0.005);
           }

           if(gamepad1.left_bumper) {
               claw.setPosition(1.0);
               //claw2.setPosition(claw2.getPosition() - 0.005);
           }

//           if(gamepad1.triangle) {
//               claw.setPosition(0.5);
//               //claw2.setPosition(claw2.getPosition() - 0.005);
//           }


            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Claw Position",  claw.getPosition());
            //telemetry.addData("Claw2 Position",  claw2.getPosition());
            telemetry.addData("TowerYaxis1 Position", towerYaxis1.getCurrentPosition());
            telemetry.addData("TowerYaxis2 Position", towerYaxis2.getCurrentPosition());
            telemetry.addData("DebugTimes", debugTimes);
            telemetry.update();
        }

    }



// Need code for moving arm side to side
// Need code for close and opening claw (left and right button)