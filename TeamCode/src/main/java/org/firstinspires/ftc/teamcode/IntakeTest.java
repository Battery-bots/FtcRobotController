package org.firstinspires.ftc.teamcode;

//imports qualcom libraries
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

//test
@Disabled //ensures program shows up in Teleop screen
public class IntakeTest extends OpMode {
    DcMotor intakeMotor;
    boolean intakeOn = false;
    boolean aState = false;

    @Override
    public void init(){
        telemetry.addData("Controller 1", "Press A + Start");

        intakeMotor = hardwareMap.get(DcMotor.class, "intake_motor");
    }

    @Override
    public void loop(){

        double intakePower = 1.0; // Power of the motor is at 100%
        int motorPos = intakeMotor.getCurrentPosition();
        double ticksPerRev = intakeMotor.getMotorType().getTicksPerRev();

        // sets on/off state of the intake when driver presses A and is NOT holding the button
        if (gamepad1.a && !aState){
            intakeOn = !intakeOn;
        }
        // records if player has pressed A in this loop
        aState = gamepad1.a;

        // if intake is true, the motor is set to assigned power
        if (intakeOn){
            intakeMotor.setPower(intakePower);
        }
        else {
            intakeMotor.setPower(0);
            intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
        // returns data of the intake
        telemetry.addData("Intake On", intakeOn);
        telemetry.addData("Intake Revs", motorPos/ ticksPerRev);
        telemetry.update();
    }
}
