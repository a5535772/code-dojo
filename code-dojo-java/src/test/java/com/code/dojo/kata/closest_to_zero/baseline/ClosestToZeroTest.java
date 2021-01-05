package com.code.dojo.kata.closest_to_zero.baseline;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import com.code.dojo.demo.shitcode01.common.ApiException;

@RunWith(MockitoJUnitRunner.class)
public class ClosestToZeroTest {
    static final int ZERO = 0;

    @InjectMocks
    ClosestToZero sut;

    @Test()
    public void test_closestToZero_when_zero_exists_return_zero() throws ApiException {
        /** given */
        int[] sourceNumbers = {-2, -1, 0, 1, 2};
        /** when */
        int result = sut.closestToZero(sourceNumbers);
        /** the */
        assertThat(result).isEqualTo(ZERO);
    }

}
