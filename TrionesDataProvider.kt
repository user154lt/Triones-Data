import java.awt.Color
import java.util.UUID

enum class PowerStatus{
    ON,
    OFF,
}

class TrionesDataProvider{
    val serviceUUID = UUID.fromString("0000ffd5-0000-1000-8000-00805f9b34fb")
    val characteristicUUID = UUID.fromString("0000ffd9-0000-1000-8000-00805f9b34fb")

    /**
     * Makes characteristic values to set the power status of the lights.
     * @param powerStatus Enum value that can ON or OFF
     */

    fun makePowerData(powerStatus: PowerStatus) = byteArrayOf(
        0xCC.toByte(),
        if (powerStatus == PowerStatus.ON) 0x23.toByte() else 0x24.toByte(),
        0x33.toByte(),
    )


    /**
     * Makes characteristic values to set the colour of the lights. This function is also used to control brightness, by
     * sending darker/lighter colours.
     * @param color The colour to set.
     */
    fun makeColorData(color: Color) = byteArrayOf(
        0x56.toByte(),
        color.red.toByte(),
        color.green.toByte(),
        color.blue.toByte(),
        0x00.toByte(),
        0xF0.toByte(),
        0xAA.toByte(),
    )

    /**
     * Makes characteristic values to set the pattern of the lights.
     * @param pattern The pattern to set, see PatternListProvider.kt for pattern indices.
     */
    fun makePatternData(pattern: Pattern) = byteArrayOf(
        0xBB.toByte(),
        pattern.index.toByte(),
        0x10.toByte(),
        0x44.toByte(),
    )

    /**
     * Makes characteristic values to set the pattern speed.
     * @param percent Percentage to set, this will only be approximate as the light's speed value only has 31 steps.
     */

    fun makePatternSpeedData(percent: Int): ByteArray{
        var speedValue = (percent / 100) * 31
        speedValue = 31 - speedValue
        val data = byteArrayOf(
            0xBB.toByte(),
            0x2E.toByte(),
            speedValue.toByte(),
            0x44.toByte(),
        )
        return data
    }


    /**
     *  This is sent every 100ms and appears to just send a random colour.
     *  @param color The colour to set.
     */
    fun makeMusicData(color: Color) = byteArrayOf(
        0x78.toByte(),
        color.red.toByte(),
        color.green.toByte(),
        color.blue.toByte(),
        0x00.toByte(),
        0xF0.toByte(),
        0xEE.toByte(),
    )

}