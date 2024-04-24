import kotlin.collections.emptyList
import kotlin.collections.mutableListOf

class LiftController(private val liftSystem: LiftSystem) {
    private val floorsToGoTo: MutableList<Int> = mutableListOf()

    fun step() {
        if (floorsToGoTo.isNotEmpty()) {
            liftSystem.gtf(floorsToGoTo.first())
            floorsToGoTo.removeFirst()
        }
    }

    fun call(floor: Int, destination: Int) {
        floorsToGoTo += listOf(floor, destination)
    }
}