import kotlin.collections.emptyList
import kotlin.collections.mutableListOf

class LiftController(private val liftSystem: LiftSystem) {
    private val floorsToGoTo: MutableList<Int> = mutableListOf()
    private var currentFloor = 1

    fun step() {
        if (floorsToGoTo.isNotEmpty()) {
            liftSystem.gtf(floorsToGoTo.first())
            currentFloor = floorsToGoTo.first()
            
            floorsToGoTo.removeFirst()
        } else if (currentFloor != 1) {
            liftSystem.gtf(1)
            currentFloor = 1
        }
    }

    fun call(floor: Int, destination: Int) {
        floorsToGoTo += listOf(floor, destination)
    }
}