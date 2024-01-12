package enums

import com.fitness.resources.R
import extensions.Item

enum class EGoals(override val title: Int, override val desc: Int? = null, override val apiParameter: String? = null): Item {
    INCREASE_CARDIO_ENDURANCE(R.string.title_increase_cardio_endurance),
    BUILD_MUSCLE_STRENGTH(R.string.title_build_muscle_strength),
    ENHANCE_FLEXIBILITY(R.string.title_enhance_flexibility),
    IMPROVE_BALANCE(R.string.title_improve_balance),
    WEIGHT_LOSS(R.string.title_weight_loss),
    WEIGHT_GAIN(R.string.title_weight_gain),
    IMPROVE_CORE_STRENGTH(R.string.title_improve_core_strength),
    REDUCE_STRESS(R.string.title_reduce_stress),
    IMPROVE_SLEEP_QUALITY(R.string.title_improve_sleep_quality),
    EAT_HEALTHIER(R.string.title_eat_healthier),
    STAY_HYDRATED(R.string.title_stay_hydrated),
    INCREASE_ACTIVITY_LEVEL(R.string.title_increase_activity_level),
    IMPROVE_MENTAL_HEALTH(R.string.title_improve_mental_health),
    QUIT_SMOKING(R.string.title_quit_smoking),
    REDUCE_ALCOHOL_CONSUMPTION(R.string.title_reduce_alcohol_consumption),
    PRACTICE_MINDFULNESS(R.string.title_practice_mindfulness),
    RUN_A_MARATHON(R.string.title_run_a_marathon),
    PARTICIPATE_IN_A_TRIATHLON(R.string.title_participate_in_a_triathlon),
    LEARN_A_NEW_SPORT(R.string.title_learn_a_new_sport),
    IMPROVE_POSTURE(R.string.title_improve_posture),
    INCREASE_FLEXIBILITY(R.string.title_increase_flexibility),
    BOOST_IMMUNE_SYSTEM(R.string.title_boost_immune_system),
    IMPROVE_DIGESTION(R.string.title_improve_digestion),
    DECREASE_SCREEN_TIME(R.string.title_decrease_screen_time),
    ACHIEVE_WORK_LIFE_BALANCE(R.string.title_achieve_work_life_balance);

}
