/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ctu.cit.sudoku.Factories;

import edu.ctu.cit.sudoku.Models.Puzzle;

/**
 *
 * @author charlie
 */
public class PuzzleFactory {

    public enum GameDifficulties {
        EXTREMELY_EASY,
        EASY,
        MEDIUM,
        DIFFICULT,
        EVIL
    }

    public Puzzle createPuzzle(GameDifficulties difficulties) {
        switch (difficulties) {
            case EXTREMELY_EASY:
                break;
            case EASY:
                break;
            case MEDIUM:
                break;
            case DIFFICULT:
                break;
            case EVIL:
                break;
            default:
                throw new EnumConstantNotPresentException(GameDifficulties.class, difficulties.toString());

        }
        return null;
    }
}
