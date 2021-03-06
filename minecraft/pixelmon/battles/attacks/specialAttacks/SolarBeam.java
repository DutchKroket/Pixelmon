package pixelmon.battles.attacks.specialAttacks;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.statusEffects.StatusEffectBase;
import pixelmon.battles.attacks.statusEffects.StatusEffectType;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class SolarBeam extends MultiTurnSpecialAttackBase {

	public SolarBeam() {
		super(MultiTurnSpecialAttackType.SolarBeam, 2);
	}

	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, ArrayList<String> attackList, ArrayList<String> targetAttackList) throws Exception {
		if (!doesPersist(user)) {
			setPersists(user, true);
			initTurnCount(user);
		}
		incrementTurnCount(user);
		for (StatusEffectBase e : user.status)
			if (e.type == StatusEffectType.Sunny)
				incrementTurnCount(user);
		if (getTurnCount(user) == 1) {
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getName() + " is storing energy!");
			return true;
		} else {
			setPersists(user, false);
			return false;
		}
	}

	@Override
	public boolean cantMiss(EntityPixelmon user) {
		if (getTurnCount(user) == 0)
			return true;
		return false;
	}

}
