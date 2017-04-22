package ru.codemonkeystudio.objects;

import java.util.ArrayList;

/**
 * Created by maximus on 22.04.17.
 */
public class Board {
	public static final int SIZE = 8;

	private byte grid[][];

	public Board () {
		grid = new byte[SIZE][SIZE];
	}

	public void newGrid() {
		ArrayList cells = new ArrayList();
		for (int i = 0; i < (int)(SIZE * SIZE * 2.625 / 12); i++) {
			for (int j = 0; j < 12; j++) {
				cells.add(j);
			}
		}

		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				int r = (int) (Math.random() * 12);
				grid[x][y] = Byte.parseByte(cells.get(r).toString());
				cells.remove(r);
			}
		}
	}

	public byte[][] getGrid() {
		return grid;
	}
}
