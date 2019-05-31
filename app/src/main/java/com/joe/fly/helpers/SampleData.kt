package com.joe.fly.helpers

import com.joe.fly.models.Destination

object SampleData {

    val DESTINATIONS = ArrayList<Destination>()

    private var count = 5

    private var dummy_description = "Some dummy description right here."

    init {
        // Add sample destinations
        val newDestination1 = Destination()
        newDestination1.id = 1
        newDestination1.city = "Juba"
        newDestination1.description = dummy_description
        newDestination1.country = "South Sudan"
        DESTINATIONS.add(newDestination1)

        val newDestination2 = Destination()
        newDestination2.id = 2
        newDestination2.city = "Nairobi"
        newDestination2.description = dummy_description
        newDestination2.country = "Kenya"
        DESTINATIONS.add(newDestination2)

        val newDestination3 = Destination()
        newDestination3.id = 3
        newDestination3.city = "New York"
        newDestination3.description = dummy_description
        newDestination3.country = "USA"
        DESTINATIONS.add(newDestination3)

        val newDestination4 = Destination()
        newDestination4.id = 4
        newDestination4.city = "London"
        newDestination4.description = dummy_description
        newDestination4.country = "United Kingdom"
        DESTINATIONS.add(newDestination4)

        val newDestination5 = Destination()
        newDestination5.id = 5
        newDestination5.city = "Sydney"
        newDestination5.description = dummy_description
        newDestination5.country = "Australia"
        DESTINATIONS.add(newDestination5)
    }

    fun addDestination(item: Destination) {
        item.id = count
        DESTINATIONS.add(item)
        count += 1
    }

    fun getDestinationById(id: Int): Destination? {
        for (i in DESTINATIONS.indices) {
            if (DESTINATIONS[i].id == id)
                return DESTINATIONS[i]
        }
        return null
    }

    fun deleteDestination(id: Int) {
        var destinationToRemove: Destination? = null
        for (i in DESTINATIONS.indices) {
            if (DESTINATIONS[i].id == id)
                destinationToRemove = DESTINATIONS[i]
        }
        if (destinationToRemove != null)
            DESTINATIONS.remove(destinationToRemove)
    }

    fun updateDestination(destination: Destination) {
        for (i in DESTINATIONS.indices) {
            if (DESTINATIONS[i].id == destination.id) {
                val destinationToUpdate = DESTINATIONS[i]
                destinationToUpdate.city = destination.city
                destinationToUpdate.country = destination.country
                destinationToUpdate.description = destination.description
            }
        }
    }

}
