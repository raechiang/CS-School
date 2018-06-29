/*
Rachel Chiang
Project 3: Four-in-a-Line
CS 420.01
--------------------------------------------------------------------------------
*/
#include "Engine.h"

#include "Constants.h"

Engine::Engine()
    : ai(ui.getComputerTurn(), ui.getTimeLimit())
{
    
}

void Engine::run()
{
    ui.writeBoard(board.getXPositions(), board.getOPositions());

    // while game is not over
    //    update
    while (board.checkGameOver() == Constants::NOT_OVER)
    {
        update();
    }

    ui.writeGameOver(board.checkGameOver());
}

void Engine::update()
{
    int64_t cMove = 0;
    int64_t hMove = 0;
    if (ai.getPlayerType() == Constants::PLAYER_X)
    {
        // ai is first
        // ai makes move
        cMove = ai.getMove(board.getXPositions(), board.getOPositions(), hMove);
        board.updateXPositions(cMove);
        // ui prints out ai's move
        ui.writeComputerMove(cMove);
        ui.writeBoard(board.getXPositions(), board.getOPositions());
        // human makes move
        if (board.checkGameOver() == Constants::NOT_OVER)
        {
            hMove = queryHuman();
            board.updateOPositions(hMove);
        }
    }
    else
    {
        // human is first
        // human makes move
        hMove = queryHuman();
        board.updateXPositions(hMove);
        // ai makes move
        if (board.checkGameOver() == Constants::NOT_OVER)
        {
            cMove = ai.getMove(board.getXPositions(), board.getOPositions(), hMove);
            board.updateOPositions(cMove);
            // ui prints out ai's move
            ui.writeComputerMove(cMove);
        }
        ui.writeBoard(board.getXPositions(), board.getOPositions());
    }

    // print board and total moves
    ui.writeMoves(hMove, cMove);
}

int64_t Engine::queryHuman()
{
    while (true)
    {
        int64_t move = ui.getHumanMove();
        if (board.isValidMove(move))
        {
            return move;
        }
    }
}