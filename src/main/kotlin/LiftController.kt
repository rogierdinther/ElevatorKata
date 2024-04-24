import kotlin.collections.mutableListOf

class LiftController(private val liftSystem: LiftSystem) {
    private val floorsToGoTo: MutableList<Int> = mutableListOf()
    private var currentFloor: Int?

    init {
        currentFloor = null
        step()
    }

    fun call(floor: Int, destination: Int) {
        floorsToGoTo += listOf(floor, destination)
    }

    fun step() {
        floorsToGoTo.sort()
        if (floorsToGoTo.isNotEmpty()) {
            val floor = floorsToGoTo.first()
            goToFloor(floor)
            floorsToGoTo.removeAll {it == floor} 
        } else if(currentFloor != 1) {
            goToFloor(1)
        }
    }

    private fun goToFloor(floor: Int) {
        liftSystem.gtf(floor)
        currentFloor = floor
    }
}