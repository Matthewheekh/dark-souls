package game.weaponactions;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.WeaponAction;
import edu.monash.fit2099.engine.WeaponItem;
import game.actions.AttackAction;
import game.enums.Status;

public class WindSlashAction extends WeaponAction {

    private Actor target;
    private String direction;
    /**
     * Constructor
     *
     * @param weaponItem the weapon item that has capabilities
     */
    public WindSlashAction(WeaponItem weaponItem, Actor target, String direction) {
        super(weaponItem);
        this.target = target;
        this.direction = direction;
    }

    @Override
    public String execute(Actor actor, GameMap map) {

        weapon.addCapability(Status.WIND_SLASH_BONUS);

        AttackAction attack = new AttackAction(target, direction);
        target.addCapability(Status.STUNNED);
        String output = attack.execute(actor, map);

        weapon.removeCapability(Status.WIND_SLASH_BONUS);
        weapon.removeCapability(Status.CHARGED);
        return output;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " uses WindSlashAction on " + target + " at " + direction;
    }
}
