package model;

import java.util.Arrays;

/**
 * Класс, определяющий технику. Содержит также все свойства круглых объектов.
 */
public class Vehicle extends CircularUnit {
    private final long playerId;
    private int durability;
    private final int maxDurability;
    private final double maxSpeed;
    private final double visionRange;
    private final double squaredVisionRange;
    private final double groundAttackRange;
    private final double squaredGroundAttackRange;
    private final double aerialAttackRange;
    private final double squaredAerialAttackRange;
    private final int groundDamage;
    private final int aerialDamage;
    private final int groundDefence;
    private final int aerialDefence;
    private final int attackCooldownTicks;
    private int remainingAttackCooldownTicks;
    private final VehicleType type;
    private final boolean aerial;
    private boolean selected;
    private int[] groups;
    private boolean yetUsed = false; // on this tick

    public Vehicle(
            long id, double x, double y, double radius, long playerId, int durability, int maxDurability,
            double maxSpeed, double visionRange, double squaredVisionRange, double groundAttackRange,
            double squaredGroundAttackRange, double aerialAttackRange, double squaredAerialAttackRange,
            int groundDamage, int aerialDamage, int groundDefence, int aerialDefence, int attackCooldownTicks,
            int remainingAttackCooldownTicks, VehicleType type, boolean aerial, boolean selected, int[] groups) {
        super(id, x, y, radius);

        this.playerId = playerId;
        this.durability = durability;
        this.maxDurability = maxDurability;
        this.maxSpeed = maxSpeed;
        this.visionRange = visionRange;
        this.squaredVisionRange = squaredVisionRange;
        this.groundAttackRange = groundAttackRange;
        this.squaredGroundAttackRange = squaredGroundAttackRange;
        this.aerialAttackRange = aerialAttackRange;
        this.squaredAerialAttackRange = squaredAerialAttackRange;
        this.groundDamage = groundDamage;
        this.aerialDamage = aerialDamage;
        this.groundDefence = groundDefence;
        this.aerialDefence = aerialDefence;
        this.attackCooldownTicks = attackCooldownTicks;
        this.remainingAttackCooldownTicks = remainingAttackCooldownTicks;
        this.type = type;
        this.aerial = aerial;
        this.selected = selected;
        this.groups = Arrays.copyOf(groups, groups.length);
    }

    public Vehicle(Vehicle vehicle, VehicleUpdate vehicleUpdate) {
        super(vehicle.getId(), vehicleUpdate.getX(), vehicleUpdate.getY(), vehicle.getRadius());

        this.playerId = vehicle.playerId;
        this.durability = vehicleUpdate.getDurability();
        this.maxDurability = vehicle.maxDurability;
        this.maxSpeed = vehicle.maxSpeed;
        this.visionRange = vehicle.visionRange;
        this.squaredVisionRange = vehicle.squaredVisionRange;
        this.groundAttackRange = vehicle.groundAttackRange;
        this.squaredGroundAttackRange = vehicle.squaredGroundAttackRange;
        this.aerialAttackRange = vehicle.aerialAttackRange;
        this.squaredAerialAttackRange = vehicle.squaredAerialAttackRange;
        this.groundDamage = vehicle.groundDamage;
        this.aerialDamage = vehicle.aerialDamage;
        this.groundDefence = vehicle.groundDefence;
        this.aerialDefence = vehicle.aerialDefence;
        this.attackCooldownTicks = vehicle.attackCooldownTicks;
        this.remainingAttackCooldownTicks = vehicleUpdate.getRemainingAttackCooldownTicks();
        this.type = vehicle.type;
        this.aerial = vehicle.aerial;
        this.selected = vehicleUpdate.isSelected();

        int[] updateGroups = vehicleUpdate.getGroups();
        this.groups = Arrays.copyOf(updateGroups, updateGroups.length);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vehicle vehicle = (Vehicle) o;

        if (playerId != vehicle.playerId) return false;
        if (durability != vehicle.durability) return false;
        if (maxDurability != vehicle.maxDurability) return false;
        if (Double.compare(vehicle.maxSpeed, maxSpeed) != 0) return false;
        if (Double.compare(vehicle.visionRange, visionRange) != 0) return false;
        if (Double.compare(vehicle.squaredVisionRange, squaredVisionRange) != 0) return false;
        if (Double.compare(vehicle.groundAttackRange, groundAttackRange) != 0) return false;
        if (Double.compare(vehicle.squaredGroundAttackRange, squaredGroundAttackRange) != 0) return false;
        if (Double.compare(vehicle.aerialAttackRange, aerialAttackRange) != 0) return false;
        if (Double.compare(vehicle.squaredAerialAttackRange, squaredAerialAttackRange) != 0) return false;
        if (groundDamage != vehicle.groundDamage) return false;
        if (aerialDamage != vehicle.aerialDamage) return false;
        if (groundDefence != vehicle.groundDefence) return false;
        if (aerialDefence != vehicle.aerialDefence) return false;
        if (attackCooldownTicks != vehicle.attackCooldownTicks) return false;
        if (remainingAttackCooldownTicks != vehicle.remainingAttackCooldownTicks) return false;
        if (aerial != vehicle.aerial) return false;
        if (selected != vehicle.selected) return false;
        if (yetUsed != vehicle.yetUsed) return false;
        if (type != vehicle.type) return false;
        return Arrays.equals(groups, vehicle.groups);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (playerId ^ (playerId >>> 32));
        result = 31 * result + durability;
        result = 31 * result + maxDurability;
        temp = Double.doubleToLongBits(maxSpeed);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(visionRange);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(squaredVisionRange);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(groundAttackRange);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(squaredGroundAttackRange);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(aerialAttackRange);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(squaredAerialAttackRange);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + groundDamage;
        result = 31 * result + aerialDamage;
        result = 31 * result + groundDefence;
        result = 31 * result + aerialDefence;
        result = 31 * result + attackCooldownTicks;
        result = 31 * result + remainingAttackCooldownTicks;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (aerial ? 1 : 0);
        result = 31 * result + (selected ? 1 : 0);
        result = 31 * result + Arrays.hashCode(groups);
        result = 31 * result + (yetUsed ? 1 : 0);
        return result;
    }

    public void update(VehicleUpdate vehicleUpdate) {
        setX(vehicleUpdate.getX());
        setY(vehicleUpdate.getY());
        setDurability(vehicleUpdate.getDurability());
        setRemainingAttackCooldownTicks(vehicleUpdate.getRemainingAttackCooldownTicks());
        setSelected(vehicleUpdate.isSelected());
        int[] updateGroups = vehicleUpdate.getGroups();
        setGroups(Arrays.copyOf(updateGroups, updateGroups.length));
    }

    /**
     * @return Возвращает идентификатор игрока, которому принадлежит техника.
     */
    public long getPlayerId() {
        return playerId;
    }

    /**
     * @return Возвращает текущую прочность.
     */
    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    /**
     * @return Возвращает максимальную прочность.
     */
    public int getMaxDurability() {
        return maxDurability;
    }

    /**
     * @return Возвращает максимальное расстояние, на которое данная техника может переместиться за один игровой тик,
     * без учёта типа местности и погоды. При перемещении по дуге учитывается длина дуги,
     * а не кратчайшее расстояние между начальной и конечной точками.
     */
    public double getMaxSpeed() {
        return maxSpeed;
    }

    /**
     * @return Возвращает максимальное расстояние (от центра до центра),
     * на котором данная техника обнаруживает другие объекты, без учёта типа местности и погоды.
     */
    public double getVisionRange() {
        return visionRange;
    }

    /**
     * @return Возвращает квадрат максимального расстояния (от центра до центра),
     * на котором данная техника обнаруживает другие объекты, без учёта типа местности и погоды.
     */
    public double getSquaredVisionRange() {
        return squaredVisionRange;
    }

    /**
     * @return Возвращает максимальное расстояние (от центра до центра),
     * на котором данная техника может атаковать наземные объекты.
     */
    public double getGroundAttackRange() {
        return groundAttackRange;
    }

    /**
     * @return Возвращает квадрат максимального расстояния (от центра до центра),
     * на котором данная техника может атаковать наземные объекты.
     */
    public double getSquaredGroundAttackRange() {
        return squaredGroundAttackRange;
    }

    /**
     * @return Возвращает максимальное расстояние (от центра до центра),
     * на котором данная техника может атаковать воздушные объекты.
     */
    public double getAerialAttackRange() {
        return aerialAttackRange;
    }

    /**
     * @return Возвращает квадрат максимального расстояния (от центра до центра),
     * на котором данная техника может атаковать воздушные объекты.
     */
    public double getSquaredAerialAttackRange() {
        return squaredAerialAttackRange;
    }

    /**
     * @return Возвращает урон одной атаки по наземному объекту.
     */
    public int getGroundDamage() {
        return groundDamage;
    }

    /**
     * @return Возвращает урон одной атаки по воздушному объекту.
     */
    public int getAerialDamage() {
        return aerialDamage;
    }

    /**
     * @return Возвращает защиту от атак наземных юнитов.
     */
    public int getGroundDefence() {
        return groundDefence;
    }

    /**
     * @return Возвращает защиту от атак воздушых юнитов.
     */
    public int getAerialDefence() {
        return aerialDefence;
    }

    /**
     * @return Возвращает минимально возможный интервал между двумя последовательными атаками данной техники.
     */
    public int getAttackCooldownTicks() {
        return attackCooldownTicks;
    }

    /**
     * @return Возвращает количество тиков, оставшееся до следующей атаки.
     * Для совершения атаки необходимо, чтобы это значение было равно нулю.
     */
    public int getRemainingAttackCooldownTicks() {
        return remainingAttackCooldownTicks;
    }

    public void setRemainingAttackCooldownTicks(int remainingAttackCooldownTicks) {
        this.remainingAttackCooldownTicks = remainingAttackCooldownTicks;
    }

    /**
     * @return Возвращает тип техники.
     */
    public VehicleType getType() {
        return type;
    }

    /**
     * @return Возвращает {@code true} в том и только том случае, если эта техника воздушная.
     */
    public boolean isAerial() {
        return aerial;
    }

    /**
     * @return Возвращает {@code true} в том и только том случае, если эта техника выделена.
     */
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    /**
     * @return Возвращает группы, в которые входит эта техника.
     */
    public int[] getGroups() {
        return Arrays.copyOf(groups, groups.length);
    }

    public void setGroups(int[] groups) {
        this.groups = groups;
    }

    public boolean isYetUsed() {
        return yetUsed;
    }

    public void setYetUsed(boolean yetUsed) {
        this.yetUsed = yetUsed;
    }

}
