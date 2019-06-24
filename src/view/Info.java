package view;

import javafx.scene.layout.Pane;
import model.Card;
import model.Item;

public interface Info{
    public Pane cardInfo(Card card);
    public Pane itemInfo(Item item);
}
