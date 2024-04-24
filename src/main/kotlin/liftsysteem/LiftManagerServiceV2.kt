package liftsysteem

import java.lang.Exception
import kotlin.random.Random

// De implementatie mag niet veranderd worden. De signature (naam en inheritance) van de class wel.
class LiftManagerServiceV2 {
    private var connected = false
    private val elevatorIds: List<LiftId>

    init {
        elevatorIds = buildList<LiftId> {
            repeat(5) {
                add(LiftId(Random.nextInt(1000, 10000)))
            }
        }
    }

    fun connectEvs(): List<LiftId> {
        if (connected) {
            throw Exception("Already connected")
        }

        connected = true
        return elevatorIds;
    }

    fun gtf(id: LiftId, floor: Int) {
        if (!elevatorIds.contains(id)) {
            throw Exception("Elevator $id not found.")
        }

        println("Lift $id naar Verdieping $floor")
    }
}

data class LiftId(private val code: Int)