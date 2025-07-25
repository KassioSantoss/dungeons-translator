package brcomkassin.dungeonstranslator.utils;

import brcomkassin.dungeonstranslator.DungeonsTranslatorPlugin;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public final class KTrigUtils {

    private KTrigUtils() {
        // Utility class — no instantiation
    }

    /**
     * Gera pontos em uma circunferência no plano XZ (horizontal), no centro fornecido.
     *
     * @param center Localização central (Y é fixo)
     * @param radius Raio da circunferência
     * @param points Quantidade de pontos
     * @return Lista de Location correspondentes aos pontos
     */
    public static List<Location> generateCircle(Location center, double radius, int points) {
        List<Location> locations = new ArrayList<>(points);
        double increment = 2 * Math.PI / points;

        for (int i = 0; i < points; i++) {
            double angle = i * increment;
            double x = center.getX() + radius * Math.cos(angle);
            double z = center.getZ() + radius * Math.sin(angle);
            Location loc = new Location(center.getWorld(), x, center.getY(), z);
            locations.add(loc);
        }

        return locations;
    }

    public static void spawnCircleParticles(Location center, double radius, int points, Particle particle) {
        double increment = 2 * Math.PI / points;

        for (int i = 0; i < points; i++) {
            double angle = i * increment;
            double x = center.getX() + radius * Math.cos(angle);
            double z = center.getZ() + radius * Math.sin(angle);

            Location loc = new Location(center.getWorld(), x, center.getY(), z);
            center.getWorld().spawnParticle(particle, loc, 1, 0, 0, 0, 0);
        }
    }

    public static void spawnCircleParticles(Location center, double radius, int points, Particle.DustOptions dustOptions) {
        double increment = 2 * Math.PI / points;
        Particle particle = Particle.DUST;
        for (int i = 0; i < points; i++) {
            double angle = i * increment;
            double x = center.getX() + radius * Math.cos(angle);
            double z = center.getZ() + radius * Math.sin(angle);

            Location loc = new Location(center.getWorld(), x, center.getY(), z);
            center.getWorld().spawnParticle(particle, loc, 1, dustOptions);
        }
    }

    /**
     * Gera uma espiral ascendente.
     *
     * @param center Localização central (base da espiral)
     * @param radius Raio da espiral
     * @param height Altura total da espiral
     * @param coils  Número de voltas (coils)
     * @param points Quantidade total de pontos
     * @return Lista de Location correspondentes aos pontos da espiral
     */
    public static List<Location> generateSpiral(Location center, double radius, double height, double coils, int points) {
        List<Location> locations = new ArrayList<>(points);

        double heightStep = height / points;
        double angleStep = coils * 2 * Math.PI / points;

        for (int i = 0; i < points; i++) {
            double angle = i * angleStep;
            double x = center.getX() + radius * Math.cos(angle);
            double z = center.getZ() + radius * Math.sin(angle);
            double y = center.getY() + i * heightStep;

            Location loc = new Location(center.getWorld(), x, y, z);
            locations.add(loc);
        }

        return locations;
    }

    /**
     * Retorna um vetor unitário na direção de um ângulo em radianos (plano XZ).
     *
     * @param angleRads Ângulo em radianos
     * @return Vector unitário
     */
    public static Vector getUnitVector(double angleRads) {
        return new Vector(Math.cos(angleRads), 0, Math.sin(angleRads));
    }

    /**
     * Converte graus em radianos.
     */
    public static double toRadians(double degrees) {
        return Math.toRadians(degrees);
    }

    /**
     * Converte radianos em graus.
     */
    public static double toDegrees(double radians) {
        return Math.toDegrees(radians);
    }

    /**
     * Calcula a distância 2D (plano XZ) entre dois pontos.
     */
    public static double distance2D(Location a, Location b) {
        double dx = a.getX() - b.getX();
        double dz = a.getZ() - b.getZ();
        return Math.sqrt(dx * dx + dz * dz);
    }

    /**
     * Calcula a distância 3D entre dois pontos.
     */
    public static double distance3D(Location a, Location b) {
        return a.distance(b);
    }

    /**
     * Obtem a localização do alvo com base na localização do jogador e na distância máxima.
     */
    public static Location getTargetLocation(Player player, double maxRange) {
        World world = player.getWorld();
        Location eyeLocation = player.getEyeLocation();
        Vector direction = eyeLocation.getDirection();

        RayTraceResult result = world.rayTraceBlocks(
                eyeLocation,
                direction,
                maxRange,
                FluidCollisionMode.NEVER,
                true
        );

        Location targetLocation;

        if (result != null && result.getHitBlock() != null) {
            Block hitBlock = result.getHitBlock();
            targetLocation = hitBlock.getLocation();
        } else {

            Location endPoint = eyeLocation.add(direction.multiply(maxRange));
            targetLocation = world.getHighestBlockAt(endPoint).getLocation();
        }
        return targetLocation.add(0.5, 0, 0.5);
    }

    public static void spawnFallingBlocksCircle(Location center, double radius, int count, double yOffset, Player caster) {
        World world = center.getWorld();
        if (world == null) return;

        for (int i = 0; i < count; i++) {
            double angle = (2 * Math.PI / count) * i;
            double x = center.getX() + radius * Math.cos(angle);
            double z = center.getZ() + radius * Math.sin(angle);
            double y = center.getY() + yOffset;

            Location spawnLoc = new Location(world, x, y, z);
            Location blockBelow = spawnLoc.clone().subtract(0, 1, 0);

            if (!blockBelow.getBlock().getType().isSolid()) continue;

            Material material = blockBelow.getBlock().getType();

            if (material == Material.AIR || material == Material.BEDROCK) continue;

            FallingBlock falling = world.spawnFallingBlock(spawnLoc, blockBelow.getBlock().getBlockData());

            falling.setMetadata("visual-falling-block", new FixedMetadataValue(DungeonsTranslatorPlugin.getInstance(), true));
            falling.setDropItem(false);
            falling.setHurtEntities(false);
            falling.setGravity(true);
            falling.setInvulnerable(true);

            falling.setVelocity(new Vector(0, 0.5 + Math.random() * 0.4, 0));
        }
    }

}
