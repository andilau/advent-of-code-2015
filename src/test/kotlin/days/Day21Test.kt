package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 21")
class Day21Test {
    val input = """
        Hit Points: 100
        Damage: 8
        Armor: 2
        """.trimIndent().lines()

    @Test
    fun `In this scenario, the player wins Barely`() {
        // Given: For example, suppose you have 8 hit points, 5 damage, and 5 armor,
        // and that the boss has 12 hit points, 7 damage, and 2 armor:
        val player = Day21.Boss(8, 5, 5)
        val boss = Day21.Boss(12, 7, 2)
        val playerBefore = player.copy()
        // Act
        var won = player.attacks(boss)
        // Assert
        assertThat(won).isFalse
        assertThat(player).isEqualTo(playerBefore)
        assertThat(boss.health).isEqualTo(9)

        // Act
        val bossBefore = boss.copy()
        won = boss.attacks(player)
        // Assert
        assertThat(won).isFalse
        assertThat(boss).isEqualTo(bossBefore)
        assertThat(player.health).isEqualTo(6)

        assertThat( player.attacks(boss)).isFalse
        assertThat(boss.health).isEqualTo(6)

        assertThat(boss.attacks(player)).isFalse
        assertThat(player.health).isEqualTo(4)

        assertThat( player.attacks(boss)).isFalse
        assertThat(boss.health).isEqualTo(3)

        assertThat(boss.attacks(player)).isFalse
        assertThat(player.health).isEqualTo(2)

        assertThat( player.attacks(boss)).isTrue
        assertThat(boss.health).isEqualTo(0)
    }

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `What is the least amount of gold you can spend and still win the fight`() {
            assertThat(Day21(input).partOne()).isEqualTo(91)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `What is the most amount of gold you can spend and still lose the fight`() {
            assertThat(Day21(input).partTwo()).isEqualTo(158)
        }
    }
}