package ru.codemonkeystudio.objects;

import java.util.ArrayList;

/**
 * Created by maximus on 22.04.17.
 */
public class Board {
	private byte grid[][];

	public Board () {
		grid = new byte[64][64];

	}

	public void newGrid() {
		ArrayList cells = new ArrayList();
		for (int i = 0; i < 896; i++) {
			for (int j = 0; j < 12; j++) {
				cells.add(j);
			}
		}

		for (int y = 0; y < 64; y++) {
			for (int x = 0; x < 64; x++) {
				int r = (int) (Math.random() * 12);
				grid[x][y] = Byte.parseByte(cells.get(r).toString());
				cells.remove(r);
			}
		}
	}
}
