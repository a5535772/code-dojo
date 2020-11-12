package com.code.dojo.kata.gilded_rose.baseline;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

class GildedRoseTest {

	@Test
	void foo() {
		Item[] items = new Item[] { new Item("foo", 0, 0) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertThat("fixme").isEqualTo(app.items[0].name);
	}

}