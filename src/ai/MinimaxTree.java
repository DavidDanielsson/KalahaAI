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
        int totalValue = 0;
        int currentPlayer = currentBoard.getNextPlayer();
        // Iterate through all bowls
        for (int i=1;i<7;i++)
        {
            // Get score of player so we can later compare after a move
            //int startScore = currentBoard.getScore(currentPlayer);
            if(currentBoard.moveIsPossible(i))
            {
                GameState boardClone = currentBoard.clone();
                boardClone.makeMove(i);
                int endScore = boardClone.getScore(currentPlayer);
                //int dif = endScore - startScore;
                // Flip score if it's the other guy, since we only want max to get positive scores
                if(currentPlayer != thisPlayer)
                {
                    endScore *= -1;
                }
                MinimaxNode child = new MinimaxNode(endScore, currentPlayer);
                currentNode.children.add(child);
                BuildNode(child, boardClone, depth+1);
                if(currentPlayer == thisPlayer)
                {
                    if(child.value > totalValue)
                        totalValue = child.value;
                }
                else
                {
                    if(child.value < totalValue)
                        totalValue = child.value;
                }
            }
        }
        currentNode.value = totalValue;
    }
}
