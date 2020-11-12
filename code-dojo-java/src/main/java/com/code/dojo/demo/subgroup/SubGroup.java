package com.code.dojo.demo.subgroup;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class SubGroup {
	static final String TAB = "	";
	final String ABSEND = "X";
	static final List<String> HOSTS = Arrays.asList("章立", "张馨予");

	public List<String> devide(String filePath) throws FileNotFoundException {
		List<String> playerList = readFile(filePath);
		Collections.shuffle(playerList);
		return playerList;
	}

	private List<String> readFile(String filePath) throws FileNotFoundException {
		List<String> players = new ArrayList<>();
		Scanner scanner = new Scanner(new File(filePath));
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] playerDatas = line.split(TAB);

			if (playerDatas.length != 2)
				continue;
			String playerName = playerDatas[0];
			String attendStatus = playerDatas[1];
			if (HOSTS.contains(playerName) || "".equals(playerName) || ABSEND.equals(attendStatus)) {
				continue;
			}
			players.add(playerName);
		}
		scanner.close();
		return players;
	}

}
