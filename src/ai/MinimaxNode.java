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
    // Number representing which bowl this node is
    public int moveIndex;

    public MinimaxNode(int value, int player)
    {
        children = new ArrayList<>();
        this.value = value;
        this.player = player;
    }

}
