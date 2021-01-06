package com.code.dojo.demo.leetcode412;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FizzBuzzRFTest {

    @InjectMocks
    FizzBuzzRF sut;

    @Test
    public void 输出集合的长度等于输入的数值() {
        // given
        int number = 18;
        // when
        List<String> result = sut.fizzBuzz(number);
        // then
        assertThat(result.size()).isEqualTo(number);
    }

    @Test(expected = IllegalArgumentException.class)
    public void 当输入为0时_抛出异常() {
        // given
        int zero = 0;
        // when
        sut.fizzBuzz(zero);
    }

    @Test(expected = IllegalArgumentException.class)
    public void 当输入为101时_抛出异常() {
        // given
        int number = 101;
        // when
        sut.fizzBuzz(number);
    }

    @Test
    public void 当输入为1时_返回1() {
        // given
        int one = 1;
        // when
        String result = sut.of(one);
        // then
        assertThat(result).isEqualTo(String.valueOf(one));
    }

    @Test
    public void 当输入为3时_返回Fizz() {
        // given
        int three = 3;
        // when
        String result = sut.of(three);
        // then
        assertThat(result).isEqualTo(FizzBuzzRF.FIZZ);
    }

    @Test
    public void 当输入为5时_返回Buzz() {
        // given
        int five = 5;
        // when
        String result = sut.of(five);
        // then
        assertThat(result).isEqualTo(FizzBuzzRF.BUZZ);
    }

    @Test
    public void 当输入为15时_返回FizzBuzz() {
        // given
        int number = 15;
        // when
        String result = sut.of(number);
        // then
        assertThat(result).isEqualTo(FizzBuzzRF.FIZZ + FizzBuzzRF.BUZZ);
    }


}
