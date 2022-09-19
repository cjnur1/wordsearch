package com.example.wordsearch.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
@org.springframework.stereotype.Service
public class Service {

enum Direction {
    HORIZONTAL, VERTICAL, DIAGONAL, HR_INVERSE, VR_INVERSE, DG_INVERSE
}



private record Coordinate(int x, int y) {
}


public char[][] genarateGrid(int gridSize, List<String> words) {

    char[][] content = new char[gridSize][gridSize];
  List<Coordinate>  coordinates = new ArrayList<>();
    for (int i = 0; i < content.length; i++) {
        for (int j = 0; j < content.length; j++) {
            content[i][j] = '-';
            coordinates.add(new Coordinate(i,
                    j));
        }
    }
    Collections.shuffle(coordinates);

    for (String word1 : words) {
        String word = word1.toUpperCase();
        for (Coordinate coordinate : coordinates) {
            Direction direction = doesFit(content,word, coordinate);

            if (direction != null) {
                int x = coordinate.x;
                int y = coordinate.y;
                switch (direction) {
                    case HORIZONTAL:
                        for (char c : word.toCharArray()) {
                            content[x][y++] = c;
                        }
                        break;
                    case VERTICAL:
                        for (char c : word.toCharArray()) {
                            content[x++][y] = c;
                        }
                        break;
                    case DIAGONAL:
                        for (char c : word.toCharArray()) {
                            content[x++][y++] = c;
                        }
                        break;
                    case HR_INVERSE:
                        for (char c : word.toCharArray()) {
                            content[x][y--] = c;
                        }
                        break;
                    case VR_INVERSE:
                        for (char c : word.toCharArray()) {
                            content[x--][y] = c;
                        }
                        break;
                    case DG_INVERSE:
                        for (char c : word.toCharArray()) {
                            content[x--][y--] = c;
                        }
                        break;
                }
                break;
            }
        }
    }
    randamFillGrid(content);
    return content;
}

private Direction doesFit(char[][] content,String word, Coordinate coordinate) {
    List<Direction> directions = Arrays.asList(Direction.values());
    Collections.shuffle(directions);
    for (Direction direction : directions)
        if (doesFit(content,word, coordinate, direction)) {
            return direction;
        }

    return null;
}

private boolean doesFit(char[][] content,String word, Coordinate coordinate, Direction direction) {
int gridSize=content[0].length;
    int length = word.length();
    int x=coordinate.x;
    int y=coordinate.y;
    switch (direction) {
        case HORIZONTAL:
            if (y + length > gridSize) return false;
            for (int i = 0; i <length; i++) {
                if (content[x][y+i] != '-') {
                    return false;
                }
            }
            break;
        case HR_INVERSE:
            if (y - length < length) return false;
            for (int i = 0; i <length; i++) {
                if (content[x][y-i] != '-') {
                    return false;
                }
            }
            break;

        case VERTICAL:
            if (x + length > gridSize) return false;
            for (int i = 0; i < length; i++) {
                if (content[x+i][y]  != '-') {
                    return false;
                }
            }
            break;
        case VR_INVERSE:
            if (x - length <length) return false;
            for (int i = 0; i <length; i++) {
                if (content[x-i][y] != '-') {
                    return false;
                }
            }
            break;
        case DIAGONAL:
            if (x + length > gridSize || y + length > gridSize) return false;
            for (int i = 0; i < length; i++) {
                if (content[x+ i][y + i]  != '-') {
                    return false;
                }
            }
            break;
        case DG_INVERSE:
            if (x - length < length || y - length < length) return false;
            for (int i = 0; i <length; i++) {
                if (content[x-i][y-i] != '-') {
                    return false;
                }
            }
            break;
    }
    return true;
}

private  void randamFillGrid(char[][] content)
{

    String randomLatters="ABCDEFGHIGKLMNOPQRSTUVWXYZ";

    for (int i = 0; i < content.length; i++) {
        for (int j = 0; j < content.length; j++) {
            int random= ThreadLocalRandom.current()
                                .nextInt(0,randomLatters.length());
            if (content[i][j] == '-')
                content[i][j] = randomLatters.charAt(random);
        }
    }
}

public void displayGrid(char[][] content) {
    for (int i = 0; i < content.length; i++) {
        for (int j = 0; j < content.length; j++) {
            System.out.print(content[i][j] + " ");
        }
        System.out.println();
    }
}
}
