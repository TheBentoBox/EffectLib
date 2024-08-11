package de.slikey.effectlib.effect;

import org.bukkit.Particle;
import org.bukkit.Location;

import de.slikey.effectlib.Effect;
import de.slikey.effectlib.EffectType;
import de.slikey.effectlib.EffectManager;

public class IconEffect extends Effect {

    public int yOffset = 2;

    public IconEffect(EffectManager effectManager) {
        super(effectManager);
        type = EffectType.REPEATING;
        particle = Particle.HAPPY_VILLAGER;
        period = 4;
        iterations = 25;
    }

    @Override
    public void onRun() {
        Location location = getLocation();

        if (location == null) {
            cancel();
            return;
        }

        location.add(0, yOffset, 0);
        display(particle, location);
    }

}
