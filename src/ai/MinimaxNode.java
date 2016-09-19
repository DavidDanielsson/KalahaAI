package ai;

import java.util.ArrayList;

/**
 * Created by BOSS on 2016-09-19.
 */
public class MinimaxNode
{
    public int value;
    public ArrayList<MinimaxNode> children;
    public int player;

    public MinimaxNode(int value, int player)
    {
        children = new ArrayList<>();
        this.value = value;
        this.player = player;
    }

}
