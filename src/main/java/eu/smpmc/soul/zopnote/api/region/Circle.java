package eu.smpmc.soul.zopnote.api.region;

import org.bukkit.Particle;

public class Circle {
  public void spawn(Region region, Particle particle) {
    for (int degree = 0; degree < 360; degree++) {
      degree++;
      double radians = Math.toRadians(degree);
      double x = region.getRadius() * Math.cos(radians);
      double z = region.getRadius() * Math.sin(radians);
      region.getCenter().add(x, 0, z);
      region.getCenter().getWorld().spawnParticle(particle, region.getCenter(), 1);
      region.getCenter().subtract(x, 0, z);
    }
  }
}
