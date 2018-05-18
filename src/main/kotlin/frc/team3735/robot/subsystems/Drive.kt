package frc.team3735.robot.subsystems

import com.ctre.phoenix.motorcontrol.FeedbackDevice
import com.sun.tools.internal.jxc.ap.Const
import edu.wpi.first.wpilibj.command.Subsystem
import frc.team3735.robot.settings.Constants
import frc.team3735.robot.settings.RobotMap
import org.usfirst.frc.team3735.robot.commands.drive.DDxDrive
import org.usfirst.frc.team3735.robot.util.hardware.VortxTalon
import org.usfirst.frc.team3735.robot.util.settings.PIDSetting

class Drive : Subsystem()
{

    // *********** Motors ***********
    private var leftDrive = VortxTalon(RobotMap.Drive.leftTrain,"Left Drive")
    private var rightDrive = VortxTalon(RobotMap.Drive.rightTrain,"Right Drive")

    // *********** Settings ***********
    // PID Settings
    var dP = 1.0
    var dI = 0.0
    var dD = 0.0
    var dF = 0.0
    var iZone = 2.0
    var maxV = 160.0
    var drivePID = PIDSetting(dP,dI,dD,dF,0.0,iZone)

    // *********** Driving Settings ***********
    var leftAddTurn = 0
    var rightAddTurn = 0
    var visionAssist = 0
    var navxAssist = 0

    // *********** Dashboard Settings ***********

    init {

        // Set motor options
        leftDrive.setInchesPerTick(Constants.Drive.InchesPerTick)
        rightDrive.setInchesPerTick(Constants.Drive.InchesPerTick)

        // Setting PID
        leftDrive.setPIDSetting(drivePID)
        rightDrive.setPIDSetting(drivePID)

        // Setting Max V
        leftDrive.setFMaxV(maxV)
        rightDrive.setFMaxV(maxV)

        // Sending Drive PID to Dashboard
        drivePID.sendToDash("Drive PID")

        // Init Sensors
        leftDrive.initSensor(FeedbackDevice.CTRE_MagEncoder_Relative, true)
        rightDrive.initSensor(FeedbackDevice.CTRE_MagEncoder_Relative, true)

    }

    override fun initDefaultCommand() {
        defaultCommand = DDxDrive()
    }

}