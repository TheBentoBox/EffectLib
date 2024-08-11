package de.slikey.effectlib.effect;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.util.Vector;

import de.slikey.effectlib.Effect;
import de.slikey.effectlib.EffectType;
import de.slikey.effectlib.EffectManager;
import de.slikey.effectlib.util.RandomUtils;

public class CloudEffect extends Effect {

    /**
     * Particle of the cloud
     */
    public Particle cloudParticle = Particle.CLOUD;
    public Color cloudColor = null;
    public float cloudSpeed = 0;
    public int cloudParticles = 50;

    /**
     * Particle of the rain/snow
     */
    public Particle mainParticle = Particle.DRIPPING_WATER;
    public int mainParticles = 15;

    /**
     * Size of the cloud
     */
    public float cloudSize = 0.7F;

    /**
     * Radius of the rain/snow
     */
    public float particleRadius = cloudSize - 0.1F;

    /**
     * Y-Offset from location
     */
    public double yOffset = 0.8;

    // Should the effect increase its height every iteration?
    public boolean increaseHeight = true;

    public CloudEffect(EffectManager manager) {
        super(manager);
        type = EffectType.REPEATING;
        period = 5;
        iterations = 50;
    }

    @Override
    public void onRun() {
        Location location = getLocation();

        if (location == null) {
            cancel();
            return;
        }

        location.add(0, yOffset, 0);

        Vector v;

        for (int i = 0; i < cloudParticles; i++) {
            v = RandomUtils.getRandomCircleVector().multiply(RandomUtils.random.nextDouble() * cloudSize);
            display(cloudParticle, location.add(v), cloudColor, cloudSpeed, 1);
            location.subtract(v);
        }

        Location l;
        if (increaseHeight) l = location.add(0, 0.2, 0);
        else l = location;

        int r;
        double x;
        double z;

        for (int i = 0; i < mainParticles; i++) {
            r = RandomUtils.random.nextInt(2);
            x = RandomUtils.random.nextDouble() * particleRadius;
            z = RandomUtils.random.nextDouble() * particleRadius;

            l.add(x, 0, z);
            if (r != 1) display(mainParticle, l);

            l.subtract(x, 0, z);
            l.subtract(x, 0, z);

            if (r != 1) display(mainParticle, l);
            l.add(x, 0, z);
        }
    }

}
