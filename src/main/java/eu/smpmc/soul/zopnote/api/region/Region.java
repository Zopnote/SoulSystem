package eu.smpmc.soul.zopnote.api.region;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.Collection;

public class Region {
  private Location center;
  private double radius;
  private World world;
  private Player owner;
  private boolean pvp;

  public Region(Location center, double radius, Player owner, boolean pvp) {
    this.pvp = pvp;
    this.world = center.getWorld();
    this.center = center;
    this.radius = radius;
    this.owner = owner;
  }
  public World getWorld(){
    return world;
  }
  public Location getCenter() {
    return center;
  }
  public double getRadius() {
    return radius;
  }
  public Player getOwner() {
    return owner;
  }
  public boolean getPvP() {
    return pvp;
  }
  public void clear() {
    this.world=null;
    this.center=null;
    this.radius=0;
    this.owner=null;
    this.pvp=false;
  }
  public static Collection<LivingEntity> contains(Region region) {
    return region.getCenter().getNearbyLivingEntities(region.getRadius());
  }
  public static boolean containsObject(LivingEntity entity, Region region) {
    if (region == null) {
      return false;
    }
    else if (entity.getLocation().distance(region.getCenter()) < region.getRadius()) {
      return true;
    }
    else {
      return false;
    }
  }
  public static void delete(Region region) {
    region.clear();
  }
  public static void spawnCircle(Region region, Particle particle) {
    new Circle().spawn(region, particle);
  }
}
