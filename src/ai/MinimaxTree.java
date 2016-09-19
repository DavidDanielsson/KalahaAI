package ai;

import kalaha.GameState;

/**
 * Created by BOSS on 2016-09-19.
 */
public class MinimaxTree
{
    MinimaxNode root;
    int thisPlayer;
    final int maxDepth;

    public MinimaxTree(GameState currentBoard, int thisPlayer, int maxDepth)
    {
        this.thisPlayer = thisPlayer;
        this.maxDepth = maxDepth;
        root = new MinimaxNode(0, thisPlayer);
        BuildNode(root, currentBoard, 0);
    }



    void BuildNode(MinimaxNode currentNode, GameState currentBoard, int depth)
    {

        if(depth >= maxDepth)
            return;
        int totalValue;
        int currentPlayer = currentBoard.getNextPlayer();

        if(currentPlayer == thisPlayer)
        {
            totalValue = Integer.MIN_VALUE;
        }
        else
        {
            totalValue = Integer.MAX_VALUE;
        }


        // Iterate through all bowls
        for (int i=1;i<7;i++)
        {
            // Get score of player so we can later compare after a move
            if(currentBoard.moveIsPossible(i))
            {
                GameState boardClone = currentBoard.clone();
                boardClone.makeMove(i);
                int utility = GetUtilityValue(boardClone);
                MinimaxNode child = new MinimaxNode(utility, currentPlayer);
                currentNode.children.add(child);
                BuildNode(child, boardClone, depth+1);

                if(currentPlayer == thisPlayer)
                {
                    if(child.value > totalValue)
                    {
                        totalValue = child.value;
                        child.moveIndex = i;
                    }

                }
                else
                {
                    if(child.value < totalValue)
                    {
                        totalValue = child.value;
                        child.moveIndex = i;
                    }
                }
            }
        }
        currentNode.value = totalValue;
    }
    /**
     * Returns utility value for a given board. Call after having made a move to
     * see the value after it. Utility value is the difference is score between
     * the two players.*/
    int GetUtilityValue(GameState currentBoard)
    {
        return currentBoard.getScore(thisPlayer) - currentBoard.getScore((~thisPlayer)&0b11);
    }
}
