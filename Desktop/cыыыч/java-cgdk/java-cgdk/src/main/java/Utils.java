import model.Move;
import model.Vehicle;

import static model.ActionType.CLEAR_AND_SELECT;
import static model.ActionType.MOVE;

public class Utils {

    public static void log(String tag, String message) {
        System.out.println(tag + "$" + message);
    }

    public static Move buildClearAndSelectMove(Vehicle vehicle) {
        return buildClearAndSelectMove(vehicle.getX() - vehicle.getRadius(), vehicle.getY() - vehicle.getRadius()
                , vehicle.getX() + vehicle.getRadius(), vehicle.getY() + vehicle.getRadius());
    }

    public static Move buildClearAndSelectMove(double left, double top, double right, double bottom) {
        final Move move = new Move();
        move.setAction(CLEAR_AND_SELECT);
        move.setLeft(left);
        move.setTop(top);
        move.setRight(right);
        move.setBottom(bottom);

        return move;
    }

    public static Move buildMoveMove(double dx, double dy) {
        final Move move = new Move();
        move.setAction(MOVE);
        move.setX(dx);
        move.setY(dy);

        return move;
    }

}
