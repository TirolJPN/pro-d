package lifegame;
public class ModelPrinter implements BoardListener {
    public void updated(BoardModel model) {
        model.printForDebug();
        System.out.println(model.getIndexHistory());
    }
}