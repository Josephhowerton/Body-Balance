package com.fitness.component

import kotlin.math.abs

fun calculateWeightProgressAsFraction(startWeight:Double, currentWeight:Double, targetWeight:Double) :Double {
    val totalWeightDifference = startWeight-targetWeight
    val currentWeightDifference = currentWeight-startWeight

    return abs(currentWeightDifference/totalWeightDifference)
}

fun calculateWeightProgress(startWeight:Double, currentWeight:Double, targetWeight:Double) :Double =
    calculateWeightProgressAsFraction(startWeight, currentWeight, targetWeight) *100


fun formatWeightProgress(startWeight:Double, currentWeight:Double, targetWeight:Double) : String {

    val overUnder =
        if( isOnTrack(startWeight, currentWeight, targetWeight) ){
            "towards"
        } else if(isGoalToGain(startWeight = startWeight, targetWeight = targetWeight)){
            "under"
        }else if(!isGoalToGain(startWeight = startWeight, targetWeight = targetWeight)){
            "over"
        } else{
            return "Completed"
        }
    return "${"%.1f".format(calculateWeightProgress(startWeight, currentWeight, targetWeight))}% $overUnder"
}

private fun isGoalToGain(startWeight:Double, targetWeight:Double) : Boolean = (startWeight-targetWeight) < 0

fun isOnTrack(startWeight:Double, currentWeight:Double, targetWeight:Double) : Boolean =
    isGoalToGain(startWeight, targetWeight) && currentWeight-startWeight > 0 ||
            !isGoalToGain(startWeight, targetWeight) && currentWeight-startWeight < 0