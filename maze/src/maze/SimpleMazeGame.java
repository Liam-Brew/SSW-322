/*
 * SimpleMazeGame.java
 * Copyright (c) 2008, Drexel University.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the Drexel University nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY DREXEL UNIVERSITY ``AS IS'' AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL DREXEL UNIVERSITY BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package maze;

import maze.ui.MazeViewer;
import java.io.*;
import java.util.*;

/**
 * 
 * @author Sunny
 * @version 1.0
 * @since 1.0
 */
public class SimpleMazeGame {
	/**
	 * Generates a maze consisting of 3 rooms.
	 */
	public Maze createMaze() {
		// Data fields
		Maze maze = new Maze();

		// Paramets are (room # - 1) due to indexing
		Room room_1 = new Room(0);
		Room room_2 = new Room(1);
		Room room_3 = new Room(2);

		Door door_1 = new Door(room_1, room_2);
		Door door_2 = new Door(room_2, room_3);

		// Generates room 1 with an opening in the North
		maze.addRoom(room_1);
		room_1.setSide(Direction.North, door_1);
		room_1.setSide(Direction.South, new Wall());
		room_1.setSide(Direction.East, new Wall());
		room_1.setSide(Direction.West, new Wall());

		// Ball starts out here
		maze.setCurrentRoom(room_1);

		// Generates room 2 with an opening to the West
		maze.addRoom(room_2);
		room_2.setSide(Direction.North, new Wall());
		room_2.setSide(Direction.South, door_1);
		room_2.setSide(Direction.East, new Wall());
		room_2.setSide(Direction.West, door_2);

		// Generates room 3
		maze.addRoom(room_3);
		room_3.setSide(Direction.North, new Wall());
		room_3.setSide(Direction.South, new Wall());
		room_3.setSide(Direction.East, door_2);
		room_3.setSide(Direction.West, new Wall());

		return maze;
	}

	/**
	 * Loads maze from an existing file.
	 * 
	 * @throws FileNotFoundException
	 */
	public Maze loadMaze(final String path) throws FileNotFoundException {
		// Data fields
		Maze maze = new Maze();
		Scanner scanner = new Scanner(new File(path));

		// LL to enable sequential chaining
		LinkedList<Room> room_list = new LinkedList<>();
		LinkedList<Door> door_list = new LinkedList<>();
		LinkedList<String[]> roomConfigs = new LinkedList<>();

		// Parses door input
		String line = null;
		while (scanner.hasNextLine()) {
			line = scanner.nextLine();

			// Removing spaces
			String[] args = line.split(" ");

			if (args[0].equals("room")) {
				// Room: added to room list & config updated
				// args[0] = label, args[1] = number, args[2] = from_door, args[2] = to_door
				Room temp_room = new Room(Integer.parseInt(args[1]));
				roomConfigs.add(args);

				// Adds room to list of rooms and appends to maze
				room_list.add(temp_room);
				maze.addRoom(temp_room);
			} else if (args[0].equals("door")) {
				// Door: connection required

				// args[0] = label, args[1] = number, args[2] = from_door, args[2] = to_door
				int from_door = Integer.parseInt(args[2]);
				int to_door = Integer.parseInt(args[3]);

				Door temp_door = new Door(room_list.get(from_door), room_list.get(to_door));

				// Is the door open?
				temp_door.setOpen(args[4].equals("open"));

				// Adds door to list of doors
				door_list.add(temp_door);
			}
		}
		scanner.close();

		for (int temp_room = 0; temp_room < room_list.size(); temp_room++) {
			// Initial starting position
			if (temp_room == 0) {
				maze.setCurrentRoom(temp_room);
			}

			// Getting current room and its configuration
			Room current_room = room_list.get(temp_room);
			String[] room_configuration = roomConfigs.get(temp_room);

			for (int configuration_index = 2; configuration_index < 6; configuration_index++) {
				MapSite site;

				// Site determination
				if (room_configuration[configuration_index].equals("wall")) {
					site = new Wall();
				} else if (room_configuration[configuration_index].startsWith("d")) {
					site = door_list.get(Integer.parseInt(room_configuration[configuration_index].substring(1)));
				} else {
					site = room_list.get(Integer.parseInt(room_configuration[configuration_index]));
				}

				Direction[] direction_array = { Direction.North, Direction.South, Direction.East, Direction.West };
				
				// Building room
				current_room.setSide(direction_array[configuration_index - 2], site);
			}
		}
		return maze;
	}

	public static void main(String[] args) throws FileNotFoundException {

		// Improper arguments
		if (args.length > 1) {
			throw new IllegalArgumentException("Error: too many arguments!");
		}

		Maze maze; 
		
		// Type of maze determination
		SimpleMazeGame simpleMaze = new SimpleMazeGame();
		if (args.length == 1) {
			maze = simpleMaze.loadMaze(args[0]);
		} else {
			maze = simpleMaze.createMaze();
		}
		MazeViewer viewer = new MazeViewer(maze);
		viewer.run();
	}
}
