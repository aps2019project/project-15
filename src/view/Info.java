package view;

import javafx.scene.layout.Pane;
import model.Card;
import model.Item;

public interface Info{
     Pane cardInfo(Card card);
     Pane itemInfo(Item item);
}
