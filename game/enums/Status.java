package game.enums;

/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 */
public enum Status {
    HOSTILE_TO_ENEMY, // use this capability to be hostile towards something (e.g., to be attacked by enemy)
    IS_ENEMY,
    IS_BOSS,
    REVIVABLE,
    SPIN_ATTACKING,
    CHARGED,
    CHARGING,
    DISARMED,
    WIND_SLASH_BONUS,
    STUNNED,
    DULLNESS,
    REMOVE_FROM_MAP,
    RESET,
    NEW_SPAWN,
    DEAD,
    FALL_INTO_VALLEY,
    EMBER_FORM,
    RAGE_MODE,
    BLOCKED,
    ACTIVATED,
    INTERACTED_WITH_BONFIRE,
    IS_MIMIC
}
