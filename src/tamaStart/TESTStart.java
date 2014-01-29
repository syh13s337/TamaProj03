package tamaStart;

import tamaSystem.MySQLEngine;

public class TESTStart {

	public static void main(String[] args) {
		MySQLEngine mSQL = new MySQLEngine();
		mSQL.getMySQLDB("tester", "test");
	}
}
